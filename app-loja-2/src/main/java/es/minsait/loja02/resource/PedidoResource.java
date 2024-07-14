package es.minsait.loja02.resource;

import java.time.LocalDate;
import java.util.Optional;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.minsait.loja02.entity.Cliente;
import es.minsait.loja02.entity.Pedido;
import es.minsait.loja02.entity.Pessoa;
import es.minsait.loja02.enums.StatusPedidoEnum;
import es.minsait.loja02.json.LocalDateJsonAdapter;
import es.minsait.loja02.json.PedidoStatusResponse;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path( "pedido" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PedidoResource{
    
    private static final Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter( LocalDate.class, new LocalDateJsonAdapter() )
        .create();
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger( PedidoResource.class );

    
    @POST
    @Transactional
    public Response createPedido(Pedido pedido){
        try{
            LOG.info( String.format( "Pedido recebido:\n%s", GSON.toJson( pedido ) ) );
            if( pedido.getCliente() != null ){
                Optional<Cliente> cliente = Cliente.findByEmail( pedido.getCliente().getEmail() );
                if( cliente.isPresent() )
                    pedido.setCliente( cliente.get() );
                else{
                    pedido.getCliente().id = null;
                    pedido.getCliente().getPessoa().id = null;
                    Pessoa.persist( pedido.getCliente().getPessoa() );
                    Cliente.persist( pedido.getCliente() );
                }
            }
            pedido.setStatus( StatusPedidoEnum.RECEBIDO );
            pedido.persist();
            this.launchQueue( pedido.getUuid() );
            return Response.ok( pedido.copySemIds() ).build();
        }catch( Exception e ){
            LOG.error( e.getLocalizedMessage(), e );
            return Response.serverError().entity( "Erro ao criar pedido" ).build();
        }
    }

    @PUT
    @Path( "/{idPedido}/{novoStatus}" )
    @Transactional
    public Response mudarStatus(@PathParam( "idPedido" )long idPedido, @PathParam( "novoStatus" ) StatusPedidoEnum novoStatus){
        final Optional<Pedido> pedido = Pedido.findByIdOptional( idPedido );
        if( pedido.isEmpty() )
            return Response.serverError()
                .entity( String.format( "Pedido %d NAO ENCONTRADO", 
                    idPedido ) ).build();
        try{
            pedido.get().setStatus( novoStatus );
            pedido.get().persist();
            this.launchQueue( pedido.get().getUuid() );
            return Response.ok().build();
        }catch( Exception e ){
            return Response.serverError()
                .entity( String.format( "Erro ao atualizar o status do pedido %d", 
                    idPedido ) ).build();
        }
    }


    private void launchQueue(final String uuid){
        final Thread t = new Thread( ()->{
            try{
                Thread.sleep( 1_000 );
            }catch(InterruptedException e){
            }
            this.lancharStatusPedidoAppFood( uuid );
        } );
        t.setDaemon( false );
        t.start();
    }

    @Transactional
    @Incoming("pedido-status-loja-request")
    @Outgoing("pedido-status-loja-response")
    public String lancharStatusPedidoAppFood(final String uuid){
        final PedidoStatusResponse response = new PedidoStatusResponse();
        response.setUuidPedido( uuid );
        try{
            final Optional<Pedido> pedido = Pedido.findByUUID( uuid );
            if( pedido.isPresent() ){
                response.setStatus( pedido.get().getStatus() );
                LOG.info( String.format( "Status do pedido '%s': %s", uuid, pedido.get().getStatus() ) );
            }else{
                response.setMensagemErro( String.format( "Pedido '%s' NÃO encontrado!", uuid ) );
                LOG.warn( String.format( "Pedido NAO ACHADO '%s'", uuid ) );
            }
        }catch( Exception e ){
            LOG.error( String.format( "ERRO em buscar status do pedido '%s'", uuid ), e );
            response.setMensagemErro( String.format( "Erro ocorrido na busca pelo pedido '%s': %s", 
                uuid, e.getLocalizedMessage() ) );
        }
        //final PedidoStatusLojaResponseQueue queue = new PedidoStatusLojaResponseQueue( response );
        //queue.handlerStatusPedido();
        final String json = GSON.toJson( response );
        LOG.info( String.format( "Enviando dados para o 'app-food' %s", json ) );
        return json;
    }

}
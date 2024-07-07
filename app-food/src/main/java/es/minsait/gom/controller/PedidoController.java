package es.minsait.gom.controller;

import static es.minsait.gom.util.Constantes.HTTP_STATUS_OK;

import java.time.LocalDate;
import java.util.Optional;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.minsait.gom.json.LocalDateJsonAdapter;
import es.minsait.gom.json.PedidoResponse;
import es.minsait.gom.model.Cliente;
import es.minsait.gom.model.Loja;
import es.minsait.gom.model.Pedido;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path( "/pedido" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PedidoController{
    
    private static final Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter( LocalDate.class, new LocalDateJsonAdapter() )
        .create();
    private static final Logger LOG = LoggerFactory.getLogger( PedidoController.class );

    @GET
    @Path( "/{idLoja}" )
    public Response listAllByLoja(final @PathParam( "idLoja" ) long idLoja){
        return Response.status( Response.Status.OK )
            .entity( Pedido.findByLojaId( idLoja ) ).build();
    }

    @GET
    @Path( "/{id}" )
    public Response findById(final @PathParam( "id" ) Long id){
        final Optional<Pedido> pedido = Pedido.findByIdOptional( id );
        if( pedido.isPresent() )
            return Response.status( Response.Status.OK ).entity( pedido.get() ).build();
        return Response.status( Response.Status.NOT_FOUND ).build();
    }

    @POST
    public Response create(final Pedido pedido){
        try{
            final String msgValidacao = this.validarDadosPedido( pedido );
            if( !msgValidacao.isEmpty() ){
                LOG.error( msgValidacao );
                return Response.status( Response.Status.BAD_REQUEST ).entity( msgValidacao )
                    .build();
            }
            final Client client = ClientBuilder.newClient();
            final String jsonPedido = GSON.toJson( pedido );
            LOG.info( String.format( " >>>>>>>>>>> Pedido a ser realizado: \n%s", jsonPedido ) );

            //enviar o pedido para a url da loja
            final Response pedidoResp = client.target( pedido.getLoja().getUrlApi() + "pedido" )
                .request( MediaType.APPLICATION_JSON )
                .post( Entity.entity( jsonPedido, MediaType.APPLICATION_JSON ) );

            //testar se a requisoao retorno OK (200 201)
            if( !HTTP_STATUS_OK.contains( pedidoResp.getStatus() ) ){
                LOG.error( String.format( "Erro ao criar pedido na loja: '%s', URL: '%s', StatusCode: %d", 
                    pedido.getLoja().getNome(), pedido.getLoja().getUrlApi(), pedidoResp.getStatus() ) );
                return Response.status( Response.Status.BAD_REQUEST )
                    .entity( "Erro ao criar pedido na loja" ).build();
            }else{
                LOG.error( " >>> Passou!" );
                return pedidoResp;
            }
        }catch( Exception e ){
            LOG.error( "Erro ao criar pedido", e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }
    }

    private String validarDadosPedido(final Pedido pedido){
        final StringBuilder erros = new StringBuilder();
        if( pedido == null )
            erros.append( "Pedido não informado!" );
        else{
            if( pedido.getItensPedido().isEmpty() ){
                erros.append( "Pedido está vázio!" );
            }else
                this.validarDadosItemPedido( pedido, erros );
            this.validarDadosCliente( pedido, erros );
            this.validarDadosLoja( pedido, erros );
        }
        return erros.toString();
    }

    private void validarDadosItemPedido(final Pedido pedido, final StringBuilder erros){
        long count = pedido.getItensPedido().stream()
            .filter( item -> ( item.getNome() == null || item.getNome().strip().isEmpty() ) )
            .count();
        if( count > 0 ){
            if( erros.length() > 0 )
                erros.append( System.lineSeparator() );
            erros.append( String.format( "Existe(m) %d item(ns) sem o nome informado", count ) );
        }
        count = pedido.getItensPedido().stream()
            .filter( item -> ( item.getQuantidade() < 1 ) )
            .count();
        if( count > 0 ){
            if( erros.length() > 0 )
                erros.append( System.lineSeparator() );
            erros.append( String.format( "Existe(m) %d item(ns) sem a quantidade informada", count ) );
        }
        count = pedido.getItensPedido().stream()
            .filter( item -> ( item.getPreco() == null || item.getPreco().doubleValue() <= 0.0 ) )
            .count();
        if( count > 0 ){
            if( erros.length() > 0 )
                erros.append( System.lineSeparator() );
            erros.append( String.format( "Existe(m) %d item(ns) sem o preço informado", count ) );
        }
    }

    private void validarDadosCliente(final Pedido pedido, final StringBuilder erros){
        if( pedido.getCliente() == null && pedido.getCliente().id == null ){
            if( erros.length() > 0 )
                erros.append( System.lineSeparator() );
            erros.append( "Cliente não informado!" );
        }else{
            final Optional<Cliente> c = Cliente.findByIdOptional( pedido.getCliente().id );
            if( c.isPresent() ) pedido.setCliente( c.get() );
            else{
                if( erros.length() > 0 )
                    erros.append( System.lineSeparator() );
                erros.append( "Cliente não existe." );
            }
        }
    }

    private void validarDadosLoja(final Pedido pedido, final StringBuilder erros){
        if( pedido.getLoja() == null && pedido.getLoja().id == null ){
            if( erros.length() > 0 )
                erros.append( System.lineSeparator() );
            erros.append( "Loja não informada!" );
        }else{
            final Optional<Loja> l = Loja.findByIdOptional( pedido.getLoja().id );
            if( l.isPresent() ){
                if( l.get().getUrlApi() == null || l.get().getUrlApi().strip().isEmpty() ){
                    if( erros.length() > 0 )
                        erros.append( System.lineSeparator() );
                    erros.append( String.format( "Cadastro da loja '%s' incompleto.", 
                        l.get().getNome() ) );
                }else
                    pedido.setLoja( l.get() );
            }else{
                if( erros.length() > 0 )
                    erros.append( System.lineSeparator() );
                erros.append( "Loja não existe." );
            }
        }
    }

    @Incoming( "pedido-responses" )
    public void handlerPedido(final byte[] message){
        String json = new String( message );
        try{
            final ObjectMapper mapper = new ObjectMapper();
            PedidoResponse pedidoResponse = mapper.readValue( json, PedidoResponse.class );
            if( pedidoResponse.isConfirmed() ){
                LOG.info( "PEDIDO CONFIRMADO 😁" );
            }else{
                LOG.info( "PEDIDO NAO CONFIRMADO 😔" );
            }
        }catch( Exception e ){
            LOG.error( "Erro ao processar fila de pedido", e );
        }
    }

}
package es.minsait.loja02.resource;

import java.time.LocalDate;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.minsait.loja02.entity.Pedido;
import es.minsait.loja02.json.LocalDateJsonAdapter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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
    public Response createPedido(Pedido pedido){
        try{
            LOG.info( String.format( "Pedido recebido:\n%s", GSON.toJson( pedido ) ) );
            return Response.ok( pedido ).build();
        }catch( Exception e ){
            return Response.serverError().entity( "Erro ao criar pedido" ).build();
        }
    }

}
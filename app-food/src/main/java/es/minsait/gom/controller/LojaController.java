package es.minsait.gom.controller;

import static es.minsait.gom.util.Constantes.HTTP_STATUS_OK;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.minsait.gom.dto.ItemCardapioDTO;
import es.minsait.gom.model.Loja;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path( "/loja" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LojaController{
    
    private static final Logger LOG = LoggerFactory.getLogger( LojaController.class );


    @GET
    public Response listAll(){
        return Response.status( Response.Status.OK )
            .entity( Loja.listAll() ).build();
    }

    @POST
    @Transactional
    public Response create(final Loja loja){
        loja.persist();
        return Response.status( Response.Status.CREATED )
            .entity( loja ).build();
    }

    @GET
    @Path( "/{id}" )
    public Response findById(final @PathParam( "id" ) Long id){
        final Optional<Loja> loja = Loja.findByIdOptional( id );
        if( loja.isPresent() )
            return Response.status( Response.Status.OK ).entity( loja.get() ).build();
        return Response.status( Response.Status.NOT_FOUND ).build();
    }

    @GET
    @Path( "/{id}/cardapio" )
    public Response getCardapio(final @PathParam( "id" ) Long id){
        try{
            final Optional<Loja> loja = Loja.findByIdOptional( id );
            if( loja.isEmpty() ){
                LOG.error( String.format( "Loja não encontrada para ID %d", id ) );
                return Response.status( Response.Status.NOT_FOUND ).build();
            }
            final Client client = ClientBuilder.newClient();
            final Response cardapioResp = client.target( loja.get().getUrlApi() + "cardapio" )
                .request( MediaType.APPLICATION_JSON ).get();
            
            if( !HTTP_STATUS_OK.contains( cardapioResp.getStatus() ) ){
                LOG.error( String.format( "Erro ao buscar cardápio da loja: '%s', URL: '%s'", 
                    loja.get().getNome(), loja.get().getUrlApi() ) );
                return Response.status( Response.Status.INTERNAL_SERVER_ERROR )
                    .entity( "Erro ao buscar cardápio da loja." )
                    .build();
            }else
                LOG.info( String.format( "Cardapio da URL: '%s'.", cardapioResp ) );

            final List<ItemCardapioDTO> cardapio = this.convertRequestToList( cardapioResp );
            if( cardapio == null || cardapio.isEmpty() ){
                LOG.error( String.format( "Cardápio não encontrado para a loja: '%s', URL: '%s'", 
                    loja.get().getNome(), loja.get().getUrlApi() ) );
                return Response.status( Response.Status.NOT_FOUND ).build();
            }
            return Response.ok( Response.Status.OK ).entity( cardapio ).build();
        }catch( Exception e ){
            LOG.error( "Erro ao obter cardápio", e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }
    }

    private List<ItemCardapioDTO> convertRequestToList(Response response){
        return Arrays.stream( response.readEntity( ItemCardapioDTO[].class ) )
            .toList();
    }

}
package es.minsait.loja01;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

import es.minsait.loja01.dto.ItemCardapioDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path( "cardapio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class CardapioResource{
    
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger( CardapioResource.class );

    @GET
    public Response getCardapio(){
        final List<ItemCardapioDTO> cardapio = Arrays.asList(
            new ItemCardapioDTO( "Coxinha", "Coxinha de frango com queijo", 10.0 ),
            new ItemCardapioDTO( "Pastel", "Pastel de 4 queijos", 15.0 ),
            new ItemCardapioDTO( "Refrigerante", "Latinha Refigerante de Cola", 2.0 )
        );
        LOG.info( String.format( "Cardápio solicitado: \n%s", cardapio ) );
        return Response.ok( cardapio ).build();
    }

}
package es.minsait.loja02.resource;

import java.util.List;

import org.slf4j.Logger;

import es.minsait.loja02.entity.ItemMenu;
import es.minsait.loja02.vo.ItemMenuVO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path( "cardapio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class MenuResource{
    
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger( MenuResource.class );

    @GET
    public Response listAll(){
        final List<ItemMenuVO> menu = ItemMenu.listAll().stream()
            .map( entity ->{
                final ItemMenu item = (ItemMenu)entity;
                return new ItemMenuVO( item.id, item.getNome(), item.getDescricao(), item.getPreco() );
            } ).toList();
        LOG.info( String.format( "Menu solicitado: \n%s", menu ) );
        return Response.ok( menu ).build();
    }

}
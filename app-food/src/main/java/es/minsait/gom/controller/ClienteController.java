package es.minsait.gom.controller;

import java.util.Optional;

import es.minsait.gom.model.Cliente;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path( "/cliente" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ClienteController{
    
    @GET
    public Response listAll(){
        return Response.status( Response.Status.OK )
            .entity( Cliente.listAll() ).build();
    }

    @POST
    @Transactional
    public Response create(final Cliente cliente){
        cliente.persist();
        return Response.status( Response.Status.CREATED )
            .entity( cliente ).build();
    }

    @GET
    @Path( "/{id}" )
    public Response findById(final @PathParam( "id" ) Long id){
        final Optional<Cliente> cliente = Cliente.findByIdOptional( id );
        if( cliente.isPresent() )
            return Response.status( Response.Status.OK ).entity( cliente.get() ).build();
        return Response.status( Response.Status.NOT_FOUND ).build();
    }

}
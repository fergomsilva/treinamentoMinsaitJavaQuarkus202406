package es.minsait.gom.controller;

import java.util.Optional;

import es.minsait.gom.model.Pessoa;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path( "/pessoa" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PessoaController{
    
    @GET
    public Response listAll(){
        return Response.status( Response.Status.OK )
            .entity( Pessoa.listAll() ).build();
    }

    @POST
    @Transactional
    public Response create(final Pessoa pessoa){
        pessoa.persist();
        return Response.status( Response.Status.CREATED )
            .entity( pessoa ).build();
    }

    @GET
    @Path( "/{id}" )
    public Response findById(final @PathParam( "id" ) Long id){
        final Optional<Pessoa> pessoa = Pessoa.findByIdOptional( id );
        if( pessoa.isPresent() )
            return Response.status( Response.Status.OK ).entity( pessoa.get() ).build();
        return Response.status( Response.Status.NOT_FOUND ).build();
    }

}
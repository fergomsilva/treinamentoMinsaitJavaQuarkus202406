package es.minsait.gom.controller;

import java.util.List;

import es.minsait.gom.model.Pessoa;
import es.minsait.gom.repository.PessoaRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path( "/pessoa" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PessoaController{
    
    @Inject
    private PessoaRepository repository;

    @GET
    public List<Pessoa> listAll(){
        return this.repository.listAll();
    }

    @POST
    @Transactional
    public Pessoa create(final Pessoa pessoa){
        this.repository.persist( pessoa );
        return pessoa;
    }

    @GET
    @Path( "/{id}" )
    public Pessoa findById(final @PathParam( "id" ) Long id){
        return this.repository.findById( id );
    }

}
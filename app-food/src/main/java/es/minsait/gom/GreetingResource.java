package es.minsait.gom;

import es.minsait.gom.model.Pessoa;
import es.minsait.gom.model.TipoDocumentoEnum;
import es.minsait.gom.service.GreetingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource{

    @Inject
    private GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        final Pessoa p = new Pessoa( null, "Nome pessoa", "Endereco pessoa", 
            "Cidade pessoa", "UF pessoa", TipoDocumentoEnum.CPF, "Documento pessoa" );
        //return String.format( "Hello RESTEasy \n%s", p );
        //return this.service.greet();
        return this.service.greetPessoa( p );
    }

}
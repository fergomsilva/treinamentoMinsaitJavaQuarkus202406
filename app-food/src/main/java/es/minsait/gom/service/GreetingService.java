package es.minsait.gom.service;

import es.minsait.gom.model.Pessoa;
import es.minsait.gom.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class GreetingService{
    
    @Inject
    private PessoaRepository repository;

    public String greet(){
        return "Hello from GreetingService";
    }

    @Transactional
    public String greetPessoa(final Pessoa p){
        try{
            this.repository.persist( p );
        }catch( Exception e ){
            return String.format( "Erro ao cadastrar pessoa: %s", e.getLocalizedMessage() );
        }
        return String.format( "Cadastro de pessoa: \n%s", p.toString() );
    }

}
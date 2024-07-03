package es.minsait.gom.repository;

import es.minsait.gom.model.Pessoa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa>{

}
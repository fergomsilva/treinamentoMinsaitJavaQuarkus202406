package es.minsait.loja02.entity;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;


@Entity
public class Cliente extends PanacheEntity{
    
    private String email;

    @OneToOne
    private Pessoa pessoa;

    public Cliente(){
        super();
    }
    public Cliente(final String email, final Pessoa pessoa){
        this();
        this.setEmail( email );
        this.setPessoa( pessoa );
    }

    public Cliente copySemIds(){
        return new Cliente( this.getEmail(), this.getPessoa().copySemIds() );
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }



    public static Optional<Cliente> findByEmail(final String email){
        return Cliente.find( "from Cliente c where c.email=?1", email )
            .page( 0, 1 )
            .singleResultOptional();
    }

}
package es.minsait.loja02.entity;

import es.minsait.loja02.enums.TipoDocumentoEnum;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
public class Pessoa extends PanacheEntity{

    @Column( name="nome" )
    private String nome;

    @Column( name="endereco" )
    private String endereco;

    @Column( name="cidade" )
    private String cidade;

    @Column( name="uf" )
    private String uf;

    @Column( name="tipoDocumento" )
    private TipoDocumentoEnum tipoDocumento;

    @Column( name="documento" )
    private String documento;

    public Pessoa(){
        super();
    }

    public Pessoa(final Long id, final String nome, final String endereco, 
    final String cidade, final String uf, final TipoDocumentoEnum tipoDocumento, 
    final String documento){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
    }

    public Pessoa copySemIds(){
        return new Pessoa( null, this.getNome(), this.getEndereco(), this.getCidade(), 
            this.getUf(), this.getTipoDocumento(), this.getDocumento() );
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public TipoDocumentoEnum getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
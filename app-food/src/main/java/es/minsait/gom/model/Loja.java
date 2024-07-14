package es.minsait.gom.model;

import es.minsait.gom.enums.TIPO_ACESSO_LOJA_ENUM;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Loja extends PanacheEntity{

    private String nome;
    private String endereco;
    private TIPO_ACESSO_LOJA_ENUM tipoAcesso;
    private String urlApi;
    

    public Loja(){
        super();
    }
    public Loja(String nome, String endereco, TIPO_ACESSO_LOJA_ENUM tipoAcesso, String urlApi) {
        this();
        this.nome = nome;
        this.urlApi = urlApi;
        this.endereco = endereco;
        this.tipoAcesso = tipoAcesso;
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
    public String getUrlApi() {
        return urlApi;
    }
    public void setUrlApi(String urlApi) {
        this.urlApi = urlApi;
    }
    public TIPO_ACESSO_LOJA_ENUM getTipoAcesso() {
        if( tipoAcesso == null )
            tipoAcesso = TIPO_ACESSO_LOJA_ENUM.REST;
        return tipoAcesso;
    }
    public void setTipoAcesso(TIPO_ACESSO_LOJA_ENUM tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

}
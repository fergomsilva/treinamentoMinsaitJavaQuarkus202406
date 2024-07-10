package es.minsait.loja01.model;

import es.minsait.loja01.enums.TipoDocumentoEnum;

public class Pessoa{

    private String nome;
    private String endereco;
    private String cidade;
    private String uf;
    private TipoDocumentoEnum tipoDocumento;
    private String documento;

    public Pessoa(){
        super();
    }

    public Pessoa(final String nome, final String endereco, 
    final String cidade, final String uf, final TipoDocumentoEnum tipoDocumento, 
    final String documento){
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
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
    
}
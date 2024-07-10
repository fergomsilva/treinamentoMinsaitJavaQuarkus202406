package es.minsait.loja02.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ItemMenu extends PanacheEntity{

    private String nome;
    private String descricao;
    private Double preco;
    
    
    public ItemMenu(){
        super();
    }
    public ItemMenu(String nome, String descricao, Double preco) {
        this();
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
package es.minsait.loja02.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido extends PanacheEntity{
    
    private String nome;
    private Integer quantidade;
    private Double preco;
    @ManyToOne
    private Pedido pedido;
    
    public ItemPedido() {
        super();
    }

    public ItemPedido(String nome, Integer quantidade, Double preco, Pedido pedido) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.pedido = pedido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
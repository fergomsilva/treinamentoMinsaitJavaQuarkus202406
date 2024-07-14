package es.minsait.loja02.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido extends PanacheEntity{
    
    private Long idItemLoja;
    private String nome;
    private Integer quantidade;
    private Double preco;
    @ManyToOne
    private Pedido pedido;
    
    public ItemPedido() {
        super();
    }

    public ItemPedido(Long idItemLoja, String nome, Integer quantidade, Double preco, Pedido pedido) {
        this.idItemLoja = idItemLoja;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.pedido = pedido;
    }

    public ItemPedido copySemIds(){
        return new ItemPedido( this.getIdItemLoja(), this.getNome(), 
            this.getQuantidade(), this.getPreco(), null );
    }

    public Long getIdItemLoja() {
        return idItemLoja;
    }

    public void setIdItemLoja(Long idItemLoja) {
        this.idItemLoja = idItemLoja;
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
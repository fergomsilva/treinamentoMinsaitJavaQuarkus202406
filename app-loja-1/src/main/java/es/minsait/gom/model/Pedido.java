package es.minsait.gom.model;

import java.time.LocalDate;
import java.util.List;


public class Pedido{
    
    private Cliente cliente;
    private Loja loja;
    private LocalDate dataPedido;
    private String descricao;
    private List<ItemPedido> itensPedido;
    
    public Pedido(){
        super();
    }

    public Pedido(Cliente cliente, Loja loja, LocalDate dataPedido, String descricao, List<ItemPedido> itensPedido) {
        this();
        this.cliente = cliente;
        this.loja = loja;
        this.dataPedido = dataPedido;
        this.descricao = descricao;
        this.itensPedido = itensPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

}
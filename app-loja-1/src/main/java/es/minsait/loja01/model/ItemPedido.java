package es.minsait.loja01.model;


public class ItemPedido{
    
    private String nome;
    private int quantidade;
    private Double preco;
    private Pedido pedido;
    
    public ItemPedido() {
        super();
    }

    public ItemPedido(String nome, int quantidade, Double preco, Pedido pedido) {
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
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
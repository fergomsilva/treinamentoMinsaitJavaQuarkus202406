package es.minsait.gom.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Pedido extends PanacheEntity{
    
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Loja loja;
    private LocalDate dataPedido;
    private String descricao;
    @OneToMany( mappedBy="pedido", cascade=CascadeType.ALL, orphanRemoval=true )
    private List<ItemPedido> itensPedido;
    
    public Pedido(){
        super();
    }

    public static List<Pedido> findByLojaId(final long idLoja){
        return Pedido.list( "from Pedido p where p.loja.id=?1", idLoja );
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
        if( itensPedido == null )
            itensPedido = new ArrayList<>();
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

}
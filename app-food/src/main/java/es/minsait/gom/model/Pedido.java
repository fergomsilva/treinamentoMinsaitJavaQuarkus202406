package es.minsait.gom.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.minsait.gom.enums.StatusPedidoEnum;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Pedido extends PanacheEntity{
    
    private String uuid;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Loja loja;
    private LocalDate dataPedido;
    private String descricao;
    @OneToMany( mappedBy="pedido", cascade=CascadeType.ALL, orphanRemoval=true )
    private List<ItemPedido> itensPedido;
    private StatusPedidoEnum status;
    

    public static List<Pedido> findByLojaId(final long idLoja){
        return Pedido.list( "from Pedido p where p.loja.id=?1", idLoja );
    }

    public static Optional<Pedido> findByUUID(final String uuid){
        return Pedido.find( "from Pedido p where p.uuid=?1", uuid )
            .page( 0, 1 )
            .singleResultOptional();
    }

    public static void updateStatus(final String uuid, final StatusPedidoEnum novoStatus){
        final Optional<Pedido> pedido = Pedido.findByUUID( uuid );
        if( pedido.isPresent() ){
            pedido.get().setStatus( novoStatus );
            Pedido.persist( pedido.get() );
        }
    }

    public Pedido(){
        super();
    }

    public Pedido(String uuid, Cliente cliente, Loja loja, LocalDate dataPedido, String descricao, List<ItemPedido> itensPedido, StatusPedidoEnum status) {
        this();
        this.uuid = uuid;
        this.cliente = cliente;
        this.loja = loja;
        this.dataPedido = dataPedido;
        this.descricao = descricao;
        this.itensPedido = itensPedido;
        this.status = status;
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

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }
    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }

}
package es.minsait.loja02.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import es.minsait.loja02.enums.StatusPedidoEnum;
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
    private LocalDate dataPedido;
    private String descricao;
    @OneToMany( mappedBy="pedido", cascade=CascadeType.ALL, orphanRemoval=true )
    private List<ItemPedido> itensPedido;
    private StatusPedidoEnum status;

    public Pedido(){
        super();
    }

    public Pedido(String uuid, Cliente cliente, LocalDate dataPedido, String descricao, List<ItemPedido> itensPedido,
            StatusPedidoEnum status) {
        this();
        this.uuid = uuid;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.descricao = descricao;
        this.itensPedido = itensPedido;
        this.status = status;
    }

    public Pedido copySemIds(){
        return new Pedido( this.getUuid(), this.getCliente().copySemIds(), 
            this.getDataPedido(), this.getDescricao(), 
            this.getItensPedido().stream()
                .map( ItemPedido::copySemIds ).toList(), 
            this.getStatus() );
    }

    public static Optional<Pedido> findByUUID(final String uuid){
        return Pedido.find( "from Pedido p where p.uuid=?1", uuid )
            .page( 0, 1 )
            .singleResultOptional();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }
    
}
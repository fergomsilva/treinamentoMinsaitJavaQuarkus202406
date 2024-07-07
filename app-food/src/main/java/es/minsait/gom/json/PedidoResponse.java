package es.minsait.gom.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.minsait.gom.model.Pedido;


@JsonDeserialize
public class PedidoResponse{

    private Boolean confirmed;
    private Pedido pedido;

    public PedidoResponse(){
        super();
    }
    public PedidoResponse(Boolean confirmed, Pedido pedido) {
        this();
        this.confirmed = confirmed;
        this.pedido = pedido;
    }

    public Boolean isConfirmed() {
        if( confirmed == null ){
            confirmed = Boolean.FALSE;
        }
        return confirmed;
    }
    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
}
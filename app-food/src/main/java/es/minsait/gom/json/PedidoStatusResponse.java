package es.minsait.gom.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.minsait.gom.enums.StatusPedidoEnum;


@JsonDeserialize
public class PedidoStatusResponse{

    private String uuidPedido;
    private StatusPedidoEnum status;
    private String mensagemErro;

    public PedidoStatusResponse(){
        super();
    }
    public PedidoStatusResponse(String uuidPedido, StatusPedidoEnum status, String mensagemErro) {
        this();
        this.status = status;
        this.uuidPedido = uuidPedido;
        this.mensagemErro = mensagemErro;
    }

    public String getUuidPedido() {
        return uuidPedido;
    }
    public void setUuidPedido(String uuidPedido) {
        this.uuidPedido = uuidPedido;
    }
    public StatusPedidoEnum getStatus() {
        return status;
    }
    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }
    public String getMensagemErro() {
        return mensagemErro;
    }
    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
    
}
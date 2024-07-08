package es.minsait.gom.model;

public enum StatusPedidoEnum{
    ENVIANDO( "Enviando Dados do Pedido para a Loja" ), 
    ERRO_ENVIO( "Erro no Envio dos Dados para a Loja" ), 
    ENVIADO( "Enviado" ), 
    AGUARDANDO_CONFIRMACAO( "Aguardando Confirmação" ), 
    CONFIRMADO( "Confirmado" ), 
    EM_PREPARACAO( "Em Preparação" ),
    PRONTO_PARA_ENTREGA( "Pronto para Entrega" ), 
    ESPERANDO_ENTREGADOR( "Esperando Entregador" ), 
    SAIU_ENTREGA( "Saiu para Entrega" ), 
    ENTREGUE( "Entregue" ), 
    CANCELADO_PELA_LOJA( "Cancelado pela Loja" ), 
    CANCELADO_PELO_CLIENTE( "Cancelado pelo Cliente" ),
    CANCELADO_PELO_APP( "Cancelado pelo APP" ) 
    ;

    private String descricao;
    private StatusPedidoEnum(final String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }

}
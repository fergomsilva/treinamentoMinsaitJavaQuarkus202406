package es.minsait.gom.queue;

import java.time.LocalDate;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.minsait.gom.json.LocalDateJsonAdapter;
import es.minsait.gom.model.Pedido;


public class EnviarPedidoQueue{

    private static final Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter( LocalDate.class, new LocalDateJsonAdapter() )
        .create();
    
    private Pedido pedido;


    private EnviarPedidoQueue(){
        super();
        this.pedido = null;
    }
    public EnviarPedidoQueue(final Pedido pedido){
        this();
        this.pedido = pedido;
    }

    @Outgoing( "pedido-request" )
    public byte[] send(){
        if( this.pedido != null ){
            try{
                final String jsonPedido = GSON.toJson( this.pedido );
                System.out.printf( "'pedido-request' PEDIDO :: %s", jsonPedido ).println();
                return jsonPedido.getBytes();
            }finally{
                this.pedido = null;
            }
        }
        return new byte[0];
    }

}
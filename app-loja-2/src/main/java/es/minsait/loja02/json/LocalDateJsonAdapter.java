package es.minsait.loja02.json;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class LocalDateJsonAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate>{

    public JsonElement serialize(final LocalDate date, final Type typeOfSrc, final JsonSerializationContext context){
        return new JsonPrimitive( date.format( DateTimeFormatter.ISO_LOCAL_DATE ) );
    }

    @Override
    public LocalDate deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
        throws JsonParseException {
        if( !json.getAsString().strip().isEmpty() ){
            if( json.getAsString().contains( "-" ) )
                return LocalDate.parse( json.getAsString(), DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) );
            else
                return LocalDate.parse( json.getAsString(), DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );
        }
        return null;
    }

}
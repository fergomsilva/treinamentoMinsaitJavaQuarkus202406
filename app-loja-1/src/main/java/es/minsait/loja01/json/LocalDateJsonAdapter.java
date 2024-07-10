package es.minsait.loja01.json;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class LocalDateJsonAdapter implements JsonSerializer<LocalDate>{

    public JsonElement serialize(final LocalDate date, final Type typeOfSrc, final JsonSerializationContext context){
        return new JsonPrimitive( date.format( DateTimeFormatter.ISO_LOCAL_DATE ) );
    }

}
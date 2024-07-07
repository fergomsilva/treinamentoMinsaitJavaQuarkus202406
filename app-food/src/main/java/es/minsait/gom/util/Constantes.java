package es.minsait.gom.util;

import java.util.Arrays;
import java.util.List;

import jakarta.ws.rs.core.Response;


public interface Constantes{

    List<Integer> HTTP_STATUS_OK = Arrays.asList( Response.Status.OK.getStatusCode(), Response.Status.CREATED.getStatusCode() );

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.FestivalDTO;
import co.edu.uniandes.rest.cines.exceptions.FestivalException;
import co.edu.uniandes.rest.cines.mocks.FestivalMock;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author s.ardila13
 */
@Path("festivales")
@Produces("application/json")
public class FestivalResource {
    FestivalMock festivales = new FestivalMock();
    
    /**
     * Obtiene el listado de festivales.
     *
     * @return lista de festivales
     * @throws FestivalException excepción retornada por la lógica
     */
    @GET
    public List<FestivalDTO> getBoletas() throws FestivalException {
        return festivales.getFestivales();
    }

   
    /**
     * Agrega una festival
     *
     * @param festival festival a agregar
     * @return datos del festival a agregar
     * @throws FestivalException cuando ya existe un festival con el nombre
     * suministrado
     */
    @POST
    public FestivalDTO createBoleta(FestivalDTO festival) throws FestivalException {
        return festivales.createFestival(festival);
    }


    /**
     * Retorna un festival dado su nombre
     * 
     * @param nombre nombre de la boleta a retornar
     * @return un festival
     * @throws FestivalException excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public FestivalDTO getFestival(@PathParam("nombre") String nombre) throws FestivalException{
       return festivales.getFestival(nombre);
    }
    
    
    /**
     * Retorna un festival dado su duracion
     * 
     * @param duracion del festival a retornar
     * @return un festival
     * @throws FestivalException excepción retornada por la lógica
     */
    @GET
    @Path("{precio: [0-9][0-9]*}")
    public FestivalDTO getBoletaPorPrecio(@PathParam("duracion") int duracion) throws FestivalException {
        return festivales.getFestivalPorDuracion(duracion);
    }
    
    
    /**
     * Actualiza la información del festival identificada con nombre
     * 
     * @param nombre del festival
     * @param festival con el que actualizar la información
     * @return el festival actualizado
     * @throws FestivalException excepción retornada por la lógica
     */
    @PUT
    @Path("{id: \\d+}")
    public FestivalDTO updateCity(@PathParam("nombre") String nombre, FestivalDTO boleta) throws FestivalException{
        return festivales.updateFestival(nombre, boleta);
    }
    
    /**
     * Elimina un festival dado su nombre
     * 
     * @param nombre del festival eliminado
     * @throws FestivalException excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCity(@PathParam("nombre")String nombre) throws FestivalException{
        festivales.deleteFestival(nombre);
    }
}
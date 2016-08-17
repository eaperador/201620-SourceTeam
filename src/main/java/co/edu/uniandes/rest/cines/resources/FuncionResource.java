/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.FuncionDTO;
import co.edu.uniandes.rest.cines.exceptions.FuncionException;
import co.edu.uniandes.rest.cines.mocks.FuncionMock;
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
 * @author ba.bohorquez10
 */
@Path("funciones")
@Produces("application/json")
public class FuncionResource 
{
    FuncionMock funciones = new FuncionMock();
    
    /**
     * Obtiene el listado de funciones.
     * @return El listado de funciones.
     * @throws FuncionException Si hay algun problema con el metodo.
     */
    @GET
    public List<FuncionDTO> getFunciones() throws FuncionException
    {
        return funciones.getFunciones();
    }
    
    /**
     * Obtiene la funcion con el id dado por parametro.
     * @param pId Id de la funcion a buscar.
     * @return Funcion con el id dado.
     * @throws FuncionException Si hay algun problema en el metodo.
     */
    @GET
    @Path("{id: \\d+}")
    public FuncionDTO getFuncionById(@PathParam("id") int pId) throws FuncionException
    {
        return funciones.getFuncionById(pId);
    }
    
    /**
     * Crea una nueva funcion.
     * @param nueva Nueva funcion.
     * @return LA funcion creada.
     * @throws FuncionException 
     */
    @POST
    public FuncionDTO createFuncion(FuncionDTO nueva) throws FuncionException
    {
        return funciones.createFuncion(nueva);
    }
    
    /**
     * Modifica una funcion existente.
     * @param pId Id de la funcion a modificar.
     * @param nueva Funcion con la informacion que se quiere actualizar
     * @return Funcion actualizada.
     * @throws FuncionException 
     */
    @PUT
    @Path("{id: \\d+}")
    public FuncionDTO updateFuncion(@PathParam("id") int pId, FuncionDTO nueva) throws FuncionException
    {
        return funciones.updateFuncion(pId, nueva);
    }
    
    /**
     * Elimina la funcion con el id dado.
     * @param pId Id de la funcion que se quiere eliminar.
     * @throws FuncionException 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFuncion(@PathParam("id") int pId) throws FuncionException
    {
        funciones.deleteFuncion(pId);
    }
    
}

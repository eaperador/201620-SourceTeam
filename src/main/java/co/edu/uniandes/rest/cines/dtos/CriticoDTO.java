/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 *
 * @author s.ardila13
 */
public class CriticoDTO {
    
    private int duracion;
    
    private String nombre;
    
    private int credencial;
    
    public CriticoDTO(){
        
    }
    
    public CriticoDTO(int duracion, String nombre, int credencial){
        
        this.duracion = duracion;
        
        this.nombre = nombre;
        
        this.credencial = credencial;
        
    }
    
    public int getDuracion(){
        return duracion;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getCredencial(){
        return credencial;
    }
    
    public void setDuracion(int duracion){
        this.duracion = duracion;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setCredencial(int credencial){
        this.credencial = credencial;
    }
    
    @Override
    public String toString(){
        return "Nombre: " + nombre + ", duracion: " + duracion + " credencial: " + credencial;
    }
    
}
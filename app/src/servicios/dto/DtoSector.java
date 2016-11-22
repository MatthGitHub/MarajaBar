/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

/**
 *
 * @author Matth
 */
public class DtoSector {
    private Integer numeroSector;
    private String nombreSector;
    
    public DtoSector(){
        
    }

    public Integer getNumeroSector() {
        return numeroSector;
    }

    public void setNumeroSector(Integer numeroSector) {
        this.numeroSector = numeroSector;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }
    
    
    
}

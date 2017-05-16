/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import negocio.entidades.Sectores;

/**
 *
 * @author Matth
 */
public class DtoSector {
    private Integer idSector;
    private String nombreSector;
    
    public DtoSector(){
        
    }
    
    public DtoSector cargarDto(Sectores aCargar){
        this.setIdSector(aCargar.getIdSector());
        this.setNombreSector(aCargar.getNombreSector());
        return this;
    }
    
    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }
    
    @Override
    public String toString() {
        return this.getNombreSector();
    }
    
    
}

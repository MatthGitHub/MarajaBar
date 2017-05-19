/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import negocio.entidades.Roles;

/**
 *
 * @author matth
 */
public class DtoRoles {
    private Integer idRol;
    private String descripcion;
    
    public DtoRoles(){
        
    }
    
    public DtoRoles cargarDto(Roles aCargar){
        this.setIdRol(aCargar.getIdRol());
        this.setDescripcion(aCargar.getDescripcion());
        return this;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return this.descripcion;
    }
    
}

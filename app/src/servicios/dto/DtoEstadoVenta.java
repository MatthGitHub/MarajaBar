/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import negocio.entidades.Estadosventa;

/**
 *
 * @author matth
 */
public class DtoEstadoVenta {
    private Integer idEstadoVenta;
    private String descripcion;

    public DtoEstadoVenta() {
        
    }
    
    public DtoEstadoVenta cargarDto(Estadosventa aCargar){
        this.setIdEstadoVenta(aCargar.getIdEstadoVenta());
        this.setDescripcion(aCargar.getDescripcion());
        return this;
    }

    /**
     * @return the idEstadoVenta
     */
    public Integer getIdEstadoVenta() {
        return idEstadoVenta;
    }

    /**
     * @param idEstadoVenta the idEstadoVenta to set
     */
    public void setIdEstadoVenta(Integer idEstadoVenta) {
        this.idEstadoVenta = idEstadoVenta;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}

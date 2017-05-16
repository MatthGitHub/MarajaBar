/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import negocio.entidades.Detalleventas;
import negocio.entidades.Productos;
import negocio.entidades.Ventas;

/**
 *
 * @author matth
 */
public class DtoDetalleVenta {
    private Ventas fkVenta;
    private Productos fkProducto;
    private Integer cantidad;
    
    
    public DtoDetalleVenta() {
    }
    
    public DtoDetalleVenta cargarDto(Detalleventas aCargar){
        this.setCantidad(aCargar.getCantidad());
        this.setFkProducto(aCargar.getProductos());
        this.setFkVenta(aCargar.getVentas());
        return this;
    }
    
    
    /**
     * @return the fkVenta
     */
    public Ventas getFkVenta() {
        return fkVenta;
    }

    /**
     * @param fkVenta the fkVenta to set
     */
    public void setFkVenta(Ventas fkVenta) {
        this.fkVenta = fkVenta;
    }

    /**
     * @return the fkProducto
     */
    public Productos getFkProducto() {
        return fkProducto;
    }

    /**
     * @param fkProducto the fkProducto to set
     */
    public void setFkProducto(Productos fkProducto) {
        this.fkProducto = fkProducto;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
    
}

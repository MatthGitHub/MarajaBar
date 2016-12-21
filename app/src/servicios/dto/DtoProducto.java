/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import negocio.Producto;

/**
 *
 * @author matth
 */
public class DtoProducto {
    private Integer idProducto;
    private String nombreProducto;
    private String descripcion;
    private Float precio;
    
    public DtoProducto(){
        
    }
    /**
     * Carga el producto pasado por paramtro
     * 
     * @param aCargar
     * @return DtoProducto
     */
    public DtoProducto cargarDtoProducto(Producto aCargar){
        this.setIdProducto(aCargar.getIdProducto());
        this.setNombreProducto(aCargar.getNombreProducto());
        this.setDescripcion(aCargar.getDescripcion());
        this.setPrecio(aCargar.getPrecio());
        return this;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    public DtoProducto cargarDto(Producto aCargar){
        this.setNombreProducto(aCargar.getNombreProducto());
        this.setDescripcion(aCargar.getDescripcion());
        this.setPrecio(aCargar.getPrecio());
        return this;
    }
    
}

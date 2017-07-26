/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import negocio.entidades.Productos;
import negocio.entidades.Tipoproducto;

/**
 *
 * @author matth
 */
public class DtoProducto {
    private Integer idProducto;
    private String nombreProducto;
    private String descripcion;
    private Integer precio;
    private Tipoproducto fkTipo;
    
    public DtoProducto(){
        
    }
    /**
     * Carga el producto pasado por paramtro
     * 
     * @param aCargar
     * @return DtoProducto
     */
    public DtoProducto cargarDtoProducto(Productos aCargar){
        this.setIdProducto(aCargar.getIdProducto());
        this.setNombreProducto(aCargar.getNombreProducto());
        this.setDescripcion(aCargar.getDescripcion());
        this.setPrecio(aCargar.getPrecio());
        this.setFkTipo(aCargar.getFkTipo());
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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    
    public DtoProducto cargarDto(Productos aCargar){
        this.setIdProducto(aCargar.getIdProducto());
        this.setNombreProducto(aCargar.getNombreProducto());
        this.setDescripcion(aCargar.getDescripcion());
        this.setPrecio(aCargar.getPrecio());
        this.setFkTipo(aCargar.getFkTipo());
        return this;
    }

    public Tipoproducto getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(Tipoproducto fkTipo) {
        this.fkTipo = fkTipo;
    }
    
}

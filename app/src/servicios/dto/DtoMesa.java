/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import negocio.entidades.Sectores;
import negocio.entidades.Mesa;

/**
 *
 * @author Matth
 */
public class DtoMesa {
    private Integer idMesa;
    private String descripcion;
    private Sectores sector;
    
    
    public DtoMesa(){
        
    }
    
    public DtoMesa cargarDto(Mesa mesaParaCargar)
    {
        this.setIdMesa(mesaParaCargar.getIdMesa());
        this.setSector(mesaParaCargar.getFkSector());
        this.setDescripcion(mesaParaCargar.getDescripcion());
        return this;
    }
    
    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sectores getSector() {
        return sector;
    }

    public void setSector(Sectores sector) {
        this.sector = sector;
    }
    
}

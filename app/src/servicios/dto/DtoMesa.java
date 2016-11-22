/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import negocio.Mesa;

/**
 *
 * @author Matth
 */
public class DtoMesa {
    private Integer idMesa;
    private String descripcion;
    private Integer sector;
    
    public DtoMesa(){
        
    }
    
    public DtoMesa cargarDto(Mesa mesaParaCargar)
    {
        try
        {
        this.setIdMesa(mesaParaCargar.getNumeroMesa());

        }catch(NullPointerException e)
        {
            throw  e;
        }
           //seguir hasta el final
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

    public Integer getSector() {
        return sector;
    }

    public void setSector(Integer sector) {
        this.sector = sector;
    }
    
    
}

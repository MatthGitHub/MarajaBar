/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;



/**
 *
 * @author Matth
 */

public class MesaN {
    
    private Integer numeroMesa;
    private Boolean estadoMesa;
    private String descripcion;
    private Integer fkSector;


    public MesaN(Integer numeroMesa, Boolean estadoMesa, Integer fkSector) {
        this.numeroMesa = numeroMesa;
        this.estadoMesa = estadoMesa;
        this.fkSector = fkSector;
    }
    
    

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Boolean getEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(Boolean estadoMesa) {
        this.estadoMesa = estadoMesa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFkSector() {
        return fkSector;
    }

    public void setFkSector(Integer fkSector) {
        this.fkSector = fkSector;
    }

    
}

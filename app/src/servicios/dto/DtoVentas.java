/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import java.util.Date;
import negocio.entidades.Estadosventa;
import negocio.entidades.Mesa;
import negocio.entidades.Usuarios;
import negocio.entidades.Ventas;

/**
 *
 * @author matth
 */
public class DtoVentas {
    private Integer idVenta;
    private Date fecha;
    private Integer total;
    private Mesa fkMesa;
    private Estadosventa fkEstado;
    private Usuarios fkUsuario;
    
    public DtoVentas() {
    }
    
    public DtoVentas cargarDto(Ventas aCargar){
        this.setFecha(aCargar.getFecha());
        this.setFkEstado(aCargar.getFkEstado());
        this.setFkMesa(aCargar.getFkMesa());
        this.setIdVenta(aCargar.getIdVenta());
        this.setTotal(aCargar.getTotal());
        this.setFkUsuario(aCargar.getFkUsuario());
        return this;
    } 
    /**
     * @return the idVenta
     */
    public Integer getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the fkMesa
     */
    public Mesa getFkMesa() {
        return fkMesa;
    }

    /**
     * @param fkMesa the fkMesa to set
     */
    public void setFkMesa(Mesa fkMesa) {
        this.fkMesa = fkMesa;
    }

    /**
     * @return the fkEstado
     */
    public Estadosventa getFkEstado() {
        return fkEstado;
    }

    /**
     * @param fkEstado the fkEstado to set
     */
    public void setFkEstado(Estadosventa fkEstado) {
        this.fkEstado = fkEstado;
    }

    public Usuarios getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuarios fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}

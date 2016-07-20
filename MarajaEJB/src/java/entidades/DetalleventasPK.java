/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrador
 */
@Embeddable
public class DetalleventasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "fkVenta")
    private int fkVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fkProducto")
    private int fkProducto;

    public DetalleventasPK() {
    }

    public DetalleventasPK(int fkVenta, int fkProducto) {
        this.fkVenta = fkVenta;
        this.fkProducto = fkProducto;
    }

    public int getFkVenta() {
        return fkVenta;
    }

    public void setFkVenta(int fkVenta) {
        this.fkVenta = fkVenta;
    }

    public int getFkProducto() {
        return fkProducto;
    }

    public void setFkProducto(int fkProducto) {
        this.fkProducto = fkProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fkVenta;
        hash += (int) fkProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleventasPK)) {
            return false;
        }
        DetalleventasPK other = (DetalleventasPK) object;
        if (this.fkVenta != other.fkVenta) {
            return false;
        }
        if (this.fkProducto != other.fkProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleventasPK[ fkVenta=" + fkVenta + ", fkProducto=" + fkProducto + " ]";
    }
    
}

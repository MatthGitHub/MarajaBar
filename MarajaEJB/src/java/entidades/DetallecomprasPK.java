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
public class DetallecomprasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "fkMerca")
    private int fkMerca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fkCompras")
    private int fkCompras;

    public DetallecomprasPK() {
    }

    public DetallecomprasPK(int fkMerca, int fkCompras) {
        this.fkMerca = fkMerca;
        this.fkCompras = fkCompras;
    }

    public int getFkMerca() {
        return fkMerca;
    }

    public void setFkMerca(int fkMerca) {
        this.fkMerca = fkMerca;
    }

    public int getFkCompras() {
        return fkCompras;
    }

    public void setFkCompras(int fkCompras) {
        this.fkCompras = fkCompras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fkMerca;
        hash += (int) fkCompras;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallecomprasPK)) {
            return false;
        }
        DetallecomprasPK other = (DetallecomprasPK) object;
        if (this.fkMerca != other.fkMerca) {
            return false;
        }
        if (this.fkCompras != other.fkCompras) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetallecomprasPK[ fkMerca=" + fkMerca + ", fkCompras=" + fkCompras + " ]";
    }
    
}

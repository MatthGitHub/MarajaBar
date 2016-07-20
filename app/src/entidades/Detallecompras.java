/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "detallecompras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallecompras.findAll", query = "SELECT d FROM Detallecompras d"),
    @NamedQuery(name = "Detallecompras.findByFkMerca", query = "SELECT d FROM Detallecompras d WHERE d.detallecomprasPK.fkMerca = :fkMerca"),
    @NamedQuery(name = "Detallecompras.findByFkCompras", query = "SELECT d FROM Detallecompras d WHERE d.detallecomprasPK.fkCompras = :fkCompras"),
    @NamedQuery(name = "Detallecompras.findByCantidad", query = "SELECT d FROM Detallecompras d WHERE d.cantidad = :cantidad")})
public class Detallecompras implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallecomprasPK detallecomprasPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "fkCompras", referencedColumnName = "idCompras", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compras compras;
    @JoinColumn(name = "fkMerca", referencedColumnName = "idMerca", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mercaderia mercaderia;

    public Detallecompras() {
    }

    public Detallecompras(DetallecomprasPK detallecomprasPK) {
        this.detallecomprasPK = detallecomprasPK;
    }

    public Detallecompras(DetallecomprasPK detallecomprasPK, int cantidad) {
        this.detallecomprasPK = detallecomprasPK;
        this.cantidad = cantidad;
    }

    public Detallecompras(int fkMerca, int fkCompras) {
        this.detallecomprasPK = new DetallecomprasPK(fkMerca, fkCompras);
    }

    public DetallecomprasPK getDetallecomprasPK() {
        return detallecomprasPK;
    }

    public void setDetallecomprasPK(DetallecomprasPK detallecomprasPK) {
        this.detallecomprasPK = detallecomprasPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Compras getCompras() {
        return compras;
    }

    public void setCompras(Compras compras) {
        this.compras = compras;
    }

    public Mercaderia getMercaderia() {
        return mercaderia;
    }

    public void setMercaderia(Mercaderia mercaderia) {
        this.mercaderia = mercaderia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallecomprasPK != null ? detallecomprasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallecompras)) {
            return false;
        }
        Detallecompras other = (Detallecompras) object;
        if ((this.detallecomprasPK == null && other.detallecomprasPK != null) || (this.detallecomprasPK != null && !this.detallecomprasPK.equals(other.detallecomprasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallecompras[ detallecomprasPK=" + detallecomprasPK + " ]";
    }
    
}

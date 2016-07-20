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
@Table(name = "detalleventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleventas.findAll", query = "SELECT d FROM Detalleventas d"),
    @NamedQuery(name = "Detalleventas.findByFkVenta", query = "SELECT d FROM Detalleventas d WHERE d.detalleventasPK.fkVenta = :fkVenta"),
    @NamedQuery(name = "Detalleventas.findByFkProducto", query = "SELECT d FROM Detalleventas d WHERE d.detalleventasPK.fkProducto = :fkProducto"),
    @NamedQuery(name = "Detalleventas.findByCantidad", query = "SELECT d FROM Detalleventas d WHERE d.cantidad = :cantidad")})
public class Detalleventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleventasPK detalleventasPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "fkProducto", referencedColumnName = "idProducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Productos productos;
    @JoinColumn(name = "fkVenta", referencedColumnName = "idVenta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ventas ventas;

    public Detalleventas() {
    }

    public Detalleventas(DetalleventasPK detalleventasPK) {
        this.detalleventasPK = detalleventasPK;
    }

    public Detalleventas(DetalleventasPK detalleventasPK, int cantidad) {
        this.detalleventasPK = detalleventasPK;
        this.cantidad = cantidad;
    }

    public Detalleventas(int fkVenta, int fkProducto) {
        this.detalleventasPK = new DetalleventasPK(fkVenta, fkProducto);
    }

    public DetalleventasPK getDetalleventasPK() {
        return detalleventasPK;
    }

    public void setDetalleventasPK(DetalleventasPK detalleventasPK) {
        this.detalleventasPK = detalleventasPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleventasPK != null ? detalleventasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleventas)) {
            return false;
        }
        Detalleventas other = (Detalleventas) object;
        if ((this.detalleventasPK == null && other.detalleventasPK != null) || (this.detalleventasPK != null && !this.detalleventasPK.equals(other.detalleventasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detalleventas[ detalleventasPK=" + detalleventasPK + " ]";
    }
    
}

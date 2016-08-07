/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matth
 */
@Entity
@Table(name = "estadosCaja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosCaja.findAll", query = "SELECT e FROM EstadosCaja e"),
    @NamedQuery(name = "EstadosCaja.findByIdEstadoVenta", query = "SELECT e FROM EstadosCaja e WHERE e.idEstadoVenta = :idEstadoVenta"),
    @NamedQuery(name = "EstadosCaja.findByDescripcion", query = "SELECT e FROM EstadosCaja e WHERE e.descripcion = :descripcion")})
public class EstadosCaja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstadoVenta")
    private Integer idEstadoVenta;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    public EstadosCaja() {
    }

    public EstadosCaja(Integer idEstadoVenta) {
        this.idEstadoVenta = idEstadoVenta;
    }

    public EstadosCaja(Integer idEstadoVenta, String descripcion) {
        this.idEstadoVenta = idEstadoVenta;
        this.descripcion = descripcion;
    }

    public Integer getIdEstadoVenta() {
        return idEstadoVenta;
    }

    public void setIdEstadoVenta(Integer idEstadoVenta) {
        this.idEstadoVenta = idEstadoVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoVenta != null ? idEstadoVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadosCaja)) {
            return false;
        }
        EstadosCaja other = (EstadosCaja) object;
        if ((this.idEstadoVenta == null && other.idEstadoVenta != null) || (this.idEstadoVenta != null && !this.idEstadoVenta.equals(other.idEstadoVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadosCaja[ idEstadoVenta=" + idEstadoVenta + " ]";
    }
    
}

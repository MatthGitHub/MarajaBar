/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matth
 */
@Entity
@Table(name = "estadosventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadosventa.findAll", query = "SELECT e FROM Estadosventa e"),
    @NamedQuery(name = "Estadosventa.findByIdEstadoVenta", query = "SELECT e FROM Estadosventa e WHERE e.idEstadoVenta = :idEstadoVenta"),
    @NamedQuery(name = "Estadosventa.findByDescripcion", query = "SELECT e FROM Estadosventa e WHERE e.descripcion = :descripcion")})
public class Estadosventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstadoVenta")
    private Integer idEstadoVenta;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstado")
    private List<Ventas> ventasList;

    public Estadosventa() {
    }

    public Estadosventa(Integer idEstadoVenta) {
        this.idEstadoVenta = idEstadoVenta;
    }

    public Estadosventa(Integer idEstadoVenta, String descripcion) {
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

    @XmlTransient
    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
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
        if (!(object instanceof Estadosventa)) {
            return false;
        }
        Estadosventa other = (Estadosventa) object;
        if ((this.idEstadoVenta == null && other.idEstadoVenta != null) || (this.idEstadoVenta != null && !this.idEstadoVenta.equals(other.idEstadoVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidades.Estadosventa[ idEstadoVenta=" + idEstadoVenta + " ]";
    }
    
}

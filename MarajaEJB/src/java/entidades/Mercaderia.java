/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "mercaderia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mercaderia.findAll", query = "SELECT m FROM Mercaderia m"),
    @NamedQuery(name = "Mercaderia.findByIdMerca", query = "SELECT m FROM Mercaderia m WHERE m.idMerca = :idMerca"),
    @NamedQuery(name = "Mercaderia.findByDescripcion", query = "SELECT m FROM Mercaderia m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Mercaderia.findByCosto", query = "SELECT m FROM Mercaderia m WHERE m.costo = :costo")})
public class Mercaderia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMerca")
    private Integer idMerca;
    @Size(max = 50)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private int costo;
    @JoinColumn(name = "fkProveedor", referencedColumnName = "idProveedor")
    @ManyToOne(optional = false)
    private Proveedores fkProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mercaderia")
    private List<Detallecompras> detallecomprasList;

    public Mercaderia() {
    }

    public Mercaderia(Integer idMerca) {
        this.idMerca = idMerca;
    }

    public Mercaderia(Integer idMerca, int costo) {
        this.idMerca = idMerca;
        this.costo = costo;
    }

    public Integer getIdMerca() {
        return idMerca;
    }

    public void setIdMerca(Integer idMerca) {
        this.idMerca = idMerca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public Proveedores getFkProveedor() {
        return fkProveedor;
    }

    public void setFkProveedor(Proveedores fkProveedor) {
        this.fkProveedor = fkProveedor;
    }

    @XmlTransient
    public List<Detallecompras> getDetallecomprasList() {
        return detallecomprasList;
    }

    public void setDetallecomprasList(List<Detallecompras> detallecomprasList) {
        this.detallecomprasList = detallecomprasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMerca != null ? idMerca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mercaderia)) {
            return false;
        }
        Mercaderia other = (Mercaderia) object;
        if ((this.idMerca == null && other.idMerca != null) || (this.idMerca != null && !this.idMerca.equals(other.idMerca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mercaderia[ idMerca=" + idMerca + " ]";
    }
    
}

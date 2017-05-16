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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import servicios.dto.DtoProducto;

/**
 *
 * @author matth
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdProducto", query = "SELECT p FROM Productos p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Productos.findByNombreProducto", query = "SELECT p FROM Productos p WHERE p.nombreProducto = :nombreProducto"),
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByPrecio", query = "SELECT p FROM Productos p WHERE p.precio = :precio")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @Column(name = "nombreProducto")
    private String nombreProducto;
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "precio")
    private Integer precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos")
    private List<Detallecompras> detallecomprasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos")
    private List<Detalleventas> detalleventasList;
    @JoinColumn(name = "fkTipo", referencedColumnName = "idTipoProducto")
    @ManyToOne(optional = false)
    private Tipoproducto fkTipo;

    public Productos() {
    }
    
    public Productos cargarProducto(DtoProducto aCargar){
        this.setDescripcion(aCargar.getDescripcion());
        this.setFkTipo(aCargar.getFkTipo());
        this.setIdProducto(aCargar.getIdProducto());
        this.setNombreProducto(aCargar.getNombreProducto());
        this.setPrecio(aCargar.getPrecio());
        return this;
    }

    public Productos(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Productos(Integer idProducto, String nombreProducto, int precio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @XmlTransient
    public List<Detallecompras> getDetallecomprasList() {
        return detallecomprasList;
    }

    public void setDetallecomprasList(List<Detallecompras> detallecomprasList) {
        this.detallecomprasList = detallecomprasList;
    }

    @XmlTransient
    public List<Detalleventas> getDetalleventasList() {
        return detalleventasList;
    }

    public void setDetalleventasList(List<Detalleventas> detalleventasList) {
        this.detalleventasList = detalleventasList;
    }

    public Tipoproducto getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(Tipoproducto fkTipo) {
        this.fkTipo = fkTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidades.Productos[ idProducto=" + idProducto + " ]";
    }
    
}

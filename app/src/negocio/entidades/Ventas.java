/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import servicios.dto.DtoVentas;

/**
 *
 * @author Matth
 */
@Entity
@Table(name = "ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v")
    , @NamedQuery(name = "Ventas.findByIdVenta", query = "SELECT v FROM Ventas v WHERE v.idVenta = :idVenta")
    , @NamedQuery(name = "Ventas.findByFecha", query = "SELECT v FROM Ventas v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "Ventas.findByTotal", query = "SELECT v FROM Ventas v WHERE v.total = :total")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVenta")
    private Integer idVenta;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "total")
    private int total;
    @JoinColumn(name = "fkEstado", referencedColumnName = "idEstadoVenta")
    @ManyToOne(optional = false)
    private Estadosventa fkEstado;
    @JoinColumn(name = "fkMesa", referencedColumnName = "idMesa")
    @ManyToOne(optional = false)
    private Mesa fkMesa;
    @JoinColumn(name = "fkUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuarios fkUsuario;

    public Ventas() {
    }

    public Ventas(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Ventas(Integer idVenta, Date fecha, int total) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
    }
    
    public Ventas cargarVentas(DtoVentas aCargar){
        this.setFecha(aCargar.getFecha());
        this.setFkEstado(aCargar.getFkEstado());
        this.setFkMesa(aCargar.getFkMesa());
        this.setIdVenta(aCargar.getIdVenta());
        this.setTotal(aCargar.getTotal());
        return this;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Estadosventa getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Estadosventa fkEstado) {
        this.fkEstado = fkEstado;
    }

    public Mesa getFkMesa() {
        return fkMesa;
    }

    public void setFkMesa(Mesa fkMesa) {
        this.fkMesa = fkMesa;
    }

    public Usuarios getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuarios fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidades.Ventas[ idVenta=" + idVenta + " ]";
    }
    
}

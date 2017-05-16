/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidades;

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
 * @author matth
 */
@Entity
@Table(name = "cajas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cajas.findAll", query = "SELECT c FROM Cajas c"),
    @NamedQuery(name = "Cajas.findByIdCaja", query = "SELECT c FROM Cajas c WHERE c.idCaja = :idCaja"),
    @NamedQuery(name = "Cajas.findByFecha", query = "SELECT c FROM Cajas c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cajas.findByTotal", query = "SELECT c FROM Cajas c WHERE c.total = :total"),
    @NamedQuery(name = "Cajas.findByFkEstado", query = "SELECT c FROM Cajas c WHERE c.fkEstado = :fkEstado")})
public class Cajas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCaja")
    private Integer idCaja;
    @Basic(optional = false)
    @Column(name = "fecha")
    private int fecha;
    @Basic(optional = false)
    @Column(name = "total")
    private int total;
    @Basic(optional = false)
    @Column(name = "fkEstado")
    private int fkEstado;

    public Cajas() {
    }

    public Cajas(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public Cajas(Integer idCaja, int fecha, int total, int fkEstado) {
        this.idCaja = idCaja;
        this.fecha = fecha;
        this.total = total;
        this.fkEstado = fkEstado;
    }

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(int fkEstado) {
        this.fkEstado = fkEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaja != null ? idCaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cajas)) {
            return false;
        }
        Cajas other = (Cajas) object;
        if ((this.idCaja == null && other.idCaja != null) || (this.idCaja != null && !this.idCaja.equals(other.idCaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidades.Cajas[ idCaja=" + idCaja + " ]";
    }
    
}

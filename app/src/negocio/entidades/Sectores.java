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
@Table(name = "sectores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sectores.findAll", query = "SELECT s FROM Sectores s"),
    @NamedQuery(name = "Sectores.findByIdSector", query = "SELECT s FROM Sectores s WHERE s.idSector = :idSector"),
    @NamedQuery(name = "Sectores.findByNombreSector", query = "SELECT s FROM Sectores s WHERE s.nombreSector = :nombreSector")})
public class Sectores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSector")
    private Integer idSector;
    @Basic(optional = false)
    @Column(name = "nombreSector")
    private String nombreSector;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSector")
    private List<Mesa> mesaList;

    public Sectores() {
    }

    public Sectores(Integer idSector) {
        this.idSector = idSector;
    }

    public Sectores(Integer idSector, String nombreSector) {
        this.idSector = idSector;
        this.nombreSector = nombreSector;
    }

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }

    @XmlTransient
    public List<Mesa> getMesaList() {
        return mesaList;
    }

    public void setMesaList(List<Mesa> mesaList) {
        this.mesaList = mesaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSector != null ? idSector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sectores)) {
            return false;
        }
        Sectores other = (Sectores) object;
        if ((this.idSector == null && other.idSector != null) || (this.idSector != null && !this.idSector.equals(other.idSector))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidades.Sectores[ idSector=" + idSector + " ]";
    }
    
}

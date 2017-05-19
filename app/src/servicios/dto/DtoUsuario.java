/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import negocio.entidades.Roles;
import negocio.entidades.Usuarios;

/**
 *
 * @author matth
 */
public class DtoUsuario {
    private Integer idUsuario;
    private String nombreUsuario;
    private String clave;
    private Roles rol;
    
    public DtoUsuario(){
        
    }
    
    public DtoUsuario cargarDto(Usuarios aCargar){
        this.setIdUsuario(aCargar.getIdUsuario());
        this.setNombreUsuario(aCargar.getNombreUsuario());
        this.setClave(aCargar.getClave());
        this.setRol(aCargar.getFkRol());
        return this;
    }
    
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
    
    
    
}

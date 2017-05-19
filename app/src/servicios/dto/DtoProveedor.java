/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import negocio.entidades.Proveedores;

/**
 *
 * @author matth
 */
public class DtoProveedor {
    private Integer idProveedor;
    private String nombreProveedor;
    private String cuit;
    private String telefono;
    private String correo;
    
    public DtoProveedor(){
        
    }
    
    public DtoProveedor cargarDto(Proveedores aCargar){
        this.setIdProveedor(aCargar.getIdProveedor());
        this.setNombreProveedor(aCargar.getNombreProveedor());
        this.setCuit(aCargar.getCuit());
        this.setTelefono(aCargar.getTelefono());
        this.setCorreo(aCargar.getEmail());
        return this;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}

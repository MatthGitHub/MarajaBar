/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import controladores.ProveedoresJpaController;
import entidades.Proveedores;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Matth
 */
public class ProveedoreServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPu");
    ProveedoresJpaController jpa = new ProveedoresJpaController(emf);
    
    public List<Proveedores> traerTodos(){
        return jpa.findProveedoresEntities();
    }
    
    public boolean eliminarProveedor(int id) {
        try {
            jpa.destroy(id);
            System.out.println("Proveedor eliminado correctamente");
            return true;
        } catch (Exception e) {
            System.out.println(e +"- Error al eliminar proveedor ProveedoreServ");
            return false;
        }
    }
    
    public boolean guardarProveedor(Proveedores proveedor){
        try {
            jpa.create(proveedor);
            return true;
        } catch (Exception e) {
            System.out.println(e + "Error carga proveedor ProveedoreServ");
            return false;
        }
    }
    public boolean editarProveedor(Proveedores proveedor){
        try {
            jpa.edit(proveedor);
            return true;
        } catch (Exception e) {
            System.out.println(e+"- Error al modificar proveedor ProveedoreServ");
            return false;
        }
    }
}

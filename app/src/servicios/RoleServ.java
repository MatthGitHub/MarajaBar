/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import controladores.RolesJpaController;
import entidades.Roles;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Matth
 */
public class RoleServ {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
    RolesJpaController jpa = new RolesJpaController(emf);

    public List<Roles> traerTodos() {
        return jpa.findRolesEntities();
    }
    
    public boolean eliminarRol(int id) {
        try {
            jpa.destroy(id);
            System.out.println("Rol eliminado correctamente");
            return true;
        } catch (Exception e) {
            System.out.println(e +"- Error al eliminar Rol RoleServ");
            return false;
        }
    }
    
    public boolean guardarRol(Roles rol){
        try {
            jpa.create(rol);
            return true;
        } catch (Exception e) {
            System.out.println(e + "Error carga rol RoleServ");
            return false;
        }
    }
    public boolean editarRol(Roles rol){
        try {
            jpa.edit(rol);
            return true;
        } catch (Exception e) {
            System.out.println(e+"- Error al modificar rol RoleServ");
            return false;
        }
    }
}

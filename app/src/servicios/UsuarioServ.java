/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import controladores.UsuariosJpaController;
import entidades.Usuarios;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Matth
 */
public class UsuarioServ {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
    UsuariosJpaController jpa = new UsuariosJpaController(emf);

    public List<Usuarios> traerTodos() {
        return jpa.findUsuariosEntities();
    }

    public boolean guardarUsuario(Usuarios user) {
        try {
            jpa.create(user);
            return true;
        } catch (Exception e) {
            System.out.println(e + "Error carga usuario UsuarioServ");
            return false;
        }

    }

    public boolean eliminarUsuario(int id) {
        try {
            jpa.destroy(id);
            System.out.println("Usuario eliminado correctamente");
            return true;
        } catch (Exception e) {
            System.out.println(e +"- Error al eliminar usuario UsuarioServ");
            return false;
        }
    }
    
    public boolean editarUsuario(Usuarios user){
        try {
            jpa.edit(user);
            return true;
        } catch (Exception e) {
            System.out.println(e+"- Error al modificar usuario UsuarioServ");
            return false;
        }
    }

}

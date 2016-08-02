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
    public List<Usuarios> traerTodos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        UsuariosJpaController jpa = new UsuariosJpaController(emf);
        return jpa.findUsuariosEntities();
    }
    
    
}

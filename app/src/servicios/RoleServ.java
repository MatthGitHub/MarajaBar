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
    public List <Roles> traerTodos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        RolesJpaController jpa = new RolesJpaController(emf);
        return jpa.findRolesEntities();
    }
}

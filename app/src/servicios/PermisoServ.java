/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import controladores.PermisosJpaController;
import entidades.Permisos;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Matth
 */
public class PermisoServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
    PermisosJpaController jpa = new PermisosJpaController(emf);

    public List<Permisos> traerTodos() {
        return jpa.findPermisosEntities();
    }
}

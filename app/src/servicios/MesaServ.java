/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import controladores.MesaJpaController;
import entidades.Mesa;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Administrador
 */
public class MesaServ {
    
    public List<Mesa> traerTodos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        MesaJpaController jpa = new MesaJpaController(emf);
        return jpa.findMesaEntities();
    }
}

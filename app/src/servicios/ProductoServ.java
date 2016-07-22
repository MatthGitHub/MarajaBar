/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import controladores.MesaJpaController;
import controladores.ProductosJpaController;
import entidades.Mesa;
import entidades.Productos;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Administrador
 */
public class ProductoServ {
     public List<Productos> traerTodos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
       ProductosJpaController jpa = new ProductosJpaController(emf);
        return jpa.findProductosEntities();
    }
}

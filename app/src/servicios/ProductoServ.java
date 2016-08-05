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

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
    ProductosJpaController jpa = new ProductosJpaController(emf);

    public List<Productos> traerTodos() {
        return jpa.findProductosEntities();
    }
    
    public boolean eliminarProducto(int id) {
        try {
            jpa.destroy(id);
            System.out.println("Producto eliminado correctamente");
            return true;
        } catch (Exception e) {
            System.out.println(e +"- Error al eliminar Producto ProductoServ");
            return false;
        }
    }
    
    public boolean guardarProducto(Productos producto){
        try {
            jpa.create(producto);
            return true;
        } catch (Exception e) {
            System.out.println(e + "Error carga producto ProductoServ");
            return false;
        }
    }
    public boolean editarProducto(Productos producto){
        try {
            jpa.edit(producto);
            return true;
        } catch (Exception e) {
            System.out.println(e+"- Error al modificar producto ProductoServ");
            return false;
        }
    }
}

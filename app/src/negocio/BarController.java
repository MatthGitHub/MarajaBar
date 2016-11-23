/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matth
 */
public class BarController {
    private static BarController esteBar;
    
    private List<Sector> sectores;
    
    private BarController(){
        
    }
    
    public static BarController getBarController(){
        if(esteBar == null){
            esteBar = new BarController();
        }
        return esteBar;
    }
    
    public List<Mesa> getTodasLasMesas(String nombreSector){
        
        List<Mesa> listMesas = new ArrayList<>();
        listMesas.add(new Mesa(1));
        listMesas.add(new Mesa(2));
        listMesas.add(new Mesa(3));
        listMesas.add(new Mesa(4));
        listMesas.add(new Mesa(5));
        listMesas.add(new Mesa(6));
        listMesas.add(new Mesa(7));
        listMesas.add(new Mesa(8));
        listMesas.add(new Mesa(9));
        listMesas.add(new Mesa(10));
        listMesas.add(new Mesa(11));
        listMesas.add(new Mesa(12));
        listMesas.add(new Mesa(13));
        listMesas.add(new Mesa(14));
        listMesas.add(new Mesa(15));
        //listMesas.add(new Mesa(16));
        
        return listMesas;
    }
    
    public List<Sector> getTodosLosSectores(){
        sectores = new ArrayList<>();
        sectores.add(new Sector("Arriba"));
        sectores.add(new Sector("Abajo"));
        sectores.add(new Sector("Salon"));
        sectores.add(new Sector("Afuera"));
        return sectores;
    }
    
    public Sector getSector(String nombre){
        return new Sector(nombre);
    }
    
    public List<Producto> getTodosLosProductos(){
        List<Producto> productos = new ArrayList<>();
        
        return productos; 
    }
    
    
}

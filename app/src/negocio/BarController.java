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
    private static List<Mesa> listMesas;
    private List<Sector> sectores;
    
    private BarController(){
        listMesas = new ArrayList<>();
        setearMesas();
    }
    
    public static BarController getBarController(){
        if(esteBar == null){
            esteBar = new BarController();
        }
        return esteBar;
    }
    
// ------------------------  Metodos Mesas -------------------------------------------    
    /**
     * 
     * @param nombreSector
     * @return 
     */ 
    
    private void setearMesas(){
        //Deberia buscar mesas de la BD
        listMesas.add(new Mesa(1,false, 1));
        listMesas.add(new Mesa(2,false,1));
        listMesas.add(new Mesa(3,false,1));
        listMesas.add(new Mesa(4,false,1));
        listMesas.add(new Mesa(5,false,1));
        listMesas.add(new Mesa(6,false,2));
        listMesas.add(new Mesa(7,false,2));
        listMesas.add(new Mesa(8,false,2));
        listMesas.add(new Mesa(9,false,2));
        listMesas.add(new Mesa(10,false,2));
        listMesas.add(new Mesa(11,false,3));
        listMesas.add(new Mesa(12,false,3));
        listMesas.add(new Mesa(13,false,3));
        listMesas.add(new Mesa(14,false,3));
        listMesas.add(new Mesa(15,false,3));
        listMesas.add(new Mesa(16,false,3));
    }
    
    public List<Mesa> getTodasLasMesas(Integer idSector){
        List<Mesa> aux = new ArrayList<>();
        for(int i = 0; i < listMesas.size(); i++){
            if(listMesas.get(i).getFkSector().equals(idSector)){
                aux.add(listMesas.get(i));
            }
        }
        return aux;
    }
    
    public Mesa getMesa(Integer idMesa){
       for(int i = 0; i < listMesas.size();i++){
           if(listMesas.get(i).getNumeroMesa().equals(idMesa)){
               return listMesas.get(i);
           }
       }
       return null;
    }
    
    
// ------------------------  Metodos Mesas ----------------------------------------------
// ------------------------  Metodos Sectores -------------------------------------------      
    public List<Sector> getTodosLosSectores(){
        sectores = new ArrayList<>();//Deberia buscar sectores de la BD
        sectores.add(new Sector("Arriba"));
        sectores.add(new Sector("Abajo"));
        sectores.add(new Sector("Salon"));
        sectores.add(new Sector("Afuera"));
        return sectores;
    }
    
    public Sector getSector(String nombre){
        return new Sector(nombre);
    }
// ------------------------  Metodos Sectores --------------------------------------------
// ------------------------  Metodos Productos -------------------------------------------   
    
    public List<Producto> getTodosLosProductos(){
        List<Producto> productos = new ArrayList<>();//Deberia buscar productos de la BD
        productos.add(new Producto(1, "Jarra cerveza", "1Lts", Float.parseFloat("120")));
        productos.add(new Producto(2, "Pizza maraja", "Con de todo", Float.parseFloat("220")));
        productos.add(new Producto(3, "Nachos", "Con de todo", Float.parseFloat("80")));
        return productos; 
    }
// ------------------------  Metodos Productos -------------------------------------------       
    
}

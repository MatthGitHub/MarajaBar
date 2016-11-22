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
        sectores = new ArrayList<>();
        sectores.add(new Sector("Arriba"));
        sectores.add(new Sector("Abajo"));
        sectores.add(new Sector("Salon"));
        sectores.add(new Sector("Afuera"));
        
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
        
        return listMesas;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import servicios.dto.DtoMesa;

/**
 *
 * @author Matth
 */
public class FacadeNegocio {
    private static FacadeNegocio estaClase;
    
    private FacadeNegocio(){
    }
    
   public static FacadeNegocio getFacadeNegocio(){
       if(estaClase == null){
           estaClase = new FacadeNegocio();
       }
       return estaClase;
   }
   
   /**
    * 
    * @param sector
    * @return 
    * @throws NullPointerException
    */
   public List<DtoMesa> getTodasLasMesas(String sector){
       BarController barController = BarController.getBarController();
       List<DtoMesa> mesas = new ArrayList<>();
       Iterator<Mesa> iteradorMesa =  barController.getTodasLasMesas(sector).iterator();
       while(iteradorMesa.hasNext()){
           DtoMesa nuevodto = new DtoMesa();
           nuevodto.cargarDto(iteradorMesa.next());
           mesas.add(nuevodto);
       }
       return mesas;
   }
}

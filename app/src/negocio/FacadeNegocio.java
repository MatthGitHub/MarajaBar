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
import servicios.dto.DtoProducto;

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
   // ******************************** Metodos mesas ******************************************//
   /**
    * 
    * @param sector
    * @return 
    * @throws NullPointerException
    */
   public List<DtoMesa> getTodasLasMesas(Integer sector){
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
   public DtoMesa getMesa(Integer idMesa){
       BarController barController = BarController.getBarController();
       DtoMesa miMesa = new DtoMesa();
       miMesa.cargarDto(barController.getMesa(idMesa));
       return miMesa;
   }
   // ******************************** Metodos mesas **********************************************//
   // ******************************** Metodos productos ******************************************//
   public List<DtoProducto> getTodosLosProductos(){
       BarController barController = BarController.getBarController();
       List<DtoProducto> productos = new ArrayList<>();
       Iterator<Producto> iteradorProducto = barController.getTodosLosProductos().iterator();
       while(iteradorProducto.hasNext()){
           DtoProducto nuevodto = new DtoProducto();
           nuevodto.cargarDto(iteradorProducto.next());
           productos.add(nuevodto);
       }
       return productos;
   }
   
   // ******************************** Metodos productos ******************************************//
   
}

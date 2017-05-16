/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import gui.main.Main;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import negocio.entidades.Detalleventas;
import negocio.entidades.Mesa;
import negocio.entidades.Productos;
import negocio.entidades.Sectores;
import negocio.entidades.Ventas;
import servicios.dto.DtoDetalleVenta;
import servicios.dto.DtoMesa;
import servicios.dto.DtoProducto;
import servicios.dto.DtoSector;
import servicios.dto.DtoVentas;

/**
 *
 * @author Matth
 */
public class FacadeNegocio {
    private static FacadeNegocio estaClase;
    private Main mainFrame;
    
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
   /**
    * 
    * */
   public List<DtoMesa> getTodasLasMesas(){
       BarController barController = BarController.getBarController();
       List<DtoMesa> mesas = new ArrayList<>();
       Iterator<Mesa> iteradorMesa =  barController.getTodasLasMesas().iterator();
       while(iteradorMesa.hasNext()){
           DtoMesa nuevodto = new DtoMesa();
           nuevodto.cargarDto(iteradorMesa.next());
           mesas.add(nuevodto);
       }
       return mesas;
   }
   
   /**
    * 
    */
   public DtoMesa getMesa(Integer idMesa){
       BarController barController = BarController.getBarController();
       DtoMesa miMesa = new DtoMesa();
       miMesa.cargarDto(barController.getMesa(idMesa));
       return miMesa;
   }
   
   public boolean nuevaMesa(DtoSector sector, String descripcion){
       BarController barController = BarController.getBarController();
       Mesa aCargar = new Mesa();
       
       aCargar.setDescripcion(descripcion);
       aCargar.setFkSector(barController.getSector(sector.getIdSector()));
       try {
           barController.nuevaMesa(aCargar);
           return true;
       } catch (Exception e) {
           return false;
       }
   }
   // ******************************** Metodos mesas **********************************************//
   // ******************************** Metodos productos ******************************************//
   public List<DtoProducto> getTodosLosProductos(){
       BarController barController = BarController.getBarController();
       List<DtoProducto> productos = new ArrayList<>();
       Iterator<Productos> iteradorProducto = barController.getTodosLosProductos().iterator();
       while(iteradorProducto.hasNext()){
           DtoProducto nuevodto = new DtoProducto();
           nuevodto.cargarDto(iteradorProducto.next());
           productos.add(nuevodto);
       }
       return productos;
   }
   
   public DtoProducto getProducto(Integer id){
       BarController barController = BarController.getBarController();
       Productos producto = barController.getProducto(id);
       return new DtoProducto().cargarDto(producto);
       
   }
   
   public void nuevoProducto(DtoProducto nuevo){
       BarController barController = BarController.getBarController();
       Productos producto = new Productos();
       producto.cargarProducto(nuevo);
       barController.nuevoProducto(producto);
   }
   
   
   
   // ******************************** Metodos productos ******************************************//
   // ******************************** Metodos Sectores *******************************************//
   public List<DtoSector> getTodosLosSectores(){
       BarController barController = BarController.getBarController();
       List<DtoSector> sectores = new ArrayList<>();
       Iterator<Sectores> iteradorSectores = barController.getTodosLosSectores().iterator();
       while(iteradorSectores.hasNext()){
           DtoSector nuevodto = new DtoSector();
           nuevodto.cargarDto(iteradorSectores.next());
           sectores.add(nuevodto);
       }
       return sectores;
   }
   // /********************************* */Metodos Sectores *******************************************//
   // --------------------------------- Metodos Venta ---------------------------------------------//

    /**
     *
     * @param mesa
     * @return Id generado de venta
     * @throws java.lang.Exception
     */
    public Integer nuevaVenta(DtoMesa mesa) throws Exception{
       BarController barController = BarController.getBarController();
       Mesa laMesa = new Mesa();
       laMesa = laMesa.cargarMesa(mesa);
       return barController.nuevaVenta(laMesa);
   }
   
    public DtoVentas getUltimaVenta(DtoMesa mesa){
        BarController barController = BarController.getBarController();
        DtoVentas ultimaVenta = new DtoVentas();
        Mesa laMesa = new Mesa();
        laMesa.cargarMesa(mesa);
        return ultimaVenta.cargarDto(barController.getUltimaVenta(laMesa));
    }
    
    // --------------------------------- Metodos Venta ---------------------------------------------//
    // --------------------------------- Metodos DetalleVenta ---------------------------------------------//
    public List<DtoDetalleVenta> getDetalleVenta(DtoVentas venta){
        BarController barController = BarController.getBarController();
        List<DtoDetalleVenta> detalles = new ArrayList<>();
        Ventas miVenta = new Ventas();
        miVenta.cargarVentas(venta);
        Iterator<Detalleventas> iteradorDetalle = barController.getDetalleventas(miVenta).iterator();
        while(iteradorDetalle.hasNext()){
            DtoDetalleVenta nuevodto = new DtoDetalleVenta();
            nuevodto.cargarDto(iteradorDetalle.next());
            detalles.add(nuevodto);
        }
        return detalles;
    }
    /**
     * Crea un nuevo detalle de venta
     * @param venta
     * @param producto
     * @return 
     */
    public boolean nuevoDetalleVenta(DtoVentas venta, DtoProducto producto){
        BarController barController = BarController.getBarController();
        Ventas miVenta = new Ventas();
        miVenta.cargarVentas(venta);
        Productos miProducto = new Productos();
        miProducto.cargarProducto(producto);
        if(barController.nuevoDetalleVenta(miVenta, miProducto)){
            return true;
        }else{
            return false;
        }
        
    }
    
    
    
    // --------------------------------- Metodos DetalleVenta ---------------------------------------------//
    // --------------------------------- Metodos Productos ---------------------------------------------//
    
    
    // --------------------------------- Metodos Productos ---------------------------------------------//
}

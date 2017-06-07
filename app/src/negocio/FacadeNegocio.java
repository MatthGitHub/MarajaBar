/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import gui.main.Main;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.controladores.exceptions.IllegalOrphanException;
import negocio.controladores.exceptions.NonexistentEntityException;
import negocio.entidades.Detalleventas;
import negocio.entidades.Mesa;
import negocio.entidades.Productos;
import negocio.entidades.Proveedores;
import negocio.entidades.Roles;
import negocio.entidades.Sectores;
import negocio.entidades.Usuarios;
import negocio.entidades.Ventas;
import servicios.dto.DtoDetalleVenta;
import servicios.dto.DtoMesa;
import servicios.dto.DtoProducto;
import servicios.dto.DtoProveedor;
import servicios.dto.DtoRoles;
import servicios.dto.DtoSector;
import servicios.dto.DtoUsuario;
import servicios.dto.DtoVentas;

/**
 *
 * @author Matth
 */
public class FacadeNegocio {
    private static FacadeNegocio estaClase;
    private Main mainFrame;
    public static Usuarios usuario;
    public static String mensaje;
    
    private FacadeNegocio(){
    }
    
   public static FacadeNegocio getFacadeNegocio(){
       if(estaClase == null){
           estaClase = new FacadeNegocio();
       }
       return estaClase;
   }
   
   public boolean verificarPermisos(int idPermiso){
       
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
   
   public boolean modificarMesa(DtoMesa aModif){
       BarController barController = BarController.getBarController();
       Mesa modif = new Mesa();
       modif.cargarMesa(aModif);
        try {
            barController.modificarMesa(modif);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(FacadeNegocio.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
   }
   
   public void eliminarMesa(Integer id) throws IllegalOrphanException, NonexistentEntityException{
       BarController.getBarController().eliminarMesa(id);
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
   
   public boolean eliminarProducto(Integer id){
       return BarController.getBarController().eliminarProducto(id);
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
    public DtoVentas nuevaVenta(DtoMesa mesa) throws Exception{
       BarController barController = BarController.getBarController();
       Mesa laMesa = new Mesa();
       laMesa = laMesa.cargarMesa(mesa);
       DtoVentas venta = new DtoVentas();
       venta.cargarDto(barController.nuevaVenta(laMesa));
       return venta;
   }
   /**
    * Trae la ultima venta de la mesa
    * @param mesa
    * @return 
    */
    public DtoVentas getUltimaVenta(DtoMesa mesa){
        BarController barController = BarController.getBarController();
        DtoVentas ultimaVenta = new DtoVentas();
        Mesa laMesa = new Mesa();
        laMesa.cargarMesa(mesa);
        if(barController.getUltimaVenta(laMesa) == null){
            return null;
        }
        return ultimaVenta.cargarDto(barController.getUltimaVenta(laMesa));
    }
    
    public boolean cerrarVenta(DtoVentas aCerrar){
        BarController barController = BarController.getBarController();
        Ventas venta = new Ventas();
        venta.cargarVentas(aCerrar);
        return barController.cerrarVenta(venta);
    }
    
    public List<DtoVentas> getVentasEntreFechas(Date desde, Date hasta){
        List<DtoVentas> dtoVentas = new ArrayList<>();
        Iterator<Ventas> iteradorVentas = BarController.getBarController().getVentasEntreFechas(desde, hasta).iterator();
        while(iteradorVentas.hasNext()){
            DtoVentas  nuevoDto = new  DtoVentas();
            nuevoDto.cargarDto(iteradorVentas.next());
            dtoVentas.add(nuevoDto);
        }
        return dtoVentas;
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
    
    public boolean eliminarDetalleVenta(DtoVentas venta, DtoProducto producto){
        BarController barController = BarController.getBarController();
        Ventas miVenta = new Ventas();
        miVenta.cargarVentas(venta);
        Productos miProducto = new Productos();
        miProducto.cargarProducto(producto);
        if(barController.eliminarDetalleVenta(miVenta, miProducto)){
            return true;
        }else{
            return false;
        }
    }
    
    
    
 // --------------------------------- Metodos DetalleVenta ---------------------------------------------//
 // --------------------------------- Metodos Proveedores ---------------------------------------------//
 
    public List<DtoProveedor> getTodosLosProveedores(){
        BarController barController = BarController.getBarController();
        List<DtoProveedor> proveedores = new ArrayList<>();
        Iterator<Proveedores> iteradorProveedores = barController.getTodosLosProveedores().iterator();
        while(iteradorProveedores.hasNext()){
           DtoProveedor nuevodto = new DtoProveedor();
           nuevodto.cargarDto(iteradorProveedores.next());
           proveedores.add(nuevodto);
        }
        return proveedores;
    }
    
    /**
     * 
     * @param nuevo
     * @return 
     */
    public boolean nuevoProveedor(DtoProveedor nuevo){
        Proveedores prov = new Proveedores();
        prov.cargarProveedor(nuevo);
        if(BarController.getBarController().nuevoProveedor(prov)){
            return true;
        }else{
            return false;
        }
    }
    
    public void eliminarProveedor(Integer id) throws NonexistentEntityException{
        BarController.getBarController().eliminarProveedor(id);
    }
 // --------------------------------- Metodos Proveedores ---------------------------------------------//
// --------------------------------- Metodos Usuarios ---------------------------------------------//
    public boolean login(String nombre, String clave){
        MD5 md5 = MD5.getMD5();
        clave = md5.md5(clave);
        
        usuario = BarController.getBarController().login(nombre);
        
        if(!usuario.getNombreUsuario().equals("No")){
            if(usuario.getClave().equals(clave)){
                return true;
            }else{
                 mensaje = "Clave incorrecta";
                 return false;
            }
        }else{
             mensaje = "Usuario inexistente";
             return false;
        }
    }
    
    /**
     * 
     * @return 
     */
    public List<DtoUsuario> getTodosLosUsuarios(){
        BarController barController = BarController.getBarController();
        List<DtoUsuario> usuarios = new ArrayList<>();
        Iterator<Usuarios> iteradorUsuarios = barController.getTodosLosUsuarios().iterator();
        while(iteradorUsuarios.hasNext()){
           DtoUsuario nuevodto = new DtoUsuario();
           nuevodto.cargarDto(iteradorUsuarios.next());
           usuarios.add(nuevodto);
        }
        return usuarios;
    }
    
    public boolean nuevoUsuario(DtoRoles rol,DtoUsuario nuevo){
        Usuarios usu = new Usuarios();
        Roles role;
        role = BarController.getBarController().buscarRol(rol.getIdRol());
        nuevo.setRol(role);
        usu.cargarUsuario(nuevo);
        if(BarController.getBarController().nuevoUsuario(usu)){
            return true;
        }else{
            return false;
        }
    }
    
    public void eliminarUsuario(Integer id) throws NonexistentEntityException, IllegalOrphanException{
        BarController.getBarController().eliminarUsuario(id);
    }
// --------------------------------- Metodos Usuarios ---------------------------------------------//
// --------------------------------- Metodos Roles ---------------------------------------------//
    
    public List<DtoRoles> getTodosLosRoles(){
        BarController barController = BarController.getBarController();
        List<DtoRoles> roles = new ArrayList<>();
        Iterator<Roles> iteradorRoles = barController.getTodosLosRoles().iterator();
        while(iteradorRoles.hasNext()){
           DtoRoles nuevodto = new DtoRoles();
           nuevodto.cargarDto(iteradorRoles.next());
           roles.add(nuevodto);
        }
        return roles;
    }
// --------------------------------- Metodos Roles ---------------------------------------------//
}

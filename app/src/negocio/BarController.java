/*
 * *******************************************  * 
 * Desarrollado por Matias Benditti.
 * *******************************************  * 
 */
package negocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import negocio.controladores.DetalleventasJpaController;
import negocio.controladores.EstadosventaJpaController;
import negocio.controladores.MesaJpaController;
import negocio.controladores.PermisosJpaController;
import negocio.controladores.ProductosJpaController;
import negocio.controladores.ProveedoresJpaController;
import negocio.controladores.RolesJpaController;
import negocio.controladores.SectoresJpaController;
import negocio.controladores.TipoproductoJpaController;
import negocio.controladores.UsuariosJpaController;
import negocio.controladores.VentasJpaController;
import negocio.controladores.exceptions.IllegalOrphanException;
import negocio.controladores.exceptions.NonexistentEntityException;
import negocio.entidades.Detalleventas;
import negocio.entidades.DetalleventasPK;
import negocio.entidades.Estadosventa;
import negocio.entidades.Mesa;
import negocio.entidades.Permisos;
import negocio.entidades.Productos;
import negocio.entidades.Proveedores;
import negocio.entidades.Roles;
import negocio.entidades.Sectores;
import negocio.entidades.Tipoproducto;
import negocio.entidades.Usuarios;
import negocio.entidades.Ventas;

/**
 *
 * @author Matth
 */
public class BarController {
    private static BarController esteBar;
    private static List<Mesa> listMesas;
    private List<Sectores> sectores;
    
    
    private BarController(){
        listMesas = new ArrayList<>();
    }
    
    public static BarController getBarController(){
        if(esteBar == null){
            esteBar = new BarController();
        }
        return esteBar;
    }
   
// ------------------------  Metodos Mesas -------------------------------------------// 
    
    /**
     * 
     * @param idSector
     * @return 
     */
    public List<Mesa> getTodasLasMesas(Integer idSector){
        List<Mesa> aux = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        MesaJpaController jpa = new MesaJpaController(emf);
        listMesas = jpa.findMesaEntities();
        for(int i = 0; i < listMesas.size(); i++){
            if(listMesas.get(i).getFkSector().getIdSector().equals(idSector)){
                aux.add(listMesas.get(i));
            }
        }
        return aux;
    }
    /**
     * 
     * @return 
     */
    public List<Mesa> getTodasLasMesas(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        MesaJpaController jpa = new MesaJpaController(emf);
        return jpa.findMesaEntities();
    }
    /**
     * 
     * @param idMesa
     * @return 
     */
    public Mesa getMesa(Integer idMesa){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        MesaJpaController jpa = new MesaJpaController(emf);
        return jpa.findMesa(idMesa);
    }
    
    /**
     * 
     * @param nueva
     * @throws Exception 
     */
    public void nuevaMesa(Mesa nueva) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        MesaJpaController jpa = new MesaJpaController(emf);
        jpa.create(nueva);
    }
    
    /**
     * 
     * @param mesa
     * @throws NonexistentEntityException
     * @throws Exception 
     */
    public void modificarMesa(Mesa mesa) throws NonexistentEntityException, Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        MesaJpaController jpa = new MesaJpaController(emf);
        Mesa mesaAux = new Mesa();
        mesaAux = jpa.findMesa(mesa.getIdMesa());
        mesaAux.setDescripcion(mesa.getDescripcion());
        jpa.edit(mesaAux);
    }
    
    /**
     * 
     * @param id
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException 
     */
    public void eliminarMesa(Integer id) throws IllegalOrphanException, NonexistentEntityException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        MesaJpaController jpa = new MesaJpaController(emf);
        
        jpa.destroy(id);
    }
    
    
// ------------------------  Metodos Mesas ----------------------------------------------//
// ------------------------  Metodos Sectores -------------------------------------------//      
    /**
     * 
     * @return 
     */
    public List<Sectores> getTodosLosSectores(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        SectoresJpaController jpa = new SectoresJpaController(emf);
        return jpa.findSectoresEntities();
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Sectores getSector(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        SectoresJpaController jpa = new SectoresJpaController(emf);
        
        return jpa.findSectores(id);
    }
// ------------------------  Metodos Sectores --------------------------------------------//
// ------------------------  Metodos Productos -------------------------------------------//
    
    public List<Productos> getTodosLosProductos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        ProductosJpaController jpa = new ProductosJpaController(emf);
        return jpa.findProductosEntities(); 
    }
    
    public Productos getProducto(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        ProductosJpaController jpa = new ProductosJpaController(emf);
        return jpa.findProductos(id);
    }
    
    public void nuevoProducto(Productos nuevo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        ProductosJpaController jpa = new ProductosJpaController(emf);
        nuevo.setFkTipo(getTipoproducto(1));
        jpa.create(nuevo);
    }
    
    public Tipoproducto getTipoproducto(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        TipoproductoJpaController jpa = new TipoproductoJpaController(emf);
        return jpa.findTipoproducto(id);
    }
    
    public boolean eliminarProducto(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        ProductosJpaController jpa = new ProductosJpaController(emf);
        try {
            jpa.destroy(id);
            return true;
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al eliminar producto -"+ex);
            return false;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al eliminar producto -"+ex);
            return false;
        }
    }
    
    public void modificarProducto(Productos modificar) throws NonexistentEntityException, Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        ProductosJpaController jpa = new ProductosJpaController(emf);
        jpa.edit(modificar);
    }
    
// ------------------------  Metodos Productos -------------------------------------------//    
// ------------------------  Metodos Venta -----------------------------------------------//
    /**
     * 
     * @param mesa
     * @return Id generado
     * @throws negocio.controladores.exceptions.NonexistentEntityException
     */
    public Ventas nuevaVenta(Mesa mesa) throws NonexistentEntityException, Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        VentasJpaController jpa = new VentasJpaController(emf);
        Date fecha = new Date();
        Ventas venta = new Ventas();
        venta.setTotal(0);
        venta.setFecha(fecha);
        venta.setFkMesa(mesa);
        venta.setFkEstado(getEstadoventa(2));
        venta.setFkUsuario(FacadeNegocio.usuario);
        jpa.create(venta);
        return venta;
    }
    /**
     * Trae la ultima venta de esa mesa
     * @param mesa
     * @return 
     */
    public Ventas getUltimaVenta(Mesa mesa){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        VentasJpaController jpa = new VentasJpaController(emf);
        List<Ventas> miLista = jpa.findVentasEntities();
        List<Ventas> aux = new ArrayList<>();
        Ventas ultimo = new Ventas();
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getFkMesa().equals(mesa)){
                aux.add(miLista.get(i));
                ultimo = miLista.get(i);
            }
        }
        
        for(int i = 0 ; i < aux.size(); i++){
            if(aux.get(i).getFecha().compareTo(ultimo.getFecha()) > 0){
                ultimo = aux.get(i);
            }
        }
        if(ultimo.getIdVenta() == null){
            return null;
        }
        return ultimo;
    }
    
    public boolean cerrarVenta(Ventas venta){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        VentasJpaController jpa = new VentasJpaController(emf);
        venta = jpa.findVentas(venta.getIdVenta());
        
        venta.setFkEstado(getEstadoventa(1));
        
        try {
            
            jpa.edit(venta);
            return true;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public List<Ventas> getVentasEntreFechas(Date desde, Date hasta){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        EntityManager em = emf.createEntityManager();
        
        java.sql.Date desdeS = new java.sql.Date(desde.getTime());
        java.sql.Date hastaS = new java.sql.Date(hasta.getTime());
        
        Query q = em.createNativeQuery("SELECT * FROM ventas WHERE fecha BETWEEN ? AND ?",Ventas.class);
        q.setParameter(1,desdeS);
        q.setParameter(2,hastaS);
        
        return q.getResultList();
    }

    /************************* Estados venta *********************************************/
    /**
     * 
     * @param id
     * @return 
     */
    public Estadosventa getEstadoventa(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        EstadosventaJpaController jpa = new EstadosventaJpaController(emf);
        return jpa.findEstadosventa(id);
    }
    /************************* Estados venta *********************************************/
 
// ------------------------  Metodos Venta -----------------------------------------------//  
// ------------------------  Metodos DetalleVenta ----------------------------------------//

    /**
     * Estados venta
     * @param venta
     * @return
     */
    public List<Detalleventas> getDetalleventas(Ventas venta){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        DetalleventasJpaController jpa = new DetalleventasJpaController(emf);
        
        List<Detalleventas> miLista =  jpa.findDetalleventasEntities();
        List<Detalleventas> aux = new ArrayList<>();
        
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getVentas().equals(venta)){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
    /**
     * Crea un nuevo detalle de venta y si existe le agrega +1 a la cantidad 
     * @param venta
     * @param producto
     * @return 
     */
    public boolean nuevoDetalleVenta(Ventas venta, Productos producto){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        DetalleventasJpaController jpa = new DetalleventasJpaController(emf);
        VentasJpaController jpa2 = new VentasJpaController(emf);
        
        DetalleventasPK clave = new DetalleventasPK();
        Detalleventas detalle = new Detalleventas();
        
        
        clave.setFkVenta(venta.getIdVenta());
        clave.setFkProducto(producto.getIdProducto());
        
        detalle.setDetalleventasPK(clave);
        detalle.setProductos(producto);
        detalle.setVentas(venta);
        detalle.setCantidad(1);
        
        try {
            //Crea un nuevo detalle si no existe la combinacion de claves primarias
            jpa.create(detalle);
            System.out.println("Se crea detalle");
            //Modifica la venta para sumarle el neuvo producto al total
            try {
                venta = jpa2.findVentas(venta.getIdVenta());
                venta.setTotal(venta.getTotal()+producto.getPrecio());
                jpa2.edit(venta);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error al crear nuevo detalle venta "+e);
            System.out.println("Se trata de modificar");
            //Si no se creo porque ya existia la combinacion de claves primarias se trata de modificar para sumarle 1 a la cantidad del detalle
            try {
                detalle = jpa.findDetalleventas(clave);
                detalle.setCantidad(detalle.getCantidad()+1);
                jpa.edit(detalle);
                System.out.println("Se modifica detalle");
                //Modifica la venta para sumarle el nuevo producto al total
                try {
                    venta = jpa2.findVentas(venta.getIdVenta());
                    venta.setTotal(venta.getTotal()+producto.getPrecio());
                    jpa2.edit(venta);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            } catch (Exception e2) {
                System.out.println("Error al editar detalle venta "+e2);
                return false;
            }
        }
        
    }
           
    public boolean eliminarDetalleVenta(Ventas venta, Productos producto){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        DetalleventasJpaController jpa = new DetalleventasJpaController(emf);
        VentasJpaController jpa2 = new VentasJpaController(emf);
        
        DetalleventasPK clave = new DetalleventasPK();
        
        clave.setFkVenta(venta.getIdVenta());
        clave.setFkProducto(producto.getIdProducto());
        
        
        Detalleventas detalle = jpa.findDetalleventas(clave);
        
        if(detalle.getCantidad() > 1){
            detalle.setCantidad(detalle.getCantidad()-1);
            try {
                jpa.edit(detalle);
                venta = jpa2.findVentas(venta.getIdVenta());
                venta.setTotal(venta.getTotal()-producto.getPrecio());
                jpa2.edit(venta);
                return true;
            } catch (Exception ex) {
                Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }else{
            try {
                jpa.destroy(clave);
                venta = jpa2.findVentas(venta.getIdVenta());
                venta.setTotal(venta.getTotal()-producto.getPrecio());
                jpa2.edit(venta);
                return true;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (Exception ex) {
                Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        //return false;
        
    }
    
// ------------------------  Metodos DetalleVenta ----------------------------------------//
// ------------------------  Metodos Proveedores ----------------------------------------//
    /**
     * Busca todos los proveedores
     * @return 
     */
    public List<Proveedores> getTodosLosProveedores(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        ProveedoresJpaController jpa = new ProveedoresJpaController(emf);
        
        return jpa.findProveedoresEntities();
    }
    
    public boolean nuevoProveedor(Proveedores nuevo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        ProveedoresJpaController jpa = new ProveedoresJpaController(emf);
        
        try {
            jpa.create(nuevo);
            return true;
        } catch (Exception e) {
            System.out.println("Erro nuevo proveedor "+e);
            return false;
        }
        
    }
    
    public void eliminarProveedor(Integer id) throws NonexistentEntityException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        ProveedoresJpaController jpa = new ProveedoresJpaController(emf);
        
        jpa.destroy(id);
    }
// ------------------------  Metodos Proveedores ----------------------------------------//
// ------------------------  Metodos Usuarios ----------------------------------------//
    // -------------------------- Login -------------------//
    public Usuarios login(String nombre) {
        
        Usuarios miUsuario = getUsuarioPorNombre(nombre);
        
        return miUsuario;
      
    }
// -------------------------- Login ---------------------// 
    public List<Usuarios>  getTodosLosUsuarios(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        UsuariosJpaController jpa = new UsuariosJpaController(emf);
        
        return jpa.findUsuariosEntities();
    }
    
    /**
     * 
     * @param nuevo
     * @return 
     */
    public boolean nuevoUsuario(Usuarios nuevo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        UsuariosJpaController jpa = new UsuariosJpaController(emf);
        
        try {
            jpa.create(nuevo);
            return true;
        } catch (Exception e) {
            System.out.println("Error nuevo usuario "+e);
            return false;
        }
    }
    
    public void eliminarUsuario(Integer id) throws NonexistentEntityException, IllegalOrphanException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        UsuariosJpaController jpa = new UsuariosJpaController(emf);
        
        jpa.destroy(id);
    }
    
    public Usuarios getUsuarioPorNombre(String nombre){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        UsuariosJpaController jpa = new UsuariosJpaController(emf);
        List<Usuarios> miListaU = jpa.findUsuariosEntities();
        for(int i = 0; i < miListaU.size(); i++){
            if(miListaU.get(i).getNombreUsuario().equalsIgnoreCase(nombre)){
                return miListaU.get(i);
            }
        }
        Usuarios miUsu = new Usuarios();
        miUsu.setNombreUsuario("No");
        return miUsu;
    }
// ------------------------  Metodos Usuarios ----------------------------------------//
// ------------------------  Metodos Roles ----------------------------------------//
    
    public List<Roles> getTodosLosRoles(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        RolesJpaController jpa = new RolesJpaController(emf);
        
        return jpa.findRolesEntities();
    }
    
    public Roles buscarRol(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        RolesJpaController jpa = new RolesJpaController(emf);
        
        return jpa.findRoles(id);
    }
    
// ------------------------  Metodos Roles ----------------------------------------//
// ------------------------  Metodos Permisos ----------------------------------------//
    public Permisos getPermiso(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appPU");
        PermisosJpaController jpa = new PermisosJpaController(emf);
        
        return jpa.findPermisos(id);
    }
// ------------------------  Metodos Permisos ----------------------------------------//
}

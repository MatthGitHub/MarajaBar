/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.roles;

import app.ControlledScreen;
import app.ScreensController;
import entidades.Permisos;
import entidades.Roles;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import servicios.PermisoServ;
import servicios.RoleServ;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class RolesController implements Initializable,ControlledScreen {
    private ScreensController myController;
    
     /**
     * -----------Variables Roles
     */
    @FXML
    TableColumn colDescripcion;
    @FXML
    TableView tblRoles;
    
    private RoleServ serviciosR;
    private ObservableList<Roles> listaRoles;
    
    /**
     * -----------FIN Variables rol
     */
    /**
     *  ----------Variables modificar rol
     */
    @FXML
    public Label lblRol;
    @FXML
    public TableColumn colNombrePermisoMod,colDescripcionPermisoMod,colNombrePermisoAgregarMod,colDescripcionPermisoAgregarMod;
    @FXML
    public TableView tblPermisosMod,tblPermisosAgregarMod;
    
    
    public ObservableList <Permisos> listaPermisos;
    private PermisoServ serviciosP;
    
    /**
     *  ----------FIN Variables modificar rol
     */
    /**
     *  ----------Variables nuevo rol
     */
    @FXML
    public TextField txtDescripcion;
    
    private Roles rol;
    /**
     *  ----------FIN Variables nuevo rol
     */
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviciosR = new RoleServ();
        serviciosP = new PermisoServ();
        cargarTablaRoles();
        llenarTablaRoles();
    }    
    
    
 /**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- Pestaña Roles -----------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    public void cargarTablaRoles(){
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Roles, String>("Descripcion"));
    }
    
    public void llenarTablaRoles(){
        listaRoles = FXCollections.observableArrayList(serviciosR.traerTodos());
        tblRoles.setItems(listaRoles);
    }
    
    public void limpiarTablaRoles(){
        tblRoles.getItems().clear();
    }
    
    public void refrescarTablaRoles(){
        limpiarTablaRoles();
        llenarTablaRoles();
    }
    
    public void eliminarRol(){
        rol = (Roles) tblRoles.getSelectionModel().getSelectedItem();
        serviciosR.eliminarRol(rol.getIdRol());
        refrescarTablaRoles();
    }
/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- FIN Pestaña roles -------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
 /**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- Pestaña modificar rol -------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    
    /**
     * ------------ Tabla de permisos del rol a modificar ----------------------
     */
    public void cargarTablaPermisosMod(){
        colNombrePermisoMod.setCellValueFactory(new PropertyValueFactory<Permisos, String>("nombrePermiso"));
        colDescripcionPermisoMod.setCellValueFactory(new PropertyValueFactory<Permisos, String>("Descripcion"));
    }
    
    public void llenarTablaPermisosMod(){
        listaPermisos = FXCollections.observableArrayList(rol.getPermisosList());
        tblPermisosMod.setItems(listaPermisos);
    }
    
    public void limpiarTablaPermisosMod(){
        tblPermisosMod.getItems().clear();
    }
    
    public void refrescarTablaPermisosMod(){
        limpiarTablaPermisosMod();
        llenarTablaPermisosMod();
    }
    
    public void quitarPermisoMod(){
        Permisos permiso = new Permisos();
        permiso = (Permisos) tblPermisosMod.getSelectionModel().getSelectedItem();
        rol.getPermisosList().remove(permiso);
        guardarRolMod();
        refrescarTablaPermisosMod();
        refrescarTablaPermisosModAgregar();
    }
    /**
     *  ----------- FIN Tabla de permisos del rol a modificar ------------------
     */
    /**
     * ------------- Tabla de permisos disponibles para agregar al rol ----------------
     */
    public void cargarTablaPermisosAgregarMod(){
        colNombrePermisoAgregarMod.setCellValueFactory(new PropertyValueFactory<Permisos, String>("nombrePermiso"));
        colDescripcionPermisoAgregarMod.setCellValueFactory(new PropertyValueFactory<Permisos, String>("Descripcion"));
    }
    
    public void llenarTablaPermisosAgregarMod(){
        listaPermisos = FXCollections.observableArrayList(serviciosP.traerTodos());
        listaPermisos.removeAll(rol.getPermisosList());
        tblPermisosAgregarMod.setItems(listaPermisos);
    }
    
    public void limpiarTablaPermisosAgregarMod(){
        tblPermisosAgregarMod.getItems().clear();
    }
    
    public void refrescarTablaPermisosModAgregar(){
        limpiarTablaPermisosAgregarMod();
        llenarTablaPermisosAgregarMod();
    }
    
    /**
     * ------------- FIN Tabla de permisos disponibles para agregar al rol ----------------
     */
    /**
     * Este metodo es el que corre cuando se entra en la pestaña de modificar
     * y carga todo lo que corresponde de esta pestaña.
     */
   public void modificarRoles() {
        if(tblRoles.getSelectionModel().getSelectedIndex() != -1){
            rol = (Roles) tblRoles.getSelectionModel().getSelectedItem();
            lblRol.setText(rol.getDescripcion());
            cargarTablaPermisosMod();
            llenarTablaPermisosMod();
            cargarTablaPermisosAgregarMod();
            llenarTablaPermisosAgregarMod();
        }
    }

    public void limpiarModificar() {
        lblRol.setText("");
        limpiarTablaPermisosMod();
    }
    
    public void guardarRolMod(){
            if(serviciosR.editarRol(rol)){
                
            }
    }
    
    public void agregarPermisoMod(){
        if(tblRoles.getSelectionModel().getSelectedIndex() != -1){
             if(tblPermisosAgregarMod.getSelectionModel().getSelectedIndex() != -1){
                rol.getPermisosList().add((Permisos) tblPermisosAgregarMod.getSelectionModel().getSelectedItem());
                guardarRolMod();
                refrescarTablaPermisosMod();
                refrescarTablaPermisosModAgregar();
            }
         }
        
    }
/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- FIN Pestaña modificar rol ---------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
 /**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ------------------------------------ Pestaña nuevo rol -----------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    /**
     * Metodo que persiste el rol nuevo en la base de datos
     */
    public void guardarRol() {
        if (validar() == true) {
            rol = new Roles();
            rol.setDescripcion(txtDescripcion.getText());
            if(serviciosR.guardarRol(rol)){
                System.out.println("Rol guardado");
                limpiarFormNuevo();
                refrescarTablaRoles();
            }else{
                System.out.println("Error al guardar rol");
            }
        }
    }
    /**
     * Este metodo valida que los campos del formulario nuevo rol esten correctamente completados 
     * Retorna TRUE en caso de que esten correctos, caso contrario retorna FALSE
     * @return boolean
     */
    private boolean validar() {
            if(!txtDescripcion.getText().trim().isEmpty()){
                return true;
            }else{
                System.out.println("La descripcion esta vacia");
                return false;
            }
    }
    /**
     * Limpia todos los campos del formulario nuevo rol
     */
    public void limpiarFormNuevo(){
        txtDescripcion.setText("");

    }
/**
* -------------------------------------------------------------------------------------------------------------------------------------------------------------
* ------------------------------------FIN Pestaña nuevo rol ---------------------------------------------------------------------------------------------
* -------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
    
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    public void volver(){
        myController.setScreen("menuPpal");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.roles;

import app.ControlledScreen;
import app.ScreensController;
import entidades.Roles;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import servicios.RoleServ;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class RolesController implements Initializable,ControlledScreen {
    private ScreensController myController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     /**
     * -----------Variables Roles
     */
    @FXML
    TableColumn colDescripcion;
    @FXML
    TableView tblRoles;
    
    private RoleServ serviciosR;
    private ObservableList<Roles> listaRoles;
    private Roles rol;
    
    /**
     * -----------FIN Variables rol
     */
    /**
     *  ----------Variables modificar rol
     */
    @FXML
    public Label lblRol;
    public List <Permisos> listaPermisos;
    /**
     *  ----------FIN Variables modificar rol
     */
    /**
     *  ----------Variables nuevo rol
     */
    @FXML
    public TextField txtDescripcion;
    /**
     *  ----------FIN Variables nuevo rol
     */
   
    
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
   public void modificarRoles() {
        if(tblRoles.getSelectionModel().getSelectedIndex() != -1){
            rol = (Roles) tblRoles.getSelectionModel().getSelectedItem();
            lblRol.setText(rol.getDescripcion());
        }
    }

    public void limpiarModificar() {
        lblProducto.setText("");
        txtNombreMod.setText("");
        txtaDescripcionMod.setText("");
        txtPrecioMod.setText("");
    }
    
    public void guardarProductoMod(){
        if(validarFormMod()){
            producto.setNombreProducto(txtNombreMod.getText());
            producto.setDescripcion(txtaDescripcionMod.getText());
            producto.setPrecio(Integer.parseInt(txtPrecioMod.getText()));
            if(serviciosP.editarProducto(producto)){
                limpiarModificar();
            }
        }
    }
    
    private boolean validarFormMod(){
        if(!txtNombreMod.getText().trim().isEmpty()){
            if(!txtaDescripcionMod.getText().trim().isEmpty()){
                if(!txtPrecioMod.getText().trim().isEmpty()){
                    return true;
                }else{
                    System.out.println("El campo precio nueva esta vacio");
                    return false;
                }
            }else{
                System.out.println("La descripcion esta vacia");
                return false;
            }
        }else{
            System.out.println("El campo nombre esta vacio");
            return false;
        }
    }
/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- FIN Pestaña modificar producto ---------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
 /**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ------------------------------------ Pestaña nuevo producto -----------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    /**
     * Metodo que persiste el producto nuevo en la base de datos
     */
    public void guardarProducto() {
        if (validar() == true) {
            producto = new Productos();
            producto.setNombreProducto(txtNombre.getText());
            producto.setDescripcion(txtaDescripcion.getText());
            producto.setPrecio(Integer.parseInt(txtPrecio.getText()));
            if(serviciosP.guardarProducto(producto)){
                System.out.println("Producto guardado");
                limpiarFormNuevo();
                refrescarTablaProductos();
            }else{
                System.out.println("Error al guardar producto");
            }
        }
    }
    /**
     * Este metodo valida que los campos del formulario nuevo producto esten correctamente completados 
     * Retorna TRUE en caso de que esten correctos, caso contrario retorna FALSE
     * @return boolean
     */
    private boolean validar() {
        if(!txtNombre.getText().trim().isEmpty()){
            if(!txtaDescripcion.getText().trim().isEmpty()){
                if(!txtPrecio.getText().trim().isEmpty()){
                    return true;
                }else{
                    System.out.println("El campo precio nueva esta vacio");
                    return false;
                }
            }else{
                System.out.println("La descripcion esta vacia");
                return false;
            }
        }else{
            System.out.println("El campo nombre esta vacio");
            return false;
        }

    }
    /**
     * Limpia todos los campos del formulario nuevo producto
     */
    public void limpiarFormNuevo(){
        txtNombre.setText("");
        txtaDescripcion.setText("");
        txtPrecio.setText("");
    }
/**
* -------------------------------------------------------------------------------------------------------------------------------------------------------------
* ------------------------------------FIN Pestaña nuevo producto ---------------------------------------------------------------------------------------------
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

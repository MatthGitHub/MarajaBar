/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.proveedores;

import app.ControlledScreen;
import app.ScreensController;
import entidades.Proveedores;
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
import javafx.scene.control.cell.PropertyValueFactory;
import servicios.ProveedoreServ;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class ProveedoresController implements Initializable,ControlledScreen {
    private ScreensController myController;
    
    /**
     * -----------Variables proveedores
     */
    @FXML
    TableColumn colNombre,colCuit,colTelefono, colMail;
    @FXML
    TableView tblProveedores;
    
    private ProveedoreServ serviciosP;
    private ObservableList<Proveedores> listaProv;
    private Proveedores proveedor;
    
    /**
     * -----------FIN Variables proveedores
     */
    /**
     *  ----------Variables modificar proveedor
     */
    @FXML
    public Label lblProveedor;
    @FXML
    public TextField txtCuitMod,txtCorreoMod,txtTelefonoMod,txtNombreMod;
    /**
     *  ----------FIN Variables modificar proveedor
     */
    /**
     *  ----------Variables nuevo proveedor
     */
    @FXML
    public TextField txtCuit,txtCorreo,txtTelefono,txtNombre;
    /**
     *  ----------FIN Variables nuevo proveedor
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviciosP = new ProveedoreServ();
    }    
    
 /**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- Pestaña proveedores -----------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    public void cargarTablaProveedores(){
        colNombre.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("nombreProveedor"));
        colCuit.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("cuit"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("telefono"));
        colMail.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("email"));
    }
    
    public void llenarTablaProveedores(){
        listaProv = FXCollections.observableArrayList(serviciosP.traerTodos());
        tblProveedores.setItems(listaProv);
    }
    
    public void limpiarTablaProveedores(){
        tblProveedores.getItems().clear();
    }
    
    public void refrescarTablaProveedores(){
        limpiarTablaProveedores();
        llenarTablaProveedores();
    }
    
    public void eliminarProveedor(){
        proveedor = (Proveedores) tblProveedores.getSelectionModel().getSelectedItem();
        serviciosP.eliminarProveedor(proveedor.getIdProveedor());
        refrescarTablaProveedores();
    }
/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- FIN Pestaña proveedores -------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
 /**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- Pestaña modificar proveedores -------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
   public void modificarProveedor() {
        if(tblProveedores.getSelectionModel().getSelectedIndex() != -1){
            proveedor = (Proveedores) tblProveedores.getSelectionModel().getSelectedItem();
            lblProveedor.setText(proveedor.getNombreProveedor());
            txtNombreMod.setText(proveedor.getNombreProveedor());
            txtCorreoMod.setText(proveedor.getEmail());
            txtCuitMod.setText(proveedor.getCuit());
            txtTelefonoMod.setText(proveedor.getTelefono());
        }
    }

    public void limpiarModificar() {
        lblProveedor.setText("");
        txtNombreMod.setText("");
        txtCorreoMod.setText("");
        txtCuitMod.setText("");
        txtTelefonoMod.setText("");
    }
    
    public void guardarProveedorMod(){
        if(validarFormMod()){
            proveedor.setNombreProveedor(txtNombreMod.getText());
            proveedor.setCuit(txtCuitMod.getText());
            proveedor.setEmail(txtCorreoMod.getText());
            proveedor.setTelefono(txtTelefonoMod.getText());
            if(serviciosP.editarProveedor(proveedor)){
                limpiarModificar();
            }
        }
    }
    
    private boolean validarFormMod(){
        if(!txtNombreMod.getText().trim().isEmpty()){
            if(!txtCuitMod.getText().trim().isEmpty()){
                if(!txtCorreoMod.getText().trim().isEmpty()){
                    if(!txtTelefonoMod.getText().trim().isEmpty()){
                        return true;
                    }else{
                        System.out.println("El campo telefono esta vacio");
                        return false;
                    }
                }else{
                    System.out.println("El campo correo nueva esta vacio");
                    return false;
                }
            }else{
                System.out.println("La cuit actual no coincide");
                return false;
            }
        }else{
            System.out.println("El campo nombre esta vacio");
            return false;
        }
    }
/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- FIN Pestaña modificar proveedores ---------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
 /**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ------------------------------------ Pestaña nuevo proveedor -----------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    /**
     * Metodo que persiste el proveedor nuevo en la base de datos
     */
    public void guardarProveedor() {
        if (validar() == true) {
            proveedor.setNombreProveedor(txtNombre.getText());
            proveedor.setCuit(txtCuit.getText());
            proveedor.setEmail(txtCorreo.getText());
            proveedor.setTelefono(txtTelefono.getText());
            if(serviciosP.guardarProveedor(proveedor)){
                System.out.println("Proveedor guardado");
                limpiarFormNuevo();
                refrescarTablaProveedores();
            }else{
                System.out.println("Error al guardar proveedor");
            }
        }
    }
    /**
     * Este metodo valida que los campos del formulario nuevo proveedor esten correctamente completados 
     * Retorna TRUE en caso de que esten correctos, caso contrario retorna FALSE
     * @return boolean
     */
    private boolean validar() {
        if(!txtNombre.getText().trim().isEmpty()){
            if(!txtCuit.getText().trim().isEmpty()){
                if(!txtCorreo.getText().trim().isEmpty()){
                    if(!txtTelefono.getText().trim().isEmpty()){
                        return true;
                    }else{
                        System.out.println("El campo telefono esta vacio");
                        return false;
                    }
                }else{
                    System.out.println("El campo correo nueva esta vacio");
                    return false;
                }
            }else{
                System.out.println("La cuit actual no coincide");
                return false;
            }
        }else{
            System.out.println("El campo nombre esta vacio");
            return false;
        }

    }
    /**
     * Limpia todos los campos del formulario nuevo proveedor
     */
    public void limpiarFormNuevo(){
        txtNombre.setText("");
        txtCorreo.setText("");
        txtCuit.setText("");
        txtTelefono.setText("");
    }
/**
* -------------------------------------------------------------------------------------------------------------------------------------------------------------
* ------------------------------------FIN Pestaña nuevo proveedor ---------------------------------------------------------------------------------------------
* -------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
    
    public void volver(){
        myController.setScreen("menuPpal");
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
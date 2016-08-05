/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.usuarios;

import app.ControlledScreen;
import app.ScreensController;
import entidades.Productos;
import entidades.Roles;
import entidades.Usuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import servicios.RoleServ;
import servicios.UsuarioServ;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class UsuariosController implements Initializable, ControlledScreen {

    private ScreensController myController;
    

    /**
     * Variables pestaña nuevo usuario --------------------------------------------------------------------
     */
    
    private RoleServ serviciosR;
    private ObservableList<Roles> listaRol;
    private Usuarios user;
    
    @FXML
    ComboBox cmbxRoles;
    @FXML
    TextField txtUsuario;
    @FXML
    PasswordField pswrClave, pswrConfirmar;
    /**
     * Variables pestaña Usuarios -------------------------------------------------------------------------
     */
    private static ObservableList<Usuarios> listaUsu;
    private UsuarioServ serviciosU;
    
    @FXML
    TableView tblUsuarios;
    @FXML
    TableColumn colNombre, colRol;

    /**
     * Variables pestaña modificar usuario ----------------------------------------------------------------
     */
    @FXML
    Tab tabModificarUsuario;
    @FXML
    PasswordField pswrActualMod;
    @FXML
    PasswordField pswrClaveMod;
    @FXML
    PasswordField pswrConfirmarMod;
    @FXML
    ComboBox cmbxRolesMod;
    @FXML
    Label lblUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviciosU = new UsuarioServ();
        serviciosR = new RoleServ();
        listaRol = FXCollections.observableArrayList(serviciosR.traerTodos());
        //Carga el combo box de Roles
        cmbxRoles.setItems(listaRol);
        cmbxRolesMod.setItems(listaRol);
        cargarTablaUsuarios();
        llenarTablaUsuarios();
    }

    public void volver() {
        myController.setScreen("menuPpal");
    }

/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- Pestaña usuarios --------------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    /**
     * Carga la tabla con los tipos de datos que va a recibir
     */
    public void cargarTablaUsuarios() {
        //No tengo idea que hace esto lo saque de internet. Pero los nombres son de la clase Usuarios.
        colNombre.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("nombreUsuario"));
        colRol.setCellValueFactory(new Callback<CellDataFeatures<Usuarios, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(CellDataFeatures<Usuarios, String> param) {
                return new SimpleStringProperty(param.getValue().getFkRol().getDescripcion());
            }
        });

    }
    /**
     * Llena la tabla con los usuarios que hay en la base de datos
     */
    public void llenarTablaUsuarios(){
        listaUsu = FXCollections.observableArrayList(serviciosU.traerTodos());
        tblUsuarios.setItems(listaUsu);
    }
    
    public void limpiarTablaUsuarios(){
        tblUsuarios.getItems().clear();
    }
    
    public void refrescarTablaUsuarios(){
        limpiarTablaUsuarios();
        llenarTablaUsuarios();
    }
    
    public void eliminarUsuario(){
        user = (Usuarios) tblUsuarios.getSelectionModel().getSelectedItem();
        serviciosU.eliminarUsuario(user.getIdUsuario());
        refrescarTablaUsuarios();
    }
    
/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- FIN Pestaña usuarios -----------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ----------------------- Pestaña Modificar usuario ----------------------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    public void modificarUsuario() {
        if(tblUsuarios.getSelectionModel().getSelectedIndex() != -1){
            user = (Usuarios) tblUsuarios.getSelectionModel().getSelectedItem();
            lblUsuario.setText(user.getNombreUsuario());
            cmbxRolesMod.getSelectionModel().select(user.getFkRol());
        }
    }

    public void limpiarModificar() {
        lblUsuario.setText("");
        pswrActualMod.setText("");
        pswrClaveMod.setText("");
        pswrConfirmarMod.setText("");
        cmbxRolesMod.getSelectionModel().clearSelection();
    }
    
    public void guardarUsuarioMod(){
        //user = new Usuarios();
        if(validarFormMod()){
            user.setClave(pswrClaveMod.getText().trim());
            user.setFkRol((Roles) cmbxRolesMod.getSelectionModel().getSelectedItem());
            user.setNombreUsuario(lblUsuario.getText());
            if(serviciosU.editarUsuario(user)){
                limpiarModificar();
                refrescarTablaUsuarios();
            }
        }
    }
    
    private boolean validarFormMod(){
        if(!pswrActualMod.getText().isEmpty()){
            if(pswrActualMod.getText().trim().equals(user.getClave())){
                if(!pswrClaveMod.getText().trim().isEmpty()){
                    if(!pswrConfirmarMod.getText().trim().isEmpty()){
                        if(pswrClaveMod.getText().trim().equals(pswrConfirmarMod.getText().trim())){
                            return true;
                        }else{
                            System.out.println("La clave nueva y la confirmacion no coinciden");
                            return false;
                        }
                    }else{
                        System.out.println("El campo confirmacion esta vacio");
                        return false;
                    }
                }else{
                    System.out.println("El campo clave nueva esta vacio");
                    return false;
                }
            }else{
                System.out.println("La clave actual no coincide");
                return false;
            }
        }else{
            System.out.println("El campo clave actual esta vacio");
            return false;
        }
    }
    
/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ----------------------- FIN Pestaña Modificar usuario ----------------------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
/**
 * --------------------------------------------------------------------------------------------------------------------------------------------------
 * ------------------------------------ Pestaña nuevo usuario ---------------------------------------------------------------------------------------
 * --------------------------------------------------------------------------------------------------------------------------------------------------
 */
    /**
     * Metodo que persiste el usuario nuevo en la base de datos
     */
    public void guardarUsuario() {
        if (validar() == true) {
            user = new Usuarios();
            user.setNombreUsuario(txtUsuario.getText().trim());
            user.setClave(pswrClave.getText().trim());
            user.setFkRol((Roles) cmbxRoles.getSelectionModel().getSelectedItem());
            if(serviciosU.guardarUsuario(user)){
                System.out.println("Usuario guardado");
                limpiarFormNuevo();
                refrescarTablaUsuarios();
            }else{
                System.out.println("Error al guardar usuario");
            }
        }
    }
    /**
     * Este metodo valida que los campos del formulario nuevo usuario esten correctamente completados 
     * Retorna TRUE en caso de que esten correctos, caso contrario retorna FALSE
     * @return boolean
     */
    private boolean validar() {
        if (!txtUsuario.getText().trim().isEmpty()) {
            if (!pswrClave.getText().trim().isEmpty()) {
                if (!pswrConfirmar.getText().trim().isEmpty()) {
                    if (cmbxRoles.getSelectionModel().getSelectedIndex() > 0) {
                        if(pswrClave.getText().trim().equals(pswrConfirmar.getText().trim())) {
                            return true;
                        } else {
                            System.out.println("La confirmacion no coincide");
                            return false;
                        }
                    } else {
                        System.out.println("No hubo seleccion en el combobox de roles");
                        return false;
                    }
                } else {
                    System.out.println("El campo confirmacion esta vacio");
                    return false;
                }
            } else {
                System.out.println("El campo clave esta vacio");
                return false;
            }
        } else {
            System.out.println("El campo nombre de usuario esta vacio");
            return false;
        }

    }
    /**
     * Limpia todos los campos del formulario nuevo usuario
     */
    public void limpiarFormNuevo(){
        txtUsuario.setText("");
        pswrClave.setText("");
        pswrConfirmar.setText("");
        cmbxRoles.getSelectionModel().clearSelection();
    }
/**
* --------------------------------------------------------------------------------------------------------------------------------------------------
* ------------------------------------FIN Pestaña nuevo usuario ---------------------------------------------------------------------------------------
* --------------------------------------------------------------------------------------------------------------------------------------------------
*/
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

}

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
    private UsuarioServ serviciosU;
    private RoleServ serviciosR;
    private ObservableList<Roles> listaRol;
    private ObservableList<Usuarios> listaUsu;

    /**
     * Variables pestaña nuevo usuario
     */
    @FXML
    ComboBox cmbxRoles;
    @FXML
    TextField txtUsuario;
    @FXML
    PasswordField pswrClave, pswrConfirmar;
    /**
     * Variables pestaña Usuarios
     */
    @FXML
    TableView tblUsuarios;
    @FXML
    TableColumn colNombre, colRol;

    private Usuarios user;

    /**
     * Variables pestaña modificar usuario
     */
    @FXML
    Tab tabModificarUsuario;

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
        cargarTablaUsuarios();
    }

    public void volver() {
        myController.setScreen("menuPpal");
    }

    /**
     * ------------------ Pestaña usuarios
     * ------------------------------------------------------------------------
     */
    /**
     * Carga los usuarios cargados en la base de datos en la tabla view usuarios
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

        listaUsu = FXCollections.observableArrayList(serviciosU.traerTodos());
        tblUsuarios.setItems(listaUsu);
    }

    /**
     * ------------------ Pestaña Modificar usuario
     * ---------------------------------------------------------------
     */
    public void modificarUsuario() {

    }

    public void limpiarModificar() {

    }

    /**
     * -------------------- Pestaña nuevo usuario
     * -----------------------------------------------------------------
     */
    public void guardarUsuario() {
        if (validar() == true) {
            user = new Usuarios();
            user.setNombreUsuario(txtUsuario.getText().trim());
            user.setClave(pswrClave.getText().trim());
            user.setFkRol((Roles) cmbxRoles.getSelectionModel().getSelectedItem());
            if(serviciosU.guardarUsuario(user)){
                System.out.println("Usuario guardado");
            }else{
                System.out.println("Error al cargar usuario");
            }
        }
    }

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

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

}

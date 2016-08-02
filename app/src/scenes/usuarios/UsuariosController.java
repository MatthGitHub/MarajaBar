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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ObservableList <Roles> listaRol;
    private ObservableList <Usuarios> listaUsu;
    
    /**
     * Variables pestaña nuevo usuario
     */
    @FXML ComboBox cmbxRoles;
    
    /**
     * Variables pestaña Usuarios
     */
    @FXML TableView tblUsuarios;
    @FXML TableColumn colNombre,colRol;
    
    
    /**
     * Variables pestaña modificar usuario 
     */
    @FXML Tab tabModificarUsuario;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviciosR = new RoleServ();
        listaRol = FXCollections.observableArrayList(serviciosR.traerTodos());
        //Carga el combo box de Roles
        cmbxRoles.setItems(listaRol);
        cargarTablaUsuarios();
    }    
    
    public void volver(){
        myController.setScreen("menuPpal");
    }
    
    /**
     * ------------------ Pestaña usuarios ------------------------------------------------------------------------
     */
    /**
     * Carga los usuarios cargados en la base de datos en la tabla view usuarios
     */
    private void cargarTablaUsuarios() {
        //Instancion servicios de usuarios
        serviciosU = new UsuarioServ();
        //No tengo idea que hace esto lo saque de internet. Pero los nombres son de la clase Usuarios.
        colNombre.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombreUsuario"));
        colRol.setCellValueFactory(new PropertyValueFactory<Roles, String>("descripcion"));

        listaUsu = FXCollections.observableArrayList(serviciosU.traerTodos());
        tblUsuarios.setItems(listaUsu);
    }
    
    /**
     *------------------ Pestaña Modificar usuario --------------------------------------------------------------- 
     */
    
    public void modificarUsuario(){
        
    }
    
    public void limpiarModificar(){
        
    }
    
    
    
    
    
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    

}

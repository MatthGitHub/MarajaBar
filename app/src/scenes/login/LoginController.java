/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML public Button btnSalir;
    @FXML public Button btnIngresar;
    
    Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void salir(){
        Platform.exit();
    }
    
    public void ingresar(){
        stage = (Stage) btnIngresar.getScene().getWindow();
        Parent root = stage.getScene().getRoot();
        
        
        try {
            root = FXMLLoader.load(getClass().getResource("/scenes/menuPrincipal/MenuPrincipal.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stage.getScene().setRoot(root);
        
        stage.setFullScreen(true);
    }
    
    
}

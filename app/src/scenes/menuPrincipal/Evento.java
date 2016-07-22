/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.menuPrincipal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import scenes.login.LoginController;

/**
 *
 * @author Administrador
 */
public abstract class Evento extends ActionEvent{
    private Stage stage;
    public void abrirMesa(){
        stage = new Stage();
        Parent root = null;
        
        
        try {
            root = FXMLLoader.load(getClass().getResource("/scenes/menuPrincipal/Mesas.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stage.getScene().setRoot(root);
    }
}

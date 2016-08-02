/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.usuarios;

import app.ControlledScreen;
import app.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class UsuariosController implements Initializable, ControlledScreen {
    private ScreensController myController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void volver(){
        myController.setScreen("menuPpal");
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
}

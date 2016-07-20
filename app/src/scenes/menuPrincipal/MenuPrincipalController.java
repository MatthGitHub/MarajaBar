/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.menuPrincipal;

import com.sun.javafx.scene.SceneHelper;
import entidades.Mesa;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import servicios.MesaServ;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class MenuPrincipalController implements Initializable {
    
    @FXML public MenuItem btnSalir;
    @FXML public GridPane gpane;
    
    
    private MesaServ serviciosMesa;
    private List <Mesa> listaMesas;
    private List <Button> mesas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                
        mesas = new ArrayList<>();
        serviciosMesa = new MesaServ();
        listaMesas = serviciosMesa.traerTodos();
        for(int i = 0; i < listaMesas.size(); i++){
            int j =0;
            
            Button buton = new Button();
            buton.setMinSize(200,100);
            buton.setBlendMode(BlendMode.GREEN);
            buton.setText(listaMesas.get(i).getIdMesa().toString());
            gpane.add(buton,i,j);
            j++;
        }
    }    
    
    public void salir() throws IOException{
        Platform.exit();
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.menuPrincipal;

import app.ControlledScreen;
import app.ScreensController;
import entidades.Mesa;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import servicios.MesaServ;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class MenuPrincipalController implements Initializable, ControlledScreen {

    @FXML
    public MenuItem btnSalir;
    @FXML
    public FlowPane flwPane;

    private MesaServ serviciosMesa;
    private List<Mesa> listaMesas;
    private List<Button> mesas;
    private Evento evento;
    
    private static double xOffset = 0;
    private static double yOffset = 0;
    
    private ScreensController myController;
    private ScreensController mesaScreen;
    private String mesa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        mesaScreen = new ScreensController();
        
        flwPane.setHgap(10);
        mesas = new ArrayList<>();
        serviciosMesa = new MesaServ();
        listaMesas = serviciosMesa.traerTodos();
       /*
        for (int i = 0; i < listaMesas.size(); i++) {
            if(i == 0){
                mesa = "zero";
            }
            if(i == 1){
                mesa = "uno";
            }
            
            
            Button buton = new Button();
            buton.setMinSize(100, 50);
            buton.setMaxSize(200, 100);
            buton.setBlendMode(BlendMode.GREEN);
            buton.setText(listaMesas.get(i).getIdMesa().toString());
            buton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    myController.setScreen("mesas");
                    
                }
            });
            flwPane.getChildren().add(buton);
        }
        */
    }

    public void salir() throws IOException {
        Platform.exit();
    }
    
    public void usuarios(){
        myController.setScreen("usuarios");
    }
    
    public void proveedores(){
        myController.setScreen("proveedores");
    }
    
    public void productos(){
        myController.setScreen("productos");
    }
    
    public void roles(){
        myController.setScreen("roles");
    }
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}

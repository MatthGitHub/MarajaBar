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
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.BlendMode;
import static javafx.scene.input.KeyCode.X;
import static javafx.scene.input.KeyCode.Y;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Screen;
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
    @FXML
    public MenuBar menuVentana;
    @FXML
    public Button btnIconizar;
    @FXML
    public Button btnMaximizar;

    private MesaServ serviciosMesa;
    private List<Mesa> listaMesas;
    private List<Button> mesas;
    private Evento evento;
    
    private static double xOffset = 0;
    private static double yOffset = 0;
    
    private ScreensController myController;
    private ScreensController mesaScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mesaScreen = new ScreensController();

        
        menuVentana.setBlendMode(BlendMode.GREEN);
        flwPane.setHgap(10);
        mesas = new ArrayList<>();
        serviciosMesa = new MesaServ();
        listaMesas = serviciosMesa.traerTodos();
        for (int i = 0; i < listaMesas.size(); i++) {
            mesaScreen.loadScreen("mesa"+i, "/scenes/mesas/Mesas.fxml");
            
            
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
    }

    public void salir() throws IOException {
        Platform.exit();
    }
    
    public void presionarVentana(){
        menuVentana.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = myController.getScene().getWindow().getX() - event.getScreenX();
                yOffset = myController.getScene().getWindow().getY() - event.getScreenY();
            }
        });
    }
    
    public void moverVentana(){
        menuVentana.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                myController.getScene().getWindow().setX(event.getScreenX() + xOffset);
                myController.getScene().getWindow().setY(event.getScreenY() + yOffset);
            }
        });
    }
    
    public void maximizarVentana(){
        Stage primaryStage = (Stage) btnMaximizar.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setMaximized(true);
        primaryStage.show();
        
        /*
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        */
    }
    
    public void iconizarVentana(){
        Stage stage = (Stage) btnIconizar.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void usuarios(){
        myController.setScreen("usuarios");
    }
    
    public void proveedores(){
        myController.setScreen("proveedores");
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}

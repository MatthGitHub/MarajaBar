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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scenes.login.LoginController;
import servicios.MesaServ;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class MenuPrincipalController implements Initializable {

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
    private int c;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //c es mi bandera para saber si esta la ventana maximizada
        c = 0;
        menuVentana.setBlendMode(BlendMode.GREEN);
        flwPane.setHgap(10);
        mesas = new ArrayList<>();
        serviciosMesa = new MesaServ();
        listaMesas = serviciosMesa.traerTodos();
        for (int i = 0; i < listaMesas.size(); i++) {
            int j = 0;

            Button buton = new Button();
            buton.setMinSize(200, 100);
            buton.setBlendMode(BlendMode.GREEN);
            buton.setText(listaMesas.get(i).getIdMesa().toString());
            buton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Stage stageM = new Stage();
                    stageM.initStyle(StageStyle.UNDECORATED);
                    Parent root = null;
                    
                    try {
                        root = FXMLLoader.load(getClass().getResource("/scenes/mesas/Mesas.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stageM.setScene(scene);
                    
                    stageM.show();
                }
            });
            flwPane.getChildren().add(buton);
            j++;
        }
    }

    public void salir() throws IOException {
        Platform.exit();
    }
    
    public void moverVentana(){
        
    }
    
    public void maximizarVentana(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Stage stage = (Stage) btnMaximizar.getScene().getWindow();
        if(c == 0){
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            c = 1;
        }else{
            stage.setX(800);
            stage.setY(600);
            stage.setWidth(bounds.getWidth()/2);
            stage.setHeight(bounds.getHeight()/2);
            c = 0;
        }
        
        
    }
    
    public void iconizarVentana(){
        Stage stage = (Stage) btnIconizar.getScene().getWindow();
        stage.setIconified(true);
    }
}

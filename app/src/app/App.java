/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import entidades.Usuarios;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author Matth
 */
public class App extends Application {

    private Usuarios usuarioActual;
    private static Stage stage;
    /**
     * Guardo estaticamente un string con la ruta del FXML
     */
    public static String LoginFXML = "/scenes/login/Login.fxml";
    public static String MenuPpalFXML = "/scenes/menuPrincipal/MenuPrincipal.fxml";
    public static String MesasFXML = "/scenes/mesas/Mesas.fxml";
    public static String UsuariosFXML = "/scenes/usuarios/Usuarios.fxml";
    public static String ProveedoresFXML = "/scenes/proveedores/Proveedores.fxml";
    public static String ProductosFXML = "/scenes/productos/Productos.fxml";
    public static String RolesFXML = "/scenes/roles/Roles.fxml";

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setResizable(false);
        stage.setTitle("Maraja Bar");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                // consume event
                event.consume();

                // show close dialog
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmar");
                alert.setHeaderText("Seguro desea salir?");
                alert.initOwner(stage);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
        });

        //mainCointainer posee un hash con los nodos y su nombre como referencia
        ScreensController mainContainer = new ScreensController();

        //Cargo las pantallas en el hash
        mainContainer.loadScreen("login", App.LoginFXML);
        mainContainer.loadScreen("menuPpal", App.MenuPpalFXML);
        mainContainer.loadScreen("mesas", App.MesasFXML);
        mainContainer.loadScreen("usuarios", UsuariosFXML);
        mainContainer.loadScreen("proveedores", ProveedoresFXML);
        mainContainer.loadScreen("productos", ProductosFXML);
        mainContainer.loadScreen("roles", RolesFXML);
        mainContainer.setScreen("login");

        Scene scene = new Scene(mainContainer);

        //xx.getChildren().remove(test);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void habilitarResize(){
        stage.setResizable(true);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import entidades.Usuarios;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.eclipse.persistence.internal.oxm.mappings.Login;

/**
 *
 * @author Matth
 */
public class App extends Application {
    private static App instance;
    private Usuarios usuarioActual;
    
    public App() {
        instance = this;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(true);
        stage.getIcons().add(new Image("/resources/images/Icono.png"));
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/login/Login.fxml"));
        
        Scene scene = new Scene(root);
    
        stage.setScene(scene);
        stage.show();
    }

    public static App getInstance() {
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void gotoLogin(){
        
    }
    
}

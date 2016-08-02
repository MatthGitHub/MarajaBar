/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.mesas;

import app.ControlledScreen;
import app.ScreensController;
import entidades.Productos;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import servicios.ProductoServ;

/**
 * FXML Controller class
 *
 * @author Administrador
 */
public class MesasController implements Initializable, ControlledScreen {

    @FXML
    public TableView<Productos> tvProductos;
    @FXML
    public TableColumn colNombre, colPrecio, colId;
    @FXML
    public TableView<Productos> tvConsumiendo;
    @FXML
    public TableColumn colNombreC, colPrecioC, colTotal;
    @FXML
    public Button btnSalir;
    @FXML
    public Label lblTotal;

    private ProductoServ serviciosP;
    private ObservableList<Productos> listaProd;
    private ObservableList<Productos> listaConsu;
    private List<Productos> preList;
    private ScreensController myController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        preList = new ArrayList<>();
        cargarTablaProductos();
    }

    private void cargarTablaProductos() {
        //Instancion servicios de usuarios
        serviciosP = new ProductoServ();
        //No tengo idea que hace esto lo saque de internet. Pero los nombres son de la clase Usuarios.
        colId.setCellValueFactory(new PropertyValueFactory<Productos, String>("idProducto"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombreProducto"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Productos, String>("Precio"));

        listaProd = FXCollections.observableArrayList(serviciosP.traerTodos());
        tvProductos.setItems(listaProd);
    }

    public void agregar() {

        if (tvProductos.getSelectionModel().getSelectedIndex() > 0) {
            colNombreC.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombreProducto"));
            colPrecioC.setCellValueFactory(new PropertyValueFactory<Productos, String>("Precio"));
            colTotal.setCellValueFactory(new PropertyValueFactory<>("1"));

            preList.add(tvProductos.getSelectionModel().getSelectedItem());
            listaConsu = FXCollections.observableArrayList(preList);
            tvConsumiendo.setItems(listaConsu);
        }
    }

    public void salir() {
        myController.setScreen("menuPpal");
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController =  screenParent;
    }
}

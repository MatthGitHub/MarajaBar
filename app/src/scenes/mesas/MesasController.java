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
    public TableView<Productos> tvMesa;
    @FXML
    public TableColumn colNombreC, colPrecioC, colTotal;
    @FXML
    public Button btnSalir;
    @FXML
    public Label lblTotal;

    private ProductoServ serviciosP;
    private ObservableList<Productos> listaProd;
    private ObservableList<Productos> preList;
    private ScreensController myController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        preList = FXCollections.observableArrayList();
        cargarTablaProductos();
        cargarTablaConsumido();
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

    private void cargarTablaConsumido() {
        colNombreC.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombreProducto"));
        colPrecioC.setCellValueFactory(new PropertyValueFactory<Productos, String>("Precio"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("1"));
    }

    public void agregar() {
        if(tvProductos.getSelectionModel().getSelectedIndex() != -1){
            preList.add(tvProductos.getSelectionModel().getSelectedItem());
            tvMesa.setItems(preList);
        }else{
            
        }
        
        
    }
    

    public void salir() {
        myController.setScreen("menuPpal");
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}

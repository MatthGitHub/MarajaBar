/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes.productos;

import app.ControlledScreen;
import app.ScreensController;
import entidades.Productos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import servicios.ProductoServ;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class ProductosController implements Initializable,ControlledScreen {
    private ScreensController myController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviciosP = new ProductoServ();
        cargarTablaProductos();
        llenarTablaProductos();
    } 
    
    /**
     * -----------Variables Productos
     */
    @FXML
    TableColumn colNombre,colDescripcion,colPrecio;
    @FXML
    TableView tblProductos;
    
    private ProductoServ serviciosP;
    private ObservableList<Productos> listaProd;
    private Productos producto;
    
    /**
     * -----------FIN Variables productos
     */
    /**
     *  ----------Variables modificar productos
     */
    @FXML
    public Label lblProducto;
    @FXML
    public TextField txtNombreMod,txtPrecioMod;
    @FXML
    public TextArea txtaDescripcionMod;
    /**
     *  ----------FIN Variables modificar producto
     */
    /**
     *  ----------Variables nuevo producto
     */
    @FXML
    public TextField txtPrecio,txtNombre;
    @FXML
    public TextArea txtaDescripcion;
    /**
     *  ----------FIN Variables nuevo producto
     */
       
    
 /**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- Pestaña Productos -----------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    public void cargarTablaProductos(){
        colNombre.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombreProducto"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Productos, String>("Descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Productos, String>("precio"));
    }
    
    public void llenarTablaProductos(){
        listaProd = FXCollections.observableArrayList(serviciosP.traerTodos());
        tblProductos.setItems(listaProd);
    }
    
    public void limpiarTablaProductos(){
        tblProductos.getItems().clear();
    }
    
    public void refrescarTablaProductos(){
        limpiarTablaProductos();
        llenarTablaProductos();
    }
    
    public void eliminarProducto(){
        producto = (Productos) tblProductos.getSelectionModel().getSelectedItem();
        serviciosP.eliminarProducto(producto.getIdProducto());
        refrescarTablaProductos();
    }
/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- FIN Pestaña productos -------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
 /**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- Pestaña modificar productos -------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
   public void modificarProductos() {
        if(tblProductos.getSelectionModel().getSelectedIndex() != -1){
            producto = (Productos) tblProductos.getSelectionModel().getSelectedItem();
            lblProducto.setText(producto.getNombreProducto());
            txtNombreMod.setText(producto.getNombreProducto());
            txtaDescripcionMod.setText(producto.getDescripcion());
            txtPrecioMod.setText(producto.getPrecio().toString());
        }
    }

    public void limpiarModificar() {
        lblProducto.setText("");
        txtNombreMod.setText("");
        txtaDescripcionMod.setText("");
        txtPrecioMod.setText("");
    }
    
    public void guardarProductoMod(){
        if(validarFormMod()){
            producto.setNombreProducto(txtNombreMod.getText());
            producto.setDescripcion(txtaDescripcionMod.getText());
            producto.setPrecio(Integer.parseInt(txtPrecioMod.getText()));
            if(serviciosP.editarProducto(producto)){
                limpiarModificar();
                refrescarTablaProductos();
            }
        }
    }
    
    private boolean validarFormMod(){
        if(!txtNombreMod.getText().trim().isEmpty()){
            if(!txtaDescripcionMod.getText().trim().isEmpty()){
                if(!txtPrecioMod.getText().trim().isEmpty()){
                    return true;
                }else{
                    System.out.println("El campo precio nueva esta vacio");
                    return false;
                }
            }else{
                System.out.println("La descripcion esta vacia");
                return false;
            }
        }else{
            System.out.println("El campo nombre esta vacio");
            return false;
        }
    }
/**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------- FIN Pestaña modificar producto ---------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
 /**
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * ------------------------------------ Pestaña nuevo producto -----------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
    /**
     * Metodo que persiste el producto nuevo en la base de datos
     */
    public void guardarProducto() {
        if (validar() == true) {
            producto = new Productos();
            producto.setNombreProducto(txtNombre.getText());
            producto.setDescripcion(txtaDescripcion.getText());
            producto.setPrecio(Integer.parseInt(txtPrecio.getText()));
            if(serviciosP.guardarProducto(producto)){
                System.out.println("Producto guardado");
                limpiarFormNuevo();
                refrescarTablaProductos();
            }else{
                System.out.println("Error al guardar producto");
            }
        }
    }
    /**
     * Este metodo valida que los campos del formulario nuevo producto esten correctamente completados 
     * Retorna TRUE en caso de que esten correctos, caso contrario retorna FALSE
     * @return boolean
     */
    private boolean validar() {
        if(!txtNombre.getText().trim().isEmpty()){
            if(!txtaDescripcion.getText().trim().isEmpty()){
                if(!txtPrecio.getText().trim().isEmpty()){
                    return true;
                }else{
                    System.out.println("El campo precio nueva esta vacio");
                    return false;
                }
            }else{
                System.out.println("La descripcion esta vacia");
                return false;
            }
        }else{
            System.out.println("El campo nombre esta vacio");
            return false;
        }

    }
    /**
     * Limpia todos los campos del formulario nuevo producto
     */
    public void limpiarFormNuevo(){
        txtNombre.setText("");
        txtaDescripcion.setText("");
        txtPrecio.setText("");
    }
/**
* -------------------------------------------------------------------------------------------------------------------------------------------------------------
* ------------------------------------FIN Pestaña nuevo producto ---------------------------------------------------------------------------------------------
* -------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
    

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    public void volver(){
        myController.setScreen("menuPpal");
    }
    
}

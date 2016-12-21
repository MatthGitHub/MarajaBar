/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.main;

import gui.comanda.ComandaFrame;
import gui.login.LoginView;
import gui.menuPrincipal.MenuPrincipal;
import gui.mesas.MesasNuevaDialog;
import gui.mesas.MesasView;
import gui.productos.ProductosNuevoDialog;
import gui.productos.ProductosView;
import gui.proveedores.ProveedoresNuevoDialog;
import gui.proveedores.ProveedoresView;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Matth
 */
public final class Main extends javax.swing.JFrame {
/* -------------------  Variables views  ------------------------------------------- */
    private LoginView loginView;
    private MenuPrincipal menuPpalView;
    private ProveedoresView proveedoresView;
    private ProveedoresNuevoDialog proveedoresNuevo;
    private ProductosView productosView;
    private ProductosNuevoDialog productoNuevo;
    private ComandaFrame comandasView;
    private MesasView mesasView;
    private MesasNuevaDialog mesaNueva;
/* -------------------  Variables views  ------------------------------------------- */    
/* -------------------  Asigno icono de la aplicacion  ----------------------------- */  
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("resources/icono.png"));
        return retValue;
    }
/* -------------------  Asigno icono de la aplicacion  ----------------------------- */    
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        setDefaultCloseOperation(0);
        setSize(420, 310);
        jmb_menu.setVisible(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Maraja");
        goLoginView();
    }
    
    /**
     * Ingreso al login
     */
    public void goLoginView(){
        loginView = LoginView.getLoginView(this);
        if(!loginView.isVisible() == false){
            getContentPane().add(loginView);
        }else{
            loginView.setVisible(true);
        }
    }
    
    /**
     * Ingreso al menu principal
     */
    public void goMenuPrincipalPrimera(){
        menuPpalView = MenuPrincipal.getMenuPrincipal(this);
        if(!menuPpalView.isVisible() == false){
            getContentPane().add(menuPpalView);
            setSize(800, 600);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            jmb_menu.setVisible(true);
            revalidate();
        }else{
            menuPpalView.setVisible(true);
            setSize(800, 600);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            jmb_menu.setVisible(true);
            revalidate();
        }
    }
    
    /**
     * Ingreso al menu principal
     */
    public void goMenuPrincipal(){
        menuPpalView = MenuPrincipal.getMenuPrincipal(this);
        if(!menuPpalView.isVisible() == false){
            getContentPane().add(menuPpalView);
            revalidate();
        }else{
            menuPpalView.setVisible(true);
            revalidate();
        }
    }
    
    /**
     * Ingreso a la vista de todos los proveedores
     */
    public void goProveedoresView(){
        proveedoresView = ProveedoresView.getProveedoresView(this);
        if(!proveedoresView.isVisible() == false){
            getContentPane().add(proveedoresView);
        }else{
            proveedoresView.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Muestra dialog de nuevo proveedor
     */
    public void goProveedoresNuevoDialog( ){
        if(proveedoresNuevo == null){
            proveedoresNuevo = new ProveedoresNuevoDialog(this,true);
        }else{
            proveedoresNuevo.goProveedoresNuevoPanel();
            proveedoresNuevo.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ingreso a la vista de todos los productos
     */
    public void goProductosView(){
        productosView = ProductosView.getProductosView(this);
        
        if(!productosView.isVisible() == false){
            getContentPane().add(productosView);
        }else{
            productosView.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Muestra dialog de nuevo producto
     */
    public void goProductosNuevoDialog( ){
        if(productoNuevo == null){
            productoNuevo = new ProductosNuevoDialog(this, true);
        }else{
            productoNuevo.goProductosNuevoPanel();
            productoNuevo.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ingreso a la vista de las comandas
     */
    public void goComandaFrame( ){
        comandasView = ComandaFrame.getComandaFrame();
        
        if(comandasView.isVisible()){
            setLocationRelativeTo(this);
        }else{
            comandasView.setVisible(true);
        }
    }
    
    public ComandaFrame getComandaFrame(){
        comandasView = ComandaFrame.getComandaFrame();
        
        return comandasView;
    }
       
    
    /**
     * Ingreso a la vista de todos los productos
     */
    public void goMesasView(){
        mesasView = MesasView.getMesasView(this);
        if(!mesasView.isVisible() == false){
            getContentPane().add(mesasView);
        }else{
            mesasView.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ingreso a la vista de nueva mesa
     */
    public void goMesaNuevaDialog(){
        if(mesaNueva == null){
            mesaNueva = new MesasNuevaDialog(this, true);
        }else{
            mesaNueva.setVisible(true);
        }
        revalidate();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmb_menu = new javax.swing.JMenuBar();
        jm_archivo = new javax.swing.JMenu();
        mi_menuPrincipal = new javax.swing.JMenuItem();
        mi_salir = new javax.swing.JMenuItem();
        jm_administracion = new javax.swing.JMenu();
        jm_estadisticas = new javax.swing.JMenu();
        mi_ventas = new javax.swing.JMenuItem();
        mi_compras = new javax.swing.JMenuItem();
        jm_configuracion = new javax.swing.JMenu();
        jm_productos = new javax.swing.JMenu();
        mi_nuevoProducto = new javax.swing.JMenuItem();
        mi_listarProductos = new javax.swing.JMenuItem();
        jm_proveedores = new javax.swing.JMenu();
        mi_nuevoProveedor = new javax.swing.JMenuItem();
        mi_listarProveedores = new javax.swing.JMenuItem();
        jm_mesas = new javax.swing.JMenu();
        mi_nuevaMesa = new javax.swing.JMenuItem();
        mi_listarMesas = new javax.swing.JMenuItem();
        jm_usuarios = new javax.swing.JMenu();
        mi_nuevoUsuario = new javax.swing.JMenuItem();
        mi_listarUsuarios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jm_archivo.setText("Archivo");

        mi_menuPrincipal.setText("Menu Principal");
        mi_menuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_menuPrincipalActionPerformed(evt);
            }
        });
        jm_archivo.add(mi_menuPrincipal);

        mi_salir.setText("Salir");
        mi_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_salirActionPerformed(evt);
            }
        });
        jm_archivo.add(mi_salir);

        jmb_menu.add(jm_archivo);

        jm_administracion.setText("Administracion");

        jm_estadisticas.setText("Estadisticas");

        mi_ventas.setText("Ventas");
        jm_estadisticas.add(mi_ventas);

        mi_compras.setText("Compras");
        mi_compras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_comprasActionPerformed(evt);
            }
        });
        jm_estadisticas.add(mi_compras);

        jm_administracion.add(jm_estadisticas);

        jmb_menu.add(jm_administracion);

        jm_configuracion.setText("Configuracion");

        jm_productos.setText("Productos");

        mi_nuevoProducto.setText("Nuevo");
        mi_nuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_nuevoProductoActionPerformed(evt);
            }
        });
        jm_productos.add(mi_nuevoProducto);

        mi_listarProductos.setText("Ver todos");
        mi_listarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_listarProductosActionPerformed(evt);
            }
        });
        jm_productos.add(mi_listarProductos);

        jm_configuracion.add(jm_productos);

        jm_proveedores.setText("Proveedores");

        mi_nuevoProveedor.setText("Nuevo");
        mi_nuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_nuevoProveedorActionPerformed(evt);
            }
        });
        jm_proveedores.add(mi_nuevoProveedor);

        mi_listarProveedores.setText("Ver todos");
        mi_listarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_listarProveedoresActionPerformed(evt);
            }
        });
        jm_proveedores.add(mi_listarProveedores);

        jm_configuracion.add(jm_proveedores);

        jm_mesas.setText("Mesas");

        mi_nuevaMesa.setText("Nueva");
        mi_nuevaMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_nuevaMesaActionPerformed(evt);
            }
        });
        jm_mesas.add(mi_nuevaMesa);

        mi_listarMesas.setText("Ver todas");
        mi_listarMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_listarMesasActionPerformed(evt);
            }
        });
        jm_mesas.add(mi_listarMesas);

        jm_configuracion.add(jm_mesas);

        jm_usuarios.setText("Usuarios");

        mi_nuevoUsuario.setText("Nuevo");
        mi_nuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_nuevoUsuarioActionPerformed(evt);
            }
        });
        jm_usuarios.add(mi_nuevoUsuario);

        mi_listarUsuarios.setText("Ver todos");
        jm_usuarios.add(mi_listarUsuarios);

        jm_configuracion.add(jm_usuarios);

        jmb_menu.add(jm_configuracion);

        setJMenuBar(jmb_menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mi_listarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_listarProductosActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        goProductosView();
        this.repaint();
    }//GEN-LAST:event_mi_listarProductosActionPerformed

    private void mi_nuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_nuevoProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mi_nuevoProductoActionPerformed

    private void mi_nuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_nuevoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mi_nuevoProveedorActionPerformed

    private void mi_nuevaMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_nuevaMesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mi_nuevaMesaActionPerformed

    private void mi_nuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_nuevoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mi_nuevoUsuarioActionPerformed

    private void mi_comprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_comprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mi_comprasActionPerformed

    private void mi_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_salirActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Seguro desea salir?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_mi_salirActionPerformed

    private void mi_listarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_listarProveedoresActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        goProveedoresView();
        this.repaint();
    }//GEN-LAST:event_mi_listarProveedoresActionPerformed

    private void mi_menuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_menuPrincipalActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        goMenuPrincipal();
        this.repaint();
    }//GEN-LAST:event_mi_menuPrincipalActionPerformed

    private void mi_listarMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_listarMesasActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        goMesasView();
        this.repaint();
    }//GEN-LAST:event_mi_listarMesasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jm_administracion;
    private javax.swing.JMenu jm_archivo;
    private javax.swing.JMenu jm_configuracion;
    private javax.swing.JMenu jm_estadisticas;
    private javax.swing.JMenu jm_mesas;
    private javax.swing.JMenu jm_productos;
    private javax.swing.JMenu jm_proveedores;
    private javax.swing.JMenu jm_usuarios;
    private javax.swing.JMenuBar jmb_menu;
    private javax.swing.JMenuItem mi_compras;
    private javax.swing.JMenuItem mi_listarMesas;
    private javax.swing.JMenuItem mi_listarProductos;
    private javax.swing.JMenuItem mi_listarProveedores;
    private javax.swing.JMenuItem mi_listarUsuarios;
    private javax.swing.JMenuItem mi_menuPrincipal;
    private javax.swing.JMenuItem mi_nuevaMesa;
    private javax.swing.JMenuItem mi_nuevoProducto;
    private javax.swing.JMenuItem mi_nuevoProveedor;
    private javax.swing.JMenuItem mi_nuevoUsuario;
    private javax.swing.JMenuItem mi_salir;
    private javax.swing.JMenuItem mi_ventas;
    // End of variables declaration//GEN-END:variables
}

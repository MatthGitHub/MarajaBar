/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.main;

import gui.comanda.ComandaFrame;
import gui.estadisticas.EstadisticasVentas;
import gui.login.LoginView;
import gui.menuPrincipal.MenuPrincipal;
import gui.mesas.MesasNuevaDialog;
import gui.mesas.MesasView;
import gui.productos.ProductosModificarDialog;
import gui.productos.ProductosNuevoDialog;
import gui.productos.ProductosView;
import gui.proveedores.ProveedoresNuevoDialog;
import gui.proveedores.ProveedoresView;
import gui.usuarios.UsuarioNuevoDialog;
import gui.usuarios.UsuariosView;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import negocio.FacadeNegocio;

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
    private ProductosModificarDialog productoModifica;
    private ComandaFrame comandasView;
    private MesasView mesasView;
    private MesasNuevaDialog mesaNueva;
    private UsuariosView usuariosView;
    private UsuarioNuevoDialog usuarioNuevo;
    private EstadisticasVentas estadisticasV;
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
    
    
    public void aplicarPermisosMain(){
        FacadeNegocio fac = FacadeNegocio.getFacadeNegocio();
        //Estadisticas
        if(!fac.verificarPermisos(6)){
            jm_estadisticas.setVisible(false);
        }else{
            jm_estadisticas.setVisible(true);
        }
        //Ventas
        if(!fac.verificarPermisos(7)){
            mi_ventas.setVisible(false);
        }else{
            mi_ventas.setVisible(true);
        }
        //Compras
        if(!fac.verificarPermisos(8)){
            mi_compras.setVisible(false);
        }else{
            mi_compras.setVisible(true);
        }
        //Administracion
        if(!fac.verificarPermisos(9)){
            jm_administacion.setVisible(false);
        }else{
            jm_administacion.setVisible(true);
        }
        //Productos
        if(!fac.verificarPermisos(10)){
            jm_productos.setVisible(false);
        }else{
            jm_productos.setVisible(true);
        }
        //ProductosListar
        if(!fac.verificarPermisos(11)){
            mi_listarProductos.setVisible(false);
        }else{
            mi_listarProductos.setVisible(true);
        }
        //ProductosNuevo
        if(!fac.verificarPermisos(12)){
            mi_nuevoProducto.setVisible(false);
        }else{
            mi_nuevoProducto.setVisible(true);
        }
        //Proveedores
        if(!fac.verificarPermisos(14)){
            mi_proveedores.setVisible(false);
        }else{
            mi_proveedores.setVisible(true);
        }
        //Mesas
        if(!fac.verificarPermisos(17)){
            mi_mesas.setVisible(false);
        }else{
            mi_mesas.setVisible(true);
        }
        //Configuracion
        if(!fac.verificarPermisos(20)){
            jm_configuracion.setVisible(false);
        }else{
            jm_configuracion.setVisible(true);
        }
        //Usuarios
        if(!fac.verificarPermisos(21)){
            mi_usuarios.setVisible(false);
        }else{
            mi_usuarios.setVisible(true);
        }
    }
    /**
     * Ingreso al login
     */
    public void goLoginView(){
        loginView = LoginView.getLoginView(this);
        if(!loginView.isVisible() == false){
            setSize(420, 310);
            jmb_menu.setVisible(false);
            setLocationRelativeTo(null);
            getContentPane().add(loginView);
        }else{
            setSize(420, 310);
            jmb_menu.setVisible(false);
            setLocationRelativeTo(null);
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
            productoNuevo.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Muestra dialog de nuevo producto
     */
    public void goProductosModificaDialog(int idProducto){
        if(productoModifica == null){
            productoModifica = new ProductosModificarDialog(this, true,idProducto);
        }else{
            productoModifica.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ingreso a la vista de las comandas
     */
    public void goComandaFrame( ){
        comandasView = ComandaFrame.getComandaFrame();
        
        if(comandasView.isVisible()){
            comandasView.setLocationRelativeTo(this);
        }else{
            comandasView.setLocationRelativeTo(this);
            comandasView.setVisible(true);
        }
    }
    
    public ComandaFrame getComandaFrame(){
        comandasView = ComandaFrame.getComandaFrame();
        
        return comandasView;
    }
       
    
    /**
     * Ingreso a la vista de todas las mesas
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
     * Ingreso a la vista de todos los usuarios
     */
    public void goUsuariosView(){
        usuariosView = UsuariosView.getUsuariosView(this);
        if(!usuariosView.isVisible() == false){
            getContentPane().add(usuariosView);
        }else{
            usuariosView.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ingreso a la vista de nuevo usuario
     */
    public void goUsuarioNuevoDialog(){
        if(usuarioNuevo == null){
            usuarioNuevo = new UsuarioNuevoDialog(this, true);
        }else{
            usuarioNuevo.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ingreso a la vista de estadisticas ventas
     */
    public void goEstadisticasVentas(){
        estadisticasV = EstadisticasVentas.getEstadisticasVentas(this);
        if(!estadisticasV.isVisible() == false){
            getContentPane().add(estadisticasV);
        }else{
            estadisticasV.setVisible(true);
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
        jm_estadisticas = new javax.swing.JMenu();
        mi_ventas = new javax.swing.JMenuItem();
        mi_compras = new javax.swing.JMenuItem();
        jm_administacion = new javax.swing.JMenu();
        jm_productos = new javax.swing.JMenu();
        mi_listarProductos = new javax.swing.JMenuItem();
        mi_nuevoProducto = new javax.swing.JMenuItem();
        mi_proveedores = new javax.swing.JMenuItem();
        mi_mesas = new javax.swing.JMenuItem();
        jm_configuracion = new javax.swing.JMenu();
        mi_usuarios = new javax.swing.JMenuItem();

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

        mi_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mi_salir.setText("Salir");
        mi_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_salirActionPerformed(evt);
            }
        });
        jm_archivo.add(mi_salir);

        jmb_menu.add(jm_archivo);

        jm_estadisticas.setText("Estadisticas");

        mi_ventas.setText("Ventas");
        mi_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_ventasActionPerformed(evt);
            }
        });
        jm_estadisticas.add(mi_ventas);

        mi_compras.setText("Compras");
        mi_compras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_comprasActionPerformed(evt);
            }
        });
        jm_estadisticas.add(mi_compras);

        jmb_menu.add(jm_estadisticas);

        jm_administacion.setText("Administracion");

        jm_productos.setText("Productos");

        mi_listarProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        mi_listarProductos.setText("Listar");
        mi_listarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_listarProductosActionPerformed(evt);
            }
        });
        jm_productos.add(mi_listarProductos);

        mi_nuevoProducto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mi_nuevoProducto.setText("Nuevo");
        mi_nuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_nuevoProductoActionPerformed(evt);
            }
        });
        jm_productos.add(mi_nuevoProducto);

        jm_administacion.add(jm_productos);

        mi_proveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mi_proveedores.setText("Proveedores");
        mi_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_proveedoresActionPerformed(evt);
            }
        });
        jm_administacion.add(mi_proveedores);

        mi_mesas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        mi_mesas.setText("Mesas");
        mi_mesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_mesasActionPerformed(evt);
            }
        });
        jm_administacion.add(mi_mesas);

        jmb_menu.add(jm_administacion);

        jm_configuracion.setText("Configuracion");

        mi_usuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        mi_usuarios.setText("Usuarios");
        mi_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_usuariosActionPerformed(evt);
            }
        });
        jm_configuracion.add(mi_usuarios);

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
        goProductosNuevoDialog();
        this.repaint();
    }//GEN-LAST:event_mi_nuevoProductoActionPerformed

    private void mi_comprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_comprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mi_comprasActionPerformed

    private void mi_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_salirActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Seguro desea salir?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_mi_salirActionPerformed

    private void mi_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_proveedoresActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        goProveedoresView();
        this.repaint();
    }//GEN-LAST:event_mi_proveedoresActionPerformed

    private void mi_menuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_menuPrincipalActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        goMenuPrincipal();
        this.repaint();
    }//GEN-LAST:event_mi_menuPrincipalActionPerformed

    private void mi_mesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_mesasActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        goMesasView();
        this.repaint();
    }//GEN-LAST:event_mi_mesasActionPerformed

    private void mi_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_usuariosActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        goUsuariosView();
        this.repaint();
    }//GEN-LAST:event_mi_usuariosActionPerformed

    private void mi_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_ventasActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        goEstadisticasVentas();
        this.repaint();
    }//GEN-LAST:event_mi_ventasActionPerformed

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
    private javax.swing.JMenu jm_administacion;
    private javax.swing.JMenu jm_archivo;
    private javax.swing.JMenu jm_configuracion;
    private javax.swing.JMenu jm_estadisticas;
    private javax.swing.JMenu jm_productos;
    private javax.swing.JMenuBar jmb_menu;
    private javax.swing.JMenuItem mi_compras;
    private javax.swing.JMenuItem mi_listarProductos;
    private javax.swing.JMenuItem mi_menuPrincipal;
    private javax.swing.JMenuItem mi_mesas;
    private javax.swing.JMenuItem mi_nuevoProducto;
    private javax.swing.JMenuItem mi_proveedores;
    private javax.swing.JMenuItem mi_salir;
    private javax.swing.JMenuItem mi_usuarios;
    private javax.swing.JMenuItem mi_ventas;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.comanda;

import gui.resources.MenuP;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import negocio.FacadeNegocio;
import servicios.dto.DtoDetalleVenta;
import servicios.dto.DtoMesa;
import servicios.dto.DtoProducto;
import servicios.dto.DtoVentas;

/**
 *
 * @author Matth
 */
public class ComandaFrame extends javax.swing.JFrame {
    private static ComandaFrame esteFrame;
    private DefaultTableModel modeloP;
    private DefaultTableModel modeloC;
    private DtoMesa miMesa;
    private DtoVentas laVenta;
    
    /*
    Despues de abrir la mesa, se genera un detalle de venta asociado con la mesa. PROBLEMA: como saber a que detalle de venta esta asociado la mesa, ya que va a haber muchos, 
    podria ser el de la ultima fecha, pero podria ser inconsistente...
    */
    
    /* -------------------  Asigno icono de la comanda  ----------------------------- */  
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("resources/comanda.png"));
        return retValue;
    }
    /* -------------------  Asigno icono de la comanda  ----------------------------- */   
    
    /**
     * Creates new form ComandaFrame
     */
    private ComandaFrame() {
        initComponents();
        modeloP = (DefaultTableModel) jtProductos.getModel();
        modeloC = (DefaultTableModel) jtComanda.getModel();
        setResizable(false);
        setDefaultCloseOperation(0);
        setSize(500, 700);
        setVisible(true);
        lbl_descripcionMesa.setVisible(false);
        setTablaProductos();
    }
    
    public static ComandaFrame getComandaFrame(){
        if(esteFrame == null){
            esteFrame = new ComandaFrame();
        }
        return esteFrame;
    }
    
    /**
     * Verifica si la comanda esta cerrada o abierta
     * @return 
     */
    
    public void setMesa(Integer idMesa){
        vaciarTabla(jtComanda);
        setTitle("Mesa: "+idMesa.toString());
        lbl_mesa.setText("Mesa: "+idMesa.toString());
        miMesa = FacadeNegocio.getFacadeNegocio().getMesa(idMesa);
        DtoVentas ultimaVenta = FacadeNegocio.getFacadeNegocio().getUltimaVenta(miMesa);
        if(esMovil()){
            lbl_descripcionMesa.setVisible(true);
        }
        if(ultimaVenta != null){
            if(ultimaVenta.getFkEstado().getIdEstadoVenta() == 1){
                btn_abrir.setEnabled(true);
                btn_cerrar.setEnabled(false);
                btn_agregar.setEnabled(false);
                btn_quitar.setEnabled(false);
            }else{
                btn_cerrar.setEnabled(true);
                btn_abrir.setEnabled(false);
                btn_agregar.setEnabled(true);
                btn_quitar.setEnabled(true);
                List<DtoDetalleVenta> detalles = FacadeNegocio.getFacadeNegocio().getDetalleVenta(ultimaVenta);
                laVenta = ultimaVenta;
                setTablaDetalle(detalles);
            }
        }else{
            btn_abrir.setEnabled(true);
            btn_cerrar.setEnabled(false);
            btn_agregar.setEnabled(false);
            btn_quitar.setEnabled(false);
        }
    }
    
    public Boolean esMovil(){
        if(miMesa.getSector().getIdSector() == 99){
                return true;
            }else{
            return false;
        }
    }
    
    
    public void setTablaProductos(){
        List<DtoProducto> productos = FacadeNegocio.getFacadeNegocio().getTodosLosProductos();
        String v[] = new String[3];
        
        for(int i = 0; i < productos.size();i++){
            v[0] = productos.get(i).getIdProducto().toString();
            v[1] = productos.get(i).getNombreProducto();
            v[2] = productos.get(i).getPrecio().toString();
            modeloP.addRow(v);
        }
    }
    
    public void setTablaDetalle(List<DtoDetalleVenta> detalle){
        vaciarTabla(jtComanda);
        String v[] = new String[4];
        for(int i = 0; i < detalle.size();i++){
            v[0] = detalle.get(i).getFkProducto().getIdProducto().toString();
            v[1] = detalle.get(i).getFkProducto().getNombreProducto();
            v[2] = detalle.get(i).getFkProducto().getPrecio().toString();
            v[3] = detalle.get(i).getCantidad().toString();
            modeloC.addRow(v);
        }
        txtTotal.setText(FacadeNegocio.getFacadeNegocio().getUltimaVenta(miMesa).getTotal().toString());
    }
    
    private void vaciarTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    private void setearDescripcion(DtoMesa miMesa){
        setDescripcionMesaMovil descDialog = new setDescripcionMesaMovil(this, true,miMesa);
        lbl_descripcionMesa.setText(miMesa.getDescripcion());
        lbl_descripcionMesa.setVisible(true);
    }
    
    public void abrirMesa(){
        try {
            laVenta = FacadeNegocio.getFacadeNegocio().nuevaVenta(miMesa);
            if(esMovil()){
                setearDescripcion(miMesa);
            }
            btn_agregar.setEnabled(true);
            btn_quitar.setEnabled(true);
        } catch (Exception ex) {
            Logger.getLogger(ComandaFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al crear venta - "+ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cerrarMesa(){
        if(FacadeNegocio.getFacadeNegocio().cerrarVenta(laVenta)){
            JOptionPane.showMessageDialog(this, "Venta cerrada", "Venta", JOptionPane.PLAIN_MESSAGE);
            vaciarTabla(jtComanda);
            if(esMovil()){
                miMesa.setDescripcion("");
                FacadeNegocio.getFacadeNegocio().modificarMesa(miMesa);
                lbl_descripcionMesa.setText("");
            }
            txtTotal.setText("0");
            setMesa(miMesa.getIdMesa());
        }else{
            JOptionPane.showMessageDialog(this, "Error al cerrar venta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void agregarProductoAComanda(){
        DtoProducto producto = FacadeNegocio.getFacadeNegocio().getProducto(Integer.parseInt(jtProductos.getValueAt(jtProductos.getSelectedRow(), 0).toString()));
        DtoVentas ultimaVenta = FacadeNegocio.getFacadeNegocio().getUltimaVenta(miMesa);
        
        if(FacadeNegocio.getFacadeNegocio().nuevoDetalleVenta(ultimaVenta, producto)){
            List<DtoDetalleVenta> detalles = FacadeNegocio.getFacadeNegocio().getDetalleVenta(ultimaVenta);
            setTablaDetalle(detalles);
        }else{
            JOptionPane.showMessageDialog(this, "Error al cargar producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void quitarProductoAComanda(){
        DtoProducto producto = FacadeNegocio.getFacadeNegocio().getProducto(Integer.parseInt(jtComanda.getValueAt(jtComanda.getSelectedRow(), 0).toString()));
        DtoVentas ultimaVenta = FacadeNegocio.getFacadeNegocio().getUltimaVenta(miMesa);
        if(FacadeNegocio.getFacadeNegocio().eliminarDetalleVenta(ultimaVenta, producto)){
             vaciarTabla(jtComanda);
             setTablaDetalle(FacadeNegocio.getFacadeNegocio().getDetalleVenta(ultimaVenta));
        }else{
            JOptionPane.showMessageDialog(this, "Error al quitar producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new MenuP();
        lbl_mesa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtComanda = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_quitar = new javax.swing.JButton();
        btn_esconder = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        btn_abrir = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lbl_descripcionMesa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comanda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N

        lbl_mesa.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lbl_mesa.setForeground(java.awt.Color.white);
        lbl_mesa.setText("Mesa Nº ............");

        jtComanda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Producto", "Precio", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtComanda);

        jtProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Producto", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtProductosMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtProductos);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pedidos:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Productos:");

        btn_quitar.setBackground(new java.awt.Color(232, 133, 133));
        btn_quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/quitar.png"))); // NOI18N
        btn_quitar.setText("Quitar");
        btn_quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitarActionPerformed(evt);
            }
        });

        btn_esconder.setBackground(new java.awt.Color(189, 154, 109));
        btn_esconder.setText("Esconder comanda");
        btn_esconder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_esconderActionPerformed(evt);
            }
        });

        btn_cerrar.setBackground(new java.awt.Color(232, 133, 133));
        btn_cerrar.setText("Cerrar mesa");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        btn_abrir.setBackground(new java.awt.Color(158, 230, 168));
        btn_abrir.setText("Abrir mesa");
        btn_abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abrirActionPerformed(evt);
            }
        });

        btn_agregar.setBackground(new java.awt.Color(158, 230, 168));
        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/agregar.png"))); // NOI18N
        btn_agregar.setText("Agregar producto");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total:");

        txtTotal.setText("$###");
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        lbl_descripcionMesa.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lbl_descripcionMesa.setForeground(java.awt.Color.white);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(btn_abrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(lbl_mesa)
                .addGap(84, 84, 84)
                .addComponent(btn_cerrar))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(lbl_descripcionMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_agregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_esconder))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_quitar)
                                .addGap(261, 261, 261)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_abrir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cerrar)
                            .addComponent(lbl_mesa))
                        .addGap(3, 3, 3)))
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_quitar)
                    .addComponent(jLabel3)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_descripcionMesa, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_esconder)
                    .addComponent(btn_agregar))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_agregar, btn_esconder, btn_quitar});

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_esconderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_esconderActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        vaciarTabla(jtComanda);
        txtTotal.setText("0");
        if(esMovil()){
            lbl_descripcionMesa.setVisible(false);
        }
    }//GEN-LAST:event_btn_esconderActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        // TODO add your handling code here:
        if(jtProductos.getSelectedRow() != -1){
            agregarProductoAComanda();
        }
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abrirActionPerformed
        // TODO add your handling code here:
        btn_abrir.setEnabled(false);
        btn_cerrar.setEnabled(true);
        abrirMesa();
    }//GEN-LAST:event_btn_abrirActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        cerrarMesa();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarActionPerformed
        // TODO add your handling code here:
        if(jtComanda.getSelectedRow() != -1){
            quitarProductoAComanda();
        }
    }//GEN-LAST:event_btn_quitarActionPerformed

    private void jtProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProductosMousePressed
        // TODO add your handling code here:
         if(evt.getSource() == jtProductos){
            if(evt.getClickCount() == 2){
                if((jtProductos.getSelectedRow() != -1)&&(jtProductos.getSelectedRowCount() == 1)){
                    agregarProductoAComanda();
                }else{
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una y solo una fila!");
                }
            }
        }
    }//GEN-LAST:event_jtProductosMousePressed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

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
            java.util.logging.Logger.getLogger(ComandaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComandaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComandaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComandaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComandaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_abrir;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_esconder;
    private javax.swing.JButton btn_quitar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtComanda;
    private javax.swing.JTable jtProductos;
    private javax.swing.JLabel lbl_descripcionMesa;
    private javax.swing.JLabel lbl_mesa;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

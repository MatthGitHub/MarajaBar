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
import negocio.BarController;
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
    private Integer idVenta;
    
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
    public boolean verificarEstadoMesa(){
        FacadeNegocio.getFacadeNegocio().getUltimaVenta(miMesa);
        
        return false;
    }
    
    
    public void setMesa(Integer idMesa){
        setTitle("Mesa: "+idMesa.toString());
        lbl_mesa.setText("Mesa: "+idMesa.toString());
        miMesa = FacadeNegocio.getFacadeNegocio().getMesa(idMesa);
        DtoVentas ultimaVenta = FacadeNegocio.getFacadeNegocio().getUltimaVenta(miMesa);
        
        if((ultimaVenta == null)||(ultimaVenta.getFkEstado().getIdEstadoVenta() == 1)){
            btn_abrir.setEnabled(true);
            btn_cerrar.setEnabled(false);
        }else{
            btn_cerrar.setEnabled(true);
            btn_abrir.setEnabled(false);
            List<DtoDetalleVenta> detalles = FacadeNegocio.getFacadeNegocio().getDetalleVenta(ultimaVenta);
            idVenta = ultimaVenta.getIdVenta();
            setTablaDetalle(detalles);
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
        String v[] = new String[3];
        for(int i = 0; i < detalle.size();i++){
            v[0] = detalle.get(i).getFkProducto().getNombreProducto();
            v[1] = detalle.get(i).getFkProducto().getPrecio().toString();
            v[2] = detalle.get(i).getCantidad().toString();
            modeloC.addRow(v);
        }
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
    
    public void abrirMesa(){
        try {
            idVenta = FacadeNegocio.getFacadeNegocio().nuevaVenta(miMesa);
        } catch (Exception ex) {
            Logger.getLogger(ComandaFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al crear venta - "+ex, "Error", JOptionPane.ERROR_MESSAGE);
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
        jTextField1 = new javax.swing.JTextField();

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
                "Producto", "Precio", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
        jScrollPane2.setViewportView(jtProductos);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pedidos:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Productos:");

        btn_quitar.setText("Quitar");

        btn_esconder.setText("Esconder comanda");
        btn_esconder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_esconderActionPerformed(evt);
            }
        });

        btn_cerrar.setText("Cerrar mesa");

        btn_abrir.setText("Abrir mesa");
        btn_abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abrirActionPerformed(evt);
            }
        });

        btn_agregar.setText("Agregar producto");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total:");

        jTextField1.setText("$###");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(btn_abrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_quitar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_agregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_esconder)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_quitar)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_esconder)
                    .addComponent(btn_agregar)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_esconderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_esconderActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable jtComanda;
    private javax.swing.JTable jtProductos;
    private javax.swing.JLabel lbl_mesa;
    // End of variables declaration//GEN-END:variables
}

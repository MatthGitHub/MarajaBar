/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.proveedores;

import gui.main.Main;
import gui.resources.MenuP;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import negocio.FacadeNegocio;
import negocio.controladores.exceptions.NonexistentEntityException;
import servicios.dto.DtoProveedor;

/**
 *
 * @author Matth
 */
public class ProveedoresView extends MenuP {
    private static ProveedoresView estePanel;
    private Main mainFrame;
    private DefaultTableModel modelo;
    private List<DtoProveedor> proveedores;
    
    /**
     * Creates new form ProveedoresView
     */
    private ProveedoresView(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        setVisible(true);
        esconderColumna();
        setTablaProveedores();
    }
    
    public static ProveedoresView getProveedoresView(Main mainFrame){
        if(estePanel == null){
            estePanel = new ProveedoresView(mainFrame);
        }
        return estePanel;
    }
    
    public void esconderColumna(){
        jtProveedores.getColumnModel().getColumn(0).setMaxWidth(0);
        jtProveedores.getColumnModel().getColumn(0).setMinWidth(0);
        jtProveedores.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    public void setTablaProveedores(){
        vaciarTabla(jtProveedores);
       proveedores = FacadeNegocio.getFacadeNegocio().getTodosLosProveedores();
       String v[] = new String[5];
       
       for(int i = 0; i < proveedores.size(); i++){
           v[0] = proveedores.get(i).getIdProveedor().toString();
           v[1] = proveedores.get(i).getNombreProveedor();
           v[2] = proveedores.get(i).getCuit();
           v[3] = proveedores.get(i).getTelefono();
           v[4] = proveedores.get(i).getCorreo();
           modelo.addRow(v);
       }
       revalidate();
       
    }
    
    
    private void vaciarTabla(JTable tabla) {
        try {
            modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    private void eliminarProveedor(){
        try {
            FacadeNegocio.getFacadeNegocio().eliminarProveedor(Integer.parseInt(modelo.getValueAt(jtProveedores.getSelectedRow(), 0).toString()));
            setTablaProveedores();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProveedoresView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Solo se puede eliminar un proveedor si jamas se le realizo una compra", "Error", JOptionPane.ERROR_MESSAGE);
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

        jt_proveedores = new javax.swing.JScrollPane();
        jtProveedores = new javax.swing.JTable();
        btn_eliminar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        btn_eliminar1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jtProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "CUIT", "Telefono", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_proveedores.setViewportView(jtProveedores);
        if (jtProveedores.getColumnModel().getColumnCount() > 0) {
            jtProveedores.getColumnModel().getColumn(0).setResizable(false);
        }

        btn_eliminar.setBackground(new java.awt.Color(232, 133, 133));
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/package_warning.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_nuevo.setBackground(new java.awt.Color(158, 230, 168));
        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/package_add.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_eliminar1.setBackground(new java.awt.Color(189, 154, 109));
        btn_eliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/volver.png"))); // NOI18N
        btn_eliminar1.setText("Menu");
        btn_eliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_eliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_nuevo))
                    .addComponent(jt_proveedores, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_eliminar, btn_eliminar1, btn_nuevo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jt_proveedores, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eliminar1)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_nuevo))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_eliminar, btn_eliminar1, btn_nuevo});

    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        mainFrame.goProveedoresNuevoDialog();
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_eliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        System.gc();
        mainFrame.goMenuPrincipal();
    }//GEN-LAST:event_btn_eliminar1ActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if(jtProveedores.getSelectedRow() != -1){
            eliminarProveedor();
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_eliminar1;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JTable jtProveedores;
    private javax.swing.JScrollPane jt_proveedores;
    // End of variables declaration//GEN-END:variables
}

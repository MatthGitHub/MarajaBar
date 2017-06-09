/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.usuarios;

import gui.main.Main;
import gui.resources.MenuP;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import negocio.FacadeNegocio;
import negocio.controladores.exceptions.IllegalOrphanException;
import negocio.controladores.exceptions.NonexistentEntityException;
import servicios.dto.DtoUsuario;

/**
 *
 * @author matth
 */
public class UsuariosView extends MenuP {
    private Main mainFrame;
    private static UsuariosView estePanel;
    private List<DtoUsuario> usuarios;
    private DefaultTableModel modelo;
    /**
     * Creates new form UsuariosView
     */
    public UsuariosView(Main mainFrame) {
        initComponents();
        aplicarPermisosUsuarios();
        this.mainFrame = mainFrame;
        modelo = (DefaultTableModel) jtUsuarios.getModel();
        setTablaUsuarios();
        setVisible(true);
    }
    
    public static UsuariosView getUsuariosView(Main mainFrame){
        if(estePanel == null){
            estePanel = new UsuariosView(mainFrame);
        }
        return estePanel;
    }
    
    private void aplicarPermisosUsuarios(){
        FacadeNegocio fac = FacadeNegocio.getFacadeNegocio();
        if(!fac.verificarPermisos(22)){
            btnGuardar.setVisible(false);
        }
        if(!fac.verificarPermisos(23)){
            btnEliminar.setVisible(false);
        }
    }
    
    public void setTablaUsuarios(){
        vaciarTabla(jtUsuarios);
        usuarios = FacadeNegocio.getFacadeNegocio().getTodosLosUsuarios();
        
        String v[] = new String[3];
       
       for(int i = 0; i < usuarios.size(); i++){
           v[0] = usuarios.get(i).getIdUsuario().toString();
           v[1] = usuarios.get(i).getNombreUsuario();
           v[2] = usuarios.get(i).getRol().getDescripcion();
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
    
    private void eliminarUsuario(){
        try {
            FacadeNegocio.getFacadeNegocio().eliminarUsuario(Integer.parseInt(modelo.getValueAt(jtUsuarios.getSelectedRow(), 0).toString()));
            setTablaUsuarios();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuariosView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Solo se pueden eliminar los usuarios que jamas hayan realizado una venta/comanda", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(UsuariosView.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jtUsuarios = new javax.swing.JTable();
        btnMenu = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 24), new java.awt.Color(254, 254, 254))); // NOI18N

        jtUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero", "Descripcion", "Sector"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtUsuarios);

        btnMenu.setBackground(new java.awt.Color(189, 154, 109));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/volver.png"))); // NOI18N
        btnMenu.setText("Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(232, 133, 133));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/package_warning.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(158, 230, 168));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/package_add.png"))); // NOI18N
        btnGuardar.setText("Nueva");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnMenu)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.goMenuPrincipal();
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if(jtUsuarios.getSelectedRow() != -1){
            eliminarUsuario();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        mainFrame.goUsuarioNuevoDialog();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtUsuarios;
    // End of variables declaration//GEN-END:variables
}

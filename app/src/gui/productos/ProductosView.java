/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.productos;

import gui.main.Main;
import gui.resources.MenuP;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import negocio.FacadeNegocio;
import servicios.dto.DtoProducto;

/**
 *
 * @author Matth
 */
public class ProductosView extends MenuP {
    private Main mainFrame;
    private static ProductosView estePanel;
    private DefaultTableModel modelo;
    /**
     * Creates new form ProductosVieew
     */
    private ProductosView(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        aplicarPermisosProductos();
        modelo = (DefaultTableModel) jtProductos.getModel();
        esconderColumna();
        setTablaProductos();
    }
    
    public static ProductosView getProductosView(Main mainFrame){
        if(estePanel == null){
            estePanel = new ProductosView(mainFrame);
        }
        return estePanel;
    }
    
    private void aplicarPermisosProductos(){
        FacadeNegocio fac = FacadeNegocio.getFacadeNegocio();
        if(!fac.verificarPermisos(13)){
            btnEliminar.setVisible(false);
        }
        if(!fac.verificarPermisos(12)){
            btnNuevo.setVisible(false);
        }
    }
    
    public void esconderColumna(){
        jtProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtProductos.getColumnModel().getColumn(0).setMinWidth(0);
        jtProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    
    public void setTablaProductos(){
        vaciarTabla(jtProductos);
        List<DtoProducto> miLista = FacadeNegocio.getFacadeNegocio().getTodosLosProductos();
        String v[] = new String[4];
        
        for(int i = 0; i < miLista.size(); i ++){
            v[0] = miLista.get(i).getIdProducto().toString();
            v[1] = miLista.get(i).getNombreProducto();
            v[2] = miLista.get(i).getDescripcion();
            v[3] = miLista.get(i).getPrecio().toString();
            modelo.addRow(v);
        }
        revalidate();
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
    
    private void eliminarProducto(){
        if(FacadeNegocio.getFacadeNegocio().eliminarProducto(Integer.parseInt(modelo.getValueAt(jtProductos.getSelectedRow(), 0).toString()))){
            setTablaProductos();
        }else{
            JOptionPane.showMessageDialog(this, "Para eliminar un producto este no debe haber sido usado nunca", "Error", JOptionPane.ERROR_MESSAGE);
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
        jtProductos = new javax.swing.JTable();
        btnMenu = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jtProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripcion", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtProductos);
        if (jtProductos.getColumnModel().getColumnCount() > 0) {
            jtProductos.getColumnModel().getColumn(0).setResizable(false);
        }

        btnMenu.setBackground(new java.awt.Color(189, 154, 109));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/volver.png"))); // NOI18N
        btnMenu.setText("Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(158, 230, 168));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/package_add.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnEliminar, btnMenu, btnNuevo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNuevo)
                        .addComponent(btnEliminar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnMenu)
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnEliminar, btnMenu, btnNuevo});

    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        mainFrame.goProductosNuevoDialog();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.goMenuPrincipal();
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if(jtProductos.getSelectedRow() != -1){
            eliminarProducto();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtProductos;
    // End of variables declaration//GEN-END:variables
}

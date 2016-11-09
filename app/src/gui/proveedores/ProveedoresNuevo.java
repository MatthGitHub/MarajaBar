/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.proveedores;

import gui.main.Main;
import gui.resources.MenuP;

/**
 *
 * @author Matth
 */
public class ProveedoresNuevo extends MenuP {
    private static ProveedoresNuevo estePanel;
    Main mainFrame;
    /**
     * Creates new form ProveedoresNuevo
     */
    private ProveedoresNuevo(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        setVisible(true);
    }
    
    public static ProveedoresNuevo getProveedoresNuevo(Main mainFrame){
        if(estePanel == null){
            estePanel = new ProveedoresNuevo(mainFrame);
        }
        return estePanel;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo proveedor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookshelf Symbol 7", 0, 24))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
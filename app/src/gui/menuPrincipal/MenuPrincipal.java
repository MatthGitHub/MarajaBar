/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menuPrincipal;

import gui.comanda.ComandaFrame;
import gui.main.Main;
import gui.resources.MenuP;
import gui.resources.PanelMesas;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import negocio.FacadeNegocio;
import servicios.dto.DtoMesa;

/**
 *
 * @author Matth
 */
public class MenuPrincipal extends MenuP implements ActionListener{
    private static MenuPrincipal estePanel;
    private Integer idMesa;
    Main mainFrame;
    /**
     * Creates new form MenuPrincipal
     */
    private MenuPrincipal(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        setVisible(true);
    }
    
    public static MenuPrincipal getMenuPrincipal(Main mainFrame){
        if(estePanel == null){
            estePanel = new MenuPrincipal(mainFrame);
        }
        return estePanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.goComandaFrame(1);//mando el id de la mesa
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_salon = new javax.swing.JButton();
        btn_arriba = new javax.swing.JButton();
        btn_afuera = new javax.swing.JButton();
        btnAbajo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jifMesas = new javax.swing.JInternalFrame();
        jpMesas = new PanelMesas();
        btn_movil = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu Principal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookshelf Symbol 7", 0, 24), java.awt.Color.white)); // NOI18N

        btn_salon.setBackground(new java.awt.Color(189, 154, 109));
        btn_salon.setText("Salon");

        btn_arriba.setBackground(new java.awt.Color(189, 154, 109));
        btn_arriba.setText("Arriba");
        btn_arriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_arribaActionPerformed(evt);
            }
        });

        btn_afuera.setBackground(new java.awt.Color(189, 154, 109));
        btn_afuera.setText("Afuera");

        btnAbajo.setBackground(new java.awt.Color(189, 154, 109));
        btnAbajo.setText("Abajo");
        btnAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbajoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sectores");

        jButton2.setText("Cerrar sesion");

        jifMesas.setTitle("Mesas");
        jifMesas.setVisible(true);

        jpMesas.setLayout(new java.awt.GridLayout(4, 4, 25, 25));
        jifMesas.getContentPane().add(jpMesas, java.awt.BorderLayout.CENTER);

        btn_movil.setBackground(new java.awt.Color(189, 154, 109));
        btn_movil.setText("Movil");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnAbajo, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addComponent(btn_salon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btn_arriba, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_afuera, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_movil, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jifMesas, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_arriba, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_afuera, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_movil, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jifMesas, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_arribaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_arribaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_arribaActionPerformed

    private void btnAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbajoActionPerformed
        // TODO add your handling code here:
        jpMesas.removeAll();
        List<DtoMesa> mesas = FacadeNegocio.getFacadeNegocio().getTodasLasMesas("Abajo");
        for(int i = 0; i < mesas.size(); i++){
            JToggleButton boton = new JToggleButton("Mesa "+mesas.get(i).getIdMesa().toString(),false);
            boton.setSize(120,100);
            boton.addActionListener(this);
            jpMesas.add(boton);
        }
    }//GEN-LAST:event_btnAbajoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbajo;
    private javax.swing.JButton btn_afuera;
    private javax.swing.JButton btn_arriba;
    private javax.swing.JButton btn_movil;
    private javax.swing.JButton btn_salon;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JInternalFrame jifMesas;
    private javax.swing.JPanel jpMesas;
    // End of variables declaration//GEN-END:variables

    
}

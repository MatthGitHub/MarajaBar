/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menuPrincipal;

import gui.main.Main;
import gui.resources.MenuP;
import gui.resources.PanelMesas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import negocio.FacadeNegocio;
import servicios.dto.DtoMesa;

/**
 *
 * @author Matth
 */
public class MenuPrincipal extends MenuP {
    private static MenuPrincipal estePanel;
    Main mainFrame;
    /**
     * Creates new form MenuPrincipal
     */
    private MenuPrincipal(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        lblUsuario.setText(FacadeNegocio.usuario.getNombreUsuario());
        setVisible(true);
    }
    
    public static MenuPrincipal getMenuPrincipal(Main mainFrame){
        if(estePanel == null){
            estePanel = new MenuPrincipal(mainFrame);
        }
        return estePanel;
    }
    
    public Boolean esMovil(DtoMesa miMesa){
        if(miMesa.getSector().getIdSector() == 99){
                return true;
            }else{
            return false;
        }
    }
    
    public void llenarPanelMesas(Integer idSector){
        jpMesas.removeAll();
        List<DtoMesa> mesas = FacadeNegocio.getFacadeNegocio().getTodasLasMesas(idSector);
        for(int i = 0; i < mesas.size(); i++){
            final int idMesa = mesas.get(i).getIdMesa();
            final JButton boton = new JButton();
            if(esMovil(mesas.get(i))){
                boton.setText(mesas.get(i).getDescripcion());
            }else{
                boton.setText(mesas.get(i).getDescripcion());
            }
                        
            boton.setSize(120,100);
            boton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    mainFrame.goComandaFrame();
                }
            });
            boton.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    boton.setBackground(new Color(51,255,153).brighter());
                    mainFrame.getComandaFrame().setMesa(idMesa);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            jpMesas.add(boton);
        }
        jifMesas.pack();
        jifMesas.revalidate();
        jifMesas.repaint();
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
        btnLogout = new javax.swing.JButton();
        jifMesas = new javax.swing.JInternalFrame();
        jpMesas = new PanelMesas();
        btn_movil = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu Principal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N

        btn_salon.setBackground(new java.awt.Color(189, 154, 109));
        btn_salon.setText("Salon");
        btn_salon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salonActionPerformed(evt);
            }
        });

        btn_arriba.setBackground(new java.awt.Color(189, 154, 109));
        btn_arriba.setText("Arriba");
        btn_arriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_arribaActionPerformed(evt);
            }
        });

        btn_afuera.setBackground(new java.awt.Color(189, 154, 109));
        btn_afuera.setText("Afuera");
        btn_afuera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_afueraActionPerformed(evt);
            }
        });

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

        btnLogout.setBackground(new java.awt.Color(232, 133, 133));
        btnLogout.setText("Cerrar sesion");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jifMesas.setTitle("Mesas");
        jifMesas.setVisible(true);

        jpMesas.setLayout(new java.awt.GridLayout(4, 4, 25, 25));
        jifMesas.getContentPane().add(jpMesas, java.awt.BorderLayout.CENTER);

        btn_movil.setBackground(new java.awt.Color(189, 154, 109));
        btn_movil.setText("Movil");
        btn_movil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_movilActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario:");

        lblUsuario.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnAbajo, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                        .addComponent(btn_salon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(btn_arriba, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_afuera, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_movil, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jifMesas, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                    .addComponent(jifMesas, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_arribaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_arribaActionPerformed
        // TODO add your handling code here:
        llenarPanelMesas(3);
    }//GEN-LAST:event_btn_arribaActionPerformed

    private void btnAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbajoActionPerformed
        // TODO add your handling code here:
        llenarPanelMesas(4);
    }//GEN-LAST:event_btnAbajoActionPerformed

    private void btn_salonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salonActionPerformed
        // TODO add your handling code here:
        llenarPanelMesas(2);
    }//GEN-LAST:event_btn_salonActionPerformed

    private void btn_afueraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_afueraActionPerformed
        // TODO add your handling code here:
        llenarPanelMesas(5);
    }//GEN-LAST:event_btn_afueraActionPerformed

    private void btn_movilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_movilActionPerformed
        // TODO add your handling code here:
        llenarPanelMesas(99);
    }//GEN-LAST:event_btn_movilActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        mainFrame.goLoginView();
        this.setVisible(false);
    }//GEN-LAST:event_btnLogoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbajo;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btn_afuera;
    private javax.swing.JButton btn_arriba;
    private javax.swing.JButton btn_movil;
    private javax.swing.JButton btn_salon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JInternalFrame jifMesas;
    private javax.swing.JPanel jpMesas;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables

    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaAdministracion.java
 *
 * Created on 12-ene-2012, 23:36:46
 */
package GUI;

import Persistencia.GestionPendrive;
import Persistencia.HiloUSB;
import Persistencia.PersistenciaDeDatos;
import Logica.Logica;

/**
 *
 * @author Usuario
 */
public class VentanaAdministracion extends javax.swing.JFrame {

    /** Creates new form VentanaAdministracion */
    public VentanaAdministracion() {
        initComponents();
         setLocationRelativeTo(null);
         setTitle("Administrador");
         obtenerTokenWeb.setEnabled(Logica.dameLogica().hayConexion());
         transmisionApuestasWeb.setEnabled(Logica.dameLogica().hayConexion());
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        obtenerTokenWeb = new javax.swing.JButton();
        transmisionApuestasWeb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Exportar apuestas Dispositivo Externo (Pendrive)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        obtenerTokenWeb.setText("Obtener Nuevo Token para Transmisión de Apuestas (Web)");
        obtenerTokenWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obtenerTokenWebActionPerformed(evt);
            }
        });

        transmisionApuestasWeb.setText("Transmisión de Apuestas Almacenadas (Web)");
        transmisionApuestasWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transmisionApuestasWebActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(obtenerTokenWeb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(transmisionApuestasWeb, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(142, 142, 142))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obtenerTokenWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transmisionApuestasWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GestionPendrive gestion = new GestionPendrive("apuestas.xml");
        gestion.escribirArchivo();
           // GestionPorArchivo cargar = new GestionPorArchivo();
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Persistencia.HiloUSB.dti.setVentana(new InicioCategoria());
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void obtenerTokenWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obtenerTokenWebActionPerformed
        // TODO add your handling code here:
        Logica.dameLogica().setToken(Persistencia.TransmisorApuestas
                .getInstance().obtenerToken(Logica.dameLogica()
                .getID()));
    }//GEN-LAST:event_obtenerTokenWebActionPerformed

    private void transmisionApuestasWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transmisionApuestasWebActionPerformed
        Persistencia.TransmisorApuestas.getInstance().enviarApuesta();
    }//GEN-LAST:event_transmisionApuestasWebActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton obtenerTokenWeb;
    private javax.swing.JButton transmisionApuestasWeb;
    // End of variables declaration//GEN-END:variables
}
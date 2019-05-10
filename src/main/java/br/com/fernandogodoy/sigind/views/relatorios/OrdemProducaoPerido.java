/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OrdemProducaoPerido.java
 *
 * Created on 07/06/2011, 10:22:20
 */
package br.com.fernandogodoy.sigind.views.relatorios;

import br.com.fernandogodoy.sigind.util.Conexao;
import java.awt.Dialog;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Fernado
 */
public class OrdemProducaoPerido extends javax.swing.JDialog {

    /** Creates new form OrdemProducaoPerido */
    public OrdemProducaoPerido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public OrdemProducaoPerido() {
        initComponents();
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ckReunião = new javax.swing.JRadioButton();
        ckImpressão = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        dtInicio = new net.sf.nachocalendar.components.DateField();
        dtFim = new net.sf.nachocalendar.components.DateField();
        jLabel2 = new javax.swing.JLabel();
        btGerar = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de Ordem de Produção por Período");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatório de Ordem de Produção por Período"));

        ckReunião.setText("Reunião");
        ckReunião.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckReuniãoActionPerformed(evt);
            }
        });

        ckImpressão.setText("Impressão");
        ckImpressão.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckImpressãoActionPerformed(evt);
            }
        });

        jLabel1.setText("Data Inicial:");

        dtInicio.setPreferredSize(new java.awt.Dimension(75, 20));

        dtFim.setPreferredSize(new java.awt.Dimension(75, 20));

        jLabel2.setText("Data Final:");

        btGerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/printer.png"))); // NOI18N
        btGerar.setText("Gerar");
        btGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGerarActionPerformed(evt);
            }
        });

        btCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/exit.png"))); // NOI18N
        btCancel.setText("Sair");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(ckReunião)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ckImpressão)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCancel, btGerar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckReunião)
                    .addComponent(ckImpressão))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGerar)
                    .addComponent(btCancel))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCancel, btGerar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGerarActionPerformed
        try {
            InputStream relatorio;
            Map<Object, Object> parametros = new HashMap<Object, Object>();
            if (ckReunião.isSelected()) {
                relatorio = getClass().getResourceAsStream("/br/com/fernandogodoy/sigind/relatorios/RelatorioGraficoOrdemProducao.jasper");
            } else {
                relatorio = getClass().getResourceAsStream("/br/com/fernandogodoy/sigind/relatorios/RelatorioGraficoOrdemProducaoImpressao.jasper");
            }

            Conexao c = new Conexao();
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, c.getConn());
            final JasperViewer jv = new JasperViewer(impressao, false);
            this.dispose();
            jv.setVisible(true);
            jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível abrir o relatório");
            ex.printStackTrace();

        }
    }//GEN-LAST:event_btGerarActionPerformed

    private void ckReuniãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckReuniãoActionPerformed

        if (ckReunião.isSelected()) {             ckReunião.setSelected(true);             ckImpressão.setSelected(false);         }     }//GEN-LAST:event_ckReuniãoActionPerformed

    private void ckImpressãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckImpressãoActionPerformed

        if (ckImpressão.isSelected()) {             ckReunião.setSelected(false);             ckImpressão.setSelected(true);         }     }//GEN-LAST:event_ckImpressãoActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btGerar;
    private javax.swing.JRadioButton ckImpressão;
    private javax.swing.JRadioButton ckReunião;
    private net.sf.nachocalendar.components.DateField dtFim;
    private net.sf.nachocalendar.components.DateField dtInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OrdemProdRelatorioForm.java
 *
 * Created on 01/11/2010, 12:22:57
 */
package br.com.fernandogodoy.sigind.views.relatorios;

import br.com.fernandogodoy.sigind.dao.relatorios.OrdemProdRelatorioDao;
import br.com.fernandogodoy.sigind.dao.relatorios.OrdemProdRelatorioDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import br.com.fernandogodoy.sigind.models.combomodel.ComboModelStatus;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProdStatus;
import br.com.fernandogodoy.sigind.models.movimentos.PecaOrdemProd;
import br.com.fernandogodoy.sigind.views.cadastros.PecaForm;
import java.awt.Dialog;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Fernando
 */
public class OrdemProdRelatorioForm extends javax.swing.JDialog {

    private PecaForm pf;
    private Peca peca;

    public OrdemProdRelatorioForm() {
        initComponents();
        setLocationRelativeTo(null);
        setModal(true);
        cbStatus.setModel(new ComboModelStatus(OrdemProdStatus.values()));
        tfInicial.setDateFormat(DateFormat.getDateInstance());
        tfFinal.setDateFormat(DateFormat.getDateInstance());
        setVisible(true);
    }

    public OrdemProdRelatorioForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    private void buscaPeca() {
        this.setVisible(false);
        pf = new PecaForm(false);
        if (pf.table.getSelectedRow() != -1) {
            peca = new Peca();
            peca = pf.tmPeca.getLista().get(pf.table.getSelectedRow());
            tfPeca.setText(peca.getPeca());
        }
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfInicial = new net.sf.nachocalendar.components.DateField();
        tfFinal = new net.sf.nachocalendar.components.DateField();
        lbInicial = new javax.swing.JLabel();
        lbFinal = new javax.swing.JLabel();
        lbPeca = new javax.swing.JLabel();
        tfPeca = new javax.swing.JTextField();
        btPeca = new javax.swing.JButton();
        btGerar = new javax.swing.JButton();
        cbStatus = new javax.swing.JComboBox();
        lbStatus = new javax.swing.JLabel();
        btGerar1 = new javax.swing.JButton();
        ckImpressão = new javax.swing.JRadioButton();
        ckReunião = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de Ordem de Produção");

        tfInicial.setPreferredSize(new java.awt.Dimension(75, 20));

        tfFinal.setPreferredSize(new java.awt.Dimension(75, 20));

        lbInicial.setText("Data Inicial:");

        lbFinal.setText("Data Final:");

        lbPeca.setText("Peça:");

        tfPeca.setColumns(30);
        tfPeca.setEditable(false);

        btPeca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btPeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPecaActionPerformed(evt);
            }
        });

        btGerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/printer.png"))); // NOI18N
        btGerar.setText("Gerar");
        btGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGerarActionPerformed(evt);
            }
        });

        lbStatus.setText("Status:");

        btGerar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/exit.png"))); // NOI18N
        btGerar1.setText("Sair");
        btGerar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGerar1ActionPerformed(evt);
            }
        });

        ckImpressão.setText("Impressão");
        ckImpressão.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckImpressãoActionPerformed(evt);
            }
        });

        ckReunião.setText("Reunião");
        ckReunião.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckReuniãoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(ckReunião)
                        .addGap(32, 32, 32)
                        .addComponent(ckImpressão))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPeca)
                            .addComponent(lbInicial)
                            .addComponent(lbStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btGerar1))
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbFinal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfPeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btPeca)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btGerar, btGerar1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckReunião)
                    .addComponent(ckImpressão))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addComponent(tfInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addComponent(lbFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addComponent(lbInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPeca)
                    .addComponent(lbPeca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbStatus)
                    .addComponent(lbStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGerar)
                    .addComponent(btGerar1))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btGerar, btGerar1});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGerarActionPerformed
        try {
            OrdemProdRelatorioDao o = new OrdemProdRelatorioDaoImpl();
            InputStream relatorio;
            List<PecaOrdemProd> lista = o.consulta((Date) tfInicial.getValue(), (Date) tfFinal.getValue(), OrdemProdStatus.getValue(cbStatus.getSelectedIndex()), peca);
            Map<String, String> parametros = new HashMap<String, String>();
            if (ckImpressão.isSelected()) {
                relatorio = getClass().getResourceAsStream("/br/com/fernandogodoy/sigind/relatorios/ordemprodImpressao.jasper");
            } else {
                relatorio = getClass().getResourceAsStream("/br/com/fernandogodoy/sigind/relatorios/ordemprod.jasper");
            }

            JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(lista);
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, dados);
            final JasperViewer jv = new JasperViewer(impressao, false);
            this.dispose();
            jv.setVisible(true);
            jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        }
    }//GEN-LAST:event_btGerarActionPerformed

    private void btPecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPecaActionPerformed
        buscaPeca();
    }//GEN-LAST:event_btPecaActionPerformed

    private void btGerar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGerar1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btGerar1ActionPerformed

    private void ckImpressãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckImpressãoActionPerformed

        if (ckImpressão.isSelected()) {             ckReunião.setSelected(false);             ckImpressão.setSelected(true);         }     }//GEN-LAST:event_ckImpressãoActionPerformed

    private void ckReuniãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckReuniãoActionPerformed

        if (ckReunião.isSelected()) {             ckReunião.setSelected(true);             ckImpressão.setSelected(false);         }     }//GEN-LAST:event_ckReuniãoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGerar;
    private javax.swing.JButton btGerar1;
    private javax.swing.JButton btPeca;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JRadioButton ckImpressão;
    private javax.swing.JRadioButton ckReunião;
    private javax.swing.JLabel lbFinal;
    private javax.swing.JLabel lbInicial;
    private javax.swing.JLabel lbPeca;
    private javax.swing.JLabel lbStatus;
    private net.sf.nachocalendar.components.DateField tfFinal;
    private net.sf.nachocalendar.components.DateField tfInicial;
    private javax.swing.JTextField tfPeca;
    // End of variables declaration//GEN-END:variables
}

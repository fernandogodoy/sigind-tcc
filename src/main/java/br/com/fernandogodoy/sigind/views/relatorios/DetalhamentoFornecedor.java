/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DetalhamentoFornecedor.java
 *
 * Created on 04/06/2011, 17:15:55
 */
package br.com.fernandogodoy.sigind.views.relatorios;

import br.com.fernandogodoy.sigind.dao.cadastros.FornecedorDao;
import br.com.fernandogodoy.sigind.dao.cadastros.FornecedorDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Fornecedor;
import br.com.fernandogodoy.sigind.models.cadastros.TipoRelatorio;
import br.com.fernandogodoy.sigind.view.buscas.BuscaForm;
import java.awt.Dialog;
import java.io.InputStream;
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
 * @author Fernado
 */
public class DetalhamentoFornecedor extends javax.swing.JDialog {

    public Fornecedor fornecedor;

    /** Creates new form DetalhamentoFornecedor */
    public DetalhamentoFornecedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    public DetalhamentoFornecedor() {
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
        jLabel1 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        btBuscar = new javax.swing.JButton();
        btGerar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        ckReunião = new javax.swing.JRadioButton();
        ckImpressão = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalhamento do Fornecedor");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatório de Fornecedor"));

        jLabel1.setText("Nome:");

        tfNome.setColumns(35);
        tfNome.setEditable(false);

        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        btGerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/printer.png"))); // NOI18N
        btGerar.setText("Gerar");
        btGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGerarActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/exit.png"))); // NOI18N
        btCancelar.setText("Sair");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(ckReunião)
                        .addGap(32, 32, 32)
                        .addComponent(ckImpressão))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btCancelar)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCancelar, btGerar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckReunião)
                    .addComponent(ckImpressão))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btBuscar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGerar)
                    .addComponent(btCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCancelar, btGerar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        this.setVisible(false);
        BuscaForm bc = new BuscaForm(TipoRelatorio.DETALHAMENTO_FORNECEDOR);
        if (bc.tabBusca.getSelectedRow() != -1) {
            fornecedor = (bc.getTmFornecedor().getLista().get(bc.tabBusca.getSelectedRow()));
            tfNome.setText(fornecedor.getPessoa().getNomePessoa());
        }
        this.setVisible(true);
    }//GEN-LAST:event_btBuscarActionPerformed

    private void btGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGerarActionPerformed
        try {
            FornecedorDao daoR = new FornecedorDaoImpl();
            InputStream relatorio;
            List<Fornecedor> lista = daoR.consultaFornecedor(tfNome.getText());
            Map<String, String> parametros = new HashMap<String, String>();
            if (ckReunião.isSelected()) {
                relatorio = getClass().getResourceAsStream("/br/com/fernandogodoy/sigind/relatorios/RelatorioDetalhamentoFornecedor.jasper");
            } else {
                relatorio = getClass().getResourceAsStream("/br/com/fernandogodoy/sigind/relatorios/RelatorioDetalhamentoFornecedorImpressao.jasper");
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

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
}//GEN-LAST:event_btCancelarActionPerformed

    private void ckReuniãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckReuniãoActionPerformed

        if (ckReunião.isSelected()) {             ckReunião.setSelected(true);             ckImpressão.setSelected(false);         }     }//GEN-LAST:event_ckReuniãoActionPerformed

    private void ckImpressãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckImpressãoActionPerformed

        if (ckImpressão.isSelected()) {             ckReunião.setSelected(false);             ckImpressão.setSelected(true);         }     }//GEN-LAST:event_ckImpressãoActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btGerar;
    private javax.swing.JRadioButton ckImpressão;
    private javax.swing.JRadioButton ckReunião;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}

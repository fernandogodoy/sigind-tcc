/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OrdemProdStatusForm.java
 *
 * Created on 29/10/2010, 10:38:01
 */

package br.com.fernandogodoy.sigind.views.movimentos;

import br.com.fernandogodoy.sigind.dao.movimentos.OrdemProdDao;
import br.com.fernandogodoy.sigind.dao.movimentos.OrdemProdDaoImpl;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProd;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProdStatus;
import br.com.fernandogodoy.sigind.views.principais.Login;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Fernando
 */
public class OrdemProdStatusForm extends javax.swing.JDialog {

    private OrdemProd ordemProd;
    private OrdemProdDao dao;

    public OrdemProdStatusForm(OrdemProd ordemProd) {
        initComponents();
        setLocationRelativeTo(null);
        setModal(true);
        dao = new OrdemProdDaoImpl();
        this.ordemProd = ordemProd;
        statusOrdem();
        setVisible(true);
    }

    public OrdemProdStatusForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    private void botoes(Boolean a, Boolean r, Boolean p, Boolean f, Boolean c) {
        btAprovar.setEnabled(a);
        btReprovar.setEnabled(r);
        btProduzir.setEnabled(p);
        btFinalizar.setEnabled(f);
        btCancelar.setEnabled(c);
    }

    private void statusOrdem() {
        if(ordemProd.getOrdemProdStatus() == OrdemProdStatus.EM_APROVACAO) {
            botoes(true, true, false, false, false);
        } else if(ordemProd.getOrdemProdStatus() == OrdemProdStatus.APROVADA) {
            botoes(false, false, true, false, true);
        } else if(ordemProd.getOrdemProdStatus() == OrdemProdStatus.EM_PRODUCAO) {
            botoes(false, false, false, true, false);
        } else if(ordemProd.getOrdemProdStatus() == OrdemProdStatus.FINALIZADA) {
            botoes(false, false, false, false, false);
        } else {
            botoes(false, false, false, false, false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btAprovar = new javax.swing.JButton();
        btProduzir = new javax.swing.JButton();
        btFinalizar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btReprovar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btAprovar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/aprovar.png"))); // NOI18N
        btAprovar.setText("Aprovar");
        btAprovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAprovarActionPerformed(evt);
            }
        });

        btProduzir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/produzir.png"))); // NOI18N
        btProduzir.setText("Produzir");
        btProduzir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProduzirActionPerformed(evt);
            }
        });

        btFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/save.png"))); // NOI18N
        btFinalizar.setText("Finalizar");
        btFinalizar.setPreferredSize(new java.awt.Dimension(71, 35));
        btFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/cancel.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btReprovar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/reprovar.png"))); // NOI18N
        btReprovar.setText("Reprovar");
        btReprovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReprovarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAprovar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btReprovar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btProduzir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAprovar, btCancelar, btFinalizar, btProduzir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAprovar)
                    .addComponent(btProduzir)
                    .addComponent(btFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancelar)
                    .addComponent(btReprovar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAprovar, btCancelar, btFinalizar, btProduzir, btReprovar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAprovarActionPerformed
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente aprovar esta ordem de produção?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        if (sim == JOptionPane.YES_OPTION) {
            ordemProd.setOrdemProdStatus(OrdemProdStatus.APROVADA);
            ordemProd.setFuncionario(Login.usuario.getFuncionario());
            dao.save(ordemProd);
            this.dispose();
            JOptionPane.showMessageDialog(null, "Ordem de produção salva com sucesso");
        }
    }//GEN-LAST:event_btAprovarActionPerformed

    private void btReprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReprovarActionPerformed
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente reprovar esta ordem de produção?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        if (sim == JOptionPane.YES_OPTION) {
            ordemProd.setOrdemProdStatus(OrdemProdStatus.REPROVADA);
            ordemProd.setFuncionario(Login.usuario.getFuncionario());
            dao.save(ordemProd);
            JOptionPane.showMessageDialog(null, "Ordem de produção salva com sucesso");
            this.dispose();
        }
    }//GEN-LAST:event_btReprovarActionPerformed

    private void btProduzirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProduzirActionPerformed
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente produzir esta ordem de produção?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        if (sim == JOptionPane.YES_OPTION) {
            ordemProd.setOrdemProdStatus(OrdemProdStatus.EM_PRODUCAO);
            ordemProd.setFuncionario(Login.usuario.getFuncionario());
            dao.save(ordemProd);
            JOptionPane.showMessageDialog(null, "Ordem de produção salva com sucesso");
            this.dispose();
        }
    }//GEN-LAST:event_btProduzirActionPerformed

    private void btFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarActionPerformed
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente finalizar esta ordem de produção?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        if (sim == JOptionPane.YES_OPTION) {
            ordemProd.setOrdemProdStatus(OrdemProdStatus.FINALIZADA);
            ordemProd.setFuncionario(Login.usuario.getFuncionario());
            dao.save(ordemProd);
            JOptionPane.showMessageDialog(null, "Ordem de produção salva com sucesso");
            this.dispose();
        }
    }//GEN-LAST:event_btFinalizarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar esta ordem de produção?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        if (sim == JOptionPane.YES_OPTION) {
            ordemProd.setOrdemProdStatus(OrdemProdStatus.CANCELADA);
            ordemProd.setFuncionario(Login.usuario.getFuncionario());
            dao.save(ordemProd);
            JOptionPane.showMessageDialog(null, "Ordem de produção salva com sucesso");
            this.dispose();
        }
    }//GEN-LAST:event_btCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAprovar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btFinalizar;
    private javax.swing.JButton btProduzir;
    private javax.swing.JButton btReprovar;
    // End of variables declaration//GEN-END:variables

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuForm.java
 *
 * Created on 08/07/2010, 19:04:13
 */

package br.com.fernandogodoy.sigind.views.cadastros;

/**
 *
 * @author Fernando
 */
public abstract class MenuForm extends javax.swing.JDialog {

    public abstract void novo();
    public abstract void salvar();
    public abstract void alterar();
    public abstract void consultar();
    public abstract void excluir();
    public abstract void cancelar();

    private static Boolean incluir;
    private static Boolean alterar;
    private static Boolean consultar;
    private static Boolean excluir;

    public MenuForm(String privilegios) {
        initComponents();
        incluir = privilegios.charAt(0) == '1' ? true : false;
        alterar = privilegios.charAt(1) == '1' ? true : false;
        consultar = privilegios.charAt(2) == '1' ? true : false;
        excluir = privilegios.charAt(3) == '1' ? true : false;
        habCancelar();
    }
    
    public MenuForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    private void botoes(Boolean... b) {
        btNovo.setEnabled(b[0]);
        btAlterar.setEnabled(b[1]);
        btSalvar.setEnabled(b[2]);
        btConsultar.setEnabled(b[3]);
        btExcluir.setEnabled(b[4]);
        btCancelar.setEnabled(b[5]);
        btSair.setEnabled(b[6]);
    }

    protected void habNenhum() {
        botoes(false, false, false, false, false, false, true);
    }
    
    protected void habAlterar() {
//            botoes(false, true, false, true, true, true, true);
        botoes(false, alterar, false, consultar, excluir, true, true);
    }

    protected void habSalvar() {
//        botoes(true, false, false, true, false, false, true);
        botoes(incluir, false, false, consultar, false, false, true);
    }

    protected void habExcluir() {
//        botoes(true, false, false, true, false, false, true);
        botoes(incluir, false, false, consultar, false, false, true);
    }

    protected void habCancelar() {
//        botoes(true, false, false, true, false, false, true);
        botoes(incluir, false, false, consultar, false, false, true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        btNovo = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btConsultar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelMenu.setPreferredSize(new java.awt.Dimension(750, 50));

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/add.png"))); // NOI18N
        btNovo.setText("Novo");
        btNovo.setPreferredSize(new java.awt.Dimension(77, 35));
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/edit.png"))); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/save.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/search.png"))); // NOI18N
        btConsultar.setText("Consultar");
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultarActionPerformed(evt);
            }
        });

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/delete.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/cancel.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/exit.png"))); // NOI18N
        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btConsultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMenuLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAlterar, btCancelar, btConsultar, btExcluir, btNovo, btSair, btSalvar});

        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAlterar)
                    .addComponent(btSalvar)
                    .addComponent(btConsultar)
                    .addComponent(btExcluir)
                    .addComponent(btCancelar)
                    .addComponent(btSair))
                .addContainerGap())
        );

        panelMenuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAlterar, btCancelar, btConsultar, btExcluir, btNovo, btSair, btSalvar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(412, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        botoes(false, false, true, false, false, true, true);
        novo();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        botoes(false, false, true, false, false, true, true);
        alterar();
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarActionPerformed
//        botoes(true, false, false, false, false, true, true);
        botoes(incluir, false, false, false, false, true, true);
        consultar();        
    }//GEN-LAST:event_btConsultarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        habCancelar();
        cancelar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        dispose();
    }//GEN-LAST:event_btSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btConsultar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalvar;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables

}
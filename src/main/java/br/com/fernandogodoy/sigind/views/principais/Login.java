/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Login.java
 *
 * Created on 10/08/2010, 17:53:21
 */
package br.com.fernandogodoy.sigind.views.principais;

import br.com.fernandogodoy.sigind.dao.cadastros.UsuarioDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Usuario;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Fernando
 */
public class Login extends javax.swing.JFrame {

    public static Usuario usuario = new Usuario();

    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String converte(char[] c) {
        String s = "";
        for (char ch : c) {
            s = s + ch;
        }
        return s;
    }

    private void efetuarLogin() {
        UsuarioDaoImpl ud = new UsuarioDaoImpl();
        usuario = ud.validaLogin(tfUsuario.getText().trim(), converte(tfSenha.getPassword()));
        if (usuario == null) {           
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha errado(s).");
        } else {
            this.setVisible(false);
            new Principal().setVisible(true);
        }
    }

    private static void carregaLogin() {
        new HibernateUtility();
        pbInicial.setVisible(false);
        tfUsuario.setEditable(true);
        tfSenha.setEditable(true);
        btEntrar.setEnabled(true);
        btCancelar.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btEntrar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        tfUsuario = new javax.swing.JTextField();
        tfSenha = new javax.swing.JPasswordField();
        lbUsuario = new javax.swing.JLabel();
        lbSenha = new javax.swing.JLabel();
        pbInicial = new javax.swing.JProgressBar();
        imgBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btEntrar.setText("Entrar");
        btEntrar.setEnabled(false);
        btEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 245, 75, -1));

        btCancelar.setText("Cancelar");
        btCancelar.setEnabled(false);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 245, -1, -1));

        tfUsuario.setColumns(20);
        tfUsuario.setEditable(false);
        tfUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfUsuarioKeyPressed(evt);
            }
        });
        getContentPane().add(tfUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 193, -1, -1));

        tfSenha.setColumns(20);
        tfSenha.setEditable(false);
        tfSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfSenhaKeyPressed(evt);
            }
        });
        getContentPane().add(tfSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 219, -1, -1));

        lbUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbUsuario.setText("Usuário:");
        getContentPane().add(lbUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 200, 50, -1));

        lbSenha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbSenha.setText("Senha:");
        getContentPane().add(lbSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        pbInicial.setForeground(new java.awt.Color(0, 153, 255));
        pbInicial.setValue(100);
        pbInicial.setIndeterminate(true);
        pbInicial.setString("Carregando...");
        pbInicial.setStringPainted(true);
        getContentPane().add(pbInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 400, 20));

        imgBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/telaTeste.jpg"))); // NOI18N
        getContentPane().add(imgBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEntrarActionPerformed
        efetuarLogin();
    }//GEN-LAST:event_btEntrarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btCancelarActionPerformed

    private void tfSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSenhaKeyPressed
        if (evt.getKeyCode() == 10) {
            efetuarLogin();
        }
    }//GEN-LAST:event_tfSenhaKeyPressed

    private void tfUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfUsuarioKeyPressed
        if (evt.getKeyCode() == 10) {
            efetuarLogin();
        }
    }//GEN-LAST:event_tfUsuarioKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception x) {
            x.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Login().setVisible(true);
            }
        });
        carregaLogin();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton btCancelar;
    private static javax.swing.JButton btEntrar;
    private javax.swing.JLabel imgBackground;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbUsuario;
    private static javax.swing.JProgressBar pbInicial;
    private static javax.swing.JPasswordField tfSenha;
    private static javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}

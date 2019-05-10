/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 07/07/2010, 00:42:18
 */
package br.com.fernandogodoy.sigind.views.principais;

import br.com.fernandogodoy.sigind.dao.cadastros.ClienteDao;
import br.com.fernandogodoy.sigind.dao.cadastros.ClienteDaoImpl;
import br.com.fernandogodoy.sigind.dao.cadastros.FornecedorDao;
import br.com.fernandogodoy.sigind.dao.cadastros.FornecedorDaoImpl;
import br.com.fernandogodoy.sigind.dao.cadastros.FuncionarioDao;
import br.com.fernandogodoy.sigind.dao.cadastros.FuncionarioDaoImpl;
import br.com.fernandogodoy.sigind.dao.cadastros.UsuarioDao;
import br.com.fernandogodoy.sigind.dao.cadastros.UsuarioDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Cliente;
import br.com.fernandogodoy.sigind.models.cadastros.Fornecedor;
import br.com.fernandogodoy.sigind.models.cadastros.Funcionario;
import br.com.fernandogodoy.sigind.models.cadastros.Usuario;
import br.com.fernandogodoy.sigind.util.AtualizarRelogio;
import br.com.fernandogodoy.sigind.util.Backup;
import br.com.fernandogodoy.sigind.views.cadastros.ClienteForm;
import br.com.fernandogodoy.sigind.views.cadastros.EmpresaForm;
import br.com.fernandogodoy.sigind.views.cadastros.FornecedorForm;
import br.com.fernandogodoy.sigind.views.cadastros.FuncaoForm;
import br.com.fernandogodoy.sigind.views.cadastros.FuncionarioForm;
import br.com.fernandogodoy.sigind.views.cadastros.MaquinaForm;
import br.com.fernandogodoy.sigind.views.cadastros.MarcaForm;
import br.com.fernandogodoy.sigind.views.cadastros.MatPrimaForm;
import br.com.fernandogodoy.sigind.views.cadastros.PecaForm;
import br.com.fernandogodoy.sigind.views.cadastros.PessoaForm;
import br.com.fernandogodoy.sigind.views.cadastros.SetorForm;
import br.com.fernandogodoy.sigind.views.cadastros.UsuarioForm;
import br.com.fernandogodoy.sigind.views.movimentos.ComprasForm;
import br.com.fernandogodoy.sigind.views.movimentos.ExpedicaoForm;
import br.com.fernandogodoy.sigind.views.movimentos.OrdemProducaoForm;
import br.com.fernandogodoy.sigind.views.movimentos.PagamentoContaForm;
import br.com.fernandogodoy.sigind.views.movimentos.RecebimentoContaForm;
import br.com.fernandogodoy.sigind.views.movimentos.VendasForm;
import br.com.fernandogodoy.sigind.views.relatorios.ClientesMaisCompram;
import br.com.fernandogodoy.sigind.views.relatorios.DetalhamentoCliente;
import br.com.fernandogodoy.sigind.views.relatorios.DetalhamentoFornecedor;
import br.com.fernandogodoy.sigind.views.relatorios.DetalhamentoFuncionario;
import br.com.fernandogodoy.sigind.views.relatorios.DetalhamentoUsuario;
import br.com.fernandogodoy.sigind.views.relatorios.EstoquePecaGrafico;
import br.com.fernandogodoy.sigind.views.relatorios.MaioresFornecedores;
import br.com.fernandogodoy.sigind.views.relatorios.PecaMaisVendidaGrafico;
import br.com.fernandogodoy.sigind.views.relatorios.OrdemProdRelatorioForm;
import br.com.fernandogodoy.sigind.views.relatorios.OrdemProducaoPerido;

import br.com.fernandogodoy.sigind.views.relatorios.RelCompraForm;
import br.com.fernandogodoy.sigind.views.relatorios.RelContasPagarForm;
import br.com.fernandogodoy.sigind.views.relatorios.RelContasReceberForm;
import br.com.fernandogodoy.sigind.views.relatorios.RelVendaForm;
import java.awt.Dialog;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author altitdb
 */
public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("SIGCOM - Sistema Integrado de Gerenciamento Comercial - [ Usuário: " + Login.usuario.getUsuario() + " ]");
        mostrarHora();
        menuCliente.setVisible(Login.usuario.getClienteUsuario().equals("0000") ? false : true);
        menuEmpresa.setVisible(Login.usuario.getEmpresaUsuario().equals("0000") ? false : true);
        menuFornecedor.setVisible(Login.usuario.getFornecedorUsuario().equals("0000") ? false : true);
        menuFuncao.setVisible(Login.usuario.getFuncaoUsuario().equals("0000") ? false : true);
        menuFuncionario.setVisible(Login.usuario.getFuncionarioUsuario().equals("0000") ? false : true);
        menuMaquina.setVisible(Login.usuario.getMaquinaUsuario().equals("0000") ? false : true);
        menuMarca.setVisible(Login.usuario.getMarcaUsuario().equals("0000") ? false : true);
        menuMatPrima.setVisible(Login.usuario.getMateriaUsuario().equals("0000") ? false : true);
        menuPeca.setVisible(Login.usuario.getPecaUsuario().equals("0000") ? false : true);
        menuPessoa.setVisible(Login.usuario.getPessoaUsuario().equals("0000") ? false : true);
        menuSetor.setVisible(Login.usuario.getSetorUsuario().equals("0000") ? false : true);
        menuUsuario.setVisible(Login.usuario.getUsuarioUsuario().equals("0000") ? false : true);
//            setVisible(true);
//        menuCliente.setVisible(true); //OK
//        menuEmpresa.setVisible(true); //OK
//        menuFornecedor.setVisible(true); //OK
//        menuFuncao.setVisible(true); //OK
//        menuFuncionario.setVisible(true); //OK
//        menuMaquina.setVisible(true); //OK
//        menuMarca.setVisible(true); //OK
//        menuMatPrima.setVisible(true); //OK
//        menuPeca.setVisible(true); //OK
//        menuPessoa.setVisible(true); //OK
//        menuSetor.setVisible(true); //OK
//        menuUsuario.setVisible(true); //OK
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelHora = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        menuEmpresa = new javax.swing.JMenuItem();
        menuCliente = new javax.swing.JMenuItem();
        menuFornecedor = new javax.swing.JMenuItem();
        menuFuncionario = new javax.swing.JMenuItem();
        menuPessoa = new javax.swing.JMenuItem();
        menuSetor = new javax.swing.JMenuItem();
        menuFuncao = new javax.swing.JMenuItem();
        menuMatPrima = new javax.swing.JMenuItem();
        menuPeca = new javax.swing.JMenuItem();
        menuMarca = new javax.swing.JMenuItem();
        menuMaquina = new javax.swing.JMenuItem();
        menuUsuario = new javax.swing.JMenuItem();
        menuMovimentos = new javax.swing.JMenu();
        menuOdemProducao = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );

        labelHora.setFont(new java.awt.Font("Tahoma", 1, 24));
        labelHora.setForeground(new java.awt.Color(255, 0, 51));
        labelHora.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelHora.setText("00/00/00 - 00:00:00");

        menuCadastros.setText("Cadastros");

        menuEmpresa.setText("Empresa");
        menuEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEmpresaActionPerformed(evt);
            }
        });
        menuCadastros.add(menuEmpresa);

        menuCliente.setText("Cliente");
        menuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClienteActionPerformed(evt);
            }
        });
        menuCadastros.add(menuCliente);

        menuFornecedor.setText("Fornecedor");
        menuFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFornecedorActionPerformed(evt);
            }
        });
        menuCadastros.add(menuFornecedor);

        menuFuncionario.setText("Funcionário");
        menuFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFuncionarioActionPerformed(evt);
            }
        });
        menuCadastros.add(menuFuncionario);

        menuPessoa.setText("Pessoa");
        menuPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPessoaActionPerformed(evt);
            }
        });
        menuCadastros.add(menuPessoa);

        menuSetor.setText("Setor");
        menuSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSetorActionPerformed(evt);
            }
        });
        menuCadastros.add(menuSetor);

        menuFuncao.setText("Função");
        menuFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFuncaoActionPerformed(evt);
            }
        });
        menuCadastros.add(menuFuncao);

        menuMatPrima.setText("Materia Prima");
        menuMatPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMatPrimaActionPerformed(evt);
            }
        });
        menuCadastros.add(menuMatPrima);

        menuPeca.setText("Peça");
        menuPeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPecaActionPerformed(evt);
            }
        });
        menuCadastros.add(menuPeca);

        menuMarca.setText("Marca");
        menuMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMarcaActionPerformed(evt);
            }
        });
        menuCadastros.add(menuMarca);

        menuMaquina.setText("Maquina");
        menuMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMaquinaActionPerformed(evt);
            }
        });
        menuCadastros.add(menuMaquina);

        menuUsuario.setText("Usuário");
        menuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUsuarioActionPerformed(evt);
            }
        });
        menuCadastros.add(menuUsuario);

        menuBar.add(menuCadastros);

        menuMovimentos.setText("Movimentos");

        menuOdemProducao.setText("Ordem de produção");
        menuOdemProducao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOdemProducaoActionPerformed(evt);
            }
        });
        menuMovimentos.add(menuOdemProducao);

        jMenuItem2.setText("Compra");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuMovimentos.add(jMenuItem2);

        jMenuItem3.setText("Venda");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuMovimentos.add(jMenuItem3);

        jMenuItem9.setText("Pagamento de Contas");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menuMovimentos.add(jMenuItem9);

        jMenuItem10.setText("Recebimento de Contas");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        menuMovimentos.add(jMenuItem10);

        jMenuItem25.setText("Expedição");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        menuMovimentos.add(jMenuItem25);

        menuBar.add(menuMovimentos);

        jMenu1.setText("Relatórios");

        jMenu3.setText("Cadastral");

        jMenu5.setText("Cliente");

        jMenuItem4.setText("Detalhamento Cliente");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuItem11.setText("Lista de Clientes");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenu3.add(jMenu5);

        jMenu6.setText("Funcionário");

        jMenuItem5.setText("Detalhamento Funcionário");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem5);

        jMenuItem12.setText("Lista de Funcionário");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenu3.add(jMenu6);

        jMenu7.setText("Fornecedor");

        jMenuItem8.setText("Detalhamento Fornecedor");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem8);

        jMenuItem13.setText("Lista de Fornecedores");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenu3.add(jMenu7);

        jMenu8.setText("Usuário");

        jMenuItem6.setText("Detalhamento Usuario");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem6);

        jMenuItem14.setText("Lista de Usuários");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem14);

        jMenu3.add(jMenu8);

        jMenu1.add(jMenu3);

        jMenu2.setText("Gerencial");

        jMenuItem1.setText("Ordem de Produção");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem15.setText("Compras por Período");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem16.setText("Venda por Período");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem16);

        jMenuItem17.setText("Contas a Pagar");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        jMenuItem18.setText("Contas a Receber");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem18);

        jMenuItem20.setText("Estoque de Matéria Prima");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem20);

        jMenu1.add(jMenu2);

        jMenu4.setText("Estratégico");

        jMenuItem7.setText("Estatistica Ordem Produção");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem19.setText("Estatisca de Venda de Peças");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem19);

        jMenuItem21.setText("Estatisca Compra de Clientes");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem21);

        jMenuItem22.setText("Estatisca Maiores Fornecedores");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem22);

        jMenu1.add(jMenu4);

        menuBar.add(jMenu1);

        jMenu9.setText("Backup");

        jMenuItem23.setText("Backup");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem23);

        menuBar.add(jMenu9);

        jMenu10.setText("Ajuda");

        jMenuItem24.setText("Sobre");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem24);

        menuBar.add(jMenu10);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHora, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClienteActionPerformed
        ClienteForm clienteForm = new ClienteForm(true);
    }//GEN-LAST:event_menuClienteActionPerformed

    private void menuFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFornecedorActionPerformed
        FornecedorForm fornecedorForm = new FornecedorForm(true);
    }//GEN-LAST:event_menuFornecedorActionPerformed

    private void menuFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFuncionarioActionPerformed
        FuncionarioForm funcionarioForm = new FuncionarioForm(true);
    }//GEN-LAST:event_menuFuncionarioActionPerformed

    private void menuPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPessoaActionPerformed
        PessoaForm pessoaForm = new PessoaForm(true);
    }//GEN-LAST:event_menuPessoaActionPerformed

    private void menuSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSetorActionPerformed
        SetorForm setorForm = new SetorForm(true);
    }//GEN-LAST:event_menuSetorActionPerformed

    private void menuFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFuncaoActionPerformed
        FuncaoForm funcaoForm = new FuncaoForm(true);
    }//GEN-LAST:event_menuFuncaoActionPerformed

    private void menuMatPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMatPrimaActionPerformed
        MatPrimaForm matPrimaForm = new MatPrimaForm(true);
    }//GEN-LAST:event_menuMatPrimaActionPerformed

    private void menuPecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPecaActionPerformed
        PecaForm pecaForm = new PecaForm(true);
    }//GEN-LAST:event_menuPecaActionPerformed

    private void menuMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMarcaActionPerformed
        MarcaForm marcaForm = new MarcaForm(true);
    }//GEN-LAST:event_menuMarcaActionPerformed

    private void menuMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMaquinaActionPerformed
        MaquinaForm maquinaForm = new MaquinaForm();
    }//GEN-LAST:event_menuMaquinaActionPerformed

    private void menuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUsuarioActionPerformed
        UsuarioForm usuarioForm = new UsuarioForm();
    }//GEN-LAST:event_menuUsuarioActionPerformed

    private void menuEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEmpresaActionPerformed
        EmpresaForm empresaForm = new EmpresaForm(true);
    }//GEN-LAST:event_menuEmpresaActionPerformed

    private void menuOdemProducaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOdemProducaoActionPerformed
        OrdemProducaoForm ordemOProducaoForm = new OrdemProducaoForm();
    }//GEN-LAST:event_menuOdemProducaoActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    ComprasForm cf = new ComprasForm(true);
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    VendasForm vf = new VendasForm(true);
}//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        OrdemProdRelatorioForm ordemProdRelatorioForm = new OrdemProdRelatorioForm();     }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        DetalhamentoCliente dc = new DetalhamentoCliente();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        DetalhamentoFuncionario df = new DetalhamentoFuncionario();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        DetalhamentoUsuario du = new DetalhamentoUsuario();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        DetalhamentoFornecedor dff = new DetalhamentoFornecedor();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        OrdemProducaoPerido op = new OrdemProducaoPerido();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        PagamentoContaForm pc = new PagamentoContaForm(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        RecebimentoContaForm rc = new RecebimentoContaForm(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        try {
            ClienteDao daoR = new ClienteDaoImpl();
            List<Cliente> lista = daoR.consultaCliente();
            Map<String, String> parametros = new HashMap<String, String>();
            InputStream relatorio = getClass().getResourceAsStream("/br/com/fernandogodoy/sigind/relatorios/ListaCliente.jasper");
            JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(lista);
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, dados);
            final JasperViewer jv = new JasperViewer(impressao, false);
            jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            jv.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        try {
            FuncionarioDao daoR = new FuncionarioDaoImpl();
            List<Funcionario> lista = daoR.consultaFuncionario();
            Map<String, String> parametros = new HashMap<String, String>();
            InputStream relatorio = getClass().getResourceAsStream("/br/com/fernandogodoy/sigind/relatorios/ListaFuncionario.jasper");
            JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(lista);
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, dados);
            final JasperViewer jv = new JasperViewer(impressao, false);
            jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            jv.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        try {
            FornecedorDao daoR = new FornecedorDaoImpl();
            List<Fornecedor> lista = daoR.consultaFornecedor();
            Map<String, String> parametros = new HashMap<String, String>();
            InputStream relatorio = getClass().getResourceAsStream("/br/com/fernandogodoy/sigind/relatorios/ListaFornecedor.jasper");
            JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(lista);
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, dados);
            final JasperViewer jv = new JasperViewer(impressao, false);
            jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            jv.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        try {
            UsuarioDao daoR = new UsuarioDaoImpl();
            List<Usuario> lista = daoR.consultaUsuario();
            Map<String, String> parametros = new HashMap<String, String>();
            InputStream relatorio = getClass().getResourceAsStream("/br/com/fernandogodoy/sigind/relatorios/ListaUsuario.jasper");
            JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(lista);
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, dados);
            final JasperViewer jv = new JasperViewer(impressao, false);
            jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            jv.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        RelCompraForm rcf = new RelCompraForm();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        RelVendaForm rvf = new RelVendaForm();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        RelContasPagarForm rpf = new RelContasPagarForm();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        RelContasReceberForm rcpf = new RelContasReceberForm();
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        PecaMaisVendidaGrafico epf = new PecaMaisVendidaGrafico();
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        EstoquePecaGrafico epg = new EstoquePecaGrafico();
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        ClientesMaisCompram cmc = new ClientesMaisCompram();
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        MaioresFornecedores mf = new MaioresFornecedores();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        try {
            Backup bk = new Backup();
            bk.realizaBackup();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        SobreForm sf = new SobreForm();
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
      ExpedicaoForm exf = new ExpedicaoForm();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelHora;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenuItem menuCliente;
    private javax.swing.JMenuItem menuEmpresa;
    private javax.swing.JMenuItem menuFornecedor;
    private javax.swing.JMenuItem menuFuncao;
    private javax.swing.JMenuItem menuFuncionario;
    private javax.swing.JMenuItem menuMaquina;
    private javax.swing.JMenuItem menuMarca;
    private javax.swing.JMenuItem menuMatPrima;
    private javax.swing.JMenu menuMovimentos;
    private javax.swing.JMenuItem menuOdemProducao;
    private javax.swing.JMenuItem menuPeca;
    private javax.swing.JMenuItem menuPessoa;
    private javax.swing.JMenuItem menuSetor;
    private javax.swing.JMenuItem menuUsuario;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception x) {
            x.printStackTrace();
        }
        new Principal();
    }

    public final void mostrarHora() {
        AtualizarRelogio ah = new AtualizarRelogio(labelHora);
        ah.mostrarData(true);
        Thread thHora = ah;
        thHora.start();
    }
}

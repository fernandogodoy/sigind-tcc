/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OrdemProducao.java
 *
 * Created on 27/10/2010, 09:31:42
 */
package br.com.fernandogodoy.sigind.views.movimentos;

import br.com.fernandogodoy.sigind.dao.movimentos.OrdemProdDao;
import br.com.fernandogodoy.sigind.dao.movimentos.OrdemProdDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import br.com.fernandogodoy.sigind.models.combomodel.ComboModelStatus;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProd;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProdStatus;
import br.com.fernandogodoy.sigind.models.movimentos.PecaOrdemProd;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelOrdemProd;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelProdOrdemProd;
import br.com.fernandogodoy.sigind.util.DocumentRightLeft;
import br.com.fernandogodoy.sigind.views.cadastros.PecaForm;
import br.com.fernandogodoy.sigind.views.principais.Login;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class OrdemProducaoForm extends javax.swing.JDialog {

    private OrdemProd ordemProd;
    private TableModelProdOrdemProd tmProdOrdemProd;
    private PecaForm pf;
    private PecaOrdemProd pecaOrdemProd;
    private Peca peca;
    private OrdemProdDao dao;
    private List<OrdemProd> lista;
    private int i, max;

    public OrdemProducaoForm() {
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        tfDtInclusao.setDateFormat(DateFormat.getDateInstance());
        tfDtProducao.setDateFormat(DateFormat.getDateInstance());
        tfDtInicial.setDateFormat(DateFormat.getDateInstance());
        tfDtFinal.setDateFormat(DateFormat.getDateInstance());
        tfCodPeca.setDocument(new DocumentRightLeft((5)));
        tfQuantidade.setDocument(new DocumentRightLeft((7)));
        dao = new OrdemProdDaoImpl();
        tabelaPecaOrdemProd.setModel(new TableModelProdOrdemProd(null));
        lista = new ArrayList<OrdemProd>();
        cancelar();
        botoes(true, false, false, true, true);
        cbStatus.setModel(new ComboModelStatus(OrdemProdStatus.values()));
        tabela.setModel(new TableModelOrdemProd(null));
        setVisible(true);
    }

    public OrdemProducaoForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    public OrdemProd getOrdemProd() {
        return ordemProd;
    }

    public void setOrdemProd(OrdemProd ordemProd) {
        this.ordemProd = ordemProd;
    }

    private void botoes(Boolean novo, Boolean salvar, Boolean status, Boolean cancelar, Boolean consultar) {
        btNovo.setEnabled(novo);
        btSalvar.setEnabled(salvar);
        btStatus.setEnabled(status);
        btCancelar.setEnabled(cancelar);
        btConsultar.setEnabled(consultar);
    }

    private void habilita(Boolean b) {
        tfCodPeca.setEditable(b);
        tfDtProducao.setEnabled(b);
        tfQuantidade.setEditable(b);
        btPeca.setEnabled(b);
        btAdiciona.setEnabled(b);
        btRemove.setEnabled(b);
    }

    private void limpa() {
        tfCodigo.setText("");
        tfCodPeca.setText("");
        tfFuncionario.setText("");
        tfPeca.setText("");
        tfQuantidade.setText("");
        tfStatus.setText("");
        tfDtInclusao.setValue(new Date());
        tfDtProducao.setValue(new Date());
        tabelaPecaOrdemProd.setModel(new TableModelProdOrdemProd(null));
    }

    private void novo() {
        setOrdemProd(new OrdemProd());
        limpa();
        habilita(true);
        lbRegistros.setText("0/0");
//        tfStatus.setText(OrdemProdStatus.EM_APROVACAO.toString());
//        tfStatus.setForeground(new java.awt.Color(255, 0, 0));
        botoes(false, true, false, true, true);
    }

    private void salvar() {
        try {
            if (getOrdemProd().getPecaOrdemProd().size() > 0) {
                getOrdemProd().setDtIncOrdemProd((Date) tfDtInclusao.getValue());
                getOrdemProd().setDtProdOrdemProd((Date) tfDtProducao.getValue());
                getOrdemProd().setFuncionario(Login.usuario.getFuncionario());
                getOrdemProd().setOrdemProdStatus(OrdemProdStatus.EM_APROVACAO);
                dao.save(getOrdemProd());

                habilita(false);
                limpa();
                botoes(true, false, false, true, true);
                tabelaPecaOrdemProd.setModel(new TableModelProdOrdemProd((null)));
                JOptionPane.showMessageDialog(null, "Ordem de produção salva com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Adicione pelo menos uma peça!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            habilita(false);
            limpa();
            JOptionPane.showMessageDialog(null, "Erro ao salvar ordem de produção.");
        }
    }

    private void cancelar() {
        habilita(false);
        limpa();
        btPrimeiro.setEnabled(false);
        btAnterior.setEnabled(false);
        btProximo.setEnabled(false);
        btUltimo.setEnabled(false);
        botoes(true, false, false, true, true);
        tab.setSelectedIndex(0);
        tab.setEnabledAt(0, true);
        tab.setEnabledAt(1, false);
        lbRegistros.setText("0/0");
        setOrdemProd(null);
    }

    private void buscaPeca() {
        this.setVisible(false);
        pf = new PecaForm(false);
        if (pf.table.getSelectedRow() != -1) {
            peca = new Peca();
            peca = pf.tmPeca.getLista().get(pf.table.getSelectedRow());
            tfCodPeca.setText(peca.getIdPeca().toString());
            tfPeca.setText(peca.getPeca());
        }
        this.setVisible(true);
    }

    private void adicionaPeca() {
        Boolean p = false;
        for (PecaOrdemProd pop : getOrdemProd().getPecaOrdemProd()) {
            if (pop.getPeca().equals(peca)) {
                p = true;
            }
        }
        if (tfPeca.getText().equals("") || tfPeca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma peça");
        } else if (tfQuantidade.getText().equals("") || tfQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantidade de peças inválida");
        } else if (p) {
            JOptionPane.showMessageDialog(null, "Peça já inclusa");
            tfCodPeca.setText("");
            tfPeca.setText("");
            tfQuantidade.setText("");
        } else {
            pecaOrdemProd = new PecaOrdemProd();
            pecaOrdemProd.setPeca(peca);
            pecaOrdemProd.setOrdemProd(getOrdemProd());
            pecaOrdemProd.setQtdPecas(new BigDecimal(tfQuantidade.getText()));
            getOrdemProd().getPecaOrdemProd().add(pecaOrdemProd);
            tmProdOrdemProd = new TableModelProdOrdemProd(getOrdemProd().getPecaOrdemProd());
            tabelaPecaOrdemProd.setModel(tmProdOrdemProd);
            tfCodPeca.setText("");
            tfPeca.setText("");
            tfQuantidade.setText("");
        }
    }

    private void removePeca() {
        if (tabelaPecaOrdemProd.getSelectedRow() != -1) {
            getOrdemProd().getPecaOrdemProd().remove(tabelaPecaOrdemProd.getSelectedRow());
            tmProdOrdemProd = new TableModelProdOrdemProd(getOrdemProd().getPecaOrdemProd());
            tabelaPecaOrdemProd.setModel(tmProdOrdemProd);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma peça para ser excluída");
        }
    }

    private void mostraPeca() {
        try {
            peca = new Peca();
            peca = dao.getPecaById(Long.valueOf(tfCodPeca.getText().trim()));
            if (peca != null) {
                tfCodPeca.setText(peca.getIdPeca().toString());
                tfPeca.setText(peca.getPeca());
            } else {
                tfCodPeca.requestFocus();
                tfCodPeca.setText("");
                tfPeca.setText("");
            }
        } catch (NumberFormatException nfe) {
            tfCodPeca.setText("");
            tfPeca.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível realizar esta operação");
        }
    }

    public void consultaOrdemProd() {
        lista = dao.consulta((Date) tfDtInicial.getValue(), (Date) tfDtFinal.getValue(), OrdemProdStatus.getValue(cbStatus.getSelectedIndex()));
        tabela.setModel(new TableModelOrdemProd(lista));
    }

    public void buscaOrdemProd() {
        tab.setSelectedIndex(1);
        tab.setEnabledAt(0, false);
        tab.setEnabledAt(1, true);
        tabela.setModel(new TableModelOrdemProd(null));
        tfDtInicial.setValue(new Date());
        tfDtFinal.setValue(new Date());
        cbStatus.setModel(new ComboModelStatus(OrdemProdStatus.values()));
        botoes(true, false, false, true, false);
    }

    public void controlaNavegacao() {
        if (i == 1 && max == 1) {
            btPrimeiro.setEnabled(false);
            btAnterior.setEnabled(false);
            btUltimo.setEnabled(false);
            btProximo.setEnabled(false);
        } else if (i == 1) {
            btPrimeiro.setEnabled(false);
            btAnterior.setEnabled(false);
            btUltimo.setEnabled(true);
            btProximo.setEnabled(true);
        } else if (i == max) {
            btPrimeiro.setEnabled(true);
            btAnterior.setEnabled(true);
            btUltimo.setEnabled(false);
            btProximo.setEnabled(false);
        } else if (i < max && i > 1) {
            btPrimeiro.setEnabled(true);
            btAnterior.setEnabled(true);
            btUltimo.setEnabled(true);
            btProximo.setEnabled(true);
        }
        lbRegistros.setText(i + "/" + max);
        setaValores(lista.get(i - 1));
    }

    private void setaValores(OrdemProd o) {
        if (o.getOrdemProdStatus() == OrdemProdStatus.FINALIZADA
                || o.getOrdemProdStatus() == OrdemProdStatus.REPROVADA
                || o.getOrdemProdStatus() == OrdemProdStatus.CANCELADA) {
            btStatus.setEnabled(false);
        } else {
            btStatus.setEnabled(true);
        }
        if (o.getOrdemProdStatus() == OrdemProdStatus.APROVADA) {
            tfStatus.setForeground(new java.awt.Color(0, 153, 51));
        } else if (o.getOrdemProdStatus() == OrdemProdStatus.CANCELADA) {
            tfStatus.setForeground(new java.awt.Color(255, 51, 51));
        } else if (o.getOrdemProdStatus() == OrdemProdStatus.EM_APROVACAO) {
            tfStatus.setForeground(new java.awt.Color(153, 153, 153));
        } else if (o.getOrdemProdStatus() == OrdemProdStatus.EM_PRODUCAO) {
            tfStatus.setForeground(new java.awt.Color(255, 153, 0));
        } else if (o.getOrdemProdStatus() == OrdemProdStatus.FINALIZADA) {
            tfStatus.setForeground(new java.awt.Color(51, 51, 255));
        } else if (o.getOrdemProdStatus() == OrdemProdStatus.REPROVADA) {
            tfStatus.setForeground(new java.awt.Color(255, 51, 51));
        }
        tfCodigo.setText(o.getIdOrdemProd().toString());
        tfFuncionario.setText(o.getFuncionario().getPessoa().getNomePessoa());
        tfDtInclusao.setValue(o.getDtIncOrdemProd());
        tfDtProducao.setValue(o.getDtProdOrdemProd());
        tfStatus.setText(o.getOrdemProdStatus().toString());
        tabelaPecaOrdemProd.setModel(new TableModelProdOrdemProd(o.getPecaOrdemProd()));
    }

    private void status() {
        OrdemProdStatusForm opsf = new OrdemProdStatusForm(lista.get(i - 1));
        cancelar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        tab = new javax.swing.JTabbedPane();
        tab01 = new javax.swing.JPanel();
        tfStatus = new javax.swing.JTextField();
        tfCodigo = new javax.swing.JTextField();
        lbDtProducao = new javax.swing.JLabel();
        btPrimeiro = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();
        btAnterior = new javax.swing.JButton();
        tfDtInclusao = new net.sf.nachocalendar.components.DateField();
        btProximo = new javax.swing.JButton();
        tfDtProducao = new net.sf.nachocalendar.components.DateField();
        btUltimo = new javax.swing.JButton();
        lbDtInclusao = new javax.swing.JLabel();
        lbFuncionario = new javax.swing.JLabel();
        lbCodigo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPecaOrdemProd = new javax.swing.JTable();
        tfFuncionario = new javax.swing.JTextField();
        lbRegistros = new javax.swing.JLabel();
        painelProduto = new javax.swing.JPanel();
        tfCodPeca = new javax.swing.JTextField();
        lbCodigoPeca = new javax.swing.JLabel();
        lbPeca = new javax.swing.JLabel();
        btPeca = new javax.swing.JButton();
        tfPeca = new javax.swing.JTextField();
        tfQuantidade = new javax.swing.JTextField();
        lbQuantidade = new javax.swing.JLabel();
        btAdiciona = new javax.swing.JButton();
        btRemove = new javax.swing.JButton();
        tab02 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        lbStatusConsulta = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox();
        lbDtInicial = new javax.swing.JLabel();
        tfDtInicial = new net.sf.nachocalendar.components.DateField();
        tfDtFinal = new net.sf.nachocalendar.components.DateField();
        lbDtFinal = new javax.swing.JLabel();
        btConsulta = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btStatus = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btConsultar = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordem Produção");

        tfStatus.setColumns(10);
        tfStatus.setEditable(false);
        tfStatus.setFont(new java.awt.Font("Tahoma", 1, 12));

        tfCodigo.setColumns(6);
        tfCodigo.setEditable(false);

        lbDtProducao.setText("Data Produção:");

        btPrimeiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/primeiro.png"))); // NOI18N
        btPrimeiro.setEnabled(false);
        btPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrimeiroActionPerformed(evt);
            }
        });

        lbStatus.setText("Status:");

        btAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/anterior.png"))); // NOI18N
        btAnterior.setEnabled(false);
        btAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnteriorActionPerformed(evt);
            }
        });

        tfDtInclusao.setEnabled(false);
        tfDtInclusao.setPreferredSize(new java.awt.Dimension(75, 20));

        btProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/proximo.png"))); // NOI18N
        btProximo.setEnabled(false);
        btProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoActionPerformed(evt);
            }
        });

        tfDtProducao.setEnabled(false);
        tfDtProducao.setPreferredSize(new java.awt.Dimension(75, 20));

        btUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/ultimo.png"))); // NOI18N
        btUltimo.setEnabled(false);
        btUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUltimoActionPerformed(evt);
            }
        });

        lbDtInclusao.setText("Data Inclusão:");

        lbFuncionario.setText("Funcionário:");

        lbCodigo.setText("Código:");

        tabelaPecaOrdemProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaPecaOrdemProd);

        tfFuncionario.setColumns(29);
        tfFuncionario.setEditable(false);

        lbRegistros.setFont(new java.awt.Font("Tahoma", 1, 14));
        lbRegistros.setText("0/0");

        painelProduto.setBorder(javax.swing.BorderFactory.createTitledBorder("Produto"));

        tfCodPeca.setColumns(5);
        tfCodPeca.setEditable(false);
        tfCodPeca.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfCodPeca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodPecaFocusLost(evt);
            }
        });

        lbCodigoPeca.setText("Código:");

        lbPeca.setText("Peça:");

        btPeca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btPeca.setEnabled(false);
        btPeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPecaActionPerformed(evt);
            }
        });

        tfPeca.setColumns(30);
        tfPeca.setEditable(false);

        tfQuantidade.setColumns(5);
        tfQuantidade.setEditable(false);
        tfQuantidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lbQuantidade.setText("Quantidade:");

        btAdiciona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/addin.png"))); // NOI18N
        btAdiciona.setEnabled(false);
        btAdiciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionaActionPerformed(evt);
            }
        });

        btRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/deletein.png"))); // NOI18N
        btRemove.setEnabled(false);
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelProdutoLayout = new javax.swing.GroupLayout(painelProduto);
        painelProduto.setLayout(painelProdutoLayout);
        painelProdutoLayout.setHorizontalGroup(
            painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelProdutoLayout.createSequentialGroup()
                        .addComponent(lbCodigoPeca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfCodPeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelProdutoLayout.createSequentialGroup()
                        .addComponent(lbQuantidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPeca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelProdutoLayout.createSequentialGroup()
                        .addComponent(tfPeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPeca))
                    .addGroup(painelProdutoLayout.createSequentialGroup()
                        .addComponent(btAdiciona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRemove)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        painelProdutoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAdiciona, btRemove});

        painelProdutoLayout.setVerticalGroup(
            painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelProdutoLayout.createSequentialGroup()
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigoPeca)
                    .addComponent(btPeca)
                    .addComponent(tfPeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPeca)
                    .addComponent(tfCodPeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbQuantidade)
                    .addComponent(btAdiciona)
                    .addComponent(btRemove))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelProdutoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAdiciona, btRemove});

        javax.swing.GroupLayout tab01Layout = new javax.swing.GroupLayout(tab01);
        tab01.setLayout(tab01Layout);
        tab01Layout.setHorizontalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addComponent(painelProduto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab01Layout.createSequentialGroup()
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDtInclusao)
                            .addComponent(lbCodigo)
                            .addComponent(lbRegistros))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab01Layout.createSequentialGroup()
                                .addComponent(btPrimeiro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btAnterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btProximo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btUltimo))
                            .addGroup(tab01Layout.createSequentialGroup()
                                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfDtInclusao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab01Layout.createSequentialGroup()
                                        .addComponent(lbFuncionario)
                                        .addGap(20, 20, 20)
                                        .addComponent(tfFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(tab01Layout.createSequentialGroup()
                                        .addComponent(lbDtProducao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfDtProducao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbStatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        tab01Layout.setVerticalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btPrimeiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btProximo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btUltimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCodigo)
                            .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbDtInclusao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfDtInclusao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFuncionario)
                            .addComponent(tfFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbStatus)
                                .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbDtProducao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfDtProducao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Ordem de Produção", tab01);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabela);

        lbStatusConsulta.setText("Status:");

        lbDtInicial.setText("Data Inicial:");

        tfDtInicial.setPreferredSize(new java.awt.Dimension(75, 20));

        tfDtFinal.setPreferredSize(new java.awt.Dimension(75, 20));

        lbDtFinal.setText("Data Final:");

        btConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab02Layout = new javax.swing.GroupLayout(tab02);
        tab02.setLayout(tab02Layout);
        tab02Layout.setHorizontalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab02Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addGroup(tab02Layout.createSequentialGroup()
                        .addComponent(lbStatusConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lbDtInicial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbDtFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btConsulta)))
                .addContainerGap())
        );
        tab02Layout.setVerticalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab02Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbStatus)
                        .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbStatusConsulta)
                            .addComponent(lbDtInicial))
                        .addComponent(tfDtInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfDtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btConsulta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        tab.addTab("Consulta", tab02);

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/add.png"))); // NOI18N
        btNovo.setText("Novo");
        btNovo.setEnabled(false);
        btNovo.setPreferredSize(new java.awt.Dimension(77, 35));
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/cancel.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.setEnabled(false);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/edit.png"))); // NOI18N
        btStatus.setText("Status");
        btStatus.setEnabled(false);
        btStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStatusActionPerformed(evt);
            }
        });

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/save.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setEnabled(false);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/search.png"))); // NOI18N
        btConsultar.setText("Consultar");
        btConsultar.setEnabled(false);
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCancelar, btConsultar, btNovo, btSalvar, btStatus});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalvar)
                    .addComponent(btStatus)
                    .addComponent(btConsultar)
                    .addComponent(btCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCancelar, btConsultar, btNovo, btSalvar, btStatus});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        novo();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btPecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPecaActionPerformed
        buscaPeca();
    }//GEN-LAST:event_btPecaActionPerformed

    private void tfCodPecaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodPecaFocusLost
        if (!tfCodPeca.getText().isEmpty() || !tfCodPeca.getText().equals("")) {
            mostraPeca();
        }
    }//GEN-LAST:event_tfCodPecaFocusLost

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btAdicionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionaActionPerformed
        adicionaPeca();
    }//GEN-LAST:event_btAdicionaActionPerformed

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        removePeca();
    }//GEN-LAST:event_btRemoveActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultaActionPerformed
        consultaOrdemProd();
    }//GEN-LAST:event_btConsultaActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        if (evt.getClickCount() == 2) {
            if (tabela.getSelectedRow() != -1) {
                tab.setSelectedIndex(0);
                tab.setEnabledAt(0, true);
                tab.setEnabledAt(1, false);
                max = lista.size();
                i = tabela.getSelectedRow() + 1;
                controlaNavegacao();
                botoes(false, false, true, true, true);
            }
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarActionPerformed
        buscaOrdemProd();
    }//GEN-LAST:event_btConsultarActionPerformed

    private void btAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnteriorActionPerformed
        i--;
        controlaNavegacao();
    }//GEN-LAST:event_btAnteriorActionPerformed

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed
        i++;
        controlaNavegacao();
    }//GEN-LAST:event_btProximoActionPerformed

    private void btUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUltimoActionPerformed
        i = max;
        controlaNavegacao();
    }//GEN-LAST:event_btUltimoActionPerformed

    private void btPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrimeiroActionPerformed
        if (i != 0) {
            i = 1;
            controlaNavegacao();
        }
    }//GEN-LAST:event_btPrimeiroActionPerformed

    private void btStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStatusActionPerformed
        status();
    }//GEN-LAST:event_btStatusActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdiciona;
    private javax.swing.JButton btAnterior;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btConsulta;
    private javax.swing.JButton btConsultar;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btPeca;
    private javax.swing.JButton btPrimeiro;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btRemove;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btStatus;
    private javax.swing.JButton btUltimo;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbCodigoPeca;
    private javax.swing.JLabel lbDtFinal;
    private javax.swing.JLabel lbDtInclusao;
    private javax.swing.JLabel lbDtInicial;
    private javax.swing.JLabel lbDtProducao;
    private javax.swing.JLabel lbFuncionario;
    private javax.swing.JLabel lbPeca;
    private javax.swing.JLabel lbQuantidade;
    private javax.swing.JLabel lbRegistros;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbStatusConsulta;
    private javax.swing.JPanel painelProduto;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JPanel tab01;
    private javax.swing.JPanel tab02;
    private javax.swing.JTable tabela;
    private javax.swing.JTable tabelaPecaOrdemProd;
    private javax.swing.JTextField tfCodPeca;
    private javax.swing.JTextField tfCodigo;
    private net.sf.nachocalendar.components.DateField tfDtFinal;
    private net.sf.nachocalendar.components.DateField tfDtInclusao;
    private net.sf.nachocalendar.components.DateField tfDtInicial;
    private net.sf.nachocalendar.components.DateField tfDtProducao;
    private javax.swing.JTextField tfFuncionario;
    private javax.swing.JTextField tfPeca;
    private javax.swing.JTextField tfQuantidade;
    public static javax.swing.JTextField tfStatus;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VendasForm.java
 *
 * Created on 19/11/2011, 13:01:00
 */
package br.com.fernandogodoy.sigind.views.movimentos;

import br.com.fernandogodoy.sigind.dao.cadastros.MatPrimaDao;
import br.com.fernandogodoy.sigind.dao.cadastros.MatPrimaDaoImpl;
import br.com.fernandogodoy.sigind.dao.cadastros.PecaDao;
import br.com.fernandogodoy.sigind.dao.cadastros.PecaDaoImpl;
import br.com.fernandogodoy.sigind.dao.movimentos.VendaDao;
import br.com.fernandogodoy.sigind.dao.movimentos.VendaDaoImpl;
import br.com.fernandogodoy.sigind.dao.movimentos.ContasReceberDao;
import br.com.fernandogodoy.sigind.dao.movimentos.ContasReceberDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.MatPrima;
import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import br.com.fernandogodoy.sigind.models.cadastros.TipoPagamento;
import br.com.fernandogodoy.sigind.models.combomodel.ComboModelTipoPagamento;
import br.com.fernandogodoy.sigind.models.movimentos.Venda;
import br.com.fernandogodoy.sigind.models.movimentos.ContasReceber;
import br.com.fernandogodoy.sigind.models.movimentos.PecaVenda;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelVenda;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelItensVenda;
import br.com.fernandogodoy.sigind.util.DocumentMoney;
import br.com.fernandogodoy.sigind.util.DocumentRightLeft;
import br.com.fernandogodoy.sigind.views.cadastros.ClienteForm;
import br.com.fernandogodoy.sigind.views.cadastros.MatPrimaForm;
import br.com.fernandogodoy.sigind.views.cadastros.PecaForm;
import br.com.fernandogodoy.sigind.views.principais.Login;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.bouncycastle.asn1.x509.V1TBSCertificateGenerator;
import org.hibernate.StaleObjectStateException;
import org.hibernate.validator.InvalidStateException;
import org.hibernate.validator.InvalidValue;

/**
 *
 * @author Fernado
 */
public class VendasForm extends javax.swing.JDialog {

    private Peca peca = new Peca();
    private PecaVenda pecaVenda;
    private Venda venda = new Venda();
    private TableModelVenda tmVenda;
    private TableModelItensVenda tmItensVenda;
    private Boolean consulta;
    private PecaDao daoPeca = new PecaDaoImpl();
    private ContasReceberDao daoContasReceber = new ContasReceberDaoImpl();
    private VendaDao dao = new VendaDaoImpl();

    /** Creates new form VendasForm */
    public VendasForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public VendasForm(Boolean consulta) {
        initComponents();
        setTitle("Venda de Produtos");
        setLocationRelativeTo(null);
        setModal(true);
        cbTipoPagamento.setModel(new ComboModelTipoPagamento(TipoPagamento.values()));
        botoes(true, false, true, false, true, true, false);
        tmItensVenda = new TableModelItensVenda(null);
        tableItensVenda.setModel(tmItensVenda);
        tmVenda = new TableModelVenda(null);
        tableConsulta.setModel(tmVenda);
        this.consulta = consulta;
        if (consulta) {
            tab(0, true, false);
        } else {
            tab(1, false, true);
        }
        dfDtVenda.setDateFormat(DateFormat.getDateInstance());
        tfValorProd.setDocument(new DocumentMoney());
        tfPesoPrdo.setDocument(new DocumentMoney());
        tfQtdProd.setDocument(new DocumentRightLeft(10));
        setVisible(true);

    }

    private void limparCamposVenda() {
        tfNomeForn.setText("");
        ftfCnpjForn.setText("");
        dfDtVenda.setValue(new Date());
        tfNumeroNota.setText("");
        tfNumSerieNota.setText("");
        tfQtdProd.setText("");
        tfValorProd.setText("");
        lbQtdTotal.setText("0,00");
        lbTotalPeso.setText("0,00");
        lbValorTotal.setText("0,00");
        cbTipoPagamento.setSelectedIndex(-1);
        ckCancelado.setSelected(false);

    }

    private void limparCamposItensVenda() {
        tfCodProd.setText("");
        tfNomeProd.setText("");
        tfValorProd.setText("");
        tfPesoPrdo.setText("");
        tfQtdProd.setText("");
    }

    public void consulta(Boolean ativo) {
        if (ativo) {
            tab(1, false, true);
        } else {
            tab(0, true, false);
        }
    }

    private void tab(Integer i, Boolean b1, Boolean b2) {
        tab.setSelectedIndex(i);
        tab.setEnabledAt(0, b1);
        tab.setEnabledAt(1, b2);

    }

    private void habilitarCampos(Boolean b) {
        btBuscaForn.setEnabled(b);
        btBuscaProd.setEnabled(b);
        btAdd.setEnabled(b);
        btRem.setEnabled(b);
        dfDtVenda.setEnabled(b);
        tfNumeroNota.setEditable(b);
        tfNumSerieNota.setEditable(b);
        tfCodProd.setEditable(b);
        tfValorProd.setEditable(b);
        tableItensVenda.setEnabled(b);
        cbTipoPagamento.setEnabled(b);
        tfQtdProd.setEditable(b);
        tfPesoPrdo.setEditable(b);
    }

    private void botoes(Boolean... b) {
        btNovo.setEnabled(b[0]);
        btEditar.setEnabled(b[1]);
        btConsultar.setEnabled(b[2]);
        btSalvar.setEnabled(b[3]);
        btCancelar.setEnabled(b[4]);
        btSair.setEnabled(b[5]);
        btCancelaVenda.setEnabled(b[6]);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        panelItensCompra = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tfCodProd = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfNomeProd = new javax.swing.JTextField();
        btBuscaProd = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        tfValorProd = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tfPesoPrdo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfQtdProd = new javax.swing.JTextField();
        btAdd = new javax.swing.JButton();
        btRem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableItensVenda = new javax.swing.JTable();
        panelDadosCompra = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        dfDtVenda = new net.sf.nachocalendar.components.DateField();
        jLabel2 = new javax.swing.JLabel();
        tfNomeForn = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ckCancelado = new javax.swing.JCheckBox();
        btBuscaForn = new javax.swing.JButton();
        ftfCnpjForn = new javax.swing.JFormattedTextField();
        tfNumeroNota = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tfNumSerieNota = new javax.swing.JTextField();
        cbTipoPagamento = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        tfVencCheque = new net.sf.nachocalendar.components.DateField();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbTotalPeso = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbQtdTotal = new javax.swing.JLabel();
        btCancelaVenda = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        lbValorTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfNomeFornBusca = new javax.swing.JTextField();
        btConsultarFiltros = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableConsulta = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btNovo = new javax.swing.JButton();
        btConsultar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Movimento de Vendas");

        panelItensCompra.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens da Venda"));

        jLabel10.setText("Código:");

        tfCodProd.setColumns(10);
        tfCodProd.setEditable(false);
        tfCodProd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodProdFocusLost(evt);
            }
        });

        jLabel11.setText("Produto:");

        tfNomeProd.setColumns(40);
        tfNomeProd.setEditable(false);

        btBuscaProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btBuscaProd.setEnabled(false);
        btBuscaProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscaProdActionPerformed(evt);
            }
        });

        jLabel12.setText("Valor:");

        tfValorProd.setColumns(15);
        tfValorProd.setEditable(false);
        tfValorProd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel13.setText("Peso:");

        tfPesoPrdo.setColumns(15);
        tfPesoPrdo.setEditable(false);
        tfPesoPrdo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel14.setText("Quant:");

        tfQtdProd.setColumns(10);
        tfQtdProd.setEditable(false);
        tfQtdProd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/addin.png"))); // NOI18N
        btAdd.setEnabled(false);
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/deletein.png"))); // NOI18N
        btRem.setEnabled(false);
        btRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemActionPerformed(evt);
            }
        });

        tableItensVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableItensVenda);

        javax.swing.GroupLayout panelItensCompraLayout = new javax.swing.GroupLayout(panelItensCompra);
        panelItensCompra.setLayout(panelItensCompraLayout);
        panelItensCompraLayout.setHorizontalGroup(
            panelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItensCompraLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomeProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBuscaProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfValorProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPesoPrdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfQtdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRem)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelItensCompraLayout.setVerticalGroup(
            panelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(tfCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(tfNomeProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btBuscaProd)
                        .addComponent(jLabel12)
                        .addComponent(tfValorProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(tfPesoPrdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(tfQtdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btAdd))
                    .addComponent(btRem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDadosCompra.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Venda"));

        jLabel3.setText("Data Venda:");

        dfDtVenda.setEnabled(false);
        dfDtVenda.setPreferredSize(new java.awt.Dimension(75, 20));

        jLabel2.setText("CNPJ:");

        tfNomeForn.setColumns(46);
        tfNomeForn.setEditable(false);

        jLabel1.setText("Nome Cliente:*");

        ckCancelado.setText("Cancelado");
        ckCancelado.setEnabled(false);

        btBuscaForn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btBuscaForn.setEnabled(false);
        btBuscaForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscaFornActionPerformed(evt);
            }
        });

        ftfCnpjForn.setColumns(20);
        ftfCnpjForn.setEditable(false);

        tfNumeroNota.setColumns(15);
        tfNumeroNota.setEditable(false);

        jLabel17.setText("Nº Nota:*");

        jLabel18.setText(" Serie Nota:*");

        tfNumSerieNota.setColumns(5);
        tfNumSerieNota.setEditable(false);

        cbTipoPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione", "a vista", "a prazo" }));
        cbTipoPagamento.setEnabled(false);
        cbTipoPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoPagamentoActionPerformed(evt);
            }
        });

        jLabel19.setText("Tipo Pagamento:*");

        jLabel30.setText("Vencimento:");

        tfVencCheque.setEnabled(false);

        jLabel6.setText("Status:");

        javax.swing.GroupLayout panelDadosCompraLayout = new javax.swing.GroupLayout(panelDadosCompra);
        panelDadosCompra.setLayout(panelDadosCompraLayout);
        panelDadosCompraLayout.setHorizontalGroup(
            panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel30)
                    .addComponent(jLabel19)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addGroup(panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDadosCompraLayout.createSequentialGroup()
                        .addComponent(tfNomeForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfCnpjForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btBuscaForn))
                    .addComponent(cbTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfVencCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckCancelado)
                    .addGroup(panelDadosCompraLayout.createSequentialGroup()
                        .addComponent(dfDtVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNumeroNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfNumSerieNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(373, Short.MAX_VALUE))
        );
        panelDadosCompraLayout.setVerticalGroup(
            panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosCompraLayout.createSequentialGroup()
                .addGroup(panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckCancelado)
                    .addComponent(jLabel6))
                .addGap(2, 2, 2)
                .addGroup(panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(8, 8, 8)
                .addGroup(panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfVencCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(dfDtVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addGroup(panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfNumeroNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(tfNumSerieNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btBuscaForn)
                    .addGroup(panelDadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfNomeForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(ftfCnpjForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Peso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 204))); // NOI18N

        lbTotalPeso.setFont(new java.awt.Font("Tahoma", 1, 18));
        lbTotalPeso.setText("0,00");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotalPeso)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(lbTotalPeso)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Quantidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 51, 51))); // NOI18N

        lbQtdTotal.setFont(new java.awt.Font("Tahoma", 1, 18));
        lbQtdTotal.setText("0,00");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbQtdTotal)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(lbQtdTotal)
                .addContainerGap())
        );

        btCancelaVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/cancel.png"))); // NOI18N
        btCancelaVenda.setText("Cancelar Venda");
        btCancelaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelaVendaActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total da Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 153, 0))); // NOI18N

        lbValorTotal.setFont(new java.awt.Font("Tahoma", 1, 18));
        lbValorTotal.setText("0,00");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbValorTotal)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(lbValorTotal)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btCancelaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelDadosCompra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelItensCompra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDadosCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelItensCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btCancelaVenda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Dados da Compra", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel4.setText("Nome Cliente:");

        tfNomeFornBusca.setColumns(60);

        btConsultarFiltros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btConsultarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultarFiltrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(31, 31, 31)
                .addComponent(tfNomeFornBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btConsultarFiltros)
                .addGap(487, 487, 487))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(tfNomeFornBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btConsultarFiltros))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));

        tableConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableConsultaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableConsultaMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tableConsulta);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1112, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1144, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab.addTab("Consulta", jPanel2);

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/add.png"))); // NOI18N
        btNovo.setText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/search.png"))); // NOI18N
        btConsultar.setText("Consultar");
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultarActionPerformed(evt);
            }
        });

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/save.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
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

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/edit.png"))); // NOI18N
        btEditar.setText("Alterar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btConsultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCancelar, btConsultar, btEditar, btNovo, btSair, btSalvar});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btConsultar)
                    .addComponent(btSalvar)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btCancelar)
                        .addComponent(btSair))
                    .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCancelar, btConsultar, btEditar, btNovo, btSair, btSalvar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 1169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableConsultaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableConsultaMouseEntered
    }//GEN-LAST:event_tableConsultaMouseEntered

    private void tableConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableConsultaMouseClicked
        if (evt.getClickCount() != -1) {
            setaCampos();
        }
    }//GEN-LAST:event_tableConsultaMouseClicked

    private void btConsultarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarFiltrosActionPerformed
        VendaDao dao = new VendaDaoImpl();
        tmVenda.setLista(dao.consulta(tfNomeFornBusca.getText()));
        tableConsulta.updateUI();

    }//GEN-LAST:event_btConsultarFiltrosActionPerformed

    private void btCancelaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelaVendaActionPerformed
        ContasReceber contasPagar = daoContasReceber.consultarContasReceber(venda);
        if (contasPagar.getPgContaReceber().isEmpty()) {
            UIManager.put("OptionPane.yesButtonText", "Sim");
            UIManager.put("OptionPane.noButtonText", "Não");
            int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente Cancelar esta Venda?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if (sim == JOptionPane.YES_OPTION) {
                venda.setCancelada(true);
                venda = dao.save(venda);
                atualizaCancelamentoEstoque();
                contasPagar.setCancelada(true);
                daoContasReceber.save(contasPagar);
                JOptionPane.showMessageDialog(null, "Venda cancelada com sucesso.");
                habilitarCampos(false);
                limpar();
                botoes(true, false, true, false, true, true, false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não é possivel cancelar esta venda, possui pagamentos");
        }
    }//GEN-LAST:event_btCancelaVendaActionPerformed

    private void cbTipoPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoPagamentoActionPerformed
        if (cbTipoPagamento.getSelectedItem() != null) {
            if (cbTipoPagamento.getSelectedItem().equals(TipoPagamento.A_VISTA) || cbTipoPagamento.getSelectedItem().equals(TipoPagamento.CARTAO)) {
                tfVencCheque.setEnabled(false);
            } else {
                tfVencCheque.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbTipoPagamentoActionPerformed

    private void btBuscaFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscaFornActionPerformed
        this.setVisible(false);
        ClienteForm ff = new ClienteForm(false);
        if (ff.table.getSelectedRow() != -1) {
            venda.setCliente(ff.tmCliente.getLista().get(ff.table.getSelectedRow()));
            tfNomeForn.setText(venda.getCliente().getPessoa().getNomePessoa());
            ftfCnpjForn.setText(venda.getCliente().getCnpjCliente());
        }
        this.setVisible(true);

    }//GEN-LAST:event_btBuscaFornActionPerformed

    private void btRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemActionPerformed
        remover();
    }//GEN-LAST:event_btRemActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        adicionar();
    }//GEN-LAST:event_btAddActionPerformed

    private void btBuscaProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscaProdActionPerformed
        this.setVisible(false);
        PecaForm pf = new PecaForm(false);
        if (pf.table.getSelectedRow() != -1) {
            peca = (pf.tmPeca.getLista().get(pf.table.getSelectedRow()));
            tfCodProd.setText(peca.getIdPeca().toString());
            tfNomeProd.setText(peca.getPeca());
            tfValorProd.setText(new DecimalFormat("#,##0.00").format(peca.getValorPeca()));
        }
        this.setVisible(true);
    }//GEN-LAST:event_btBuscaProdActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        novo();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarActionPerformed
        consulta(true);
    }//GEN-LAST:event_btConsultarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        alterar();
    }//GEN-LAST:event_btEditarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        limpar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        limpar();
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void tfCodProdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodProdFocusLost
        if (!tfCodProd.getText().trim().equals("")) {
            PecaDao pDao = new PecaDaoImpl();
            peca = pDao.getById(Long.valueOf(tfCodProd.getText().trim()));
            if (peca != null) {
                tfNomeProd.setText(peca.getPeca());
                tfValorProd.setText(new DecimalFormat("#,##0.00").format(peca.getValorPeca()));
            } else {
                JOptionPane.showMessageDialog(null, "Não Encontrado!");
            }
        } else {
            tfCodProd.requestFocus();
            peca = null;
            tfNomeProd.setText("");
            tfValorProd.setText("");
        }
    }//GEN-LAST:event_tfCodProdFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btBuscaForn;
    private javax.swing.JButton btBuscaProd;
    private javax.swing.JButton btCancelaVenda;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btConsultar;
    private javax.swing.JButton btConsultarFiltros;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btRem;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbTipoPagamento;
    private javax.swing.JCheckBox ckCancelado;
    private net.sf.nachocalendar.components.DateField dfDtVenda;
    private javax.swing.JFormattedTextField ftfCnpjForn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbQtdTotal;
    private javax.swing.JLabel lbTotalPeso;
    private javax.swing.JLabel lbValorTotal;
    private javax.swing.JPanel panelDadosCompra;
    private javax.swing.JPanel panelItensCompra;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tableConsulta;
    private javax.swing.JTable tableItensVenda;
    private javax.swing.JTextField tfCodProd;
    private javax.swing.JTextField tfNomeForn;
    private javax.swing.JTextField tfNomeFornBusca;
    private javax.swing.JTextField tfNomeProd;
    private javax.swing.JTextField tfNumSerieNota;
    private javax.swing.JTextField tfNumeroNota;
    private javax.swing.JTextField tfPesoPrdo;
    private javax.swing.JTextField tfQtdProd;
    private javax.swing.JTextField tfValorProd;
    private net.sf.nachocalendar.components.DateField tfVencCheque;
    // End of variables declaration//GEN-END:variables

    private void novo() {
        limparCamposVenda();
        limparCamposItensVenda();
        habilitarCampos(true);
        tfPesoPrdo.setText(new DecimalFormat("#,##0.00").format(BigDecimal.ZERO));
        tfValorProd.setText(new DecimalFormat("#,##0.00").format(BigDecimal.ZERO));
        tableItensVenda.updateUI();
        tableConsulta.updateUI();
        tableConsulta.clearSelection();
        tableItensVenda.clearSelection();
        consulta(false);
        venda = new Venda();
        botoes(false, false, false, true, true, true, false);
    }

    private void alterar() {
        tfValorProd.setText(new DecimalFormat("#,##0.00").format(BigDecimal.ZERO));
        tfPesoPrdo.setText(new DecimalFormat("#,##0.00").format(BigDecimal.ZERO));
        consulta(false);
        if (!venda.getCancelada()) {
            habilitarCampos(true);
        }
        botoes(false, false, false, !venda.getCancelada(), true, true, !venda.getCancelada(), !venda.getCancelada());
    }

    private void adicionar() {
        if (tfNomeProd.getText().equals("") || tfValorProd.getText().equals("") || tfQtdProd.getText().equals("") || tfPesoPrdo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Produto, Valor, Quantidade ou Peso não informados");
        } else {
            pecaVenda = new PecaVenda();
            pecaVenda.setPeca(peca);
            pecaVenda.setPesoPeca(new BigDecimal(tfPesoPrdo.getText().replace(".", "").replace(",", ".").trim()));
            pecaVenda.setValorPeca(new BigDecimal(tfValorProd.getText().replace(".", "").replace(",", ".").trim()));
            pecaVenda.setQtdVenda(new BigDecimal(tfQtdProd.getText().trim()));
            pecaVenda.setVenda(venda);
            venda.getPecaVenda().add(pecaVenda);
            calcularTotal();
            tmItensVenda.setLista(venda.getPecaVenda());
            tableItensVenda.updateUI();
            limparCamposItensVenda();
        }
    }

    private void salvar() {
        if (tfNomeForn.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Não há cliente incluído");
        } else {
            if (cbTipoPagamento.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Tipo de Pagamento não informado");
            } else {
                if (venda.getPecaVenda().size() == 0) {
                    JOptionPane.showMessageDialog(null, "Não há produtos incluídos");
                } else {
                    VendaDao vDao = new VendaDaoImpl();
                    try {
                        if (ckCancelado.isSelected()) {
                            venda.setCancelada(true);
                        } else {
                            venda.setCancelada(false);
                        }
                        venda.setFuncionario(Login.usuario.getFuncionario());
                        venda.setNumNotaVenda(tfNumeroNota.getText().trim());
                        venda.setSerieNotaVenda(tfNumSerieNota.getText().trim());
                        venda.setTipoPagamento((TipoPagamento) cbTipoPagamento.getSelectedItem());
                        venda.setDtVenda((Date) dfDtVenda.getValue());
                        if (!venda.getTipoPagamento().equals(TipoPagamento.A_VISTA)) {
                            venda.setDtVencVenda((Date) tfVencCheque.getValue());
                        } else {
                            venda.setDtVencVenda((Date) dfDtVenda.getValue());
                        }

                        ContasReceber cp = new ContasReceber();
                        cp.setVenda(venda);
                        cp.setValorContasReceber(venda.getValorVenda());
                        cp.setParcelaContasReceber(1L);
                        if (venda.getTipoPagamento().equals(TipoPagamento.A_VISTA)) {
                            cp.setDtContasReceber(venda.getDtVenda());
                            cp.setPago(true);
                        } else {
                            cp.setDtContasReceber(venda.getDtVencVenda());
                            cp.setPago(false);
                        }
                        if (venda.getCancelada()) {
                            cp.setCancelada(true);
                        } else {
                            cp.setCancelada(false);
                        }
                        venda.getContasRecebers().add(cp);
                        vDao.save(venda);
                        if (venda.getCancelada()) {
                            atualizaCancelamentoEstoque();
                        } else {
                            atualizarEstoque();
                        }
                        limparCamposVenda();
                        limparCamposItensVenda();
                        tmItensVenda.setLista(null);
                        tableItensVenda.updateUI();
                        habilitarCampos(false);
                        botoes(true, false, true, false, false, false, false);
                        JOptionPane.showMessageDialog(null, "Venda salva com sucesso.");
                        tab(0, true, false);
                    } catch (InvalidStateException e) {
                        String mensagem = "";
                        for (InvalidValue object : e.getInvalidValues()) {
                            mensagem = mensagem + object.getMessage() + "\n";
                        }
                        JOptionPane.showMessageDialog(null, mensagem);
                        vDao.cancel();

                    } catch (StaleObjectStateException e) {
                        limparCamposVenda();
                        limparCamposItensVenda();
                        botoes(true, false, true, false, false, false, false);
                        habilitarCampos(false);
                        tab(0, true, false);
                        JOptionPane.showMessageDialog(null, "Suas alterações não foram salvas.\nContate o suporte!");

                    }
                }
            }
        }
    }

    private void calcularTotal() {
        BigDecimal pesoTotal = BigDecimal.ZERO;
        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal qtdTotal = BigDecimal.ZERO;
        for (PecaVenda m : venda.getPecaVenda()) {
            pesoTotal = pesoTotal.add(m.getPesoPeca());
            qtdTotal = qtdTotal.add(m.getQtdVenda());
            valorTotal = valorTotal.add(m.getQtdVenda().multiply(m.getValorPeca()));
        }
        lbValorTotal.setText(new DecimalFormat("#,##0.00").format(valorTotal));
        lbTotalPeso.setText(new DecimalFormat().format(pesoTotal));
        lbQtdTotal.setText(new DecimalFormat().format(qtdTotal));
        venda.setPesoVenda(new BigDecimal(lbTotalPeso.getText().replace(".", "").replace(",", ".").trim()));
        venda.setValorVenda(new BigDecimal(lbValorTotal.getText().replace(".", "").replace(",", ".").trim()));
        venda.setQtdVenda(new BigDecimal(lbQtdTotal.getText().replace(".", "").replace(",", ".").trim()));
    }

    private void limpar() {
        limparCamposVenda();
        limparCamposItensVenda();
        consulta(false);
        tmItensVenda.setLista(null);
        tmVenda.setLista(null);
        tableItensVenda.updateUI();
        tableConsulta.updateUI();
        tableConsulta.clearSelection();
        tableItensVenda.clearSelection();
        habilitarCampos(false);
        botoes(true, false, true, false, true, true, false);
    }

    private void setaCampos() {
        if (tableConsulta.getSelectedRow() != -1) {
            venda = (tmVenda.getLista().get(tableConsulta.getSelectedRow()));
            tfNomeForn.setText(venda.getCliente().getPessoa().getNomePessoa());
            ftfCnpjForn.setText(venda.getCliente().getCnpjCliente());
            tfNumeroNota.setText(venda.getNumNotaVenda());
            tfNumSerieNota.setText(venda.getSerieNotaVenda());
            dfDtVenda.setValue(venda.getDtVenda());
            cbTipoPagamento.setSelectedItem(venda.getTipoPagamento());
            ckCancelado.setSelected(venda.getCancelada());
            tmItensVenda.setLista(venda.getPecaVenda());
            tableItensVenda.updateUI();
            calcularTotal();
            tmVenda.setLista(null);
            tableConsulta.updateUI();

            botoes(false, !venda.getCancelada(), true, false, true, true, false);
            tab(0, true, false);
        }
    }

    private void remover() {
        if (tableItensVenda.getSelectedRow() != -1) {
            venda.getPecaVenda().remove(tmItensVenda.getLista().get(tableItensVenda.getSelectedRow()));
            tableItensVenda.updateUI();
            calcularTotal();
        }
    }

    public void atualizarEstoque() {
        for (PecaVenda mp : venda.getPecaVenda()) {
            Peca p = mp.getPeca();
            p.setQtdPeca(p.getQtdPeca().add(mp.getQtdVenda()));
            p.setPesoMedioPeca(p.getPesoMedioPeca().add(mp.getPesoPeca()));
            daoPeca.save(p);
        }
    }

    public void atualizaCancelamentoEstoque() {
        for (PecaVenda mp : venda.getPecaVenda()) {
            Peca p = mp.getPeca();
            p.setQtdPeca(p.getQtdPeca().subtract(mp.getQtdVenda()));
            p.setPesoMedioPeca(p.getPesoMedioPeca().subtract(mp.getPesoPeca()));
            daoPeca.save(p);
        }
    }
}

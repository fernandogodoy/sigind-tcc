/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MaquinaForm.java
 *
 * Created on 07/07/2010, 00:53:48
 */
package br.com.fernandogodoy.sigind.views.cadastros;

import br.com.fernandogodoy.sigind.dao.cadastros.MaquinaDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Fornecedor;
import br.com.fernandogodoy.sigind.models.cadastros.Maquina;
import br.com.fernandogodoy.sigind.models.cadastros.Marca;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelMaquina;
import br.com.fernandogodoy.sigind.util.DocumentRightLeft;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import br.com.fernandogodoy.sigind.views.principais.Login;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.validator.InvalidStateException;
import org.hibernate.validator.InvalidValue;

/**
 *
 * @author Fernando
 */
public class MaquinaForm extends MenuForm {

    private Maquina maquina;
    private TableModelMaquina tmMaquina;
    private MaquinaDaoImpl md = new MaquinaDaoImpl();
    private MarcaForm mf;
    private FornecedorForm ff;

    public MaquinaForm() {
        super(Login.usuario.getMaquinaUsuario());
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        tab(0, true, false);
        dfCadastro.setDateFormat(DateFormat.getDateInstance());
        dfCompra.setDateFormat(DateFormat.getDateInstance());
        dfGarantia.setDateFormat(DateFormat.getDateInstance());
        dfFabricacao.setDateFormat(DateFormat.getDateInstance());
        habilita(false);
        limpaCampos();
        limpaCamposConsulta();
        tfCodMarca.setDocument(new DocumentRightLeft(5));
        tfCodFornecedor.setDocument(new DocumentRightLeft(5));
        setVisible(true);
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    private Maquina maquina() {
        if (!tfCodigo.getText().isEmpty()) {
            getMaquina().setDtCadastro((Date) dfCadastro.getValue());
        } else {
            getMaquina().setDtCadastro(new Date());
        }
        getMaquina().setMaquina(tfNome.getText().trim());
        getMaquina().setAtivo(cbInativo.isSelected());
        getMaquina().setNumMaquina(tfNum.getText().trim());
        getMaquina().setDtCompMaquina((Date) dfCompra.getValue());
        getMaquina().setDtFinalGarMaquina((Date) dfGarantia.getValue());
        getMaquina().setDtFabMaquina((Date) dfFabricacao.getValue());
        return getMaquina();
    }

    private void maquina(Maquina m) {
        tfCodigo.setText(m.getIdMaquina().toString());
        tfNome.setText(m.getMaquina());
        dfCadastro.setValue(m.getDtCadastro());
        cbInativo.setSelected(m.getAtivo());
        tfNum.setText(m.getNumMaquina());
        tfCodMarca.setText(m.getMarca().getIdMarca().toString());
        tfCodFornecedor.setText(m.getFornecedor().getPessoa().getIdPessoa().toString());
        tfMarca.setText(m.getMarca().getMarca());
        tfFornecedor.setText(m.getFornecedor().getPessoa().getNomePessoa());
        dfCompra.setValue(m.getDtCompMaquina());
        dfGarantia.setValue(m.getDtFinalGarMaquina());
        dfFabricacao.setValue(m.getDtFabMaquina());
    }

    private void habilita(Boolean b) {
        tfNome.setEditable(b);
        cbInativo.setEnabled(b);
        tfNum.setEditable(b);
        tfCodMarca.setEditable(b);
        tfCodFornecedor.setEditable(b);
        btMarca.setEnabled(b);
        btFornecedor.setEnabled(b);
        dfCompra.setEnabled(b);
        dfGarantia.setEnabled(b);
        dfFabricacao.setEnabled(b);
    }

    private void limpaCampos() {
        tfCodigo.setText("");
        tfNome.setText("");
        dfCadastro.setValue(new Date());
        cbInativo.setSelected(false);
        tfNum.setText("");
        tfCodMarca.setText("");
        tfCodFornecedor.setText("");
        tfMarca.setText("");
        tfFornecedor.setText("");
        dfCompra.setValue(new Date());
        dfGarantia.setValue(new Date());
        dfFabricacao.setValue(new Date());
        tabelaMaquina(null);
    }

    private void limpaCamposConsulta() {
        tfNomeConsultar.setText("");
        cbTodos.setSelectedIndex(0);
        table.setModel(new TableModelMaquina(null));
    }

    public void tab(Integer i, Boolean b1, Boolean b2) {
        tab.setSelectedIndex(i);
        tab.setEnabledAt(0, b1);
        tab.setEnabledAt(1, b2);
    }

    public void tabelaMaquina(List<Maquina> lista) {
        tmMaquina = new TableModelMaquina(lista);
        table.setModel(tmMaquina);
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getTableHeader().setReorderingAllowed(false);
    }

    public void buscaMarca() {
        this.setVisible(false);
        mf = new MarcaForm(false);
        if (mf.table.getSelectedRow() != -1) {
            getMaquina().setMarca(mf.tmMarca.getLista().get(mf.table.getSelectedRow()));
            tfCodMarca.setText(getMaquina().getMarca().getIdMarca().toString());
            tfMarca.setText(getMaquina().getMarca().getMarca());
        }
        this.setVisible(true);
    }

    public void buscaFornecedor() {
        this.setVisible(false);
        ff = new FornecedorForm(false);
        if (ff.table.getSelectedRow() != -1) {
            getMaquina().setFornecedor(ff.tmFornecedor.getLista().get(ff.table.getSelectedRow()));
            tfCodFornecedor.setText(getMaquina().getFornecedor().getPessoa().getIdPessoa().toString());
            tfFornecedor.setText(getMaquina().getFornecedor().getPessoa().getNomePessoa());
        }
        this.setVisible(true);
    }

    private void mostraMarca() {
        try {
            Marca m = new Marca();
            m = md.consultaMarca(Long.valueOf(tfCodMarca.getText().trim()));
            if (m != null) {
                getMaquina().setMarca(m);
                tfCodMarca.setText(getMaquina().getMarca().getIdMarca().toString());
                tfMarca.setText(getMaquina().getMarca().getMarca());
            } else {
                tfCodMarca.requestFocus();
                getMaquina().setMarca(null);
                tfCodMarca.setText("");
                tfMarca.setText("");
            }
        } catch (NumberFormatException nfe) {
            getMaquina().setMarca(null);
            tfCodMarca.setText("");
            tfMarca.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar esta operação");
        }
    }

    private void mostraFornecedor() {
        try {
            Fornecedor f = new Fornecedor();
            f = md.consultaFornecedor(Long.valueOf(tfCodFornecedor.getText().trim()));
            if (f != null) {
                getMaquina().setFornecedor(f);
                tfCodFornecedor.setText(getMaquina().getFornecedor().getIdFornecedor().toString());
                tfFornecedor.setText(getMaquina().getFornecedor().getPessoa().getNomePessoa());
            } else {
                tfCodFornecedor.requestFocus();
                getMaquina().setFornecedor(null);
                tfCodFornecedor.setText("");
                tfFornecedor.setText("");
            }
        } catch (NumberFormatException nfe) {
            getMaquina().setFornecedor(null);
            tfCodFornecedor.setText("");
            tfFornecedor.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar esta operação");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JTabbedPane();
        tab01 = new javax.swing.JPanel();
        lbCodigo = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        cbInativo = new javax.swing.JCheckBox();
        dfCadastro = new net.sf.nachocalendar.components.DateField();
        lbDtCadastro = new javax.swing.JLabel();
        lbCodMarca = new javax.swing.JLabel();
        tfCodMarca = new javax.swing.JFormattedTextField();
        lbMarca = new javax.swing.JLabel();
        tfMarca = new javax.swing.JTextField();
        btMarca = new javax.swing.JButton();
        lbCodFornecedor = new javax.swing.JLabel();
        tfCodFornecedor = new javax.swing.JFormattedTextField();
        lbFornecedor = new javax.swing.JLabel();
        tfFornecedor = new javax.swing.JTextField();
        btFornecedor = new javax.swing.JButton();
        lbDtCompra = new javax.swing.JLabel();
        dfCompra = new net.sf.nachocalendar.components.DateField();
        lbDtGarantia = new javax.swing.JLabel();
        dfGarantia = new net.sf.nachocalendar.components.DateField();
        lbDtFabricacao = new javax.swing.JLabel();
        dfFabricacao = new net.sf.nachocalendar.components.DateField();
        tfNum = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tab02 = new javax.swing.JPanel();
        lbNomeConsultar = new javax.swing.JLabel();
        tfNomeConsultar = new javax.swing.JTextField();
        cbTodos = new javax.swing.JComboBox();
        btListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        lbNumConsultar = new javax.swing.JLabel();
        tfNumConsultar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Maquina");

        lbCodigo.setText("Código:");

        tfCodigo.setColumns(5);
        tfCodigo.setEditable(false);

        lbNome.setText("Nome:*");

        tfNome.setColumns(50);
        tfNome.setEditable(false);

        cbInativo.setText("Inativo");
        cbInativo.setEnabled(false);

        dfCadastro.setEnabled(false);
        dfCadastro.setPreferredSize(new java.awt.Dimension(75, 20));

        lbDtCadastro.setText("Data Cadastro:");

        lbCodMarca.setText("Código:");

        tfCodMarca.setColumns(4);
        tfCodMarca.setEditable(false);
        tfCodMarca.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfCodMarca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodMarcaFocusLost(evt);
            }
        });

        lbMarca.setText("Marca:*");

        tfMarca.setColumns(30);
        tfMarca.setEditable(false);

        btMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btMarca.setEnabled(false);
        btMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMarcaActionPerformed(evt);
            }
        });

        lbCodFornecedor.setText("Código:");

        tfCodFornecedor.setColumns(4);
        tfCodFornecedor.setEditable(false);
        tfCodFornecedor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfCodFornecedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodFornecedorFocusLost(evt);
            }
        });

        lbFornecedor.setText("Fornecedor:*");

        tfFornecedor.setColumns(30);
        tfFornecedor.setEditable(false);

        btFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btFornecedor.setEnabled(false);
        btFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFornecedorActionPerformed(evt);
            }
        });

        lbDtCompra.setText("Data Compra:");

        dfCompra.setEnabled(false);
        dfCompra.setPreferredSize(new java.awt.Dimension(75, 20));

        lbDtGarantia.setText("Data Final Garantia:");

        dfGarantia.setEnabled(false);
        dfGarantia.setPreferredSize(new java.awt.Dimension(75, 20));

        lbDtFabricacao.setText("Data Fabricação");

        dfFabricacao.setEnabled(false);
        dfFabricacao.setPreferredSize(new java.awt.Dimension(75, 20));

        tfNum.setColumns(10);
        tfNum.setEditable(false);

        jLabel1.setText("Nº:*");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Campos Marcados com (*) são de peenchimento obrigatório");

        javax.swing.GroupLayout tab01Layout = new javax.swing.GroupLayout(tab01);
        tab01.setLayout(tab01Layout);
        tab01Layout.setHorizontalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab01Layout.createSequentialGroup()
                                .addComponent(lbCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 392, Short.MAX_VALUE)
                                .addComponent(lbDtCadastro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dfCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbInativo))
                            .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab01Layout.createSequentialGroup()
                                    .addComponent(lbCodMarca)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfCodMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbMarca)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btMarca))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab01Layout.createSequentialGroup()
                                    .addComponent(lbCodFornecedor)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfCodFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbFornecedor)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btFornecedor)))
                            .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab01Layout.createSequentialGroup()
                                    .addComponent(lbDtFabricacao)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dfFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab01Layout.createSequentialGroup()
                                    .addComponent(lbDtCompra)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dfCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab01Layout.createSequentialGroup()
                                    .addComponent(lbDtGarantia)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dfGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tab01Layout.createSequentialGroup()
                                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNome)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))))
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tab01Layout.setVerticalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dfCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCodigo)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDtCadastro))
                    .addComponent(cbInativo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCodMarca)
                        .addComponent(tfCodMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbMarca)
                        .addComponent(tfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btMarca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodFornecedor)
                    .addComponent(tfCodFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFornecedor)
                    .addComponent(tfFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFornecedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDtCompra)
                    .addComponent(dfCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dfGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDtGarantia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDtFabricacao)
                    .addComponent(dfFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        tab.addTab("Dados da Maquina", tab01);

        lbNomeConsultar.setText("Nome:");

        tfNomeConsultar.setColumns(50);

        cbTodos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Ativo", "Inativo" }));

        btListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListarActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        lbNumConsultar.setText("Nº");

        tfNumConsultar.setColumns(10);

        javax.swing.GroupLayout tab02Layout = new javax.swing.GroupLayout(tab02);
        tab02.setLayout(tab02Layout);
        tab02Layout.setHorizontalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab02Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(tab02Layout.createSequentialGroup()
                        .addComponent(lbNomeConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomeConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNumConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNumConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btListar)))
                .addContainerGap())
        );
        tab02Layout.setVerticalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab02Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNomeConsultar)
                        .addComponent(tfNomeConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNumConsultar)
                        .addComponent(tfNumConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab.addTab("Consultar", tab02);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListarActionPerformed
        listar();
}//GEN-LAST:event_btListarActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 2) {
            seleciona();
            habAlterar();
        }
}//GEN-LAST:event_tableMouseClicked

    private void btMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMarcaActionPerformed
        buscaMarca();
    }//GEN-LAST:event_btMarcaActionPerformed

    private void btFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFornecedorActionPerformed
        buscaFornecedor();
    }//GEN-LAST:event_btFornecedorActionPerformed

    private void tfCodMarcaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodMarcaFocusLost
        if (!tfCodMarca.getText().isEmpty() || !tfCodMarca.getText().equals("")) {
            mostraMarca();
        }
    }//GEN-LAST:event_tfCodMarcaFocusLost

    private void tfCodFornecedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodFornecedorFocusLost
        if (!tfCodFornecedor.getText().isEmpty() || !tfCodFornecedor.getText().equals("")) {
            mostraFornecedor();
        }
    }//GEN-LAST:event_tfCodFornecedorFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFornecedor;
    private javax.swing.JButton btListar;
    private javax.swing.JButton btMarca;
    private javax.swing.JCheckBox cbInativo;
    private javax.swing.JComboBox cbTodos;
    private net.sf.nachocalendar.components.DateField dfCadastro;
    private net.sf.nachocalendar.components.DateField dfCompra;
    private net.sf.nachocalendar.components.DateField dfFabricacao;
    private net.sf.nachocalendar.components.DateField dfGarantia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCodFornecedor;
    private javax.swing.JLabel lbCodMarca;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDtCadastro;
    private javax.swing.JLabel lbDtCompra;
    private javax.swing.JLabel lbDtFabricacao;
    private javax.swing.JLabel lbDtGarantia;
    private javax.swing.JLabel lbFornecedor;
    private javax.swing.JLabel lbMarca;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNomeConsultar;
    private javax.swing.JLabel lbNumConsultar;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JPanel tab01;
    private javax.swing.JPanel tab02;
    private javax.swing.JTable table;
    private javax.swing.JFormattedTextField tfCodFornecedor;
    private javax.swing.JFormattedTextField tfCodMarca;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfFornecedor;
    private javax.swing.JTextField tfMarca;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNomeConsultar;
    private javax.swing.JTextField tfNum;
    private javax.swing.JTextField tfNumConsultar;
    // End of variables declaration//GEN-END:variables

    public void novo() {
        setMaquina(new Maquina());
        habilita(true);
        limpaCampos();
        tab(0, true, false);
    }

    public void salvar() {
        if (tfNome.getText().trim().equals("") || tfMarca.getText().trim().equals("") || tfMarca.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Existem campos de preenchimento obrigatório não preenchidos");
        } else {
            try {
                md.save(maquina());
                habSalvar();
                limpaCampos();
                habilita(false);
                JOptionPane.showMessageDialog(null, "Maquina salva com sucesso.");
            } catch (InvalidStateException e) {
                String mensagem = "";
                for (InvalidValue object : e.getInvalidValues()) {
                    mensagem = mensagem + object.getMessage() + "\n";
                }
                JOptionPane.showMessageDialog(null, mensagem);
                md.cancel();
            } catch (StaleObjectStateException e) {
                limpaCampos();
                habCancelar();
                habilita(false);
                JOptionPane.showMessageDialog(null, "Suas alterações não foram salvas.\nConsulte novamente e altere!");
            }
        }
    }

    public void consultar() {
        limpaCamposConsulta();
        tabelaMaquina(null);
        tab(1, false, true);
    }

    public void alterar() {
        habilita(true);
    }

    public void excluir() {
        try {
            UIManager.put("OptionPane.yesButtonText", "Sim");
            UIManager.put("OptionPane.noButtonText", "Não");
            int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta Maquina?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if (sim == JOptionPane.YES_OPTION) {
                md.delete(getMaquina());
                habExcluir();
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Maquina excluída com sucesso.");
            }
        } catch (ConstraintViolationException cve) {
            JOptionPane.showMessageDialog(null, "Essa máquina não pode ser excluida,\npossui relações.");
        }
    }

    public void cancelar() {
        setMaquina(null);
        HibernateUtility.rollbackTransaction();
        HibernateUtility.closeSession();
        tab(0, true, false);
        habilita(false);
        limpaCampos();
    }

    public void listar() {
        Boolean ativo = cbTodos.getSelectedIndex() == 0 ? null : cbTodos.getSelectedIndex() == 1 ? false : true;
        tabelaMaquina(md.consulta(tfNomeConsultar.getText().trim(), ativo, tfNumConsultar.getText().trim()));
    }

    public void seleciona() {
        if (table.getSelectedRow() != -1) {
            setMaquina(tmMaquina.getLista().get(table.getSelectedRow()));
            maquina(getMaquina());
            tab(0, true, false);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Fornecedor.java
 *
 * Created on 07/07/2010, 00:51:56
 */
package br.com.fernandogodoy.sigind.views.cadastros;

import br.com.fernandogodoy.sigind.dao.cadastros.EmailDaoImpl;
import br.com.fernandogodoy.sigind.dao.cadastros.FornecedorDaoImpl;
import br.com.fernandogodoy.sigind.dao.cadastros.TelefoneDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Email;
import br.com.fernandogodoy.sigind.models.cadastros.Fornecedor;
import br.com.fernandogodoy.sigind.models.cadastros.Municipio;
import br.com.fernandogodoy.sigind.models.cadastros.Pessoa;
import br.com.fernandogodoy.sigind.models.cadastros.Telefone;
import br.com.fernandogodoy.sigind.models.cadastros.TipoTelefone;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelEmail;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelFornecedor;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelTelefone;
import br.com.fernandogodoy.sigind.util.DocumentRightLeft;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import br.com.fernandogodoy.sigind.views.principais.Login;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FornecedorForm extends MenuForm {

    private Fornecedor fornecedor;
    public TableModelFornecedor tmFornecedor;
    private TableModelTelefone tmTelefone;
    private TableModelEmail tmEmail;
    private FornecedorDaoImpl fd = new FornecedorDaoImpl();
    private CidadeForm cf;
    private PessoaForm pf;
    private Boolean consulta;
    private Telefone t;
    private Email e;

    public FornecedorForm(Boolean consulta) {
        super(Login.usuario.getFornecedorUsuario());
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        dfCadastro.setDateFormat(DateFormat.getDateInstance());
        for (TipoTelefone tt : TipoTelefone.values()) {
            cbTipo.addItem(tt.toString());
        }
        habilita(false);
        limpaCampos();
        limpaCamposPessoa();
        limpaCamposConsulta();
        this.consulta = consulta;
        if (consulta) {
            tab(0, true, false, false, false);
        } else {
            tab(3, false, false, false, true);
            habNenhum();
            cbTodos.setSelectedIndex(1);
            cbTodos.setEnabled(false);
        }
        tfCodCidade.setDocument(new DocumentRightLeft(5));
        setVisible(true);
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    private Fornecedor fornecedor() {
        if (!tfCodigoFornecedor.getText().isEmpty()) {
            getFornecedor().setDtCadFornecedor((Date) dfCadastro.getValue());
            getFornecedor().getPessoa().setIdPessoa(Long.parseLong(tfCodigoPessoa.getText()));
            getFornecedor().setIdFornecedor(Long.parseLong(tfCodigoFornecedor.getText()));
        } else {
            getFornecedor().setDtCadFornecedor(new Date());
        }
        getFornecedor().getPessoa().setBairroPessoa(tfBairro.getText().trim());
        getFornecedor().getPessoa().setCepPessoa(tfCep.getText().trim());
        getFornecedor().getPessoa().setComPessoa(tfComp.getText().trim());
        getFornecedor().getPessoa().setNomePessoa(tfNome.getText().trim());
        getFornecedor().getPessoa().setNumPessoa(tfNum.getText().trim());
        getFornecedor().getPessoa().setObsPessoa(tfObs.getText().trim());
        getFornecedor().getPessoa().setRuaPessoa(tfRua.getText().trim());
        getFornecedor().setContatoFornecedor(tfContato.getText().trim());
        getFornecedor().setCnpjFornecedor(tfCnpj.getText());
        getFornecedor().setAtivo(cbInativo.isSelected());
        return getFornecedor();
    }

    private void fornecedor(Fornecedor f) {
        tfCodigoFornecedor.setText(f.getIdFornecedor().toString());
        tfCodigoPessoa.setText(f.getPessoa().getIdPessoa().toString());
        tfNome.setText(f.getPessoa().getNomePessoa());
        tfContato.setText(f.getContatoFornecedor());
        dfCadastro.setValue(f.getDtCadFornecedor());
        cbInativo.setSelected(f.getAtivo());
        tfRua.setText(f.getPessoa().getRuaPessoa());
        tfNum.setText(f.getPessoa().getNumPessoa());
        tfBairro.setText(f.getPessoa().getBairroPessoa());
        tfCep.setText(f.getPessoa().getCepPessoa());
        tfComp.setText(f.getPessoa().getComPessoa());
        tfCodCidade.setText(f.getPessoa().getCidade().getIdMunicipio().toString());
        tfCidade.setText(f.getPessoa().getCidade().getxMun());
        tfUf.setText(f.getPessoa().getCidade().getUf().getSigla());
        tfObs.setText(f.getPessoa().getObsPessoa());
        tfCnpj.setText(f.getCnpjFornecedor());
        tabelaTelefone(f.getPessoa().getTelefones());
        tabelaEmail(f.getPessoa().getEmails());
    }

    private void habilita(Boolean b) {
        tfContato.setEditable(b);
        cbInativo.setEnabled(b);
        tfCnpj.setEditable(b);
        habilitaPessoa(b);
    }

    private void habilitaPessoa(Boolean b) {
        tfNome.setEditable(b);
        tfRua.setEditable(b);
        tfNum.setEditable(b);
        tfBairro.setEditable(b);
        tfCep.setEditable(b);
        tfComp.setEditable(b);
        tfCodCidade.setEditable(b);
        tfObs.setEditable(b);
        btCidade.setEnabled(b);
        btCodPessoa.setEnabled(b);
        tfTelefone.setEditable(b);
        cbTipo.setEnabled(b);
        btInserirTelefone.setEnabled(b);
        btExcluirTelefone.setEnabled(b);
        tfEmail.setEditable(b);
        btInserirEmail.setEnabled(b);
        btExcluirEmail.setEnabled(b);
    }

    private void limpaCampos() {
        tfCodigoFornecedor.setText("");
        tfContato.setText("");
        dfCadastro.setValue(new Date());
        cbInativo.setSelected(false);
        tfCnpj.setText("");
        tabelaFornecedor(null);
        tabelaTelefone(null);
        tabelaEmail(null);
    }

    private void limpaCamposPessoa() {
        tfCodigoPessoa.setText("");
        tfNome.setText("");
        tfRua.setText("");
        tfNum.setText("");
        tfBairro.setText("");
        tfCep.setText("");
        tfComp.setText("");
        tfCodCidade.setText("");
        tfCidade.setText("");
        tfUf.setText("");
        tfObs.setText("");
        tfTelefone.setText("");
        cbTipo.setSelectedIndex(0);
        tfEmail.setText("");
    }

    private void limpaCamposConsulta() {
        tfNomeConsultar.setText("");
        cbTodos.setSelectedIndex(0);
        table.setModel(new TableModelFornecedor(null));
    }

    private void tab(Integer i, Boolean b1, Boolean b2, Boolean b3, Boolean b4) {
        tab.setSelectedIndex(i);
        tab.setEnabledAt(0, b1);
        tab.setEnabledAt(1, b2);
        tab.setEnabledAt(2, b3);
        tab.setEnabledAt(3, b4);
    }

    public void tabelaFornecedor(List<Fornecedor> lista) {
        tmFornecedor = new TableModelFornecedor(lista);
        table.setModel(tmFornecedor);
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getTableHeader().setReorderingAllowed(false);
    }

    public void tabelaTelefone(List<Telefone> lista) {
        tmTelefone = new TableModelTelefone(lista);
        tableTelefone.setModel(tmTelefone);
        tableTelefone.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableTelefone.getColumnModel().getColumn(0).setResizable(false);
        tableTelefone.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableTelefone.getColumnModel().getColumn(1).setResizable(false);
        tableTelefone.getTableHeader().setReorderingAllowed(false);
    }

    public void tabelaEmail(List<Email> lista) {
        tmEmail = new TableModelEmail(lista);
        tableEmail.setModel(tmEmail);
        tableEmail.getColumnModel().getColumn(0).setPreferredWidth(300);
        tableEmail.getColumnModel().getColumn(0).setResizable(false);
        tableEmail.getTableHeader().setReorderingAllowed(false);
    }

    void buscaPessoa() {
        this.setVisible(false);
        pf = new PessoaForm(false);
        if (pf.table.getSelectedRow() != -1) {
            getFornecedor().setPessoa(pf.tmPessoa.getLista().get(pf.table.getSelectedRow()));
            tfCodigoPessoa.setText(getFornecedor().getPessoa().getIdPessoa().toString());
            tfNome.setText(getFornecedor().getPessoa().getNomePessoa());
            tfRua.setText(getFornecedor().getPessoa().getRuaPessoa());
            tfNum.setText(getFornecedor().getPessoa().getNumPessoa());
            tfBairro.setText(getFornecedor().getPessoa().getBairroPessoa());
            tfCep.setText(getFornecedor().getPessoa().getCepPessoa());
            tfComp.setText(getFornecedor().getPessoa().getComPessoa());
            tfCodCidade.setText(getFornecedor().getPessoa().getCidade().getIdMunicipio().toString());
            tfCidade.setText(getFornecedor().getPessoa().getCidade().getxMun());
            tfUf.setText(getFornecedor().getPessoa().getCidade().getUf().getSigla());
            tfObs.setText(getFornecedor().getPessoa().getObsPessoa());
            habilitaPessoa(false);
            btCancelaPessoa.setEnabled(true);
            tabelaTelefone(getFornecedor().getPessoa().getTelefones());
            tabelaEmail(getFornecedor().getPessoa().getEmails());
        }
        this.setVisible(true);
    }

    public void buscaCidade() {
        this.setVisible(false);
        cf = new CidadeForm();
        if (cf.table.getSelectedRow() != -1) {
            getFornecedor().getPessoa().setCidade(cf.tmCidade.getLista().get(cf.table.getSelectedRow()));
            tfCodCidade.setText(getFornecedor().getPessoa().getCidade().getIdMunicipio().toString());
            tfCidade.setText(getFornecedor().getPessoa().getCidade().getxMun());
            tfUf.setText(getFornecedor().getPessoa().getCidade().getUf().getSigla());
        }
        this.setVisible(true);
    }

    private Boolean verificaEmail() {
        for (Email email : getFornecedor().getPessoa().getEmails()) {
            if (e.getEmail().equals(email.getEmail())) {
                return true;
            }
        }
        return false;
    }

    private void addEmail() {
        if (!tfEmail.getText().isEmpty() || !tfEmail.getText().equals("")) {
            e = new Email();
            e.setEmail(tfEmail.getText());
            e.setPessoa(getFornecedor().getPessoa());
            if (getFornecedor().getPessoa().getEmails().size() != 0) {
                if (verificaEmail()) {
                    JOptionPane.showMessageDialog(null, "Esse e-mail já está incluso.");
                } else {
                    getFornecedor().getPessoa().getEmails().add(e);
                    tfEmail.setText("");
                }
            } else {
                getFornecedor().getPessoa().getEmails().add(e);
                tfEmail.setText("");
            }
            tabelaEmail(getFornecedor().getPessoa().getEmails());
        } else {
            JOptionPane.showMessageDialog(null, "E-mail incorreto");
        }
    }

    private void deleteEmail() {
        if (tableEmail.getSelectedRow() != -1) {
            EmailDaoImpl ed = new EmailDaoImpl();
            ed.deleteItem(tmEmail.getLista().get(tableEmail.getSelectedRow()));
            tmEmail.getLista().remove(tableEmail.getSelectedRow());
            tabelaEmail(tmEmail.getLista());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um telefone para excluir.");
        }
    }

    private Boolean verificaTelefone() {
        for (Telefone fone : getFornecedor().getPessoa().getTelefones()) {
            if ((t.getTelefone().equals(fone.getTelefone())) && (t.getTipoTelefone().toString().equals(fone.getTipoTelefone().toString()))) {
                return true;
            }
        }
        return false;
    }

    private void addTelefone() {
        if (tfTelefone.getText().replace(" ", "").length() == 13) {
            t = new Telefone();
            t.setTelefone(tfTelefone.getText());
            t.setTipoTelefone(TipoTelefone.valueOf(cbTipo.getSelectedItem().toString()));
            t.setPessoa(getFornecedor().getPessoa());
            if (getFornecedor().getPessoa().getTelefones().size() != 0) {
                if (verificaTelefone()) {
                    JOptionPane.showMessageDialog(null, "Esse telefone já está incluso.");
                } else {
                    getFornecedor().getPessoa().getTelefones().add(t);
                    tfTelefone.setText("");
                    cbTipo.setSelectedIndex(0);
                }
            } else {
                getFornecedor().getPessoa().getTelefones().add(t);
                tfTelefone.setText("");
                cbTipo.setSelectedIndex(0);
            }
            tabelaTelefone(getFornecedor().getPessoa().getTelefones());
        } else {
            JOptionPane.showMessageDialog(null, "Telefone incorreto");
        }
    }

    private void deleteTelefone() {
        if (tableTelefone.getSelectedRow() != -1) {
            TelefoneDaoImpl td = new TelefoneDaoImpl();
            td.deleteItem(tmTelefone.getLista().get(tableTelefone.getSelectedRow()));
            tmTelefone.getLista().remove(tableTelefone.getSelectedRow());
            tabelaTelefone(tmTelefone.getLista());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um telefone para excluir.");
        }
    }

    private void mostraCidade() {
        try {
            Municipio c = new Municipio();
            c = fd.consultaCidade(Long.valueOf(tfCodCidade.getText().trim()));
            if (c != null) {
                getFornecedor().getPessoa().setCidade(c);
                tfCodCidade.setText(getFornecedor().getPessoa().getCidade().getIdMunicipio().toString());
                tfCidade.setText(getFornecedor().getPessoa().getCidade().getxMun());
                tfUf.setText(getFornecedor().getPessoa().getCidade().getUf().getSigla());
            } else {
                tfCodCidade.requestFocus();
                getFornecedor().getPessoa().setCidade(null);
                tfCodCidade.setText("");
                tfCidade.setText("");
                tfUf.setText("");
            }
        } catch (NumberFormatException nfe) {
            getFornecedor().getPessoa().setCidade(null);
            tfCodCidade.setText("");
            tfCidade.setText("");
            tfUf.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar esta operação");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JTabbedPane();
        tab01 = new javax.swing.JPanel();
        tfCodigoFornecedor = new javax.swing.JTextField();
        lbContato = new javax.swing.JLabel();
        tfContato = new javax.swing.JTextField();
        cbInativo = new javax.swing.JCheckBox();
        dfCadastro = new net.sf.nachocalendar.components.DateField();
        lbDtCadastro = new javax.swing.JLabel();
        tfCnpj = new javax.swing.JFormattedTextField();
        lbCnpj = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tab02 = new javax.swing.JPanel();
        tfUf = new javax.swing.JTextField();
        lbUf = new javax.swing.JLabel();
        lbCodigoPessoa = new javax.swing.JLabel();
        tfCidade = new javax.swing.JTextField();
        lbCidade = new javax.swing.JLabel();
        tfCodCidade = new javax.swing.JFormattedTextField();
        lbCodCidade = new javax.swing.JLabel();
        tfComp = new javax.swing.JTextField();
        lbComp = new javax.swing.JLabel();
        tfCep = new javax.swing.JFormattedTextField();
        tfNome = new javax.swing.JTextField();
        lbRua = new javax.swing.JLabel();
        tfCodigoPessoa = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        btCidade = new javax.swing.JButton();
        lbObs = new javax.swing.JLabel();
        tfNum = new javax.swing.JTextField();
        lbBairro = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane();
        tfObs = new javax.swing.JTextArea();
        tfBairro = new javax.swing.JTextField();
        tfRua = new javax.swing.JTextField();
        lbCep = new javax.swing.JLabel();
        lbNum = new javax.swing.JLabel();
        btCodPessoa = new javax.swing.JButton();
        btCancelaPessoa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tab03 = new javax.swing.JPanel();
        panelTelefone = new javax.swing.JPanel();
        lbTelefone = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox();
        btInserirTelefone = new javax.swing.JButton();
        scrollTelefone = new javax.swing.JScrollPane();
        tableTelefone = new javax.swing.JTable();
        tfTelefone = new javax.swing.JFormattedTextField();
        btExcluirTelefone = new javax.swing.JButton();
        panelEmail = new javax.swing.JPanel();
        lbEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        btInserirEmail = new javax.swing.JButton();
        scrollEmail = new javax.swing.JScrollPane();
        tableEmail = new javax.swing.JTable();
        btExcluirEmail = new javax.swing.JButton();
        tab04 = new javax.swing.JPanel();
        lbNomeConsultar = new javax.swing.JLabel();
        tfNomeConsultar = new javax.swing.JTextField();
        cbTodos = new javax.swing.JComboBox();
        btListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Fornecedor");

        tfCodigoFornecedor.setColumns(5);
        tfCodigoFornecedor.setEditable(false);

        lbContato.setText("Contato:*");

        tfContato.setColumns(50);
        tfContato.setEditable(false);

        cbInativo.setText("Inativo");
        cbInativo.setEnabled(false);

        dfCadastro.setEnabled(false);
        dfCadastro.setPreferredSize(new java.awt.Dimension(75, 20));

        lbDtCadastro.setText("Data Cadastro:");

        tfCnpj.setColumns(14);
        tfCnpj.setEditable(false);
        try {
            tfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCnpj.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        lbCnpj.setText("CNPJ:*");

        jLabel2.setText("Código:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Campos Marcados com (*) são de peenchimento obrigatório");

        javax.swing.GroupLayout tab01Layout = new javax.swing.GroupLayout(tab01);
        tab01.setLayout(tab01Layout);
        tab01Layout.setHorizontalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbContato)
                    .addComponent(lbCnpj)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab01Layout.createSequentialGroup()
                        .addComponent(tfCodigoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
                        .addComponent(lbDtCadastro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dfCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbInativo))
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        tab01Layout.setVerticalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dfCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDtCadastro)
                            .addComponent(tfCodigoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbContato)
                            .addComponent(tfContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cbInativo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCnpj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        tab.addTab("Dados do Fornecedor", tab01);

        tfUf.setColumns(2);
        tfUf.setEditable(false);

        lbUf.setText("UF:");

        lbCodigoPessoa.setText("Código:");

        tfCidade.setColumns(35);
        tfCidade.setEditable(false);

        lbCidade.setText("Cidade:");

        tfCodCidade.setColumns(4);
        tfCodCidade.setEditable(false);
        tfCodCidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfCodCidade.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        tfCodCidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodCidadeFocusLost(evt);
            }
        });

        lbCodCidade.setText("Cód:");

        tfComp.setColumns(50);
        tfComp.setEditable(false);

        lbComp.setText("Comp.: ");

        tfCep.setColumns(9);
        tfCep.setEditable(false);
        try {
            tfCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCep.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        tfNome.setColumns(50);
        tfNome.setEditable(false);

        lbRua.setText("Rua:");

        tfCodigoPessoa.setColumns(5);
        tfCodigoPessoa.setEditable(false);

        lbNome.setText("Nome:");

        btCidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btCidade.setEnabled(false);
        btCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCidadeActionPerformed(evt);
            }
        });

        lbObs.setText("OBS:");

        tfNum.setColumns(5);
        tfNum.setEditable(false);

        lbBairro.setText("Bairro:");

        tfObs.setColumns(20);
        tfObs.setEditable(false);
        tfObs.setRows(5);
        ScrollPane.setViewportView(tfObs);

        tfBairro.setColumns(50);
        tfBairro.setEditable(false);

        tfRua.setColumns(50);
        tfRua.setEditable(false);

        lbCep.setText("CEP:");

        lbNum.setText("Nº:");

        btCodPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btCodPessoa.setEnabled(false);
        btCodPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCodPessoaActionPerformed(evt);
            }
        });

        btCancelaPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/cancelin.png"))); // NOI18N
        btCancelaPessoa.setEnabled(false);
        btCancelaPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelaPessoaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Todos campos desta tela são de peenchimento obigatório");

        javax.swing.GroupLayout tab02Layout = new javax.swing.GroupLayout(tab02);
        tab02.setLayout(tab02Layout);
        tab02Layout.setHorizontalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab02Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab02Layout.createSequentialGroup()
                        .addComponent(lbCodigoPessoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCodigoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCodPessoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelaPessoa))
                    .addGroup(tab02Layout.createSequentialGroup()
                        .addComponent(lbNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE))
                    .addGroup(tab02Layout.createSequentialGroup()
                        .addComponent(lbRua)
                        .addGap(18, 18, 18)
                        .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
                    .addGroup(tab02Layout.createSequentialGroup()
                        .addComponent(lbBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbCep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab02Layout.createSequentialGroup()
                        .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab02Layout.createSequentialGroup()
                                .addComponent(lbCodCidade)
                                .addGap(18, 18, 18)
                                .addComponent(tfCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab02Layout.createSequentialGroup()
                                .addComponent(lbComp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab02Layout.createSequentialGroup()
                                .addComponent(lbObs)
                                .addGap(18, 18, 18)
                                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbUf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCidade)
                        .addGap(25, 25, 25)))
                .addGap(152, 152, 152))
        );
        tab02Layout.setVerticalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab02Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(lbCodigoPessoa)
                        .addComponent(tfCodigoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btCodPessoa))
                    .addComponent(btCancelaPessoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lbNome)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lbRua)
                    .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lbBairro)
                    .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCep)
                    .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbComp)
                    .addComponent(tfComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(tfCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodCidade)
                    .addComponent(lbCidade)
                    .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUf)
                    .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbObs)
                    .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        tab.addTab("Dados Pessoais", tab02);

        panelTelefone.setBorder(javax.swing.BorderFactory.createTitledBorder("Telefone"));

        lbTelefone.setText("Nome:");

        cbTipo.setEnabled(false);

        btInserirTelefone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/addin.png"))); // NOI18N
        btInserirTelefone.setEnabled(false);
        btInserirTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInserirTelefoneActionPerformed(evt);
            }
        });

        tableTelefone.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollTelefone.setViewportView(tableTelefone);

        tfTelefone.setColumns(14);
        tfTelefone.setEditable(false);
        try {
            tfTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfTelefone.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        btExcluirTelefone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/deletein.png"))); // NOI18N
        btExcluirTelefone.setEnabled(false);
        btExcluirTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirTelefoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTelefoneLayout = new javax.swing.GroupLayout(panelTelefone);
        panelTelefone.setLayout(panelTelefoneLayout);
        panelTelefoneLayout.setHorizontalGroup(
            panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTelefoneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                    .addGroup(panelTelefoneLayout.createSequentialGroup()
                        .addComponent(lbTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btInserirTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExcluirTelefone)))
                .addContainerGap())
        );
        panelTelefoneLayout.setVerticalGroup(
            panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTelefoneLayout.createSequentialGroup()
                .addGroup(panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTelefone)
                        .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btInserirTelefone)
                        .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btExcluirTelefone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelEmail.setBorder(javax.swing.BorderFactory.createTitledBorder("E-mail"));

        lbEmail.setText("Nome:");

        tfEmail.setColumns(50);
        tfEmail.setEditable(false);

        btInserirEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/addin.png"))); // NOI18N
        btInserirEmail.setEnabled(false);
        btInserirEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInserirEmailActionPerformed(evt);
            }
        });

        tableEmail.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollEmail.setViewportView(tableEmail);

        btExcluirEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/deletein.png"))); // NOI18N
        btExcluirEmail.setEnabled(false);
        btExcluirEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEmailLayout = new javax.swing.GroupLayout(panelEmail);
        panelEmail.setLayout(panelEmailLayout);
        panelEmailLayout.setHorizontalGroup(
            panelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                    .addGroup(panelEmailLayout.createSequentialGroup()
                        .addComponent(lbEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btInserirEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExcluirEmail)))
                .addContainerGap())
        );
        panelEmailLayout.setVerticalGroup(
            panelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmailLayout.createSequentialGroup()
                .addGroup(panelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbEmail)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btInserirEmail))
                    .addComponent(btExcluirEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tab03Layout = new javax.swing.GroupLayout(tab03);
        tab03.setLayout(tab03Layout);
        tab03Layout.setHorizontalGroup(
            tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab03Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab03Layout.setVerticalGroup(
            tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab03Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panelTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("Contato", tab03);

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

        javax.swing.GroupLayout tab04Layout = new javax.swing.GroupLayout(tab04);
        tab04.setLayout(tab04Layout);
        tab04Layout.setHorizontalGroup(
            tab04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab04Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(tab04Layout.createSequentialGroup()
                        .addComponent(lbNomeConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomeConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btListar)))
                .addContainerGap())
        );
        tab04Layout.setVerticalGroup(
            tab04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab04Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomeConsultar)
                    .addComponent(tfNomeConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab.addTab("Consultar", tab04);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCidadeActionPerformed
        buscaCidade();
}//GEN-LAST:event_btCidadeActionPerformed

    private void btCodPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCodPessoaActionPerformed
        buscaPessoa();
    }//GEN-LAST:event_btCodPessoaActionPerformed

    private void btCancelaPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelaPessoaActionPerformed
        btCancelaPessoa.setEnabled(false);
        habilitaPessoa(true);
        limpaCamposPessoa();
        getFornecedor().setPessoa(new Pessoa());
    }//GEN-LAST:event_btCancelaPessoaActionPerformed

    private void btInserirTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInserirTelefoneActionPerformed
        addTelefone();
}//GEN-LAST:event_btInserirTelefoneActionPerformed

    private void btExcluirTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirTelefoneActionPerformed
        deleteTelefone();
}//GEN-LAST:event_btExcluirTelefoneActionPerformed

    private void btInserirEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInserirEmailActionPerformed
        addEmail();
}//GEN-LAST:event_btInserirEmailActionPerformed

    private void btExcluirEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirEmailActionPerformed
        deleteEmail();
}//GEN-LAST:event_btExcluirEmailActionPerformed

    private void tfCodCidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodCidadeFocusLost
        if (!tfCodCidade.getText().isEmpty() || !tfCodCidade.getText().equals("")) {
            mostraCidade();
        }
    }//GEN-LAST:event_tfCodCidadeFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JButton btCancelaPessoa;
    private javax.swing.JButton btCidade;
    private javax.swing.JButton btCodPessoa;
    private javax.swing.JButton btExcluirEmail;
    private javax.swing.JButton btExcluirTelefone;
    private javax.swing.JButton btInserirEmail;
    private javax.swing.JButton btInserirTelefone;
    private javax.swing.JButton btListar;
    private javax.swing.JCheckBox cbInativo;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JComboBox cbTodos;
    private net.sf.nachocalendar.components.DateField dfCadastro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBairro;
    private javax.swing.JLabel lbCep;
    private javax.swing.JLabel lbCidade;
    private javax.swing.JLabel lbCnpj;
    private javax.swing.JLabel lbCodCidade;
    private javax.swing.JLabel lbCodigoPessoa;
    private javax.swing.JLabel lbComp;
    private javax.swing.JLabel lbContato;
    private javax.swing.JLabel lbDtCadastro;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNomeConsultar;
    private javax.swing.JLabel lbNum;
    private javax.swing.JLabel lbObs;
    private javax.swing.JLabel lbRua;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JLabel lbUf;
    private javax.swing.JPanel panelEmail;
    private javax.swing.JPanel panelTelefone;
    private javax.swing.JScrollPane scrollEmail;
    private javax.swing.JScrollPane scrollTelefone;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JPanel tab01;
    private javax.swing.JPanel tab02;
    private javax.swing.JPanel tab03;
    private javax.swing.JPanel tab04;
    public javax.swing.JTable table;
    public javax.swing.JTable tableEmail;
    public javax.swing.JTable tableTelefone;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JFormattedTextField tfCep;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JFormattedTextField tfCnpj;
    private javax.swing.JFormattedTextField tfCodCidade;
    private javax.swing.JTextField tfCodigoFornecedor;
    private javax.swing.JTextField tfCodigoPessoa;
    private javax.swing.JTextField tfComp;
    private javax.swing.JTextField tfContato;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNomeConsultar;
    private javax.swing.JTextField tfNum;
    private javax.swing.JTextArea tfObs;
    private javax.swing.JTextField tfRua;
    private javax.swing.JFormattedTextField tfTelefone;
    private javax.swing.JTextField tfUf;
    // End of variables declaration//GEN-END:variables

    public void novo() {
        setFornecedor(new Fornecedor());
        habilita(true);
        btCancelaPessoa.setEnabled(false);
        limpaCampos();
        limpaCamposPessoa();
        tab(0, true, true, true, false);
    }

    public void salvar() {
        if (tfCidade.getText().trim().equals("") || fornecedor.getPessoa() == null || tfCnpj.getText().trim().equals("") || tfContato.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Existem campos de preenchimento obrigatório não preenchidos");

        } else {
            try {
                fd.save(fornecedor());
                habSalvar();
                limpaCampos();
                habilita(false);
                tab(0, true, false, false, false);
                JOptionPane.showMessageDialog(null, "Fornecedor salvo com sucesso.");
            } catch (InvalidStateException e) {
                String mensagem = "";
                for (InvalidValue object : e.getInvalidValues()) {
                    mensagem = mensagem + object.getMessage() + "\n";
                }
                JOptionPane.showMessageDialog(null, mensagem);
                fd.cancel();
            } catch (StaleObjectStateException e) {
                limpaCampos();
                limpaCamposPessoa();
                habCancelar();
                habilita(false);
                JOptionPane.showMessageDialog(null, "Suas alterações não foram salvas.\nConsulte novamente e altere!");
            } catch (ConstraintViolationException cve) {
                String cause = String.valueOf(cve.getCause());
                if (cause.contains("fornecedor_cnpjfornecedor_key")) {
                    JOptionPane.showMessageDialog(null, "CNPJ inválido, por favor digite outro");
                }
                if (cause.contains("fornecedor_idpessoa_key")) {
                    JOptionPane.showMessageDialog(null, "A Pessoa selecionada já é um Fornecedor,\npor favor selecione outra Pessoa.");
                }
                fd.cancel();
            }
        }
    }

    public void consultar() {
        limpaCamposConsulta();
        tabelaFornecedor(null);
        tab(3, false, false, false, true);
    }

    public void alterar() {
        habilita(true);
        btCodPessoa.setEnabled(false);
        btCancelaPessoa.setEnabled(false);
        tfCodigoPessoa.setEditable(false);
    }

    public void excluir() {
        try {
            UIManager.put("OptionPane.yesButtonText", "Sim");
            UIManager.put("OptionPane.noButtonText", "Não");
            int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta Fornecedor?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if (sim == JOptionPane.YES_OPTION) {
                fd.delete(getFornecedor());
                habExcluir();
                limpaCampos();
                limpaCamposPessoa();
                JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso.");
            }
        } catch (ConstraintViolationException cve) {
            JOptionPane.showMessageDialog(null, "Esse fornecedor não pode ser excluido,\npossui relações.");
        }
    }

    public void cancelar() {
        setFornecedor(null);
        HibernateUtility.rollbackTransaction();
        HibernateUtility.closeSession();
        tab(0, true, false, false, false);
        habilita(false);
        limpaCampos();
        limpaCamposPessoa();
    }

    public void listar() {
        Boolean ativo = cbTodos.getSelectedIndex() == 0 ? null : cbTodos.getSelectedIndex() == 1 ? false : true;
        tabelaFornecedor(fd.consulta(tfNomeConsultar.getText().trim(), ativo));
    }

    public void seleciona() {
        if (consulta) {
            if (table.getSelectedRow() != -1) {
                setFornecedor(tmFornecedor.getLista().get(table.getSelectedRow()));
                fornecedor(getFornecedor());
                tab(0, true, true, true, false);
            }
        } else {
            this.dispose();
        }
    }
}

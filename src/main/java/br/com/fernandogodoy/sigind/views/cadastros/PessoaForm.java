/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PessoaForm.java
 *
 * Created on 07/07/2010, 00:54:18
 */
package br.com.fernandogodoy.sigind.views.cadastros;

import br.com.fernandogodoy.sigind.dao.cadastros.EmailDaoImpl;
import br.com.fernandogodoy.sigind.dao.cadastros.PessoaDaoImpl;
import br.com.fernandogodoy.sigind.dao.cadastros.TelefoneDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Email;
import br.com.fernandogodoy.sigind.models.cadastros.Municipio;
import br.com.fernandogodoy.sigind.models.cadastros.Pessoa;
import br.com.fernandogodoy.sigind.models.cadastros.Telefone;
import br.com.fernandogodoy.sigind.models.cadastros.TipoTelefone;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelEmail;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelPessoa;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelTelefone;
import br.com.fernandogodoy.sigind.util.DocumentRightLeft;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import br.com.fernandogodoy.sigind.views.principais.Login;
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
public class PessoaForm extends MenuForm {

    private Pessoa pessoa;
    public TableModelPessoa tmPessoa;
    private TableModelTelefone tmTelefone;
    private TableModelEmail tmEmail;
    private PessoaDaoImpl pd = new PessoaDaoImpl();
    private CidadeForm cf;
    private Boolean consulta;
    private Telefone t;
    private Email e;

    public PessoaForm(Boolean consulta) {
        super(Login.usuario.getPessoaUsuario());
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        this.consulta = consulta;
        if (consulta) {
            tab(0, true, false, false);
        } else {
            tab(2, false, false, true);
            habNenhum();
        }
        for (TipoTelefone tt : TipoTelefone.values()) {
            cbTipo.addItem(tt.toString());
        }
        habilita(false);
        limpaCampos();
        limpaCamposConsulta();
        tfCodCidade.setDocument(new DocumentRightLeft(5));
        setVisible(true);

    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    private Pessoa pessoa() {
        if (!tfCodigo.getText().isEmpty()) {
            getPessoa().setIdPessoa(Long.parseLong(tfCodigo.getText()));
        }
        getPessoa().setNomePessoa(tfNome.getText().trim());
        getPessoa().setRuaPessoa(tfRua.getText().trim());
        getPessoa().setNumPessoa(tfNum.getText().trim());
        getPessoa().setBairroPessoa(tfBairro.getText().trim());
        getPessoa().setCepPessoa(tfCep.getText().trim());
        getPessoa().setComPessoa(tfComp.getText().trim());
        getPessoa().setObsPessoa(tfObs.getText().trim());
        return getPessoa();
    }

    private void pessoa(Pessoa p) {
        tfCodigo.setText(p.getIdPessoa().toString());
        tfNome.setText(p.getNomePessoa());
        tfRua.setText(p.getRuaPessoa());
        tfNum.setText(p.getNumPessoa());
        tfBairro.setText(p.getBairroPessoa());
        tfCep.setText(p.getCepPessoa());
        tfComp.setText(p.getComPessoa());
        tfCodCidade.setText(p.getCidade().getIdMunicipio().toString());
        tfCidade.setText(p.getCidade().getxMun());
        tfUf.setText(p.getCidade().getUf().getSigla());
        tfObs.setText(p.getObsPessoa());
        tabelaTelefone(p.getTelefones());
        tabelaEmail(p.getEmails());
    }

    private void habilita(Boolean b) {
        tfNome.setEditable(b);
        tfRua.setEditable(b);
        tfNum.setEditable(b);
        tfBairro.setEditable(b);
        tfCep.setEditable(b);
        tfComp.setEditable(b);
        tfCodCidade.setEditable(b);
        tfObs.setEditable(b);
        btCidade.setEnabled(b);
        tfTelefone.setEditable(b);
        cbTipo.setEnabled(b);
        btInserirTelefone.setEnabled(b);
        btExcluirTelefone.setEnabled(b);
        tfEmail.setEditable(b);
        btInserirEmail.setEnabled(b);
        btExcluirEmail.setEnabled(b);
    }

    private void limpaCampos() {
        tfCodigo.setText("");
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
        tabelaTelefone(null);
        tabelaEmail(null);
    }

    private void limpaCamposConsulta() {
        tfNomeConsultar.setText("");
        table.setModel(new TableModelPessoa(null));
    }

    public void tab(Integer i, Boolean b1, Boolean b2, Boolean b3) {
        tab.setSelectedIndex(i);
        tab.setEnabledAt(0, b1);
        tab.setEnabledAt(1, b2);
        tab.setEnabledAt(2, b3);
    }

    public void tabelaPessoa(List<Pessoa> lista) {
        tmPessoa = new TableModelPessoa(lista);
        table.setModel(tmPessoa);
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

    public void buscaCidade() {
        this.setVisible(false);
        cf = new CidadeForm();
        if (cf.table.getSelectedRow() != -1) {
            getPessoa().setCidade(cf.tmCidade.getLista().get(cf.table.getSelectedRow()));
            tfCodCidade.setText(getPessoa().getCidade().getIdMunicipio().toString());
            tfCidade.setText(getPessoa().getCidade().getxMun());
            tfUf.setText(getPessoa().getCidade().getUf().getSigla());
        }
        this.setVisible(true);
    }

    private Boolean verificaEmail() {
        for (Email email : getPessoa().getEmails()) {
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
            e.setPessoa(getPessoa());
            if (getPessoa().getEmails().size() != 0) {
                if (verificaEmail()) {
                    JOptionPane.showMessageDialog(null, "Esse e-mail já está incluso.");
                } else {
                    getPessoa().getEmails().add(e);
                    tfEmail.setText("");
                }
            } else {
                getPessoa().getEmails().add(e);
                tfEmail.setText("");
            }
            tabelaEmail(getPessoa().getEmails());
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
        for (Telefone fone : getPessoa().getTelefones()) {
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
            t.setPessoa(getPessoa());
            if (getPessoa().getTelefones().size() != 0) {
                if (verificaTelefone()) {
                    JOptionPane.showMessageDialog(null, "Esse telefone já está incluso.");
                } else {
                    getPessoa().getTelefones().add(t);
                    tfTelefone.setText("");
                    cbTipo.setSelectedIndex(0);
                }
            } else {
                getPessoa().getTelefones().add(t);
                tfTelefone.setText("");
                cbTipo.setSelectedIndex(0);
            }
            tabelaTelefone(getPessoa().getTelefones());
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
            c = pd.consultaCidade(Long.valueOf(tfCodCidade.getText().trim()));
            if (c != null) {
                getPessoa().setCidade(c);
                tfCodCidade.setText(getPessoa().getCidade().getIdMunicipio().toString());
                tfCidade.setText(getPessoa().getCidade().getxMun());
                tfUf.setText(getPessoa().getCidade().getUf().getSigla());
            } else {
                tfCodCidade.requestFocus();
                getPessoa().setCidade(null);
                tfCodCidade.setText("");
                tfCidade.setText("");
                tfUf.setText("");
            }
        } catch (NumberFormatException nfe) {
            getPessoa().setCidade(null);
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
        lbCodigo = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        lbRua = new javax.swing.JLabel();
        tfRua = new javax.swing.JTextField();
        lbNum = new javax.swing.JLabel();
        tfNum = new javax.swing.JTextField();
        lbBairro = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        lbCep = new javax.swing.JLabel();
        tfCep = new javax.swing.JFormattedTextField();
        lbComp = new javax.swing.JLabel();
        tfComp = new javax.swing.JTextField();
        lbCodCidade = new javax.swing.JLabel();
        tfCodCidade = new javax.swing.JFormattedTextField();
        lbCidade = new javax.swing.JLabel();
        tfCidade = new javax.swing.JTextField();
        lbUf = new javax.swing.JLabel();
        tfUf = new javax.swing.JTextField();
        lbObs = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane();
        tfObs = new javax.swing.JTextArea();
        btCidade = new javax.swing.JButton();
        tab02 = new javax.swing.JPanel();
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
        tab03 = new javax.swing.JPanel();
        lbNomeConsultar = new javax.swing.JLabel();
        tfNomeConsultar = new javax.swing.JTextField();
        btListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Pessoa");

        lbCodigo.setText("Código:");

        tfCodigo.setColumns(5);
        tfCodigo.setEditable(false);

        lbNome.setText("Nome:");

        tfNome.setColumns(50);
        tfNome.setEditable(false);

        lbRua.setText("Rua:");

        tfRua.setColumns(50);
        tfRua.setEditable(false);

        lbNum.setText("Nº:");

        tfNum.setColumns(5);
        tfNum.setEditable(false);

        lbBairro.setText("Bairro:");

        tfBairro.setColumns(50);
        tfBairro.setEditable(false);

        lbCep.setText("CEP:");

        tfCep.setColumns(9);
        tfCep.setEditable(false);
        try {
            tfCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCep.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        lbComp.setText("Comp.: ");

        tfComp.setColumns(50);
        tfComp.setEditable(false);

        lbCodCidade.setText("Cód:");

        tfCodCidade.setColumns(4);
        tfCodCidade.setEditable(false);
        tfCodCidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfCodCidade.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tfCodCidade.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        tfCodCidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodCidadeFocusLost(evt);
            }
        });

        lbCidade.setText("Cidade:");

        tfCidade.setColumns(35);
        tfCidade.setEditable(false);

        lbUf.setText("UF:");

        tfUf.setColumns(2);
        tfUf.setEditable(false);

        lbObs.setText("OBS:");

        tfObs.setColumns(20);
        tfObs.setEditable(false);
        tfObs.setRows(5);
        ScrollPane.setViewportView(tfObs);

        btCidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btCidade.setEnabled(false);
        btCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab01Layout = new javax.swing.GroupLayout(tab01);
        tab01.setLayout(tab01Layout);
        tab01Layout.setHorizontalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addComponent(lbCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addComponent(lbNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE))
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addComponent(lbRua)
                        .addGap(18, 18, 18)
                        .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addComponent(lbBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbCep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab01Layout.createSequentialGroup()
                                .addComponent(lbObs)
                                .addGap(18, 18, 18)
                                .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab01Layout.createSequentialGroup()
                                .addComponent(lbCodCidade)
                                .addGap(18, 18, 18)
                                .addComponent(tfCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab01Layout.createSequentialGroup()
                                .addComponent(lbComp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbUf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCidade)
                        .addGap(25, 25, 25)))
                .addGap(152, 152, 152))
        );

        tab01Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfBairro, tfComp, tfNome, tfRua});

        tab01Layout.setVerticalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lbCodigo)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lbNome)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lbRua)
                    .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lbBairro)
                    .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCep)
                    .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbComp)
                    .addComponent(tfComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(tfCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodCidade)
                    .addComponent(lbCidade)
                    .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUf)
                    .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbObs)
                    .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tab.addTab("Dados da Pessoais", tab01);

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

        javax.swing.GroupLayout tab02Layout = new javax.swing.GroupLayout(tab02);
        tab02.setLayout(tab02Layout);
        tab02Layout.setHorizontalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab02Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab02Layout.setVerticalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab02Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panelTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("Contato", tab02);

        lbNomeConsultar.setText("Nome:");

        tfNomeConsultar.setColumns(50);

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

        javax.swing.GroupLayout tab03Layout = new javax.swing.GroupLayout(tab03);
        tab03.setLayout(tab03Layout);
        tab03Layout.setHorizontalGroup(
            tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab03Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(tab03Layout.createSequentialGroup()
                        .addComponent(lbNomeConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomeConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btListar)))
                .addContainerGap())
        );
        tab03Layout.setVerticalGroup(
            tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab03Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomeConsultar)
                    .addComponent(tfNomeConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab.addTab("Consultar", tab03);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JButton btCidade;
    private javax.swing.JButton btExcluirEmail;
    private javax.swing.JButton btExcluirTelefone;
    private javax.swing.JButton btInserirEmail;
    private javax.swing.JButton btInserirTelefone;
    private javax.swing.JButton btListar;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBairro;
    private javax.swing.JLabel lbCep;
    private javax.swing.JLabel lbCidade;
    private javax.swing.JLabel lbCodCidade;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbComp;
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
    public javax.swing.JTable table;
    public javax.swing.JTable tableEmail;
    public javax.swing.JTable tableTelefone;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JFormattedTextField tfCep;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JFormattedTextField tfCodCidade;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfComp;
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
        setPessoa(new Pessoa());
        habilita(true);
        limpaCampos();
        tab(0, true, true, false);
    }

    public void salvar() {
        try {
            pd.save(pessoa());
            habSalvar();
            limpaCampos();
            habilita(false);
            JOptionPane.showMessageDialog(null, "Pessoa salva com sucesso.");
        } catch (InvalidStateException e) {
            String mensagem = "";
            for (InvalidValue object : e.getInvalidValues()) {
                mensagem = mensagem + object.getMessage() + "\n";
            }
            JOptionPane.showMessageDialog(null, mensagem);
            pd.cancel();
        } catch (StaleObjectStateException e) {
            limpaCampos();
            habCancelar();
            habilita(false);
            JOptionPane.showMessageDialog(null, "Suas alterações não foram salvas.\nConsulte novamente e altere!");
        }
    }

    public void consultar() {
        limpaCamposConsulta();
        tabelaPessoa(null);
        tab(2, false, false, true);
    }

    public void alterar() {
        habilita(true);
    }

    public void excluir() {
        try {
            UIManager.put("OptionPane.yesButtonText", "Sim");
            UIManager.put("OptionPane.noButtonText", "Não");
            int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta Pessoa?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if (sim == JOptionPane.YES_OPTION) {
                pd.delete(getPessoa());
                habExcluir();
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Pessoa excluída com sucesso.");
            }
        } catch (ConstraintViolationException cve) {
            JOptionPane.showMessageDialog(null, "Essa pessoa não pode ser excluida,\npossui relações.");
        }
    }

    public void cancelar() {
        setPessoa(null);
        HibernateUtility.rollbackTransaction();
        HibernateUtility.closeSession();
        tab(0, true, false, false);
        habilita(false);
        limpaCampos();
    }

    public void listar() {
        tabelaPessoa(pd.consulta(tfNomeConsultar.getText().trim()));
    }

    public void seleciona() {
        if (consulta) {
            if (table.getSelectedRow() != -1) {
                setPessoa(tmPessoa.getLista().get(table.getSelectedRow()));
                pessoa(getPessoa());
                tab(0, true, true, false);
            }
        } else {
            this.dispose();
        }
    }
}

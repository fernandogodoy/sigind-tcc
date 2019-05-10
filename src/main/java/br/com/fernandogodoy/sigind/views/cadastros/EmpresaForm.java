/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Empresa.java
 *
 * Created on 07/07/2010, 00:51:38
 */
package br.com.fernandogodoy.sigind.views.cadastros;

import br.com.fernandogodoy.sigind.dao.cadastros.EmpresaDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Empresa;
import br.com.fernandogodoy.sigind.models.cadastros.Municipio;
import br.com.fernandogodoy.sigind.models.cadastros.Pessoa;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelEmpresa;
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
public class EmpresaForm extends MenuForm {

    private Empresa empresa;
    public TableModelEmpresa tmEmpresa;
    private EmpresaDaoImpl ed = new EmpresaDaoImpl();
    private CidadeForm cf;
    private PessoaForm pf;
    private Boolean consulta;

    public EmpresaForm(Boolean consulta) {
        super(Login.usuario.getEmpresaUsuario());
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        dfCadastro.setDateFormat(DateFormat.getDateInstance());
        habilita(false);
        limpaCampos();
        limpaCamposPessoa();
        limpaCamposConsulta();
        this.consulta = consulta;
        if (consulta) {
            tab(0, true, false, false);
        } else {
            tab(2, false, false, true);
            habNenhum();
            cbTodos.setSelectedIndex(1);
            cbTodos.setEnabled(false);
        }
        tfCodCidade.setDocument(new DocumentRightLeft(5));
        setVisible(true);
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    private Empresa empresa() {
        if (!tfCodigoEmpresa.getText().isEmpty()) {
            getEmpresa().setDtCadEmpresa((Date) dfCadastro.getValue());
            getEmpresa().getPessoa().setIdPessoa(Long.parseLong(tfCodigoPessoa.getText()));
            getEmpresa().setIdEmpresa(Long.parseLong(tfCodigoEmpresa.getText()));
        } else {
            getEmpresa().setDtCadEmpresa(new Date());
        }
        getEmpresa().getPessoa().setBairroPessoa(tfBairro.getText().trim());
        getEmpresa().getPessoa().setCepPessoa(tfCep.getText().trim());
        getEmpresa().getPessoa().setComPessoa(tfComp.getText().trim());
        getEmpresa().getPessoa().setNomePessoa(tfNome.getText().trim());
        getEmpresa().getPessoa().setNumPessoa(tfNum.getText().trim());
        getEmpresa().getPessoa().setObsPessoa(tfObs.getText().trim());
        getEmpresa().getPessoa().setRuaPessoa(tfRua.getText().trim());
        getEmpresa().setRazaoEmpresa(tfRazao.getText().trim());
        getEmpresa().setEmailEmpresa(tfEmail.getText().trim());
        getEmpresa().setCnpjEmpresa(tfCnpj.getText().replace(".", "").replace("/", "").replace("-", "").trim());
        getEmpresa().setAtivo(cbInativo.isSelected());
        return getEmpresa();
    }

    private void empresa(Empresa e) {
        tfCodigoEmpresa.setText(e.getIdEmpresa().toString());
        tfCodigoPessoa.setText(e.getPessoa().getIdPessoa().toString());
        tfNome.setText(e.getPessoa().getNomePessoa());
        dfCadastro.setValue(e.getDtCadEmpresa());
        cbInativo.setSelected(e.getAtivo());
        tfRua.setText(e.getPessoa().getRuaPessoa());
        tfNum.setText(e.getPessoa().getNumPessoa());
        tfBairro.setText(e.getPessoa().getBairroPessoa());
        tfCep.setText(e.getPessoa().getCepPessoa());
        tfComp.setText(e.getPessoa().getComPessoa());
        tfCodCidade.setText(e.getPessoa().getCidade().getIdMunicipio().toString());
        tfCidade.setText(e.getPessoa().getCidade().getxMun());
        tfUf.setText(e.getPessoa().getCidade().getUf().getSigla());
        tfObs.setText(e.getPessoa().getObsPessoa());
        tfCnpj.setText(e.getCnpjEmpresa());
        tfEmail.setText(e.getEmailEmpresa());
        tfRazao.setText(e.getRazaoEmpresa());
    }

    private void habilita(Boolean b) {
        tfRazao.setEditable(b);
        cbInativo.setEnabled(b);
        tfCnpj.setEditable(b);
        tfEmail.setEditable(b);
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
    }

    private void limpaCampos() {
        tfCodigoEmpresa.setText("");
        tfRazao.setText("");
        dfCadastro.setValue(new Date());
        cbInativo.setSelected(false);
        tfCnpj.setText("");
        tfEmail.setText("");
        tabelaEmpresa(null);
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
    }

    private void limpaCamposConsulta() {
        tfNomeConsultar.setText("");
        cbTodos.setSelectedIndex(0);
        table.setModel(new TableModelEmpresa(null));
    }

    private void tab(Integer i, Boolean b1, Boolean b2, Boolean b3) {
        tab.setSelectedIndex(i);
        tab.setEnabledAt(0, b1);
        tab.setEnabledAt(1, b2);
        tab.setEnabledAt(2, b3);
    }

    public void tabelaEmpresa(List<Empresa> lista) {
        tmEmpresa = new TableModelEmpresa(lista);
        table.setModel(tmEmpresa);
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getTableHeader().setReorderingAllowed(false);
    }

    void buscaPessoa() {
        this.setVisible(false);
        pf = new PessoaForm(false);
        if (pf.table.getSelectedRow() != -1) {
            getEmpresa().setPessoa(pf.tmPessoa.getLista().get(pf.table.getSelectedRow()));
            tfCodigoPessoa.setText(getEmpresa().getPessoa().getIdPessoa().toString());
            tfNome.setText(getEmpresa().getPessoa().getNomePessoa());
            tfRua.setText(getEmpresa().getPessoa().getRuaPessoa());
            tfNum.setText(getEmpresa().getPessoa().getNumPessoa());
            tfBairro.setText(getEmpresa().getPessoa().getBairroPessoa());
            tfCep.setText(getEmpresa().getPessoa().getCepPessoa());
            tfComp.setText(getEmpresa().getPessoa().getComPessoa());
            tfCodCidade.setText(getEmpresa().getPessoa().getCidade().getIdMunicipio().toString());
            tfCidade.setText(getEmpresa().getPessoa().getCidade().getxMun());
            tfUf.setText(getEmpresa().getPessoa().getCidade().getUf().getSigla());
            tfObs.setText(getEmpresa().getPessoa().getObsPessoa());
            habilitaPessoa(false);
            btCancelaPessoa.setEnabled(true);
        }
        this.setVisible(true);
    }

    public void buscaCidade() {
        this.setVisible(false);
        cf = new CidadeForm();
        if (cf.table.getSelectedRow() != -1) {
            getEmpresa().getPessoa().setCidade(cf.tmCidade.getLista().get(cf.table.getSelectedRow()));
            tfCodCidade.setText(getEmpresa().getPessoa().getCidade().getIdMunicipio().toString());
            tfCidade.setText(getEmpresa().getPessoa().getCidade().getxMun());
            tfUf.setText(getEmpresa().getPessoa().getCidade().getUf().getSigla());
        }
        this.setVisible(true);
    }

    private void mostraCidade() {
        try {
            Municipio c = new Municipio();
            c = ed.consultaCidade(Long.valueOf(tfCodCidade.getText().trim()));
            if (c != null) {
                getEmpresa().getPessoa().setCidade(c);
                tfCodCidade.setText(getEmpresa().getPessoa().getCidade().getIdMunicipio().toString());
                tfCidade.setText(getEmpresa().getPessoa().getCidade().getxMun());
                tfUf.setText(getEmpresa().getPessoa().getCidade().getUf().getSigla());
            } else {
                tfCodCidade.requestFocus();
                getEmpresa().getPessoa().setCidade(null);
                tfCodCidade.setText("");
                tfCidade.setText("");
                tfUf.setText("");
            }
        } catch (NumberFormatException nfe) {
            getEmpresa().getPessoa().setCidade(null);
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
        tfCodigoEmpresa = new javax.swing.JTextField();
        lbRazao = new javax.swing.JLabel();
        tfRazao = new javax.swing.JTextField();
        cbInativo = new javax.swing.JCheckBox();
        dfCadastro = new net.sf.nachocalendar.components.DateField();
        lbDtCadastro = new javax.swing.JLabel();
        tfCnpj = new javax.swing.JFormattedTextField();
        lbCnpj = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lbEmail = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        lbNomeConsultar = new javax.swing.JLabel();
        tfNomeConsultar = new javax.swing.JTextField();
        cbTodos = new javax.swing.JComboBox();
        btListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Empresa");

        lbCodigo.setText("Código:");

        tfCodigoEmpresa.setColumns(5);
        tfCodigoEmpresa.setEditable(false);

        lbRazao.setText("Razão*:");

        tfRazao.setColumns(50);
        tfRazao.setEditable(false);

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

        lbCnpj.setText("CNPJ*:");

        tfEmail.setColumns(50);
        tfEmail.setEditable(false);

        lbEmail.setText("E-mail*:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Campos Marcados com (*) são de peenchimento obrigatório");

        javax.swing.GroupLayout tab01Layout = new javax.swing.GroupLayout(tab01);
        tab01.setLayout(tab01Layout);
        tab01Layout.setHorizontalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCodigo)
                    .addComponent(lbRazao)
                    .addComponent(lbEmail)
                    .addComponent(lbCnpj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addComponent(tfCodigoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
                        .addComponent(lbDtCadastro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dfCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbInativo))
                    .addComponent(tfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfRazao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.LEADING)))
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
                            .addComponent(lbCodigo)
                            .addComponent(lbDtCadastro)
                            .addComponent(tfCodigoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazao)
                            .addComponent(tfRazao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cbInativo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCnpj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        tab.addTab("Dados do Empresa", tab01);

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addGroup(tab02Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel1))
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
                    .addComponent(lbNum)
                    .addComponent(tfNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Dados Pessoais", tab02);

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
                        .addComponent(cbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(cbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab.addTab("Consultar", tab03);

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
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        getEmpresa().setPessoa(new Pessoa());
}//GEN-LAST:event_btCancelaPessoaActionPerformed

    private void btListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListarActionPerformed
        listar();
}//GEN-LAST:event_btListarActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 2) {
            seleciona();
            habAlterar();
        }
}//GEN-LAST:event_tableMouseClicked

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
    private javax.swing.JButton btListar;
    private javax.swing.JCheckBox cbInativo;
    private javax.swing.JComboBox cbTodos;
    private net.sf.nachocalendar.components.DateField dfCadastro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBairro;
    private javax.swing.JLabel lbCep;
    private javax.swing.JLabel lbCidade;
    private javax.swing.JLabel lbCnpj;
    private javax.swing.JLabel lbCodCidade;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbCodigoPessoa;
    private javax.swing.JLabel lbComp;
    private javax.swing.JLabel lbDtCadastro;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNomeConsultar;
    private javax.swing.JLabel lbNum;
    private javax.swing.JLabel lbObs;
    private javax.swing.JLabel lbRazao;
    private javax.swing.JLabel lbRua;
    private javax.swing.JLabel lbUf;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JPanel tab01;
    private javax.swing.JPanel tab02;
    private javax.swing.JPanel tab03;
    public javax.swing.JTable table;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JFormattedTextField tfCep;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JFormattedTextField tfCnpj;
    private javax.swing.JFormattedTextField tfCodCidade;
    private javax.swing.JTextField tfCodigoEmpresa;
    private javax.swing.JTextField tfCodigoPessoa;
    private javax.swing.JTextField tfComp;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNomeConsultar;
    private javax.swing.JTextField tfNum;
    private javax.swing.JTextArea tfObs;
    private javax.swing.JTextField tfRazao;
    private javax.swing.JTextField tfRua;
    private javax.swing.JTextField tfUf;
    // End of variables declaration//GEN-END:variables

    public void novo() {
        setEmpresa(new Empresa());
        habilita(true);
        btCancelaPessoa.setEnabled(false);
        limpaCampos();
        limpaCamposPessoa();
        tab(0, true, true, false);
    }

    public void salvar() {
        if (tfCidade.getText().trim().equals("") || empresa.getPessoa() == null || tfCnpj.getText().trim().equals("") || tfEmail.getText().trim().equals("") || tfRazao.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Existem campos de preenchimento obrigatório não preenchidos");

        } else {
            try {
                ed.save(empresa());
                habSalvar();
                limpaCampos();
                habilita(false);
                tab(0, true, false, false);
                JOptionPane.showMessageDialog(null, "Empresa salva com sucesso.");
            } catch (InvalidStateException e) {
                String mensagem = "";
                for (InvalidValue object : e.getInvalidValues()) {
                    mensagem = mensagem + object.getMessage() + "\n";
                }
                JOptionPane.showMessageDialog(null, mensagem);
                ed.cancel();
            } catch (StaleObjectStateException e) {
                limpaCampos();
                limpaCamposPessoa();
                habCancelar();
                habilita(false);
                JOptionPane.showMessageDialog(null, "Suas alterações não foram salvas.\nConsulte novamente e altere!");
            } catch (ConstraintViolationException cve) {
                String cause = String.valueOf(cve.getCause());
                if (cause.contains("empresa_cnpjempresa_key")) {
                    JOptionPane.showMessageDialog(null, "CNPJ inválido, por favor digite outro");
                }
                if (cause.contains("empresa_idpessoa_key")) {
                    JOptionPane.showMessageDialog(null, "A Pessoa selecionada já é um Fornecedor,\npor favor selecione outra Pessoa.");
                }
                ed.cancel();
            }
        }
    }

    public void consultar() {
        limpaCamposConsulta();
        tabelaEmpresa(null);
        tab(2, false, false, true);
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
            int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta Empresa?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if (sim == JOptionPane.YES_OPTION) {
                ed.delete(getEmpresa());
                habExcluir();
                limpaCampos();
                limpaCamposPessoa();
                JOptionPane.showMessageDialog(null, "Empresa excluída com sucesso.");
            }
        } catch (ConstraintViolationException cve) {
            JOptionPane.showMessageDialog(null, "Essa empresa não pode ser excluida,\npossui relações.");
        }
    }

    public void cancelar() {
        setEmpresa(null);
        HibernateUtility.rollbackTransaction();
        HibernateUtility.closeSession();
        tab(0, true, false, false);
        habilita(false);
        limpaCampos();
        limpaCamposPessoa();
    }

    public void listar() {
        Boolean ativo = cbTodos.getSelectedIndex() == 0 ? null : cbTodos.getSelectedIndex() == 1 ? false : true;
        tabelaEmpresa(ed.consulta(tfNomeConsultar.getText().trim(), ativo));
    }

    public void seleciona() {
        if (consulta) {
            if (table.getSelectedRow() != -1) {
                setEmpresa(tmEmpresa.getLista().get(table.getSelectedRow()));
                empresa(getEmpresa());
                tab(0, true, true, false);
            }
        } else {
            this.dispose();
        }
    }
}

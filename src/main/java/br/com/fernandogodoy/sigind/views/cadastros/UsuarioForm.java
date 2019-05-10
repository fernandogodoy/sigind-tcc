/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UsuarioForm.java
 *
 * Created on 07/07/2010, 00:54:42
 */
package br.com.fernandogodoy.sigind.views.cadastros;

import br.com.fernandogodoy.sigind.dao.cadastros.UsuarioDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Usuario;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelUsuario;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import br.com.fernandogodoy.sigind.views.principais.Login;
import java.awt.Component;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.validator.InvalidStateException;
import org.hibernate.validator.InvalidValue;

/**
 *
 * @author Fernando
 */
public class UsuarioForm extends MenuForm {

    private Usuario usuario;
    private TableModelUsuario tmUsuario;
    private UsuarioDaoImpl ud = new UsuarioDaoImpl();
    private FuncionarioForm ff;

    public UsuarioForm() {
        super(Login.usuario.getUsuarioUsuario());
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        tab(0, true, false, false, false, false);
        dfCadastro.setDateFormat(DateFormat.getDateInstance());
        habilita(false);
        limpaCampos();
        limpaCamposConsulta();
        setVisible(true);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private String senha() {
        String senha = "";
        for (char c : tfSenha.getPassword()) {
            senha = senha + c;
        }
        return senha;
    }

    private Usuario usuario() {
        if (!tfCodigo.getText().isEmpty()) {
            getUsuario().setDtCadastro((Date) dfCadastro.getValue());
            getUsuario().setIdUsuario(Long.parseLong(tfCodigo.getText()));
        } else {
            getUsuario().setDtCadastro(new Date());
        }
        getUsuario().setUsuario(tfUsuario.getText().trim());
        getUsuario().setSenha(senha());
        //CADASTROS sequenceUsuario(cb, cb, cb, cb)
        getUsuario().setEmpresaUsuario(sequenceUsuario(cb01, cb02, cb03, cb04));
        getUsuario().setClienteUsuario(sequenceUsuario(cb05, cb06, cb07, cb08));
        getUsuario().setFornecedorUsuario(sequenceUsuario(cb09, cb10, cb11, cb12));
        getUsuario().setFuncionarioUsuario(sequenceUsuario(cb13, cb14, cb15, cb16));
        getUsuario().setPessoaUsuario(sequenceUsuario(cb17, cb18, cb19, cb20));
        getUsuario().setSetorUsuario(sequenceUsuario(cb21, cb22, cb23, cb24));
        getUsuario().setFuncaoUsuario(sequenceUsuario(cb25, cb26, cb27, cb28));
        getUsuario().setMateriaUsuario(sequenceUsuario(cb29, cb30, cb31, cb32));
        getUsuario().setPecaUsuario(sequenceUsuario(cb33, cb34, cb35, cb36));
        getUsuario().setMarcaUsuario(sequenceUsuario(cb37, cb38, cb39, cb40));
        getUsuario().setMaquinaUsuario(sequenceUsuario(cb41, cb42, cb43, cb44));
        getUsuario().setUsuarioUsuario(sequenceUsuario(cb45, cb46, cb47, cb48));
        getUsuario().setAtivo(cbInativo.isSelected());
        return getUsuario();
    }

    private void usuario(Usuario u) {
        tfCodigo.setText(u.getIdUsuario().toString());
        tfFuncionario.setText(u.getFuncionario().getPessoa().getNomePessoa());
        tfUsuario.setText(u.getUsuario());
        tfSenha.setText(u.getSenha());
        sequenceUsuario(u.getEmpresaUsuario(), cb01, cb02, cb03, cb04);
        sequenceUsuario(u.getClienteUsuario(), cb05, cb06, cb07, cb08);
        sequenceUsuario(u.getFornecedorUsuario(), cb09, cb10, cb11, cb12);
        sequenceUsuario(u.getFuncionarioUsuario(), cb13, cb14, cb15, cb16);
        sequenceUsuario(u.getPessoaUsuario(), cb17, cb18, cb19, cb20);
        sequenceUsuario(u.getSetorUsuario(), cb21, cb22, cb23, cb24);
        sequenceUsuario(u.getFuncaoUsuario(), cb25, cb26, cb27, cb28);
        sequenceUsuario(u.getMateriaUsuario(), cb29, cb30, cb31, cb32);
        sequenceUsuario(u.getPecaUsuario(), cb33, cb34, cb35, cb36);
        sequenceUsuario(u.getMarcaUsuario(), cb37, cb38, cb39, cb40);
        sequenceUsuario(u.getMaquinaUsuario(), cb41, cb42, cb43, cb44);
        sequenceUsuario(u.getUsuarioUsuario(), cb45, cb46, cb47, cb48);
        dfCadastro.setValue(u.getDtCadastro());
        cbInativo.setSelected(u.getAtivo());
    }

    private void habilita(Boolean b) {
        cbTodosCad.setEnabled(b);
        tfUsuario.setEditable(b);
        tfSenha.setEditable(b);
        tfSenha1.setEditable(b);
        btFuncionario.setEnabled(b);
        cbInativo.setEnabled(b);
        checkBox(tab02, b, false, true);
    }

    private void limpaCampos() {
        tfCodigo.setText("");
        tfFuncionario.setText("");
        tfUsuario.setText("");
        tfSenha.setText("");
        cbTodosCad.setSelected(false);
        cbTodosCad.setText("Marcar todos");
        dfCadastro.setValue(new Date());
        cbInativo.setSelected(false);
        checkBox(tab02, false, true, false);
        tabelaUsuario(null);
    }

    private void limpaCamposConsulta() {
        tfNomeConsultar.setText("");
        cbTodos.setSelectedIndex(0);
        table.setModel(new TableModelUsuario(null));
    }

    public void tab(Integer i, Boolean b1, Boolean b2, Boolean b3, Boolean b4, Boolean b5) {
        tab.setSelectedIndex(i);
        tab.setEnabledAt(0, b1);
        tab.setEnabledAt(1, b2);
        tab.setEnabledAt(2, b3);
        tab.setEnabledAt(3, b4);
        tab.setEnabledAt(4, b5);
    }

    public void tabelaUsuario(List<Usuario> lista) {
        tmUsuario = new TableModelUsuario(lista);
        table.setModel(tmUsuario);
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getTableHeader().setReorderingAllowed(false);
    }

    private String sequenceUsuario(JCheckBox... cb) {
        String sequence = "";
        for (JCheckBox check : cb) {
            if (check.isSelected()) {
                sequence = sequence + "1";
            } else {
                sequence = sequence + "0";
            }
        }
        return sequence;
    }

    private void sequenceUsuario(String s, JCheckBox... cb) {
        Integer i = 0;
        String[] lista = s.split("");
        for (String string : lista) {
            if (!string.equals("")) {
                if (string.equals("1")) {
                    cb[i].setSelected(true);
                } else {
                    cb[i].setSelected(false);
                }
                i++;
            }
        }
    }

    private void checkBox(JPanel panel, Boolean b, Boolean selected, Boolean enabled) {
        Component[] c = panel.getComponents();
        for (Component component : c) {
            if (component instanceof JPanel) {
                JPanel jp = (JPanel) component;
                Component[] c2 = jp.getComponents();
                for (Component component2 : c2) {
                    if (component2 instanceof JCheckBox) {
                        JCheckBox cb = (JCheckBox) component2;
                        if (selected) {
                            cb.setSelected(b);
                        }
                        if (enabled) {
                            cb.setEnabled(b);
                        }
                    }
                }
            }
        }
    }

    public void buscaFuncionario() {
        this.setVisible(false);
        ff = new FuncionarioForm(false);
        if (ff.table.getSelectedRow() != -1) {
            getUsuario().setFuncionario(ff.tmFuncionario.getLista().get(ff.table.getSelectedRow()));
            tfFuncionario.setText(getUsuario().getFuncionario().getPessoa().getNomePessoa());
        }
        this.setVisible(true);
    }

    private void cbAlterarExcluir(JCheckBox cb01) {
        cb01.setSelected(true);
    }

    private void cbConsultar(JCheckBox cb01, JCheckBox cb02) {
        cb01.setSelected(false);
        cb02.setSelected(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JTabbedPane();
        tab01 = new javax.swing.JPanel();
        lbFuncionario = new javax.swing.JLabel();
        tfFuncionario = new javax.swing.JTextField();
        btFuncionario = new javax.swing.JButton();
        lbUsuario = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        lbSenha = new javax.swing.JLabel();
        tfSenha = new javax.swing.JPasswordField();
        lbCodigo = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        lbDtCadastro = new javax.swing.JLabel();
        dfCadastro = new net.sf.nachocalendar.components.DateField();
        cbInativo = new javax.swing.JCheckBox();
        lbSenha1 = new javax.swing.JLabel();
        tfSenha1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        tab02 = new javax.swing.JPanel();
        empresaPanel = new javax.swing.JPanel();
        cb01 = new javax.swing.JCheckBox();
        cb02 = new javax.swing.JCheckBox();
        cb03 = new javax.swing.JCheckBox();
        cb04 = new javax.swing.JCheckBox();
        clientePanel = new javax.swing.JPanel();
        cb05 = new javax.swing.JCheckBox();
        cb06 = new javax.swing.JCheckBox();
        cb07 = new javax.swing.JCheckBox();
        cb08 = new javax.swing.JCheckBox();
        fornecedorPanel = new javax.swing.JPanel();
        cb09 = new javax.swing.JCheckBox();
        cb10 = new javax.swing.JCheckBox();
        cb11 = new javax.swing.JCheckBox();
        cb12 = new javax.swing.JCheckBox();
        funcionarioPanel = new javax.swing.JPanel();
        cb13 = new javax.swing.JCheckBox();
        cb14 = new javax.swing.JCheckBox();
        cb15 = new javax.swing.JCheckBox();
        cb16 = new javax.swing.JCheckBox();
        pessoaPanel = new javax.swing.JPanel();
        cb17 = new javax.swing.JCheckBox();
        cb18 = new javax.swing.JCheckBox();
        cb19 = new javax.swing.JCheckBox();
        cb20 = new javax.swing.JCheckBox();
        setorPanel = new javax.swing.JPanel();
        cb21 = new javax.swing.JCheckBox();
        cb22 = new javax.swing.JCheckBox();
        cb23 = new javax.swing.JCheckBox();
        cb24 = new javax.swing.JCheckBox();
        funcaoPanel = new javax.swing.JPanel();
        cb25 = new javax.swing.JCheckBox();
        cb26 = new javax.swing.JCheckBox();
        cb27 = new javax.swing.JCheckBox();
        cb28 = new javax.swing.JCheckBox();
        materiaPanel = new javax.swing.JPanel();
        cb29 = new javax.swing.JCheckBox();
        cb30 = new javax.swing.JCheckBox();
        cb31 = new javax.swing.JCheckBox();
        cb32 = new javax.swing.JCheckBox();
        pecaPanel = new javax.swing.JPanel();
        cb33 = new javax.swing.JCheckBox();
        cb34 = new javax.swing.JCheckBox();
        cb35 = new javax.swing.JCheckBox();
        cb36 = new javax.swing.JCheckBox();
        marcaPanel = new javax.swing.JPanel();
        cb37 = new javax.swing.JCheckBox();
        cb38 = new javax.swing.JCheckBox();
        cb39 = new javax.swing.JCheckBox();
        cb40 = new javax.swing.JCheckBox();
        maquinaPanel = new javax.swing.JPanel();
        cb41 = new javax.swing.JCheckBox();
        cb42 = new javax.swing.JCheckBox();
        cb43 = new javax.swing.JCheckBox();
        cb44 = new javax.swing.JCheckBox();
        usuarioPanel = new javax.swing.JPanel();
        cb45 = new javax.swing.JCheckBox();
        cb46 = new javax.swing.JCheckBox();
        cb47 = new javax.swing.JCheckBox();
        cb48 = new javax.swing.JCheckBox();
        cbTodosCad = new javax.swing.JCheckBox();
        tab03 = new javax.swing.JPanel();
        empresaPanel1 = new javax.swing.JPanel();
        cb2 = new javax.swing.JCheckBox();
        cb3 = new javax.swing.JCheckBox();
        empresaPanel2 = new javax.swing.JPanel();
        cb4 = new javax.swing.JCheckBox();
        cb5 = new javax.swing.JCheckBox();
        empresaPanel3 = new javax.swing.JPanel();
        cb6 = new javax.swing.JCheckBox();
        cb7 = new javax.swing.JCheckBox();
        empresaPanel4 = new javax.swing.JPanel();
        cb8 = new javax.swing.JCheckBox();
        cb9 = new javax.swing.JCheckBox();
        empresaPanel5 = new javax.swing.JPanel();
        cb49 = new javax.swing.JCheckBox();
        cb50 = new javax.swing.JCheckBox();
        empresaPanel6 = new javax.swing.JPanel();
        cb51 = new javax.swing.JCheckBox();
        cb52 = new javax.swing.JCheckBox();
        tab04 = new javax.swing.JPanel();
        empresaPanel7 = new javax.swing.JPanel();
        cb53 = new javax.swing.JCheckBox();
        cb54 = new javax.swing.JCheckBox();
        cb55 = new javax.swing.JCheckBox();
        cb56 = new javax.swing.JCheckBox();
        cb57 = new javax.swing.JCheckBox();
        cb58 = new javax.swing.JCheckBox();
        cb59 = new javax.swing.JCheckBox();
        cb60 = new javax.swing.JCheckBox();
        empresaPanel8 = new javax.swing.JPanel();
        cb61 = new javax.swing.JCheckBox();
        cb62 = new javax.swing.JCheckBox();
        cb63 = new javax.swing.JCheckBox();
        cb64 = new javax.swing.JCheckBox();
        cb65 = new javax.swing.JCheckBox();
        cb66 = new javax.swing.JCheckBox();
        empresaPanel9 = new javax.swing.JPanel();
        cb67 = new javax.swing.JCheckBox();
        cb68 = new javax.swing.JCheckBox();
        cb69 = new javax.swing.JCheckBox();
        cb70 = new javax.swing.JCheckBox();
        tab05 = new javax.swing.JPanel();
        lbNomeConsultar = new javax.swing.JLabel();
        tfNomeConsultar = new javax.swing.JTextField();
        cbTodos = new javax.swing.JComboBox();
        btListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Usuário");

        lbFuncionario.setText("Funcionário:*");

        tfFuncionario.setColumns(50);
        tfFuncionario.setEditable(false);

        btFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/fernandogodoy/sigind/imagens/searchin.png"))); // NOI18N
        btFuncionario.setEnabled(false);
        btFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFuncionarioActionPerformed(evt);
            }
        });

        lbUsuario.setText("Usuário:*");

        tfUsuario.setColumns(20);
        tfUsuario.setEditable(false);

        lbSenha.setText("Senha:*");

        tfSenha.setColumns(20);
        tfSenha.setEditable(false);

        lbCodigo.setText("Código:");

        tfCodigo.setColumns(4);
        tfCodigo.setEditable(false);

        lbDtCadastro.setText("Data Cadastro:");

        dfCadastro.setEnabled(false);
        dfCadastro.setPreferredSize(new java.awt.Dimension(75, 20));

        cbInativo.setText("Inativo");
        cbInativo.setEnabled(false);

        lbSenha1.setText("Conf. Senha:*");

        tfSenha1.setColumns(20);
        tfSenha1.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Campos Marcados com (*) são de peenchimento obrigatório");

        javax.swing.GroupLayout tab01Layout = new javax.swing.GroupLayout(tab01);
        tab01.setLayout(tab01Layout);
        tab01Layout.setHorizontalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFuncionario)
                    .addComponent(lbUsuario)
                    .addComponent(lbSenha)
                    .addComponent(lbCodigo)
                    .addComponent(lbSenha1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                        .addComponent(lbDtCadastro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dfCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbInativo))
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addComponent(tfFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btFuncionario))
                    .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        tab01Layout.setVerticalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dfCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbInativo)
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCodigo)
                            .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDtCadastro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFuncionario)
                            .addComponent(tfFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btFuncionario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbUsuario)
                            .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSenha)
                            .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSenha1)
                            .addComponent(tfSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        tab.addTab("Dados do usuário", tab01);

        empresaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Empresa"));

        cb01.setText("Incluir");

        cb02.setText("Alterar");
        cb02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb02ActionPerformed(evt);
            }
        });

        cb03.setText("Consultar");
        cb03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb03ActionPerformed(evt);
            }
        });

        cb04.setText("Excluir");
        cb04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb04ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanelLayout = new javax.swing.GroupLayout(empresaPanel);
        empresaPanel.setLayout(empresaPanelLayout);
        empresaPanelLayout.setHorizontalGroup(
            empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb01)
                    .addComponent(cb02)
                    .addComponent(cb03)
                    .addComponent(cb04))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        empresaPanelLayout.setVerticalGroup(
            empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanelLayout.createSequentialGroup()
                .addComponent(cb01)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb02)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb03)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb04))
        );

        clientePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        clientePanel.setPreferredSize(new java.awt.Dimension(95, 119));

        cb05.setText("Incluir");

        cb06.setText("Alterar");
        cb06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb06ActionPerformed(evt);
            }
        });

        cb07.setText("Consultar");
        cb07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb07ActionPerformed(evt);
            }
        });

        cb08.setText("Excluir");
        cb08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb08ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout clientePanelLayout = new javax.swing.GroupLayout(clientePanel);
        clientePanel.setLayout(clientePanelLayout);
        clientePanelLayout.setHorizontalGroup(
            clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb07)
                    .addComponent(cb06)
                    .addComponent(cb05)
                    .addComponent(cb08))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        clientePanelLayout.setVerticalGroup(
            clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientePanelLayout.createSequentialGroup()
                .addComponent(cb05)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb06)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb07)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cb08))
        );

        fornecedorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Fornecedor"));

        cb09.setText("Incluir");

        cb10.setText("Alterar");
        cb10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb10ActionPerformed(evt);
            }
        });

        cb11.setText("Consultar");
        cb11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb11ActionPerformed(evt);
            }
        });

        cb12.setText("Excluir");
        cb12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fornecedorPanelLayout = new javax.swing.GroupLayout(fornecedorPanel);
        fornecedorPanel.setLayout(fornecedorPanelLayout);
        fornecedorPanelLayout.setHorizontalGroup(
            fornecedorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fornecedorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fornecedorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb09)
                    .addComponent(cb10)
                    .addComponent(cb11)
                    .addComponent(cb12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fornecedorPanelLayout.setVerticalGroup(
            fornecedorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fornecedorPanelLayout.createSequentialGroup()
                .addComponent(cb09)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb12))
        );

        funcionarioPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Funcionário"));

        cb13.setText("Incluir");

        cb14.setText("Alterar");
        cb14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb14ActionPerformed(evt);
            }
        });

        cb15.setText("Consultar");
        cb15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb15ActionPerformed(evt);
            }
        });

        cb16.setText("Excluir");
        cb16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout funcionarioPanelLayout = new javax.swing.GroupLayout(funcionarioPanel);
        funcionarioPanel.setLayout(funcionarioPanelLayout);
        funcionarioPanelLayout.setHorizontalGroup(
            funcionarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(funcionarioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(funcionarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb13)
                    .addComponent(cb14)
                    .addComponent(cb15)
                    .addComponent(cb16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        funcionarioPanelLayout.setVerticalGroup(
            funcionarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(funcionarioPanelLayout.createSequentialGroup()
                .addComponent(cb13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb16))
        );

        pessoaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Pessoa"));

        cb17.setText("Incluir");

        cb18.setText("Alterar");
        cb18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb18ActionPerformed(evt);
            }
        });

        cb19.setText("Consultar");
        cb19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb19ActionPerformed(evt);
            }
        });

        cb20.setText("Excluir");
        cb20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pessoaPanelLayout = new javax.swing.GroupLayout(pessoaPanel);
        pessoaPanel.setLayout(pessoaPanelLayout);
        pessoaPanelLayout.setHorizontalGroup(
            pessoaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pessoaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pessoaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb17)
                    .addComponent(cb18)
                    .addComponent(cb19)
                    .addComponent(cb20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pessoaPanelLayout.setVerticalGroup(
            pessoaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pessoaPanelLayout.createSequentialGroup()
                .addComponent(cb17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb20))
        );

        setorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Setor"));

        cb21.setText("Incluir");

        cb22.setText("Alterar");
        cb22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb22ActionPerformed(evt);
            }
        });

        cb23.setText("Consultar");
        cb23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb23ActionPerformed(evt);
            }
        });

        cb24.setText("Excluir");
        cb24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout setorPanelLayout = new javax.swing.GroupLayout(setorPanel);
        setorPanel.setLayout(setorPanelLayout);
        setorPanelLayout.setHorizontalGroup(
            setorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(setorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb21)
                    .addComponent(cb22)
                    .addComponent(cb23)
                    .addComponent(cb24))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setorPanelLayout.setVerticalGroup(
            setorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setorPanelLayout.createSequentialGroup()
                .addComponent(cb21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb24))
        );

        funcaoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Função"));

        cb25.setText("Incluir");

        cb26.setText("Alterar");
        cb26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb26ActionPerformed(evt);
            }
        });

        cb27.setText("Consultar");
        cb27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb27ActionPerformed(evt);
            }
        });

        cb28.setText("Excluir");
        cb28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout funcaoPanelLayout = new javax.swing.GroupLayout(funcaoPanel);
        funcaoPanel.setLayout(funcaoPanelLayout);
        funcaoPanelLayout.setHorizontalGroup(
            funcaoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(funcaoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(funcaoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb25)
                    .addComponent(cb26)
                    .addComponent(cb27)
                    .addComponent(cb28))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        funcaoPanelLayout.setVerticalGroup(
            funcaoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(funcaoPanelLayout.createSequentialGroup()
                .addComponent(cb25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb28))
        );

        materiaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Matéria Prima"));
        materiaPanel.setPreferredSize(new java.awt.Dimension(95, 119));

        cb29.setText("Incluir");

        cb30.setText("Alterar");
        cb30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb30ActionPerformed(evt);
            }
        });

        cb31.setText("Consultar");
        cb31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb31ActionPerformed(evt);
            }
        });

        cb32.setText("Excluir");
        cb32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout materiaPanelLayout = new javax.swing.GroupLayout(materiaPanel);
        materiaPanel.setLayout(materiaPanelLayout);
        materiaPanelLayout.setHorizontalGroup(
            materiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(materiaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(materiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb31)
                    .addComponent(cb30)
                    .addComponent(cb29)
                    .addComponent(cb32))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        materiaPanelLayout.setVerticalGroup(
            materiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(materiaPanelLayout.createSequentialGroup()
                .addComponent(cb29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cb32))
        );

        pecaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Peça"));

        cb33.setText("Incluir");

        cb34.setText("Alterar");
        cb34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb34ActionPerformed(evt);
            }
        });

        cb35.setText("Consultar");
        cb35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb35ActionPerformed(evt);
            }
        });

        cb36.setText("Excluir");
        cb36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb36ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pecaPanelLayout = new javax.swing.GroupLayout(pecaPanel);
        pecaPanel.setLayout(pecaPanelLayout);
        pecaPanelLayout.setHorizontalGroup(
            pecaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pecaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pecaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb33)
                    .addComponent(cb34)
                    .addComponent(cb35)
                    .addComponent(cb36))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pecaPanelLayout.setVerticalGroup(
            pecaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pecaPanelLayout.createSequentialGroup()
                .addComponent(cb33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb36))
        );

        marcaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Marca"));

        cb37.setText("Incluir");

        cb38.setText("Alterar");
        cb38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb38ActionPerformed(evt);
            }
        });

        cb39.setText("Consultar");
        cb39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb39ActionPerformed(evt);
            }
        });

        cb40.setText("Excluir");
        cb40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb40ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout marcaPanelLayout = new javax.swing.GroupLayout(marcaPanel);
        marcaPanel.setLayout(marcaPanelLayout);
        marcaPanelLayout.setHorizontalGroup(
            marcaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marcaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb37)
                    .addComponent(cb38)
                    .addComponent(cb39)
                    .addComponent(cb40))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        marcaPanelLayout.setVerticalGroup(
            marcaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcaPanelLayout.createSequentialGroup()
                .addComponent(cb37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb40))
        );

        maquinaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Maquina"));

        cb41.setText("Incluir");

        cb42.setText("Alterar");
        cb42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb42ActionPerformed(evt);
            }
        });

        cb43.setText("Consultar");
        cb43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb43ActionPerformed(evt);
            }
        });

        cb44.setText("Excluir");
        cb44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb44ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout maquinaPanelLayout = new javax.swing.GroupLayout(maquinaPanel);
        maquinaPanel.setLayout(maquinaPanelLayout);
        maquinaPanelLayout.setHorizontalGroup(
            maquinaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maquinaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(maquinaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb41)
                    .addComponent(cb42)
                    .addComponent(cb43)
                    .addComponent(cb44))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        maquinaPanelLayout.setVerticalGroup(
            maquinaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maquinaPanelLayout.createSequentialGroup()
                .addComponent(cb41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb44))
        );

        usuarioPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuário"));

        cb45.setText("Incluir");

        cb46.setText("Alterar");
        cb46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb46ActionPerformed(evt);
            }
        });

        cb47.setText("Consultar");
        cb47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb47ActionPerformed(evt);
            }
        });

        cb48.setText("Excluir");
        cb48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb48ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout usuarioPanelLayout = new javax.swing.GroupLayout(usuarioPanel);
        usuarioPanel.setLayout(usuarioPanelLayout);
        usuarioPanelLayout.setHorizontalGroup(
            usuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuarioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(usuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb45)
                    .addComponent(cb46)
                    .addComponent(cb47)
                    .addComponent(cb48))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        usuarioPanelLayout.setVerticalGroup(
            usuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuarioPanelLayout.createSequentialGroup()
                .addComponent(cb45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb48))
        );

        cbTodosCad.setText("Marcar Todos");
        cbTodosCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTodosCadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab02Layout = new javax.swing.GroupLayout(tab02);
        tab02.setLayout(tab02Layout);
        tab02Layout.setHorizontalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab02Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTodosCad)
                    .addGroup(tab02Layout.createSequentialGroup()
                        .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(empresaPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(funcaoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab02Layout.createSequentialGroup()
                                .addComponent(materiaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pecaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(marcaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(maquinaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(usuarioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab02Layout.createSequentialGroup()
                                .addComponent(clientePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fornecedorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(funcionarioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pessoaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(setorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        tab02Layout.setVerticalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab02Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbTodosCad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(empresaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pessoaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(funcionarioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fornecedorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usuarioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maquinaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(marcaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pecaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materiaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(funcaoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Cadastros", tab02);

        empresaPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordem Produção"));

        cb2.setText("Acessa");

        cb3.setText("Não Acessa");
        cb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanel1Layout = new javax.swing.GroupLayout(empresaPanel1);
        empresaPanel1.setLayout(empresaPanel1Layout);
        empresaPanel1Layout.setHorizontalGroup(
            empresaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb2)
                    .addComponent(cb3))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        empresaPanel1Layout.setVerticalGroup(
            empresaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel1Layout.createSequentialGroup()
                .addComponent(cb2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb3)
                .addContainerGap())
        );

        empresaPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Compra"));

        cb4.setText("Acessa");

        cb5.setText("Não Acessa");
        cb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanel2Layout = new javax.swing.GroupLayout(empresaPanel2);
        empresaPanel2.setLayout(empresaPanel2Layout);
        empresaPanel2Layout.setHorizontalGroup(
            empresaPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb4)
                    .addComponent(cb5))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        empresaPanel2Layout.setVerticalGroup(
            empresaPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel2Layout.createSequentialGroup()
                .addComponent(cb4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb5)
                .addContainerGap())
        );

        empresaPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Venda"));

        cb6.setText("Acessa");

        cb7.setText("Não Acessa");
        cb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanel3Layout = new javax.swing.GroupLayout(empresaPanel3);
        empresaPanel3.setLayout(empresaPanel3Layout);
        empresaPanel3Layout.setHorizontalGroup(
            empresaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb6)
                    .addComponent(cb7))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        empresaPanel3Layout.setVerticalGroup(
            empresaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel3Layout.createSequentialGroup()
                .addComponent(cb6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb7)
                .addContainerGap())
        );

        empresaPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Pagamento de Contas"));

        cb8.setText("Acessa");

        cb9.setText("Não Acessa");
        cb9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanel4Layout = new javax.swing.GroupLayout(empresaPanel4);
        empresaPanel4.setLayout(empresaPanel4Layout);
        empresaPanel4Layout.setHorizontalGroup(
            empresaPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb8)
                    .addComponent(cb9))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        empresaPanel4Layout.setVerticalGroup(
            empresaPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel4Layout.createSequentialGroup()
                .addComponent(cb8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb9)
                .addContainerGap())
        );

        empresaPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Recebimento de Contas"));

        cb49.setText("Acessa");

        cb50.setText("Não Acessa");
        cb50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb50ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanel5Layout = new javax.swing.GroupLayout(empresaPanel5);
        empresaPanel5.setLayout(empresaPanel5Layout);
        empresaPanel5Layout.setHorizontalGroup(
            empresaPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb49)
                    .addComponent(cb50))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        empresaPanel5Layout.setVerticalGroup(
            empresaPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel5Layout.createSequentialGroup()
                .addComponent(cb49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb50)
                .addContainerGap())
        );

        empresaPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Expedição"));

        cb51.setText("Acessa");

        cb52.setText("Não Acessa");
        cb52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb52ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanel6Layout = new javax.swing.GroupLayout(empresaPanel6);
        empresaPanel6.setLayout(empresaPanel6Layout);
        empresaPanel6Layout.setHorizontalGroup(
            empresaPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb51)
                    .addComponent(cb52))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        empresaPanel6Layout.setVerticalGroup(
            empresaPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel6Layout.createSequentialGroup()
                .addComponent(cb51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb52)
                .addContainerGap())
        );

        javax.swing.GroupLayout tab03Layout = new javax.swing.GroupLayout(tab03);
        tab03.setLayout(tab03Layout);
        tab03Layout.setHorizontalGroup(
            tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab03Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(empresaPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(empresaPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(empresaPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(empresaPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(empresaPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(empresaPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(327, Short.MAX_VALUE))
        );
        tab03Layout.setVerticalGroup(
            tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab03Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab03Layout.createSequentialGroup()
                        .addComponent(empresaPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(empresaPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab03Layout.createSequentialGroup()
                        .addComponent(empresaPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(empresaPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab03Layout.createSequentialGroup()
                        .addComponent(empresaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(empresaPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        tab.addTab("Movimentos", tab03);

        empresaPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastrais"));

        cb53.setText("Lista de Cliente");

        cb54.setText("Detalhes Clientes");
        cb54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb54ActionPerformed(evt);
            }
        });

        cb55.setText("Lista Fornecedor");
        cb55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb55ActionPerformed(evt);
            }
        });

        cb56.setText("Detalhes Fornecedor");
        cb56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb56ActionPerformed(evt);
            }
        });

        cb57.setText("Lista  Funcionario");
        cb57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb57ActionPerformed(evt);
            }
        });

        cb58.setText("Detalhes  Funcionario");
        cb58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb58ActionPerformed(evt);
            }
        });

        cb59.setText("Lista Usuario");
        cb59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb59ActionPerformed(evt);
            }
        });

        cb60.setText("Detalhes Usuario");
        cb60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb60ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanel7Layout = new javax.swing.GroupLayout(empresaPanel7);
        empresaPanel7.setLayout(empresaPanel7Layout);
        empresaPanel7Layout.setHorizontalGroup(
            empresaPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb53)
                    .addComponent(cb54)
                    .addComponent(cb55)
                    .addComponent(cb56)
                    .addComponent(cb57)
                    .addComponent(cb58)
                    .addComponent(cb59)
                    .addComponent(cb60))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        empresaPanel7Layout.setVerticalGroup(
            empresaPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel7Layout.createSequentialGroup()
                .addComponent(cb53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb60)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        empresaPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Gerenciais"));

        cb61.setText("Ordem Produção");

        cb62.setText("Compras");
        cb62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb62ActionPerformed(evt);
            }
        });

        cb63.setText("Vendas");
        cb63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb63ActionPerformed(evt);
            }
        });

        cb64.setText("Contas Pagar");
        cb64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb64ActionPerformed(evt);
            }
        });

        cb65.setText("Contas Receber");
        cb65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb65ActionPerformed(evt);
            }
        });

        cb66.setText("Expedição");
        cb66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb66ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanel8Layout = new javax.swing.GroupLayout(empresaPanel8);
        empresaPanel8.setLayout(empresaPanel8Layout);
        empresaPanel8Layout.setHorizontalGroup(
            empresaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb61)
                    .addComponent(cb62)
                    .addComponent(cb63)
                    .addComponent(cb64)
                    .addComponent(cb65)
                    .addComponent(cb66))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        empresaPanel8Layout.setVerticalGroup(
            empresaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel8Layout.createSequentialGroup()
                .addComponent(cb61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb66)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        empresaPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Estratégicos"));

        cb67.setText("Produção");

        cb68.setText("Peças Mais Vendidas");
        cb68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb68ActionPerformed(evt);
            }
        });

        cb69.setText("Maiores Clientes");
        cb69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb69ActionPerformed(evt);
            }
        });

        cb70.setText("Maiores Fornecedores");
        cb70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb70ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanel9Layout = new javax.swing.GroupLayout(empresaPanel9);
        empresaPanel9.setLayout(empresaPanel9Layout);
        empresaPanel9Layout.setHorizontalGroup(
            empresaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(empresaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb67)
                    .addComponent(cb68)
                    .addComponent(cb69)
                    .addComponent(cb70)))
        );
        empresaPanel9Layout.setVerticalGroup(
            empresaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanel9Layout.createSequentialGroup()
                .addComponent(cb67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb70)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tab04Layout = new javax.swing.GroupLayout(tab04);
        tab04.setLayout(tab04Layout);
        tab04Layout.setHorizontalGroup(
            tab04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab04Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empresaPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(empresaPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(empresaPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );
        tab04Layout.setVerticalGroup(
            tab04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab04Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(empresaPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empresaPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empresaPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        tab.addTab("Relatórios", tab04);

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

        javax.swing.GroupLayout tab05Layout = new javax.swing.GroupLayout(tab05);
        tab05.setLayout(tab05Layout);
        tab05Layout.setHorizontalGroup(
            tab05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab05Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(tab05Layout.createSequentialGroup()
                        .addComponent(lbNomeConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomeConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btListar)))
                .addContainerGap())
        );
        tab05Layout.setVerticalGroup(
            tab05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab05Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomeConsultar)
                    .addComponent(tfNomeConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab.addTab("Consultar", tab05);

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
                .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTodosCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTodosCadActionPerformed
        if (cbTodosCad.isSelected()) {
            cbTodosCad.setText("Desmarcar todos");
            checkBox(tab02, true, true, false);
        } else {
            cbTodosCad.setText("Marcar todos");
            checkBox(tab02, false, true, false);
        }
}//GEN-LAST:event_cbTodosCadActionPerformed

    private void btListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListarActionPerformed
        listar();
}//GEN-LAST:event_btListarActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 2) {
            seleciona();
            habAlterar();
        }
}//GEN-LAST:event_tableMouseClicked

    private void btFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFuncionarioActionPerformed
        buscaFuncionario();
    }//GEN-LAST:event_btFuncionarioActionPerformed

    private void cb02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb02ActionPerformed
        cbAlterarExcluir(cb03);
    }//GEN-LAST:event_cb02ActionPerformed

    private void cb03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb03ActionPerformed
        cbConsultar(cb02, cb04);
    }//GEN-LAST:event_cb03ActionPerformed

    private void cb04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb04ActionPerformed
        cbAlterarExcluir(cb03);
    }//GEN-LAST:event_cb04ActionPerformed

    private void cb06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb06ActionPerformed
        cbAlterarExcluir(cb07);
    }//GEN-LAST:event_cb06ActionPerformed

    private void cb07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb07ActionPerformed
        cbConsultar(cb06, cb08);
    }//GEN-LAST:event_cb07ActionPerformed

    private void cb08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb08ActionPerformed
        cbAlterarExcluir(cb07);
    }//GEN-LAST:event_cb08ActionPerformed

    private void cb10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb10ActionPerformed
        cbAlterarExcluir(cb11);
    }//GEN-LAST:event_cb10ActionPerformed

    private void cb11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb11ActionPerformed
        cbConsultar(cb10, cb12);
    }//GEN-LAST:event_cb11ActionPerformed

    private void cb12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb12ActionPerformed
        cbAlterarExcluir(cb11);
    }//GEN-LAST:event_cb12ActionPerformed

    private void cb14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb14ActionPerformed
        cbAlterarExcluir(cb15);
    }//GEN-LAST:event_cb14ActionPerformed

    private void cb15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb15ActionPerformed
        cbConsultar(cb14, cb16);
    }//GEN-LAST:event_cb15ActionPerformed

    private void cb16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb16ActionPerformed
        cbAlterarExcluir(cb15);
    }//GEN-LAST:event_cb16ActionPerformed

    private void cb18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb18ActionPerformed
        cbAlterarExcluir(cb19);
    }//GEN-LAST:event_cb18ActionPerformed

    private void cb19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb19ActionPerformed
        cbConsultar(cb18, cb20);
    }//GEN-LAST:event_cb19ActionPerformed

    private void cb20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb20ActionPerformed
        cbAlterarExcluir(cb19);
    }//GEN-LAST:event_cb20ActionPerformed

    private void cb22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb22ActionPerformed
        cbAlterarExcluir(cb23);
    }//GEN-LAST:event_cb22ActionPerformed

    private void cb23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb23ActionPerformed
        cbConsultar(cb22, cb24);
    }//GEN-LAST:event_cb23ActionPerformed

    private void cb24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb24ActionPerformed
        cbAlterarExcluir(cb23);
    }//GEN-LAST:event_cb24ActionPerformed

    private void cb26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb26ActionPerformed
        cbAlterarExcluir(cb27);
    }//GEN-LAST:event_cb26ActionPerformed

    private void cb27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb27ActionPerformed
        cbConsultar(cb26, cb28);
    }//GEN-LAST:event_cb27ActionPerformed

    private void cb28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb28ActionPerformed
        cbAlterarExcluir(cb27);
    }//GEN-LAST:event_cb28ActionPerformed

    private void cb30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb30ActionPerformed
        cbAlterarExcluir(cb31);
    }//GEN-LAST:event_cb30ActionPerformed

    private void cb31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb31ActionPerformed
        cbConsultar(cb30, cb32);
    }//GEN-LAST:event_cb31ActionPerformed

    private void cb32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb32ActionPerformed
        cbAlterarExcluir(cb31);
    }//GEN-LAST:event_cb32ActionPerformed

    private void cb34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb34ActionPerformed
        cbAlterarExcluir(cb35);
    }//GEN-LAST:event_cb34ActionPerformed

    private void cb35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb35ActionPerformed
        cbConsultar(cb34, cb36);
    }//GEN-LAST:event_cb35ActionPerformed

    private void cb36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb36ActionPerformed
        cbAlterarExcluir(cb35);
    }//GEN-LAST:event_cb36ActionPerformed

    private void cb38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb38ActionPerformed
        cbAlterarExcluir(cb39);
    }//GEN-LAST:event_cb38ActionPerformed

    private void cb39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb39ActionPerformed
        cbConsultar(cb38, cb40);
    }//GEN-LAST:event_cb39ActionPerformed

    private void cb40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb40ActionPerformed
        cbAlterarExcluir(cb39);
    }//GEN-LAST:event_cb40ActionPerformed

    private void cb42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb42ActionPerformed
        cbAlterarExcluir(cb43);
    }//GEN-LAST:event_cb42ActionPerformed

    private void cb43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb43ActionPerformed
        cbConsultar(cb42, cb44);
    }//GEN-LAST:event_cb43ActionPerformed

    private void cb44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb44ActionPerformed
        cbAlterarExcluir(cb43);
    }//GEN-LAST:event_cb44ActionPerformed

    private void cb46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb46ActionPerformed
        cbAlterarExcluir(cb47);
    }//GEN-LAST:event_cb46ActionPerformed

    private void cb47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb47ActionPerformed
        cbConsultar(cb46, cb48);
    }//GEN-LAST:event_cb47ActionPerformed

    private void cb48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb48ActionPerformed
        cbAlterarExcluir(cb47);
    }//GEN-LAST:event_cb48ActionPerformed

    private void cb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb3ActionPerformed

    private void cb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb5ActionPerformed

    private void cb7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb7ActionPerformed

    private void cb9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb9ActionPerformed

    private void cb50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb50ActionPerformed

    private void cb52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb52ActionPerformed

    private void cb54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb54ActionPerformed

    private void cb55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb55ActionPerformed

    private void cb56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb56ActionPerformed

    private void cb57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb57ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb57ActionPerformed

    private void cb58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb58ActionPerformed

    private void cb59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb59ActionPerformed

    private void cb60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb60ActionPerformed

    private void cb62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb62ActionPerformed

    private void cb63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb63ActionPerformed

    private void cb64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb64ActionPerformed

    private void cb65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb65ActionPerformed

    private void cb66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb66ActionPerformed

    private void cb68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb68ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb68ActionPerformed

    private void cb69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb69ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb69ActionPerformed

    private void cb70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb70ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb70ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFuncionario;
    private javax.swing.JButton btListar;
    private javax.swing.JCheckBox cb01;
    private javax.swing.JCheckBox cb02;
    private javax.swing.JCheckBox cb03;
    private javax.swing.JCheckBox cb04;
    private javax.swing.JCheckBox cb05;
    private javax.swing.JCheckBox cb06;
    private javax.swing.JCheckBox cb07;
    private javax.swing.JCheckBox cb08;
    private javax.swing.JCheckBox cb09;
    private javax.swing.JCheckBox cb10;
    private javax.swing.JCheckBox cb11;
    private javax.swing.JCheckBox cb12;
    private javax.swing.JCheckBox cb13;
    private javax.swing.JCheckBox cb14;
    private javax.swing.JCheckBox cb15;
    private javax.swing.JCheckBox cb16;
    private javax.swing.JCheckBox cb17;
    private javax.swing.JCheckBox cb18;
    private javax.swing.JCheckBox cb19;
    private javax.swing.JCheckBox cb2;
    private javax.swing.JCheckBox cb20;
    private javax.swing.JCheckBox cb21;
    private javax.swing.JCheckBox cb22;
    private javax.swing.JCheckBox cb23;
    private javax.swing.JCheckBox cb24;
    private javax.swing.JCheckBox cb25;
    private javax.swing.JCheckBox cb26;
    private javax.swing.JCheckBox cb27;
    private javax.swing.JCheckBox cb28;
    private javax.swing.JCheckBox cb29;
    private javax.swing.JCheckBox cb3;
    private javax.swing.JCheckBox cb30;
    private javax.swing.JCheckBox cb31;
    private javax.swing.JCheckBox cb32;
    private javax.swing.JCheckBox cb33;
    private javax.swing.JCheckBox cb34;
    private javax.swing.JCheckBox cb35;
    private javax.swing.JCheckBox cb36;
    private javax.swing.JCheckBox cb37;
    private javax.swing.JCheckBox cb38;
    private javax.swing.JCheckBox cb39;
    private javax.swing.JCheckBox cb4;
    private javax.swing.JCheckBox cb40;
    private javax.swing.JCheckBox cb41;
    private javax.swing.JCheckBox cb42;
    private javax.swing.JCheckBox cb43;
    private javax.swing.JCheckBox cb44;
    private javax.swing.JCheckBox cb45;
    private javax.swing.JCheckBox cb46;
    private javax.swing.JCheckBox cb47;
    private javax.swing.JCheckBox cb48;
    private javax.swing.JCheckBox cb49;
    private javax.swing.JCheckBox cb5;
    private javax.swing.JCheckBox cb50;
    private javax.swing.JCheckBox cb51;
    private javax.swing.JCheckBox cb52;
    private javax.swing.JCheckBox cb53;
    private javax.swing.JCheckBox cb54;
    private javax.swing.JCheckBox cb55;
    private javax.swing.JCheckBox cb56;
    private javax.swing.JCheckBox cb57;
    private javax.swing.JCheckBox cb58;
    private javax.swing.JCheckBox cb59;
    private javax.swing.JCheckBox cb6;
    private javax.swing.JCheckBox cb60;
    private javax.swing.JCheckBox cb61;
    private javax.swing.JCheckBox cb62;
    private javax.swing.JCheckBox cb63;
    private javax.swing.JCheckBox cb64;
    private javax.swing.JCheckBox cb65;
    private javax.swing.JCheckBox cb66;
    private javax.swing.JCheckBox cb67;
    private javax.swing.JCheckBox cb68;
    private javax.swing.JCheckBox cb69;
    private javax.swing.JCheckBox cb7;
    private javax.swing.JCheckBox cb70;
    private javax.swing.JCheckBox cb8;
    private javax.swing.JCheckBox cb9;
    private javax.swing.JCheckBox cbInativo;
    private javax.swing.JComboBox cbTodos;
    private javax.swing.JCheckBox cbTodosCad;
    private javax.swing.JPanel clientePanel;
    private net.sf.nachocalendar.components.DateField dfCadastro;
    private javax.swing.JPanel empresaPanel;
    private javax.swing.JPanel empresaPanel1;
    private javax.swing.JPanel empresaPanel2;
    private javax.swing.JPanel empresaPanel3;
    private javax.swing.JPanel empresaPanel4;
    private javax.swing.JPanel empresaPanel5;
    private javax.swing.JPanel empresaPanel6;
    private javax.swing.JPanel empresaPanel7;
    private javax.swing.JPanel empresaPanel8;
    private javax.swing.JPanel empresaPanel9;
    private javax.swing.JPanel fornecedorPanel;
    private javax.swing.JPanel funcaoPanel;
    private javax.swing.JPanel funcionarioPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDtCadastro;
    private javax.swing.JLabel lbFuncionario;
    private javax.swing.JLabel lbNomeConsultar;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbSenha1;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JPanel maquinaPanel;
    private javax.swing.JPanel marcaPanel;
    private javax.swing.JPanel materiaPanel;
    private javax.swing.JPanel pecaPanel;
    private javax.swing.JPanel pessoaPanel;
    private javax.swing.JPanel setorPanel;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JPanel tab01;
    private javax.swing.JPanel tab02;
    private javax.swing.JPanel tab03;
    private javax.swing.JPanel tab04;
    private javax.swing.JPanel tab05;
    private javax.swing.JTable table;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfFuncionario;
    private javax.swing.JTextField tfNomeConsultar;
    private javax.swing.JPasswordField tfSenha;
    private javax.swing.JPasswordField tfSenha1;
    private javax.swing.JTextField tfUsuario;
    private javax.swing.JPanel usuarioPanel;
    // End of variables declaration//GEN-END:variables

    public void novo() {
        setUsuario(new Usuario());
        habilita(true);
        limpaCampos();
        tab(0, true, true, true, true, false);
    }

    @Override
    public void salvar() {
        if (tfFuncionario.getText().trim().equals("") || tfSenha.getPassword().toString().equals("") || tfSenha1.getPassword().toString().equals("") || tfUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Existem campos de preenchimento obrigatório não preenchidos");
        } else {
            if (!tfSenha.getPassword().toString().equals(tfSenha1.getPassword().toString())) {
                JOptionPane.showMessageDialog(null, "Senha e Confirmação devem ser idênticas");
            } else {
                try {
                    ud.save(usuario());
                    habSalvar();
                    limpaCampos();
                    habilita(false);
                    tab(0, true, false, false, false, false);
                    JOptionPane.showMessageDialog(null, "Usuario salvo com sucesso.");
                } catch (InvalidStateException e) {
                    String mensagem = "";
                    for (InvalidValue object : e.getInvalidValues()) {
                        mensagem = mensagem + object.getMessage() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, mensagem);
                    ud.cancel();
                } catch (StaleObjectStateException e) {
                    limpaCampos();
                    habCancelar();
                    habilita(false);
                    JOptionPane.showMessageDialog(null, "Suas alterações não foram salvas.\nConsulte novamente e altere!");
                } catch (ConstraintViolationException cve) {
                    String cause = String.valueOf(cve.getCause());
                    if (cause.contains("usuario_idfuncionario_key")) {
                        JOptionPane.showMessageDialog(null, "Esse funcionario já possui um usuário.");
                    }
                    ud.cancel();
                }
            }
        }
    }

    public void consultar() {
        limpaCamposConsulta();
        tabelaUsuario(null);
        tab(4, false, false, false, false, true);
    }

    public void alterar() {
        habilita(true);
    }

    public void excluir() {
        try {
            UIManager.put("OptionPane.yesButtonText", "Sim");
            UIManager.put("OptionPane.noButtonText", "Não");
            int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este Usuario?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if (sim == JOptionPane.YES_OPTION) {
                ud.delete(getUsuario());
                habExcluir();
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Usuario excluído com sucesso.");
            }
        } catch (ConstraintViolationException cve) {
            JOptionPane.showMessageDialog(null, "Esse usuario não pode ser excluido,\npossui relações.");
        }
    }

    public void cancelar() {
        setUsuario(null);
        HibernateUtility.rollbackTransaction();
        HibernateUtility.closeSession();
        tab(0, true, false, false, false, false);
        habilita(false);
        limpaCampos();
    }

    public void listar() {
        Boolean ativo = cbTodos.getSelectedIndex() == 0 ? null : cbTodos.getSelectedIndex() == 1 ? false : true;
        tabelaUsuario(ud.consulta(tfNomeConsultar.getText().trim(), ativo));
    }

    public void seleciona() {
        if (table.getSelectedRow() != -1) {
            setUsuario(tmUsuario.getLista().get(table.getSelectedRow()));
            usuario(getUsuario());
            tab(0, true, true, true, true, false);
        }
    }
}

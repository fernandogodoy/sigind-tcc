/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MarcaForm.java
 *
 * Created on 08/07/2010, 22:55:56
 */
package br.com.fernandogodoy.sigind.views.cadastros;

import br.com.fernandogodoy.sigind.dao.cadastros.MarcaDaoImpl;
import br.com.fernandogodoy.sigind.models.cadastros.Marca;
import br.com.fernandogodoy.sigind.models.tablemodel.TableModelMarca;
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
public class MarcaForm extends MenuForm {

    private Marca marca;
    public TableModelMarca tmMarca;
    private MarcaDaoImpl md = new MarcaDaoImpl();
    private Boolean consulta;

    public MarcaForm(Boolean consulta) {
        super(Login.usuario.getMarcaUsuario());
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        dfCadastro.setDateFormat(DateFormat.getDateInstance());
        habilita(false);
        limpaCampos();
        limpaCamposConsulta();
        this.consulta = consulta;
        if (consulta) {
            tab(0, true, false);
        } else {
            tab(1, false, true);
            habNenhum();
            cbTodos.setSelectedIndex(1);
            cbTodos.setEnabled(false);
        }
        setVisible(true);
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    private Marca marca() {
        if (!tfCodigo.getText().isEmpty()) {
            getMarca().setDtCadastro((Date) dfCadastro.getValue());
            getMarca().setIdMarca(Long.parseLong(tfCodigo.getText()));
        } else {
            getMarca().setDtCadastro(new Date());
        }
        getMarca().setMarca(tfNome.getText().trim());
        getMarca().setAtivo(cbInativo.isSelected());
        return getMarca();
    }

    private void marca(Marca m) {
        tfCodigo.setText(m.getIdMarca().toString());
        tfNome.setText(m.getMarca());
        dfCadastro.setValue(m.getDtCadastro());
        cbInativo.setSelected(m.getAtivo());
    }

    private void habilita(Boolean b) {
        tfNome.setEditable(b);
        cbInativo.setEnabled(b);
    }

    private void limpaCampos() {
        tfCodigo.setText("");
        tfNome.setText("");
        dfCadastro.setValue(new Date());
        cbInativo.setSelected(false);
        tabelaMarca(null);
    }

    private void limpaCamposConsulta() {
        tfNomeConsultar.setText("");
        cbTodos.setSelectedIndex(0);
        table.setModel(new TableModelMarca(null));
    }

    public void tab(Integer i, Boolean b1, Boolean b2) {
        tab.setSelectedIndex(i);
        tab.setEnabledAt(0, b1);
        tab.setEnabledAt(1, b2);
    }

    public void tabelaMarca(List<Marca> lista) {
        tmMarca = new TableModelMarca(lista);
        table.setModel(tmMarca);
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getTableHeader().setReorderingAllowed(false);
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
        jLabel2 = new javax.swing.JLabel();
        tab02 = new javax.swing.JPanel();
        lbNomeConsultar = new javax.swing.JLabel();
        tfNomeConsultar = new javax.swing.JTextField();
        cbTodos = new javax.swing.JComboBox();
        btListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Marca");

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Campos Marcados com (*) são de peenchimento obrigatório");

        javax.swing.GroupLayout tab01Layout = new javax.swing.GroupLayout(tab01);
        tab01.setLayout(tab01Layout);
        tab01Layout.setHorizontalGroup(
            tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addComponent(lbCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
                        .addComponent(lbDtCadastro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dfCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbInativo))
                    .addGroup(tab01Layout.createSequentialGroup()
                        .addComponent(lbNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                            .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNome)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cbInativo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        tab.addTab("Dados da Marca", tab01);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btListar)))
                .addContainerGap())
        );
        tab02Layout.setVerticalGroup(
            tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab02Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomeConsultar)
                    .addComponent(tfNomeConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab.addTab("Consultar", tab02);

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
                .addGap(51, 51, 51)
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btListar;
    private javax.swing.JCheckBox cbInativo;
    private javax.swing.JComboBox cbTodos;
    private net.sf.nachocalendar.components.DateField dfCadastro;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDtCadastro;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNomeConsultar;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JPanel tab01;
    private javax.swing.JPanel tab02;
    public javax.swing.JTable table;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNomeConsultar;
    // End of variables declaration//GEN-END:variables

    public void novo() {
        setMarca(new Marca());
        habilita(true);
        limpaCampos();
        tab(0, true, false);
    }

    public void salvar() {
        if (tfNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Existem campos de preenchimento obrigatório não preenchidos");
        } else {
            try {
                md.save(marca());
                habSalvar();
                limpaCampos();
                habilita(false);
                JOptionPane.showMessageDialog(null, "Marca salva com sucesso.");
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
        tabelaMarca(null);
        tab(1, false, true);
    }

    public void alterar() {
        habilita(true);
    }

    public void excluir() {
        try {
            UIManager.put("OptionPane.yesButtonText", "Sim");
            UIManager.put("OptionPane.noButtonText", "Não");
            int sim = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta Marca?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if (sim == JOptionPane.YES_OPTION) {
                md.delete(getMarca());
                habExcluir();
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Marca excluída com sucesso.");
            }
        } catch (ConstraintViolationException cve) {
            JOptionPane.showMessageDialog(null, "Essa marca não pode ser excluida,\npossui relações.");
        }
    }

    public void cancelar() {
        setMarca(null);
        HibernateUtility.rollbackTransaction();
        HibernateUtility.closeSession();
        tab(0, true, false);
        habilita(false);
        limpaCampos();
    }

    public void listar() {
        Boolean ativo = cbTodos.getSelectedIndex() == 0 ? null : cbTodos.getSelectedIndex() == 1 ? false : true;
        tabelaMarca(md.consulta(tfNomeConsultar.getText().trim(), ativo));
    }

    public void seleciona() {
        if (consulta) {
            if (table.getSelectedRow() != -1) {
                setMarca(tmMarca.getLista().get(table.getSelectedRow()));
                marca(getMarca());
                tab(0, true, false);
            }
        } else {
            this.dispose();
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelUsuario extends AbstractTableModel {

    private List<Usuario> lista;

    public TableModelUsuario(List<Usuario> lista) {
        this.lista = lista;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        if (getLista() == null) {
            return 0;
        }
        return getLista().size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        String[] coluna = {"Usuário", "Funcionário"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario obj = (Usuario) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getUsuario();
            case 1:
                return obj.getFuncionario().getPessoa().getNomePessoa();
        }
        return obj;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}

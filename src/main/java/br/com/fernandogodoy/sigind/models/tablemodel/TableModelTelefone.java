/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Telefone;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelTelefone extends AbstractTableModel {

    private List<Telefone> lista;

    public TableModelTelefone(List<Telefone> lista) {
        this.lista = lista;
    }

    public List<Telefone> getLista() {
        return lista;
    }

    public void setLista(List<Telefone> lista) {
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
        String[] coluna = {"Telefone", "Tipo"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Telefone obj = (Telefone) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getTelefone();
            case 1:
                return obj.getTipoTelefone().toString();
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

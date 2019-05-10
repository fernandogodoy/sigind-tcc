/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Setor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelSetor extends AbstractTableModel {

    private List<Setor> lista;

    public TableModelSetor(List<Setor> lista) {
        this.lista = lista;
    }

    public List<Setor> getLista() {
        return lista;
    }

    public void setLista(List<Setor> lista) {
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
        String[] coluna = {"Código", "Setor"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Setor obj = (Setor) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdSetor();
            case 1:
                return obj.getSetor();
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

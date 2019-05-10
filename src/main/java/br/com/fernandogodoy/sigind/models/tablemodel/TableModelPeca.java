/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelPeca extends AbstractTableModel {

    private List<Peca> lista;

    public TableModelPeca(List<Peca> lista) {
        this.lista = lista;
    }

    public List<Peca> getLista() {
        return lista;
    }

    public void setLista(List<Peca> lista) {
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
        String[] coluna = {"Código", "Peça"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Peca obj = (Peca) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdPeca();
            case 1:
                return obj.getPeca();
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

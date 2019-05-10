/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.MatPrima;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelMatPrima extends AbstractTableModel {

    private List<MatPrima> lista;

    public TableModelMatPrima(List<MatPrima> lista) {
        this.lista = lista;
    }

    public List<MatPrima> getLista() {
        return lista;
    }

    public void setLista(List<MatPrima> lista) {
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
        String[] coluna = {"Código", "Matéria Prima"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MatPrima obj = (MatPrima) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdMateriaPrima();
            case 1:
                return obj.getMateriaPrima();
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

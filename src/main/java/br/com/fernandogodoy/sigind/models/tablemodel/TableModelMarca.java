/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Marca;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelMarca extends AbstractTableModel {

    private List<Marca> lista;

    public TableModelMarca(List<Marca> lista) {
        this.lista = lista;
    }

    public List<Marca> getLista() {
        return lista;
    }

    public void setLista(List<Marca> lista) {
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
        String[] coluna = {"Código", "Marca"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Marca obj = (Marca) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdMarca();
            case 1:
                return obj.getMarca();
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

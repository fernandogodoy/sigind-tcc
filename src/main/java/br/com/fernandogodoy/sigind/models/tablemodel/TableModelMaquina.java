/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Maquina;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelMaquina extends AbstractTableModel {

    private List<Maquina> lista;

    public TableModelMaquina(List<Maquina> lista) {
        this.lista = lista;
    }

    public List<Maquina> getLista() {
        return lista;
    }

    public void setLista(List<Maquina> lista) {
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
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        String[] coluna = {"Código", "Maquina", "Número"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Maquina obj = (Maquina) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdMaquina();
            case 1:
                return obj.getMaquina();
            case 2:
                return obj.getNumMaquina();
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

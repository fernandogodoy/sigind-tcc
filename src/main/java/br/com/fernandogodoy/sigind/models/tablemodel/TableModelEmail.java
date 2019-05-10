/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Email;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelEmail extends AbstractTableModel {

    private List<Email> lista;

    public TableModelEmail(List<Email> lista) {
        this.lista = lista;
    }

    public List<Email> getLista() {
        return lista;
    }

    public void setLista(List<Email> lista) {
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
        return 1;
    }

    @Override
    public String getColumnName(int column) {
        String[] coluna = {"E-mail"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Email obj = (Email) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getEmail();
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelCliente extends AbstractTableModel {

    private List<Cliente> lista;

    public TableModelCliente(List<Cliente> lista) {
        this.lista = lista;
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
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
        String[] coluna = {"Código", "Cliente", "CNPJ"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente obj = (Cliente) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdCliente();
            case 1:
                return obj.getPessoa().getNomePessoa();
            case 2:
                return obj.getCnpjCliente();
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

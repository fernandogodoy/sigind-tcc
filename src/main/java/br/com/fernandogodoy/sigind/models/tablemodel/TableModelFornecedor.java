/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Fornecedor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelFornecedor extends AbstractTableModel {

    private List<Fornecedor> lista;

    public TableModelFornecedor(List<Fornecedor> lista) {
        this.lista = lista;
    }

    public List<Fornecedor> getLista() {
        return lista;
    }

    public void setLista(List<Fornecedor> lista) {
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
        String[] coluna = {"Código", "Nome", "CNPJ"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fornecedor obj = (Fornecedor) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdFornecedor();
            case 1:
                return obj.getPessoa().getNomePessoa();
            case 2:
                return obj.getCnpjFornecedor();
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

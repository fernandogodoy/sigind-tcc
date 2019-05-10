/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Empresa;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelEmpresa extends AbstractTableModel {

    private List<Empresa> lista;

    public TableModelEmpresa(List<Empresa> lista) {
        this.lista = lista;
    }

    public List<Empresa> getLista() {
        return lista;
    }

    public void setLista(List<Empresa> lista) {
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
        String[] coluna = {"Nome", "CNPJ"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empresa obj = (Empresa) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getRazaoEmpresa();
            case 1:
                return obj.getCnpjEmpresa();
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

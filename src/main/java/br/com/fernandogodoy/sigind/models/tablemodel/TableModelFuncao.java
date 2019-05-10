/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Funcao;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelFuncao extends AbstractTableModel {

    private List<Funcao> lista;

    public TableModelFuncao(List<Funcao> lista) {
        this.lista = lista;
    }

    public List<Funcao> getLista() {
        return lista;
    }

    public void setLista(List<Funcao> lista) {
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
        String[] coluna = {"Código", "Função"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcao obj = (Funcao) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdFuncao();
            case 1:
                return obj.getFuncao();
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

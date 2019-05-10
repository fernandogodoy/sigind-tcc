/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.movimentos.PecaOrdemProd;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelProdOrdemProd extends AbstractTableModel {

    private List<PecaOrdemProd> lista;

    public TableModelProdOrdemProd(List<PecaOrdemProd> lista) {
        this.lista = lista;
    }

    public List<PecaOrdemProd> getLista() {
        return lista;
    }

    public void setLista(List<PecaOrdemProd> lista) {
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
        String[] coluna = {"Peça", "Quantidade"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PecaOrdemProd obj = (PecaOrdemProd) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getPeca().getPeca();
            case 1:
                return obj.getQtdPecas().intValue();
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;



import br.com.fernandogodoy.sigind.models.movimentos.Compra;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelCompra extends AbstractTableModel {

    private List<Compra> lista;

    public TableModelCompra(List<Compra> lista) {
        this.lista = lista;
    }

    public List<Compra> getLista() {
        return lista;
    }

    public void setLista(List<Compra> lista) {
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
        String[] coluna = {"Nome", "Quantidade", "Peso", "Valor"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Compra obj = (Compra) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getFornecedor().getPessoa().getNomePessoa();
            case 1:
                return obj.getQtdCompra();
            case 2:
                return obj.getPesoCompra();
            case 3:
                return obj.getValorCompra();
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

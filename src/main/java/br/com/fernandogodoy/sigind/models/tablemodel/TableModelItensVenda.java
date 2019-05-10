/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.tablemodel;

import br.com.fernandogodoy.sigind.models.movimentos.MatPrimaCompra;
import br.com.fernandogodoy.sigind.models.movimentos.PecaVenda;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernado
 */
public class TableModelItensVenda extends AbstractTableModel {

    private List<PecaVenda> lista;

    public TableModelItensVenda(List<PecaVenda> lista) {
        this.lista = lista;
    }

    public List<PecaVenda> getLista() {
        return lista;
    }

    public void setLista(List<PecaVenda> lista) {
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
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        String[] coluna = {"Nome", "Quantidade", "Peso", "Valor"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PecaVenda obj = (PecaVenda) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getPeca().getPeca();
            case 1:
                return obj.getQtdVenda();
            case 2:
                return obj.getPeca().getPesoMedioPeca();
            case 3:
                return new DecimalFormat("#,##0.00").format(obj.getValorPeca());
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

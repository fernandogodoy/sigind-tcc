/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.tablemodel;

import br.com.fernandogodoy.sigind.models.movimentos.PgContasPagar;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernado
 */
public class TableModelPgCompra extends AbstractTableModel {
    
    private List<PgContasPagar> lista;

    public TableModelPgCompra(List<PgContasPagar> lista) {
        this.lista = lista;
    }

    public List<PgContasPagar> getLista() {
        return lista;
    }

    public void setLista(List<PgContasPagar> lista) {
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
        String[] coluna = {"Valor", "Data de Pagamento"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PgContasPagar obj = (PgContasPagar) getLista().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return new DecimalFormat("#,##0.00").format(obj.getValorPgContasPagar());
            case 1:
                return obj.getDtPgContasPagar();
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

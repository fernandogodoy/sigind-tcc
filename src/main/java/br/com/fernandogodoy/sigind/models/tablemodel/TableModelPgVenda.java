/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.tablemodel;

import br.com.fernandogodoy.sigind.models.movimentos.PgContasReceber;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernado
 */
public class TableModelPgVenda extends AbstractTableModel {
    
    private List<PgContasReceber> lista;

    public TableModelPgVenda(List<PgContasReceber> lista) {
        this.lista = lista;
    }

    public List<PgContasReceber> getLista() {
        return lista;
    }

    public void setLista(List<PgContasReceber> lista) {
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
        PgContasReceber obj = (PgContasReceber) getLista().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return new DecimalFormat("#,##0.00").format(obj.getValorPgContasReceber());
            case 1:
                return obj.getDtPgContasReceber();
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

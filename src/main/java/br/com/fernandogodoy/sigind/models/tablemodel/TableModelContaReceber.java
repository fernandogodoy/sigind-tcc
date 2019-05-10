/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.tablemodel;

import br.com.fernandogodoy.sigind.models.movimentos.ContasReceber;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernado
 */
public class TableModelContaReceber extends AbstractTableModel {

    private List<ContasReceber> lista;

    public TableModelContaReceber(List<ContasReceber> lista) {
        this.lista = lista;
    }

       public List<ContasReceber> getLista() {
        return lista;
    }

    public void setLista(List<ContasReceber> lista) {
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
        String[] coluna = {"Nome Fornecedo", "Valor da conta", "Data de Vencimento"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ContasReceber obj = (ContasReceber) getLista().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getVenda().getCliente().getPessoa().getNomePessoa();
            case 1:
                return obj.getValorContasReceber();
            case 2:
                return obj.getDtContasReceber();

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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.tablemodel;

import br.com.fernandogodoy.sigind.models.movimentos.ContasPagar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernado
 */
public class TableModelConta extends AbstractTableModel {

    private List<ContasPagar> lista;

    public TableModelConta(List<ContasPagar> lista) {
        this.lista = lista;
    }

       public List<ContasPagar> getLista() {
        return lista;
    }

    public void setLista(List<ContasPagar> lista) {
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
        ContasPagar obj = (ContasPagar) getLista().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getCompra().getFornecedor().getPessoa().getNomePessoa();
            case 1:
                return obj.getValorContasPagar();
            case 2:
                return obj.getDtContasPagar();

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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.tablemodel;

import br.com.fernandogodoy.sigind.models.movimentos.PecaVenda;
import br.com.fernandogodoy.sigind.models.movimentos.Venda;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernado
 */
public class TableModelVenda extends AbstractTableModel {

    private List<Venda> lista;

    public TableModelVenda(List<Venda> lista) {
        this.lista = lista;
    }

    public List<Venda> getLista() {
        return lista;
    }

    public void setLista(List<Venda> lista) {
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
        String[] coluna = {"Nome", "CNPJ", "Data da Venda", "Valor Total"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venda obj = (Venda) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getCliente().getRazaoCliente();
            case 1:
                return obj.getCliente().getCnpjCliente();
            case 2:
                return obj.getDtVenda();
            case 3:
                return new DecimalFormat("#,##").format(obj.getValorVenda());
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

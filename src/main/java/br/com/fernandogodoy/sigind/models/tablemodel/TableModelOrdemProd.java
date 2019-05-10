/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.movimentos.OrdemProd;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelOrdemProd extends AbstractTableModel {

    private List<OrdemProd> lista;

    public TableModelOrdemProd(List<OrdemProd> lista) {
        this.lista = lista;
    }

    public List<OrdemProd> getLista() {
        return lista;
    }

    public void setLista(List<OrdemProd> lista) {
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
        String[] coluna = {"Nº Ordem Produção", "Data Inclusão", "Data Produção", "Status"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrdemProd obj = (OrdemProd) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdOrdemProd().toString();
            case 1:
                return new SimpleDateFormat("dd/MM/yyyy").format(obj.getDtIncOrdemProd());
            case 2:
                return new SimpleDateFormat("dd/MM/yyyy").format(obj.getDtProdOrdemProd());
            case 3:
                return obj.getOrdemProdStatus().toString();
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

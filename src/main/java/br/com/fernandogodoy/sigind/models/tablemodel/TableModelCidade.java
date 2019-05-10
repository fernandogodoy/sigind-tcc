/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.tablemodel;


import br.com.fernandogodoy.sigind.models.cadastros.Municipio;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelCidade extends AbstractTableModel {

    private List<Municipio> lista;

    public TableModelCidade(List<Municipio> lista) {
        this.lista = lista;
    }

    public List<Municipio> getLista() {
        return lista;
    }

    public void setLista(List<Municipio> lista) {
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
        String[] coluna = {"Código", "Cidade", "Estado"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Municipio obj = (Municipio) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdMunicipio();
            case 1:
                return obj.getxMun();
            case 2:
                return obj.getUf().getSigla();
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.tablemodel;

import br.com.fernandogodoy.sigind.models.movimentos.Expedicao;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fernando
 */
public class TableModelExpedicao extends AbstractTableModel {

    private List<Expedicao> lista;

    public TableModelExpedicao(List<Expedicao> lista) {
        this.lista = lista;
    }

    public List<Expedicao> getLista() {
        return lista;
    }

    public void setLista(List<Expedicao> lista) {
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
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        String[] coluna = {"Código", "Funcionario", "Hora Entrada", "Peso Entrada", "Hora Saida", "Peso Saida"};
        return coluna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Expedicao obj = (Expedicao) getLista().get(rowIndex);

        switch (columnIndex) {

            case 0:
                return obj.getIdExpedicao();
            case 1:
                return obj.getEntFuncionario().getPessoa().getNomePessoa();
            case 2:
                return obj.getHoraEntExp();
            case 3:
                return obj.getPesoEntExp();
            case 4:
                return obj.getHoraSaiExp();
            case 5:
                return obj.getPesoSaiExp();

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

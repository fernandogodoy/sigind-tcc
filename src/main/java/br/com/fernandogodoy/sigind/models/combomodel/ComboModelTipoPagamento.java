/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.combomodel;


import br.com.fernandogodoy.sigind.models.cadastros.TipoPagamento;
import java.util.Arrays;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Fernado
 */
public class ComboModelTipoPagamento implements ComboBoxModel {

    private List<TipoPagamento> lista;
    private TipoPagamento selecionado;

    public ComboModelTipoPagamento(TipoPagamento[] tipoPagamento) {
        if (tipoPagamento != null) {
            lista = Arrays.asList(tipoPagamento);
        }
    }

    public List<TipoPagamento> getLista() {
        return lista;
    }

    public void setLista(List<TipoPagamento> lista) {
        this.lista = lista;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selecionado = (TipoPagamento) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selecionado;

    }

    @Override
    public int getSize() {
        if (getLista() == null) {
            return 0;
        }
        return lista.size();

    }

    @Override
    public Object getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }
}


package br.com.fernandogodoy.sigind.models.combomodel;


import br.com.fernandogodoy.sigind.models.movimentos.OrdemProdStatus;
import java.util.Arrays;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
/**
 *
 
 */
public class ComboModelStatus implements ComboBoxModel{

    private List<OrdemProdStatus> lista;
    private OrdemProdStatus selecionado;
    
    public ComboModelStatus(){

    }

    public ComboModelStatus(OrdemProdStatus [] ordemProdStatus) {
        lista = Arrays.asList(ordemProdStatus);
    }

    public List<OrdemProdStatus> getLista() {
        return lista;
    }

    public void setLista(List<OrdemProdStatus> lista) {
        this.lista = lista;
    }
    
    @Override
    public void setSelectedItem(Object anItem) {
        selecionado=(OrdemProdStatus) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selecionado;
        
    }

    @Override
    public int getSize() {
        if(getLista() == null) {
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

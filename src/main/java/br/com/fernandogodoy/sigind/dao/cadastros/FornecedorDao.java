/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Fornecedor;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.List;



/**
 *
 * @author Fernando
 */
public interface FornecedorDao extends DaoGenerico<Fornecedor, Long>{
    
   public List<Fornecedor> consultaFornecedor();

    public List<Fornecedor> consultaFornecedor(String nome);

}

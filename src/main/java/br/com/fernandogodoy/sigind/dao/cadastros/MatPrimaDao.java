/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.MatPrima;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.List;



/**
 *
 * @author Fernando
 */
public interface MatPrimaDao extends DaoGenerico<MatPrima, Long>{

    public List<MatPrima> consultaMateriaPrima();
}

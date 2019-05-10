/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.movimentos;


import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProd;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProdStatus;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface OrdemProdDao extends DaoGenerico<OrdemProd, Long> {

    public List<OrdemProd> consulta(Date dtInicial, Date dtFinal, OrdemProdStatus status);

    public Peca getPecaById(Long id);

   
    
}

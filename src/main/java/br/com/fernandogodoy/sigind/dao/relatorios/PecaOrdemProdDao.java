/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.relatorios;

import br.com.fernandogodoy.sigind.models.movimentos.PecaOrdemProd;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernado
 */
public interface PecaOrdemProdDao extends DaoGenerico<PecaOrdemProd, Long> {

    public List<PecaOrdemProd> consultaPecaProdPer(Date dtInicio, Date dtFim);

    
    
}

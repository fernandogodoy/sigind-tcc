/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;

import br.com.fernandogodoy.sigind.models.movimentos.Expedicao;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.List;

/**
 *
 * @author Fernado
 */
public interface ExpedicaoDao extends DaoGenerico<Expedicao, Long> {

    public List<Expedicao> consulta(String nome);

   
}

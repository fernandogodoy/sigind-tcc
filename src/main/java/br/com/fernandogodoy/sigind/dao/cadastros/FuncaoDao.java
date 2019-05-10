/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Funcao;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface FuncaoDao extends DaoGenerico<Funcao, Long> {

    public List<Funcao> consultaFuncao();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Funcionario;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.List;



/**
 *
 * @author Fernando
 */
public interface FuncionarioDao extends DaoGenerico<Funcionario, Long>{

    public List<Funcionario> consultaFuncionario();

    public List<Funcionario> consultaFuncionario(String nome);
}

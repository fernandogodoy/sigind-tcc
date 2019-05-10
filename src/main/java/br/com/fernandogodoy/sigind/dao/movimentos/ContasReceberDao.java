/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;

import br.com.fernandogodoy.sigind.models.movimentos.ContasReceber;
import br.com.fernandogodoy.sigind.models.movimentos.Venda;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernado
 */
public interface ContasReceberDao extends DaoGenerico<ContasReceber, Long> {

    public ContasReceber consultarContasReceber(Venda venda);

    public List<ContasReceber> consultaContas();

    public List<ContasReceber> consultaContasReceber(Date dtIncial, Date dtFinal);
}

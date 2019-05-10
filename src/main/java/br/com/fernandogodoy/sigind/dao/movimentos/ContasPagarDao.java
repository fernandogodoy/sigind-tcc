/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;

import br.com.fernandogodoy.sigind.models.movimentos.Compra;
import br.com.fernandogodoy.sigind.models.movimentos.ContasPagar;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernado
 */
public interface ContasPagarDao extends DaoGenerico<ContasPagar, Long> {

    public ContasPagar consultarContasPagar(Compra compra);

    public List<ContasPagar> consultaContas();

    public List<ContasPagar> consultaContasPagar(Date dtIncial, Date dtFinal);
}

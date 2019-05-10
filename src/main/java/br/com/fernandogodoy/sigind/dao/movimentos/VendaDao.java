/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;

import br.com.fernandogodoy.sigind.models.movimentos.PecaVenda;
import br.com.fernandogodoy.sigind.models.movimentos.Venda;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernado
 */
public interface VendaDao extends DaoGenerico<Venda, Long> {

    public List<Venda> consulta(String nome);
    
     public List<PecaVenda> consultar(Date dtIncial, Date dtFinal);
}

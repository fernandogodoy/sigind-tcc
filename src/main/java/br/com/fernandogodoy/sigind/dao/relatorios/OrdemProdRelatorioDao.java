/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.relatorios;


import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProdStatus;
import br.com.fernandogodoy.sigind.models.movimentos.PecaOrdemProd;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface OrdemProdRelatorioDao {

    public List<PecaOrdemProd> consulta(Date dtInicial, Date dtFinal, OrdemProdStatus ordemProdStatus, Peca peca);
}

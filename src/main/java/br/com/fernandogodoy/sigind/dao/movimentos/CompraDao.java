/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;

import br.com.fernandogodoy.sigind.models.movimentos.Compra;
import br.com.fernandogodoy.sigind.models.movimentos.MatPrimaCompra;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernado
 */
public interface CompraDao extends DaoGenerico<Compra, Long> {

    public List<Compra> consulta(String nome);

    public List<MatPrimaCompra> consultar(Date dtIncial, Date dtFinal);
}

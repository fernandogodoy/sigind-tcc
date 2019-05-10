/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Cliente;
import br.com.fernandogodoy.sigind.models.cadastros.Municipio;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface ClienteDao extends DaoGenerico<Cliente, Long> {

    public List<Cliente> consulta(String cliente, Boolean ativo);

    public List<Cliente> consultaCliente();

    public List<Cliente> consultaCliente(String nome);

    public Municipio consultaCidade(Long idCidade);
}

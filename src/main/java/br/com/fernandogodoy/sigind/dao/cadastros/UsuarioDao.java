/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Usuario;
import br.com.fernandogodoy.sigind.util.DaoGenerico;
import java.util.List;



/**
 *
 * @author Fernando
 */
public interface UsuarioDao extends DaoGenerico<Usuario, Long>{

    public List<Usuario> consultaUsuario();

    public List<Usuario> consultaUsuario(String nome);
}

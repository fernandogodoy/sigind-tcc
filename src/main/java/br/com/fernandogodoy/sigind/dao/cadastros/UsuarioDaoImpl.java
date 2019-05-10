/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Usuario;
import br.com.fernandogodoy.sigind.util.DaoGenericoImpl;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Fernando
 */
public class UsuarioDaoImpl extends DaoGenericoImpl<Usuario, Long> implements UsuarioDao {

    public List<Usuario> consulta(String usuario, Boolean ativo) {
        List<Usuario> lista = new ArrayList<Usuario>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Usuario.class);
        crit.createAlias("funcionario", "funcionario");
        crit.add(Restrictions.like("usuario", "%" + usuario + "%").ignoreCase());
        if (ativo != null) {
            crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<Usuario>) crit.list();
        return lista;
    }

    public Usuario validaLogin(String usuario, String senha) {
        Criteria crit = HibernateUtility.getSession().createCriteria(Usuario.class);
//        crit.add(Restrictions.eq("usuario", usuario));
//        crit.add(Restrictions.eq("senha", senha));
//        crit.add(Restrictions.eq("ativo", false));  
        return (Usuario) crit.uniqueResult();
    }
//    public UsuarioDaoImpl() {
//        Usuario a = new Usuario();
//        a = validaLogin("Fernando", "dsadsa");
//        System.out.println(a.getDtCadastro());
//    }
//
//    public static void main(String [] args) {
//        new UsuarioDaoImpl();
//    }

    @Override
    public List<Usuario> consultaUsuario() {
        
        Criteria crit = HibernateUtility.getSession().createCriteria(Usuario.class);
        return crit.list();
    }
    
    @Override
     public List<Usuario> consultaUsuario(String nome){
         Criteria crit = HibernateUtility.getSession().createCriteria(Usuario.class);
         crit.add(Restrictions.ilike("nome", "%" + nome + "%"));
         return crit.list();
     }
}

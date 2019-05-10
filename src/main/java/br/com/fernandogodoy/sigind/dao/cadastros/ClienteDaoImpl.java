/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Cliente;
import br.com.fernandogodoy.sigind.models.cadastros.Municipio;
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
public class ClienteDaoImpl extends DaoGenericoImpl<Cliente, Long> implements ClienteDao {

    @Override
    public List<Cliente> consulta(String cliente, Boolean ativo) {
        List<Cliente> lista = new ArrayList<Cliente>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Cliente.class);
        crit.add(Restrictions.like("contatoCliente", "%" + cliente + "%").ignoreCase());
        crit.add(Restrictions.like("razaoCliente", "%" + cliente + "%").ignoreCase());
        if (ativo != null) {
            crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<Cliente>) crit.list();
        return lista;
    }

    @Override
    public Municipio consultaCidade(Long idCidade) {
        Criteria crit = HibernateUtility.getSession().createCriteria(Municipio.class);
        crit.add(Restrictions.eq("idMunicipio", idCidade));
        return (Municipio) crit.uniqueResult();
    }

    @Override
    public List<Cliente> consultaCliente() {
        Criteria crit = HibernateUtility.getSession().createCriteria(Cliente.class);
        return crit.list();
    }
    
    @Override
     public List<Cliente> consultaCliente(String nome){
         Criteria crit = HibernateUtility.getSession().createCriteria(Cliente.class);
         crit.add(Restrictions.ilike("razaoCliente", "%" + nome + "%"));
         return crit.list();
     }
}

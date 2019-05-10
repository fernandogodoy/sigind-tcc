/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Setor;
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
public class SetorDaoImpl extends DaoGenericoImpl<Setor, Long> implements SetorDao {

    public List<Setor> consulta(String setor, Boolean ativo) {
        List<Setor> lista = new ArrayList<Setor>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Setor.class);
        crit.add(Restrictions.like("setor", "%" + setor + "%").ignoreCase());
        if(ativo != null){
        crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<Setor>) crit.list();
        return lista;
    }
    
}

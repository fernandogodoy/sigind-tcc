/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Peca;
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
public class PecaDaoImpl extends DaoGenericoImpl<Peca, Long> implements PecaDao {

    public List<Peca> consulta(String peca, Boolean ativo) {
        List<Peca> lista = new ArrayList<Peca>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Peca.class);
        crit.add(Restrictions.like("peca", "%" + peca + "%").ignoreCase());
        if (ativo != null) {
            crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<Peca>) crit.list();
        return lista;
    }

    @Override
    public List<Peca> consultaPeca() {

        Criteria crit = HibernateUtility.getSession().createCriteria(Peca.class);
        return crit.list();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;

import br.com.fernandogodoy.sigind.models.movimentos.Compra;
import br.com.fernandogodoy.sigind.models.movimentos.ContasPagar;
import br.com.fernandogodoy.sigind.util.DaoGenericoImpl;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Fernado
 */
public class ContasPagarDaoImpl extends DaoGenericoImpl<ContasPagar, Long> implements ContasPagarDao {

    @Override
    public ContasPagar consultarContasPagar(Compra compra) {
        Criteria crit = HibernateUtility.getSession().createCriteria(ContasPagar.class);
        crit.add(Restrictions.eq("compra", compra));
        crit.setMaxResults(1);
        return (ContasPagar) crit.uniqueResult();
    }

    @Override
    public List<ContasPagar> consultaContas() {
        Criteria crit = HibernateUtility.getSession().createCriteria(ContasPagar.class);
        crit.add(Restrictions.eq("cancelada", false));
        crit.add(Restrictions.eq("pago", false));
        return crit.list();
    }

    @Override
    public List<ContasPagar> consultaContasPagar(Date dtIncial, Date dtFinal) {
        Criteria crit = HibernateUtility.getSession().createCriteria(ContasPagar.class);
        crit.add(Restrictions.eq("cancelada", false));
        crit.add(Restrictions.eq("pago", false));
        crit.add(Restrictions.between("dtContasPagar", dtIncial, dtFinal));
        return crit.list();
    }
}

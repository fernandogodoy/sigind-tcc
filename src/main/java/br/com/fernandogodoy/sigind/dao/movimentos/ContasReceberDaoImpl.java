/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;

import br.com.fernandogodoy.sigind.models.movimentos.ContasReceber;
import br.com.fernandogodoy.sigind.models.movimentos.Venda;
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
public class ContasReceberDaoImpl extends DaoGenericoImpl<ContasReceber, Long> implements ContasReceberDao {

    @Override
    public ContasReceber consultarContasReceber(Venda venda) {
        Criteria crit = HibernateUtility.getSession().createCriteria(ContasReceber.class);
        crit.add(Restrictions.eq("venda", venda));
        crit.setMaxResults(1);
        return (ContasReceber) crit.uniqueResult();
    }

    @Override
     public List<ContasReceber> consultaContas() {
        Criteria crit = HibernateUtility.getSession().createCriteria(ContasReceber.class);
        crit.add(Restrictions.eq("cancelada", false));
        crit.add(Restrictions.eq("pago", false));
        return crit.list();
    }

     @Override
    public List<ContasReceber> consultaContasReceber(Date dtIncial, Date dtFinal) {
        Criteria crit = HibernateUtility.getSession().createCriteria(ContasReceber.class);
        crit.add(Restrictions.eq("cancelada", false));
        crit.add(Restrictions.eq("pago", false));
        crit.add(Restrictions.between("dtContasReceber", dtIncial, dtFinal));
        return crit.list();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.MatPrima;
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
public class MatPrimaDaoImpl extends DaoGenericoImpl<MatPrima, Long> implements MatPrimaDao {

    public List<MatPrima> consulta(String matPrima, Boolean ativo) {
        List<MatPrima> lista = new ArrayList<MatPrima>();
        Criteria crit = HibernateUtility.getSession().createCriteria(MatPrima.class);
        crit.add(Restrictions.like("materiaPrima", "%" + matPrima + "%").ignoreCase());
        if (ativo != null) {
            crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<MatPrima>) crit.list();
        return lista;
    }

    @Override
    public List<MatPrima> consultaMateriaPrima() {

        Criteria crit = HibernateUtility.getSession().createCriteria(MatPrima.class);
        return crit.list();
    }
}

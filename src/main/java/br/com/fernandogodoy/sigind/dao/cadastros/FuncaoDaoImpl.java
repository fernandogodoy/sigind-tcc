/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Funcao;
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
public class FuncaoDaoImpl extends DaoGenericoImpl<Funcao, Long> implements FuncaoDao {

    public List<Funcao> consulta(String funcao, Boolean ativo) {
        List<Funcao> lista = new ArrayList<Funcao>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Funcao.class);
        crit.add(Restrictions.like("funcao", "%" + funcao + "%").ignoreCase());
        if (ativo != null) {
            crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<Funcao>) crit.list();
        return lista;
    }

    public List<Funcao> consultaFuncao() {
        Criteria crit = HibernateUtility.getSession().createCriteria(Funcao.class);
        return crit.list();
    }
}

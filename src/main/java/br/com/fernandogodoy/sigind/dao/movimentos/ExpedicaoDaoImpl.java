/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;

import br.com.fernandogodoy.sigind.models.movimentos.Expedicao;
import br.com.fernandogodoy.sigind.util.DaoGenericoImpl;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Fernado
 */
public class ExpedicaoDaoImpl extends DaoGenericoImpl<Expedicao, Long> implements ExpedicaoDao {

    @Override
    public List<Expedicao> consulta(String nome) {
        Criteria crit = HibernateUtility.getSession().createCriteria(Expedicao.class);
        crit.createAlias("entFuncionario", "entFuncionario");
        crit.createAlias("entFuncionario.pessoa", "pessoa");
        crit.add(Restrictions.ilike("pessoa.nomePessoa", nome, MatchMode.ANYWHERE));
        return crit.list();
    }
}

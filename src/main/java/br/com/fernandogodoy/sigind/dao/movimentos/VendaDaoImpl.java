/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;

import br.com.fernandogodoy.sigind.models.movimentos.PecaVenda;
import br.com.fernandogodoy.sigind.models.movimentos.Venda;
import br.com.fernandogodoy.sigind.util.DaoGenericoImpl;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Fernado
 */
public class VendaDaoImpl extends DaoGenericoImpl<Venda, Long> implements VendaDao {

    @Override
    public List<Venda> consulta(String nome) {

        Criteria crit = HibernateUtility.getSession().createCriteria(Venda.class);
        crit.createAlias("cliente", "cliente");
        crit.createAlias("cliente.pessoa", "pessoa");

        crit.add(Restrictions.ilike("pessoa.nomePessoa", nome, MatchMode.ANYWHERE));
        return crit.list();
    }
    
    @Override
     public List<PecaVenda> consultar(Date dtIncial, Date dtFinal) {
        Criteria crit = HibernateUtility.getSession().createCriteria(PecaVenda.class);
        crit.createAlias("venda", "venda");
        crit.add(Restrictions.between("venda.dtVenda", dtIncial, dtFinal));

        return crit.list();
    }
}

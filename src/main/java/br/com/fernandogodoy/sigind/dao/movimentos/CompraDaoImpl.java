/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;

import br.com.fernandogodoy.sigind.models.movimentos.Compra;
import br.com.fernandogodoy.sigind.models.movimentos.MatPrimaCompra;
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
public class CompraDaoImpl extends DaoGenericoImpl<Compra, Long> implements CompraDao {

    @Override
    public List<Compra> consulta(String nome) {

        Criteria crit = HibernateUtility.getSession().createCriteria(Compra.class);
        crit.createAlias("fornecedor", "fornecedor");
        crit.createAlias("fornecedor.pessoa", "pessoa");
        crit.add(Restrictions.ilike("pessoa.nomePessoa", nome, MatchMode.ANYWHERE));
        return crit.list();
    }

    public List<MatPrimaCompra> consultar(Date dtIncial, Date dtFinal) {
        Criteria crit = HibernateUtility.getSession().createCriteria(MatPrimaCompra.class);
        crit.createAlias("compra", "compra");
        crit.add(Restrictions.between("compra.dtCompra", dtIncial, dtFinal));

        return crit.list();
    }
}

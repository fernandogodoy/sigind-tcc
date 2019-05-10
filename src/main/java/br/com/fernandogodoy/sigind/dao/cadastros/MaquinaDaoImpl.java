/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Fornecedor;
import br.com.fernandogodoy.sigind.models.cadastros.Maquina;
import br.com.fernandogodoy.sigind.models.cadastros.Marca;
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
public class MaquinaDaoImpl extends DaoGenericoImpl<Maquina, Long> implements MaquinaDao {

    public List<Maquina> consulta(String maquina, Boolean ativo, String num) {
        List<Maquina> lista = new ArrayList<Maquina>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Maquina.class);
        crit.add(Restrictions.like("maquina", "%" + maquina + "%").ignoreCase());
        crit.add(Restrictions.like("numMaquina", "%" + num + "%").ignoreCase());
        if (ativo != null) {
            crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<Maquina>) crit.list();
        return lista;
    }

    public Marca consultaMarca(Long idMarca) {
        Criteria crit = HibernateUtility.getSession().createCriteria(Marca.class);
        crit.add(Restrictions.eq("idMarca", idMarca));
        return (Marca) crit.uniqueResult();
    }

    public Fornecedor consultaFornecedor(Long idFornecedor) {
        Criteria crit = HibernateUtility.getSession().createCriteria(Fornecedor.class);
        crit.add(Restrictions.eq("idFornecedor", idFornecedor));
        return (Fornecedor) crit.uniqueResult();
    }

    @Override
    public List<Maquina> consultaMaquina() {

        Criteria crit = HibernateUtility.getSession().createCriteria(Maquina.class);
        return crit.list();
    }
}

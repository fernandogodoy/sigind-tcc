/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Fornecedor;
import br.com.fernandogodoy.sigind.models.cadastros.Municipio;
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
public class FornecedorDaoImpl extends DaoGenericoImpl<Fornecedor, Long> implements FornecedorDao {

    public List<Fornecedor> consulta(String fornecedor, Boolean ativo) {
        List<Fornecedor> lista = new ArrayList<Fornecedor>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Fornecedor.class);
        crit.add(Restrictions.like("contatoFornecedor", "%" + fornecedor + "%").ignoreCase());
        if (ativo != null) {
            crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<Fornecedor>) crit.list();
        return lista;
    }

    public Municipio consultaCidade(Long idCidade) {
        Criteria crit = HibernateUtility.getSession().createCriteria(Municipio.class);
        crit.add(Restrictions.eq("idCidade", idCidade));
        return (Municipio) crit.uniqueResult();
    }

    @Override
    public List<Fornecedor> consultaFornecedor() {
        
        Criteria crit = HibernateUtility.getSession().createCriteria(Fornecedor.class);
        return crit.list();
    }
    
    @Override
    public List<Fornecedor> consultaFornecedor(String nome){
        Criteria crit = HibernateUtility.getSession().createCriteria(Fornecedor.class);
        crit.createAlias("pessoa", "pessoa");
        crit.add(Restrictions.ilike("pessoa.nomePessoa","%" + nome + "%"));
        return crit.list();
    }
}

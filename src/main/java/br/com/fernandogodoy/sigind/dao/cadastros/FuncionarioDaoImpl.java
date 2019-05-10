/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.cadastros;

import br.com.fernandogodoy.sigind.models.cadastros.Funcionario;
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
public class FuncionarioDaoImpl extends DaoGenericoImpl<Funcionario, Long> implements FuncionarioDao {

    public List<Funcionario> consulta(String funcionario, Boolean ativo) {
        List<Funcionario> lista = new ArrayList<Funcionario>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Funcionario.class);
        crit.createAlias("pessoa", "pessoa");
        crit.add(Restrictions.like("pessoa.nomePessoa", "%" + funcionario + "%").ignoreCase());
        if (ativo != null) {
            crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<Funcionario>) crit.list();
        return lista;
    }

    public Municipio consultaCidade(Long idCidade) {
        Criteria crit = HibernateUtility.getSession().createCriteria(Municipio.class);
        crit.add(Restrictions.eq("idMunicipio", idCidade));
        return (Municipio) crit.uniqueResult();
    }

    @Override
    public List<Funcionario> consultaFuncionario() {

        Criteria crit = HibernateUtility.getSession().createCriteria(Funcionario.class);

        return crit.list();
    }

    @Override
    public List<Funcionario> consultaFuncionario(String nome) {
        Criteria crit = HibernateUtility.getSession().createCriteria(Funcionario.class);
        crit.createAlias("pessoa", "pessoa");
        crit.add(Restrictions.ilike("pessoa.nomePessoa", "%" + nome + "%"));
        return crit.list();
    }
}

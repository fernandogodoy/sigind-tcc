/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.cadastros;


import br.com.fernandogodoy.sigind.models.cadastros.Municipio;
import br.com.fernandogodoy.sigind.models.cadastros.Pessoa;
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
public class PessoaDaoImpl extends DaoGenericoImpl<Pessoa, Long> implements PessoaDao {

    public List<Pessoa> consulta(String pessoa) {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Pessoa.class);
        crit.add(Restrictions.like("nomePessoa", "%" + pessoa + "%").ignoreCase());
        crit.setMaxResults(25);
        lista = (List<Pessoa>) crit.list();
        return lista;
    }

    public Municipio consultaCidade(Long idCidade) {
        Criteria crit = HibernateUtility.getSession().createCriteria(Municipio.class);
        crit.add(Restrictions.eq("idMunicipio", idCidade));
        return (Municipio) crit.uniqueResult();
    }

}

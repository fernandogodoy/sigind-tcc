/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.cadastros;


import br.com.fernandogodoy.sigind.models.cadastros.Empresa;
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
public class EmpresaDaoImpl extends DaoGenericoImpl<Empresa, Long> implements EmpresaDao {

    public List<Empresa> consulta(String empresa, Boolean ativo) {
        List<Empresa> lista = new ArrayList<Empresa>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Empresa.class);
        crit.add(Restrictions.like("razaoEmpresa", "%" + empresa + "%").ignoreCase());
        if(ativo != null){
        crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<Empresa>) crit.list();
        return lista;
    }

    public Municipio consultaCidade(Long idCidade) {
        Criteria crit = HibernateUtility.getSession().createCriteria(Municipio.class);
        crit.add(Restrictions.eq("idMunicipio", idCidade));
        return (Municipio) crit.uniqueResult();
    }
    
}

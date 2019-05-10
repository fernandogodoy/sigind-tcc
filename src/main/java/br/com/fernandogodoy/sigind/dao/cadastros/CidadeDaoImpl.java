/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.cadastros;


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
public class CidadeDaoImpl extends DaoGenericoImpl<Municipio, Long> implements CidadeDao {

    public List<Municipio> consulta(String cidade, String sigla) {
        List<Municipio> lista = new ArrayList<Municipio>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Municipio.class);
        crit.createAlias("uf", "estado");
        crit.add(Restrictions.like("xMun", "%" + cidade + "%").ignoreCase());
        if(sigla != null){
            crit.add(Restrictions.eq("Uf.sigla", sigla));
        }
        crit.setMaxResults(50);
        lista = (List<Municipio>) crit.list();
        return lista;
    }

}

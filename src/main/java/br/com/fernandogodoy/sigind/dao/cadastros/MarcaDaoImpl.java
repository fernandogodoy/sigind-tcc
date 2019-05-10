/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.dao.cadastros;


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
public class MarcaDaoImpl extends DaoGenericoImpl<Marca, Long> implements MarcaDao {
    
    public List<Marca> consulta(String marca, Boolean ativo) {
        List<Marca> lista = new ArrayList<Marca>();
        Criteria crit = HibernateUtility.getSession().createCriteria(Marca.class);
        crit.add(Restrictions.like("marca", "%" + marca + "%").ignoreCase());
        if(ativo != null){
            crit.add(Restrictions.eq("ativo", ativo));
        }
        crit.setMaxResults(25);
        lista = (List<Marca>) crit.list();
        return lista;
    }

}

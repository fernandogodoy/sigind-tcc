/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.relatorios;

import br.com.fernandogodoy.sigind.models.movimentos.PecaOrdemProd;
import br.com.fernandogodoy.sigind.util.DaoGenericoImpl;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Fernado
 */
public class PecaOrdemProdDaoImpl extends DaoGenericoImpl<PecaOrdemProd, Long> implements PecaOrdemProdDao{
    
    @Override
     public List<PecaOrdemProd> consultaPecaProdPer(Date dtInicio, Date dtFim){
        
        Query query = HibernateUtility.getSession().createQuery("select peca.peca, sum(pecaordemprod.qtdpecas) as total from "
                + "peca, pecaordemprod, ordemprod where pecaordemprod.idpeca = peca.idpeca and  "                
                + "group by peca.peca "
                + "order by total desc");
        
        
//         Criteria crit = HibernateUtility.getSession().createCriteria(PecaOrdemProd.class);         
//         crit.createAlias("peca", "peca");         
//         crit.createAlias("ordemProd", "ordemProd");         
//         crit.add(Restrictions.between("ordemProd.dtProdOrdemProd", dtInicio, dtFim));
//         crit.setProjection(Projections.sum("qtdPecas"));
//         crit.setProjection(Projections.groupProperty("peca.peca"));
//         crit.addOrder(Order.desc("peca.peca"));
         return list();
     }
}

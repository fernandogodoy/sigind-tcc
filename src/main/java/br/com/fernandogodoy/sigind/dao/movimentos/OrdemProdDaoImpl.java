/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.movimentos;


import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProd;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProdStatus;
import br.com.fernandogodoy.sigind.util.DaoGenericoImpl;
import br.com.fernandogodoy.sigind.util.HibernateUtility;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Fernando
 */
public class OrdemProdDaoImpl extends DaoGenericoImpl<OrdemProd, Long> implements OrdemProdDao {

    public List<OrdemProd> consulta(Date dtInicial, Date dtFinal, OrdemProdStatus status) {
        List<OrdemProd> lista = new ArrayList<OrdemProd>();
        try {
            DateFormat formatInicial = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            DateFormat formatFinal = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date dtI = (Date) formatInicial.parse(new SimpleDateFormat("yyyy/MM/dd 00:00:00").format(dtInicial));
            Date dtF = (Date) formatFinal.parse(new SimpleDateFormat("yyyy/MM/dd 23:59:59").format(dtFinal));
            Criteria crit = HibernateUtility.getSession().createCriteria(OrdemProd.class);
            crit.add(Restrictions.between("dtProdOrdemProd", dtI, dtF));
            if(status != null) {
            crit.add(Restrictions.eq("ordemProdStatus", status));
            }
            lista = crit.list();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data incorreta");
        }
        return lista;
    }

    
    public Peca getPecaById(Long id) {
        try {
            Criteria crit = HibernateUtility.getSession().createCriteria(Peca.class);
            crit.add(Restrictions.eq("ativo", false));
            crit.add(Restrictions.eq("idPeca", id));
            return (Peca) crit.uniqueResult();
        } catch (HibernateException hibernateException) {
            cancel();
            throw hibernateException;
        }
    }
    
   
}

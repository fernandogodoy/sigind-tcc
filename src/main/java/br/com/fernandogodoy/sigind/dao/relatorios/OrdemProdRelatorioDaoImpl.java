/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.dao.relatorios;


import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProdStatus;
import br.com.fernandogodoy.sigind.models.movimentos.PecaOrdemProd;
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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Fernando
 */
public class OrdemProdRelatorioDaoImpl extends DaoGenericoImpl<PecaOrdemProd, Long> implements OrdemProdRelatorioDao {

    @Override
    public List<PecaOrdemProd> consulta(Date dtInicial, Date dtFinal, OrdemProdStatus ordemProdStatus, Peca peca) {
        List<PecaOrdemProd> lista = new ArrayList<PecaOrdemProd>();
        try {
            DateFormat formatInicial = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            DateFormat formatFinal = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date dtI = (Date) formatInicial.parse(new SimpleDateFormat("yyyy/MM/dd 00:00:00").format(dtInicial));
            Date dtF = (Date) formatFinal.parse(new SimpleDateFormat("yyyy/MM/dd 23:59:59").format(dtFinal));
            Criteria crit = HibernateUtility.getSession().createCriteria(PecaOrdemProd.class);
            crit.createAlias("ordemProd", "ordemProd");
            crit.add(Restrictions.between("ordemProd.dtProdOrdemProd", new Date(dtI.getTime()), new Date(dtF.getTime())));
            if (ordemProdStatus != null) {
                crit.add(Restrictions.eq("ordemProd.ordemProdStatus", ordemProdStatus));
            }
            if (peca != null) {
                crit.add(Restrictions.eq("peca", peca));
            }
            lista = crit.list();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data incorreta");
        }
        return lista;
    }
}

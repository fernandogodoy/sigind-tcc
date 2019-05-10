/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.movimentos;


import br.com.fernandogodoy.sigind.models.cadastros.Funcionario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name="ORDEMPROD" )
public class OrdemProd implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDORDEMPROD", nullable = false)
    private Long idOrdemProd;

    @Column(name="DTINCORDEMPROD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtIncOrdemProd;

    @Column(name="DTPRODORDEMPROD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtProdOrdemProd;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="STATUSORDEMPROD", nullable = false)
    private OrdemProdStatus ordemProdStatus;

    @OneToMany(mappedBy = "ordemProd", targetEntity = PecaOrdemProd.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PecaOrdemProd> pecaOrdemProd = new ArrayList<PecaOrdemProd> ();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDFUNCIONARIO", nullable = false)
    private Funcionario funcionario;

    public Date getDtIncOrdemProd() {
        return dtIncOrdemProd;
    }

    public void setDtIncOrdemProd(Date dtIncOrdemProd) {
        this.dtIncOrdemProd = dtIncOrdemProd;
    }

    public Date getDtProdOrdemProd() {
        return dtProdOrdemProd;
    }

    public void setDtProdOrdemProd(Date dtProdOrdemProd) {
        this.dtProdOrdemProd = dtProdOrdemProd;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Long getIdOrdemProd() {
        return idOrdemProd;
    }

    public void setIdOrdemProd(Long idOrdemProd) {
        this.idOrdemProd = idOrdemProd;
    }

    public OrdemProdStatus getOrdemProdStatus() {
        return ordemProdStatus;
    }

    public void setOrdemProdStatus(OrdemProdStatus ordemProdStatus) {
        this.ordemProdStatus = ordemProdStatus;
    }

    public List<PecaOrdemProd> getPecaOrdemProd() {
        return pecaOrdemProd;
    }

    public void setPecaOrdemProd(List<PecaOrdemProd> pecaOrdemProd) {
        this.pecaOrdemProd = pecaOrdemProd;
    }

    
    
}

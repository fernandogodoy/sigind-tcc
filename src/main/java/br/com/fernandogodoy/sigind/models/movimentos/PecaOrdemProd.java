/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.movimentos;


import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name="PECAORDEMPROD" )
public class PecaOrdemProd implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDPECAORDEMPROD")
    private Long idPecaOrdemProd;

    @OneToOne
    @JoinColumn(name="IDPECA")
    private Peca peca;

    @Column(name="QTDPECAS")
    private BigDecimal qtdPecas;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDORDEMPROD")
    private OrdemProd ordemProd;

    public Long getIdPecaOrdemProd() {
        return idPecaOrdemProd;
    }

    public void setIdPecaOrdemProd(Long idPecaOrdemProd) {
        this.idPecaOrdemProd = idPecaOrdemProd;
    }

    public OrdemProd getOrdemProd() {
        return ordemProd;
    }

    public void setOrdemProd(OrdemProd ordemProd) {
        this.ordemProd = ordemProd;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public BigDecimal getQtdPecas() {
        return qtdPecas;
    }

    public void setQtdPecas(BigDecimal qtdPecas) {
        this.qtdPecas = qtdPecas;
    }
    
    
    
}

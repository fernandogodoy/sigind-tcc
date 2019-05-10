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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "PECAVENDA" )
public class PecaVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPECAVENDA")
    private Long idPecaVenda;
    @Column(name = "QTDVENDA", nullable = false)
    private BigDecimal qtdVenda;
    @Column(name = "VALORPECA", nullable = false)
    private BigDecimal valorPeca;
    @Column(name = "PESOPECA", nullable = false)
    private BigDecimal pesoPeca;
    @OneToOne
    private Peca peca;
    @ManyToOne
    private Venda venda;

    public Long getIdPecaVenda() {
        return idPecaVenda;
    }

    public void setIdPecaVenda(Long idPecaVenda) {
        this.idPecaVenda = idPecaVenda;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public BigDecimal getQtdVenda() {
        return qtdVenda;
    }

    public void setQtdVenda(BigDecimal qtdVenda) {
        this.qtdVenda = qtdVenda;
    }

    public BigDecimal getValorPeca() {
        return valorPeca;
    }

    public void setValorPeca(BigDecimal valorPeca) {
        this.valorPeca = valorPeca;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public BigDecimal getPesoPeca() {
        return pesoPeca;
    }

    public void setPesoPeca(BigDecimal pesoPeca) {
        this.pesoPeca = pesoPeca;
    }
}

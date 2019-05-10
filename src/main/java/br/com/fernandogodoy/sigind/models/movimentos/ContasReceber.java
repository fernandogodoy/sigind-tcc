/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.movimentos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name = "CONTASRECEBER")
public class ContasReceber implements Serializable {

    @ManyToOne
    @JoinColumn(name = "IDVENDA", nullable = false)
    private Venda venda;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContasReceber;
    private BigDecimal valorContasReceber;
    @Temporal(TemporalType.DATE)
    private Date dtContasReceber;
    private Long parcelaContasReceber;
    private Boolean cancelada;
    private Boolean pago;
    @OneToMany(mappedBy = "contasReceber", targetEntity = PgContasReceber.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PgContasReceber> pgContaReceber;

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Date getDtContasReceber() {
        return dtContasReceber;
    }

    public void setDtContasReceber(Date dtContasReceber) {
        this.dtContasReceber = dtContasReceber;
    }

    public Long getIdContasReceber() {
        return idContasReceber;
    }

    public void setIdContasReceber(Long idContasReceber) {
        this.idContasReceber = idContasReceber;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Long getParcelaContasReceber() {
        return parcelaContasReceber;
    }

    public void setParcelaContasReceber(Long parcelaContasReceber) {
        this.parcelaContasReceber = parcelaContasReceber;
    }

    public List<PgContasReceber> getPgContaReceber() {
        return pgContaReceber;
    }

    public void setPgContaReceber(List<PgContasReceber> pgContaReceber) {
        this.pgContaReceber = pgContaReceber;
    }

    public BigDecimal getValorContasReceber() {
        return valorContasReceber;
    }

    public void setValorContasReceber(BigDecimal valorContasReceber) {
        this.valorContasReceber = valorContasReceber;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}

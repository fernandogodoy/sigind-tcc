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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "CONTASPAGAR")
public class ContasPagar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContasPagar")
    private Long idContasPagar;
    private BigDecimal valorContasPagar;
    @Temporal(TemporalType.DATE)
    private Date dtContasPagar;
    private Long parcelaContasPagar;
    @ManyToOne
    private Compra compra;
    private Boolean cancelada;
    private Boolean pago;
    @OneToMany(mappedBy = "contasPagar", targetEntity = PgContasPagar.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    private List<PgContasPagar> pgConta;

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Date getDtContasPagar() {
        return dtContasPagar;
    }

    public void setDtContasPagar(Date dtContasPagar) {
        this.dtContasPagar = dtContasPagar;
    }

    public Long getIdContasPagar() {
        return idContasPagar;
    }

    public void setIdContasPagar(Long idContasPagar) {
        this.idContasPagar = idContasPagar;
    }

    public Long getParcelaContasPagar() {
        return parcelaContasPagar;
    }

    public void setParcelaContasPagar(Long parcelaContasPagar) {
        this.parcelaContasPagar = parcelaContasPagar;
    }

    public List<PgContasPagar> getPgConta() {
        return pgConta;
    }

    public void setPgConta(List<PgContasPagar> pgConta) {
        this.pgConta = pgConta;
    }

    public BigDecimal getValorContasPagar() {
        return valorContasPagar;
    }

    public void setValorContasPagar(BigDecimal valorContasPagar) {
        this.valorContasPagar = valorContasPagar;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }
}

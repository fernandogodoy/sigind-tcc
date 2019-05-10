/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.movimentos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "PGCONTASPAGAR")
public class PgContasPagar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPgContasPagar")
    private Long idPgContasPagar;
    @Temporal(TemporalType.DATE)
    private Date dtPgContasPagar;
    private BigDecimal valorPgContasPagar;
    @ManyToOne
    @JoinColumn(name = "idContasPagar")
    private ContasPagar contasPagar;

    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public Date getDtPgContasPagar() {
        return dtPgContasPagar;
    }

    public void setDtPgContasPagar(Date dtPgContasPagar) {
        this.dtPgContasPagar = dtPgContasPagar;
    }

    public Long getIdPgContasPagar() {
        return idPgContasPagar;
    }

    public void setIdPgContasPagar(Long idPgContasPagar) {
        this.idPgContasPagar = idPgContasPagar;
    }

    public BigDecimal getValorPgContasPagar() {
        return valorPgContasPagar;
    }

    public void setValorPgContasPagar(BigDecimal valorPgContasPagar) {
        this.valorPgContasPagar = valorPgContasPagar;
    }
}

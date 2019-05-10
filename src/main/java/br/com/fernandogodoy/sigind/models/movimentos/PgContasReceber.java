/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.movimentos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.jar.Attributes.Name;
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
@Table(name = "PGCONTASRECEBER")
public class PgContasReceber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPGCONTASRECEBER", nullable = false)
    private Long idPgContasReceber;
    @Temporal(TemporalType.DATE)
    private Date dtPgContasReceber;
    private BigDecimal valorPgContasReceber;
    @ManyToOne
    @JoinColumn(name = "IDCONTASRECEBER")
    private ContasReceber contasReceber;

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public Date getDtPgContasReceber() {
        return dtPgContasReceber;
    }

    public void setDtPgContasReceber(Date dtPgContasReceber) {
        this.dtPgContasReceber = dtPgContasReceber;
    }

    public Long getIdPgContasReceber() {
        return idPgContasReceber;
    }

    public void setIdPgContasReceber(Long idPgContasReceber) {
        this.idPgContasReceber = idPgContasReceber;
    }

    public BigDecimal getValorPgContasReceber() {
        return valorPgContasReceber;
    }

    public void setValorPgContasReceber(BigDecimal valorPgContasReceber) {
        this.valorPgContasReceber = valorPgContasReceber;
    }
}

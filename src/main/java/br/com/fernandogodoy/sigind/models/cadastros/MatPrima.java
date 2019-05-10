/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.cadastros;

import br.com.fernandogodoy.sigind.models.movimentos.MatPrimaProd;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.Length;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "MATPRIMA")
public class MatPrima implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDMATPRIMA", nullable = false)
    private Long idMateriaPrima;
    @Length(min = 2, max = 50, message = "A Matéria Prima deve conter entre 2 e 50 caracteres.")
    @Column(name = "MATPRIMA", nullable = false)
    private String materiaPrima;
    @Column(name = "PESOMATPRIMA", nullable = false)
    private BigDecimal pesoMateriaPrima;
    @Column(name = "QTDMATPRIMA", nullable = false)
    private BigDecimal qtdMateriaPrima;
    @Column(name = "DTCADASTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadastro;
    @Column(name = "ATIVO", nullable = false)
    private Boolean ativo;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Long getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(Long idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public String getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(String materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public BigDecimal getPesoMateriaPrima() {
        return pesoMateriaPrima;
    }

    public void setPesoMateriaPrima(BigDecimal pesoMateriaPrima) {
        this.pesoMateriaPrima = pesoMateriaPrima;
    }

    public BigDecimal getQtdMateriaPrima() {
        return qtdMateriaPrima;
    }

    public void setQtdMateriaPrima(BigDecimal qtdMateriaPrima) {
        this.qtdMateriaPrima = qtdMateriaPrima;
    }
}

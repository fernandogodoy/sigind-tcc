/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.movimentos;

import br.com.fernandogodoy.sigind.models.cadastros.MatPrima;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "MATPRIMACOMPRA")
public class MatPrimaCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatPrimaCompra;
    private Boolean aprovaMatPrima;
    private BigDecimal qtdMatPrima;
    private BigDecimal valorMatPrima;
    private BigDecimal pesoMatPrima;
    @OneToOne
    private MatPrima matprima;
    @ManyToOne
    private Compra compra;

    public Boolean getAprovaMatPrima() {
        return aprovaMatPrima;
    }

    public void setAprovaMatPrima(Boolean aprovaMatPrima) {
        this.aprovaMatPrima = aprovaMatPrima;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Long getIdMatPrimaCompra() {
        return idMatPrimaCompra;
    }

    public void setIdMatPrimaCompra(Long idMatPrimaCompra) {
        this.idMatPrimaCompra = idMatPrimaCompra;
    }

    public MatPrima getMatprima() {
        return matprima;
    }

    public void setMatprima(MatPrima matprima) {
        this.matprima = matprima;
    }

    public BigDecimal getQtdMatPrima() {
        return qtdMatPrima;
    }

    public void setQtdMatPrima(BigDecimal qtdMatPrima) {
        this.qtdMatPrima = qtdMatPrima;
    }

    public BigDecimal getValorMatPrima() {
        return valorMatPrima;
    }

    public void setValorMatPrima(BigDecimal valorMatPrima) {
        this.valorMatPrima = valorMatPrima;
    }

    public BigDecimal getPesoMatPrima() {
        return pesoMatPrima;
    }

    public void setPesoMatPrima(BigDecimal pesoMatPrima) {
        this.pesoMatPrima = pesoMatPrima;
    }
    
}

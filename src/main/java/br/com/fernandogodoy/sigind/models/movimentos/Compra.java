/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.movimentos;

import br.com.fernandogodoy.sigind.models.cadastros.Fornecedor;
import br.com.fernandogodoy.sigind.models.cadastros.Funcionario;
import br.com.fernandogodoy.sigind.models.cadastros.TipoPagamento;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "COMPRA")
public class Compra implements Serializable {

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Fornecedor não informado")
    @JoinColumn(name = "IDFORNECEDOR")
    private Fornecedor fornecedor;
    @OneToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Funcionario não informado")
    @JoinColumn(name = "IDFUNCIONARIO")
    private Funcionario funcionario;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCOMPRA", nullable = false)
    private Long idCompra;
    private BigDecimal qtdCompra;
    private BigDecimal valorCompra;
    private BigDecimal pesoCompra;
    @Temporal(TemporalType.DATE)
    private Date dtCompra;
    @Column(name = "NUMNOTACOMPRA")
    @Length(min = 1, message = "Número da nota não informado")
    private String numNotaCompra;
    @Column(name = "SERIENOTACOMPRA", nullable = false)
    @Length(min = 1, message = "Número de Série da nota não informado")
    private String serieNotaCompra;
    @OneToMany(mappedBy = "compra", targetEntity = MatPrimaCompra.class, cascade = CascadeType.ALL)
    private List<MatPrimaCompra> matprimacompras = new ArrayList<MatPrimaCompra>();
    @OneToMany(mappedBy = "compra", targetEntity = ContasPagar.class, cascade = CascadeType.ALL)
    private List<ContasPagar> contasPagars = new ArrayList<ContasPagar>();
    private Boolean cancelada;
    @Enumerated(EnumType.ORDINAL)
    @NotNull(message="Tipo de Pagamento não informado")
    private TipoPagamento tipoPagamento;
    @Temporal(TemporalType.DATE)
    private Date dtVencCompra;

    public Date getDtCompra() {
        return dtCompra;
    }

    public void setDtCompra(Date dtCompra) {
        this.dtCompra = dtCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public String getNumNotaCompra() {
        return numNotaCompra;
    }

    public void setNumNotaCompra(String numNotaCompra) {
        this.numNotaCompra = numNotaCompra;
    }

    public BigDecimal getPesoCompra() {
        return pesoCompra;
    }

    public void setPesoCompra(BigDecimal pesoCompra) {
        this.pesoCompra = pesoCompra;
    }

    public BigDecimal getQtdCompra() {
        return qtdCompra;
    }

    public void setQtdCompra(BigDecimal qtdCompra) {
        this.qtdCompra = qtdCompra;
    }

    public String getSerieNotaCompra() {
        return serieNotaCompra;
    }

    public void setSerieNotaCompra(String serieNotaCompra) {
        this.serieNotaCompra = serieNotaCompra;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public List<MatPrimaCompra> getMatprimacompras() {
        return matprimacompras;
    }

    public void setMatprimacompras(List<MatPrimaCompra> matprimacompras) {
        this.matprimacompras = matprimacompras;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Date getDtVencCompra() {
        return dtVencCompra;
    }

    public void setDtVencCompra(Date dtVencCompra) {
        this.dtVencCompra = dtVencCompra;
    }

    public List<ContasPagar> getContasPagars() {
        return contasPagars;
    }

    public void setContasPagars(List<ContasPagar> contasPagars) {
        this.contasPagars = contasPagars;
    }
}

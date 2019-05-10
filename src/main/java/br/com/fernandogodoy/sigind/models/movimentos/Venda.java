/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.movimentos;

import br.com.fernandogodoy.sigind.models.cadastros.Cliente;
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
import javax.persistence.ManyToMany;
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
@Table(name = "VENDA")
public class Venda implements Serializable {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCLIENTE", nullable = false)
    @NotNull(message = "Cliente não informado")
    private Cliente cliente;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDFUNCIONARIO", nullable = false)
    private Funcionario funcionario;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVENDA", nullable = false)
    private Long idVenda;
    private BigDecimal qtdVenda;
    private BigDecimal valorVenda;
    private BigDecimal pesoVenda;
    @Temporal(TemporalType.DATE)
    private Date dtVenda;
    @Length(min = 1, message = "Número da nota é obrigatório")
    private String numNotaVenda;
    @Length(min = 1, message = "Número de sério da nota é obrigatório")
    private String serieNotaVenda;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", targetEntity = PecaVenda.class, cascade = CascadeType.ALL)
    private List<PecaVenda> pecaVenda = new ArrayList<PecaVenda>();
    @ManyToMany(mappedBy = "vendas", targetEntity = Expedicao.class, cascade = CascadeType.ALL)
    private List<Expedicao> expedicoes;
    @OneToMany(mappedBy = "venda", targetEntity = ContasReceber.class, cascade = CascadeType.ALL)
    private List<ContasReceber> contasRecebers = new ArrayList<ContasReceber>();
    private Boolean cancelada;
    @Enumerated(EnumType.ORDINAL)
    private TipoPagamento tipoPagamento;
    @Temporal(TemporalType.DATE)
    private Date dtVencVenda;
    private Boolean aprovada;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
    }

    public List<Expedicao> getExpedicoes() {
        return expedicoes;
    }

    public void setExpedicoes(List<Expedicao> expedicoes) {
        this.expedicoes = expedicoes;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public String getNumNotaVenda() {
        return numNotaVenda;
    }

    public void setNumNotaVenda(String numNotaVenda) {
        this.numNotaVenda = numNotaVenda;
    }

    public List<PecaVenda> getPecaVenda() {
        return pecaVenda;
    }

    public void setPecaVenda(List<PecaVenda> pecaVenda) {
        this.pecaVenda = pecaVenda;
    }

    public BigDecimal getPesoVenda() {
        return pesoVenda;
    }

    public void setPesoVenda(BigDecimal pesoVenda) {
        this.pesoVenda = pesoVenda;
    }

    public Date getDtVencVenda() {
        return dtVencVenda;
    }

    public void setDtVencVenda(Date dtVencVenda) {
        this.dtVencVenda = dtVencVenda;
    }

    public BigDecimal getQtdVenda() {
        return qtdVenda;
    }

    public void setQtdVenda(BigDecimal qtdVenda) {
        this.qtdVenda = qtdVenda;
    }

    public String getSerieNotaVenda() {
        return serieNotaVenda;
    }

    public void setSerieNotaVenda(String serieNotaVenda) {
        this.serieNotaVenda = serieNotaVenda;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Boolean getAprovada() {
        return aprovada;
    }

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public List<ContasReceber> getContasRecebers() {
        return contasRecebers;
    }

    public void setContasRecebers(List<ContasReceber> contasRecebers) {
        this.contasRecebers = contasRecebers;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}

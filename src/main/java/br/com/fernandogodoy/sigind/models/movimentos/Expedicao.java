/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.movimentos;

import br.com.fernandogodoy.sigind.models.cadastros.Funcionario;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "EXPEDICAO")
public class Expedicao implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entFuncionario")
    private Funcionario entFuncionario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saiFuncionario")
    private Funcionario saiFuncionario;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEXPEDICAO", nullable = false)
    private Long idExpedicao;
    private BigDecimal pesoEntExp;
    private BigDecimal pesoSaiExp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaEntExp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaSaiExp;
    private String obsEntExp;
    private String obsSaiExp;
    @ManyToMany(targetEntity = Venda.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Expedicao_Venda", joinColumns =
    @JoinColumn(name = "IdVenda"), inverseJoinColumns =
    @JoinColumn(name = "IdExpedicao"))
    private List<Venda> vendas;

    public Funcionario getEntFuncionario() {
        return entFuncionario;
    }

    public void setEntFuncionario(Funcionario entFuncionario) {
        this.entFuncionario = entFuncionario;
    }

    public Date getHoraEntExp() {
        return horaEntExp;
    }

    public void setHoraEntExp(Date horaEntExp) {
        this.horaEntExp = horaEntExp;
    }

    public Date getHoraSaiExp() {
        return horaSaiExp;
    }

    public void setHoraSaiExp(Date horaSaiExp) {
        this.horaSaiExp = horaSaiExp;
    }

    public Long getIdExpedicao() {
        return idExpedicao;
    }

    public void setIdExpedicao(Long idExpedicao) {
        this.idExpedicao = idExpedicao;
    }

    public String getObsEntExp() {
        return obsEntExp;
    }

    public void setObsEntExp(String obsEntExp) {
        this.obsEntExp = obsEntExp;
    }

    public String getObsSaiExp() {
        return obsSaiExp;
    }

    public void setObsSaiExp(String obsSaiExp) {
        this.obsSaiExp = obsSaiExp;
    }

    public BigDecimal getPesoEntExp() {
        return pesoEntExp;
    }

    public void setPesoEntExp(BigDecimal pesoEntExp) {
        this.pesoEntExp = pesoEntExp;
    }

    public BigDecimal getPesoSaiExp() {
        return pesoSaiExp;
    }

    public void setPesoSaiExp(BigDecimal pesoSaiExp) {
        this.pesoSaiExp = pesoSaiExp;
    }

    public Funcionario getSaiFuncionario() {
        return saiFuncionario;
    }

    public void setSaiFuncionario(Funcionario saiFuncionario) {
        this.saiFuncionario = saiFuncionario;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}

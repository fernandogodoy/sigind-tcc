/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.cadastros;

import br.com.caelum.stella.hibernate.validator.CPF;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "FUNCIONARIO")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDFUNCIONARIO", nullable = false)
    private Long idFuncionario;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "IDPESSOA", nullable = false, unique = true)
    private Pessoa pessoa = new Pessoa();
    private String rgFuncionario;
    @NotNull
    @CPF(message = "CPF inválido ou já cadastrado")
    @Column(unique = true)
    private String cpfFuncionario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtNascFuncionario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadFuncionario;
    @Length(min = 14, message = "Pis Inválido")
    private String pisFuncionario;
    @Length(min = 1, message = "Numero CTPS não informada")
    private String ctpsFuncionario;
    @Length(min=3, message="Série da CTPS não informada")
    private String serieCtpsFuncionario;
    @Column(name="ufctpsfuncionario")
    private Integer estadoCtpsFuncionario;
    private Boolean ativo;
    @NotNull(message="Setor não informado")
    @ManyToOne
    @JoinColumn(name = "IDSETOR")
    private Setor setor;
    @NotNull(message="Função não informada")
    @ManyToOne
    @JoinColumn(name = "IDFUNCAO")
    private Funcao funcao;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getCtpsFuncionario() {
        return ctpsFuncionario;
    }

    public void setCtpsFuncionario(String ctpsFuncionario) {
        this.ctpsFuncionario = ctpsFuncionario;
    }

    public Date getDtCadFuncionario() {
        return dtCadFuncionario;
    }

    public void setDtCadFuncionario(Date dtCadFuncionario) {
        this.dtCadFuncionario = dtCadFuncionario;
    }

    public Date getDtNascFuncionario() {
        return dtNascFuncionario;
    }

    public void setDtNascFuncionario(Date dtNascFuncionario) {
        this.dtNascFuncionario = dtNascFuncionario;
    }

    public Integer getEstadoCtpsFuncionario() {
        return estadoCtpsFuncionario;
    }

    public void setEstadoCtpsFuncionario(Integer estadoCtpsFuncionario) {
        this.estadoCtpsFuncionario = estadoCtpsFuncionario;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getPisFuncionario() {
        return pisFuncionario;
    }

    public void setPisFuncionario(String pisFuncionario) {
        this.pisFuncionario = pisFuncionario;
    }

    public String getRgFuncionario() {
        return rgFuncionario;
    }

    public void setRgFuncionario(String rgFuncionario) {
        this.rgFuncionario = rgFuncionario;
    }

    public String getSerieCtpsFuncionario() {
        return serieCtpsFuncionario;
    }

    public void setSerieCtpsFuncionario(String serieCtpsFuncionario) {
        this.serieCtpsFuncionario = serieCtpsFuncionario;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}

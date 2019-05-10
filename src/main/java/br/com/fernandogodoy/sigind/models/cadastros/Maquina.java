/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.cadastros;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Past;


/**
 *
 * @author Fernando
 */
@Entity
@Table(name="MAQUINA")
public class Maquina implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDMAQUINA", nullable = false)
    private Long idMaquina;

    @Column(name="NUMMAQUINA", nullable = false)
    private String numMaquina;

    @Length(min = 2, max = 80, message = "A Maquina deve conter entre 2 e 80 caracteres")
    @Column(name="MAQUINA", nullable = false)
    private String maquina;

    @NotNull(message = "Selecione uma Marca")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDMARCA", nullable = false)
    private Marca marca = new Marca();

    @NotNull(message = "Selecione um Fornecedor")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDPESSOA", nullable = false)
    private Fornecedor fornecedor = new Fornecedor();

    @Past(message = "A Data de Fabricação deve ser menor que a Data Atual")
    @NotNull(message = "Selecione uma Data de Fabricação")
    @Column(name="DTFABMAQUINA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtFabMaquina;

    @Past(message = "A Data de Compra deve ser menor que a Data Atual")
    @NotNull(message = "Selecione uma Data de Compra")
    @Column(name="DTCOMPMAQUINA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtCompMaquina;

    @NotNull(message = "Selecione uma Data Final da Garantia")
    @Column(name="DTFINALMAQUINA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtFinalGarMaquina;

    @Column(name="DTCADASTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadastro;

    @Column(name="ATIVO", nullable = false)
    private Boolean ativo;

    @Version
    @Column(name="VERSAO", nullable = false)
    private Long versao;

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

    public Date getDtCompMaquina() {
        return dtCompMaquina;
    }

    public void setDtCompMaquina(Date dtCompMaquina) {
        this.dtCompMaquina = dtCompMaquina;
    }

    public Date getDtFabMaquina() {
        return dtFabMaquina;
    }

    public void setDtFabMaquina(Date dtFabMaquina) {
        this.dtFabMaquina = dtFabMaquina;
    }

    public Date getDtFinalGarMaquina() {
        return dtFinalGarMaquina;
    }

    public void setDtFinalGarMaquina(Date dtFinalGarMaquina) {
        this.dtFinalGarMaquina = dtFinalGarMaquina;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Long getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Long idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getNumMaquina() {
        return numMaquina;
    }

    public void setNumMaquina(String numMaquina) {
        this.numMaquina = numMaquina;
    }

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.cadastros;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.Length;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "EMPRESA")
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEMPRESA")
    private Long idEmpresa;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "IDPESSOA", unique = true)
    private Pessoa pessoa = new Pessoa();
    @Length(min = 14, message = "CNPJ já cadastrado ou inválido")
    @Column(unique = true)
    private String cnpjEmpresa;
    private String razaoEmpresa;
    private String emailEmpresa;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadEmpresa;
    private Boolean ativo;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public Date getDtCadEmpresa() {
        return dtCadEmpresa;
    }

    public void setDtCadEmpresa(Date dtCadEmpresa) {
        this.dtCadEmpresa = dtCadEmpresa;
    }

    public String getRazaoEmpresa() {
        return razaoEmpresa;
    }

    public void setRazaoEmpresa(String razaoEmpresa) {
        this.razaoEmpresa = razaoEmpresa;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.cadastros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPESSOA")
    private Long idPessoa;
    @Length(min = 2, max = 80, message = "O Endereço deve conter entre 2 e 80 caracteres")
    private String ruaPessoa;
    @Length(max = 10, message = "O Número não deve conter mais que 10 caracteres")
    private String numPessoa;
    @Length(max = 80, message = "O Complemento não deve conter mais que 80 caracteres")
    private String comPessoa;
    @Length(min = 2, max = 80, message = "O Bairro deve conter entre 2 e 80 caracteres")
    private String bairroPessoa;
    @Length(min = 9, message = "CEP Incorreto")
    private String cepPessoa;
    @Length(min = 2, max = 80, message = "O Nome da Pessoa deve conter entre 2 e 80 caracteres")
    private String nomePessoa;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDCIDADE")
    private Municipio cidade;
    @Length(max = 255, message = "A Observação não deve conter mais que 255 caracteres")
    private String obsPessoa;
    @OneToMany(mappedBy = "pessoa", targetEntity = Telefone.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<Telefone>();
    @OneToMany(mappedBy = "pessoa", targetEntity = Email.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Email> emails = new ArrayList<Email>();
    @Version
    @Column(name = "VERSAO", nullable = false)
    private Long versao;

    public String getBairroPessoa() {
        return bairroPessoa;
    }

    public void setBairroPessoa(String bairroPessoa) {
        this.bairroPessoa = bairroPessoa;
    }

    public String getCepPessoa() {
        return cepPessoa;
    }

    public void setCepPessoa(String cepPessoa) {
        this.cepPessoa = cepPessoa;
    }

    public Municipio getCidade() {
        return cidade;
    }

    public void setCidade(Municipio cidade) {
        this.cidade = cidade;
    }

    public String getComPessoa() {
        return comPessoa;
    }

    public void setComPessoa(String comPessoa) {
        this.comPessoa = comPessoa;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getNumPessoa() {
        return numPessoa;
    }

    public void setNumPessoa(String numPessoa) {
        this.numPessoa = numPessoa;
    }

    public String getObsPessoa() {
        return obsPessoa;
    }

    public void setObsPessoa(String obsPessoa) {
        this.obsPessoa = obsPessoa;
    }

    public String getRuaPessoa() {
        return ruaPessoa;
    }

    public void setRuaPessoa(String ruaPessoa) {
        this.ruaPessoa = ruaPessoa;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }
}

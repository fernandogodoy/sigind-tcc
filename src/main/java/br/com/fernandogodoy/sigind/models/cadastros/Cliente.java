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
@Table(name="CLIENTE")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDCLIENTE", nullable = false)
    private Long idCliente;

    @OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="IDPESSOA", nullable = false, unique = true)
    private Pessoa pessoa = new Pessoa();

    @Length(min = 18, message = "Digite um CNPJ válido")
    @Column(name="CNPJCLIENTE", nullable = false, unique = true)
    private String cnpjCliente;

    @Column(name="CONTATOCLIENTE", nullable = false)
    private String contatoCliente;

    @Column(name="RAZAOCLIENTE", nullable = false)
    private String razaoCliente;

    @Column(name="DTCADCLIENTE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadCliente;

    @Column(name="ATIVO", nullable = false)
    private Boolean ativo;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getContatoCliente() {
        return contatoCliente;
    }

    public void setContatoCliente(String contatoCliente) {
        this.contatoCliente = contatoCliente;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCnpjCliente() {
        return cnpjCliente;
    }

    public void setCnpjCliente(String cnpjCliente) {
        this.cnpjCliente = cnpjCliente;
    }

    public Date getDtCadCliente() {
        return dtCadCliente;
    }

    public void setDtCadCliente(Date dtCadCliente) {
        this.dtCadCliente = dtCadCliente;
    }

    public String getRazaoCliente() {
        return razaoCliente;
    }

    public void setRazaoCliente(String razaoCliente) {
        this.razaoCliente = razaoCliente;
    }
    
}

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



/**getPessoa().
 *
 * @author Fernando
 */
@Entity
@Table(name="FORNECEDOR")
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDFORNECEDOR", nullable = false)
    private Long idFornecedor;

    @Length(min = 18, message = "Digite um CNPJ valido")
    @Column(name="CNPJFORNECEDOR", nullable = false, unique = true)
    private String cnpjFornecedor;

    @Column(name="CONTATOFORNECEDOR", nullable = false)
    private String contatoFornecedor;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="IDPESSOA", nullable = false, unique = true)
    private Pessoa pessoa = new Pessoa();

    @Column(name="DTCADFORNECEDOR", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadFornecedor;

    @Column(name="ATIVO", nullable = false)
    private Boolean ativo;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }

    public String getContatoFornecedor() {
        return contatoFornecedor;
    }

    public void setContatoFornecedor(String contatoFornecedor) {
        this.contatoFornecedor = contatoFornecedor;
    }

    public Date getDtCadFornecedor() {
        return dtCadFornecedor;
    }

    public void setDtCadFornecedor(Date dtCadFornecedor) {
        this.dtCadFornecedor = dtCadFornecedor;
    }

    public Long getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Long idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}

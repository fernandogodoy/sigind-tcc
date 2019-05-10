/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.cadastros;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.validator.Length;


/**
 *
 * @author Fernando
 */
@Entity
@Table(name="FUNCAO")
public class Funcao implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDFUNCAO", unique = true, nullable = false)
    private Long idFuncao;

    @Length(min = 2, max = 50, message = "A Função deve ter entre 2 e 50 caracteres.")
    @Column(name="FUNCAO", nullable = false)
    private String funcao;

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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Long getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(Long idFuncao) {
        this.idFuncao = idFuncao;
    }

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }
    
}

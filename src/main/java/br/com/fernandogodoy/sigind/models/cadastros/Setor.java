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
@Table(name="SETOR")
public class Setor implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDSETOR", nullable = false)
    private Long idSetor;

    @Length(min = 2, max = 50, message = "O Setor deve conter entre 2 e 50 caracteres.")
    @Column(name="SETOR", nullable = false)
    private String setor;

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

    public Long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }
}

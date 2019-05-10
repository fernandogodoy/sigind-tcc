/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.cadastros;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name="TELEFONE")
public class Telefone implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDTELEFONE", nullable = false)
    private Long idTelefone;

    @Column(name="TELEFONE", nullable = false)
    private String telefone;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="TIPOTELEFONE", nullable = false)
    private TipoTelefone tipoTelefone;

    @ManyToOne
    @JoinColumn(name="IDPESSOA", nullable = false)
    private Pessoa pessoa;

    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
    
}

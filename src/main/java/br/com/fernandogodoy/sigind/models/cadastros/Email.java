/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.cadastros;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="EMAIL")
public class Email {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDEMAIL", nullable = false)
    private Long idEmail;

    @Column(name="EMAIL", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name="IDPESSOA", nullable = false)
    private Pessoa pessoa;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(Long idEmail) {
        this.idEmail = idEmail;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
}

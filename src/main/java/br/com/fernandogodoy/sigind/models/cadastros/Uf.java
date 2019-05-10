/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.cadastros;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "UF")
public class Uf implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUF", nullable = false)
    private Long idUf;
    @Column(name = "XUF", nullable = false)
    private String xUf;
    @Column(name = "SIGLA", nullable = false)
    private String sigla;
    @Column(name = "CUF", nullable = false)
    private String cUF;

    public String getcUF() {
        return cUF;
    }

    public void setcUF(String cUF) {
        this.cUF = cUF;
    }

    public Long getIdUf() {
        return idUf;
    }

    public void setIdUf(Long idUf) {
        this.idUf = idUf;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getxUf() {
        return xUf;
    }

    public void setxUf(String xUf) {
        this.xUf = xUf;
    }
}

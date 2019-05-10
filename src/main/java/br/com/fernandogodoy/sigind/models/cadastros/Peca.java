/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.cadastros;


import br.com.fernandogodoy.sigind.models.movimentos.DevolucaoPeca;
import br.com.fernandogodoy.sigind.models.movimentos.PecaVenda;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.Digits;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;


/**
 *
 * @author Fernando
 */
@Entity
@Table(name="PECA")
public class Peca implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDPECA", nullable = false)
    private Long idPeca;

    @Length(min = 0, max = 10, message = "O Número da Peça deve conter no maximo 10 caracteres")
    @Column(name="NUMPECA", nullable = false)
    private String numPeca;

    @Length(min = 2, max = 80, message = "O Nome da Peça deve conter entre 2 e 80 caracteres")
    @Column(name="PECA", nullable = false)
    private String peca;

    @Column(name="QTDPECA", nullable = false)
    private BigDecimal qtdPeca;

    @Column(name="PESOMPECA", nullable = false)
    private BigDecimal pesoMedioPeca;

    @Digits(fractionalDigits= 2, integerDigits= 8, message = "Preço da Peça inválido")
    @NotNull(message="Valor da peça inválido")
    @Column(name="VALORPECA", nullable = false)
    private BigDecimal valorPeca;

    @Column(name="DTCADASTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadastro;

    @Column(name="ATIVO", nullable = false)
    private Boolean ativo;

    

    @OneToMany(fetch = FetchType.LAZY)
    private List<DevolucaoPeca> devolucaoPeca = new ArrayList<DevolucaoPeca>();

    public String getNumPeca() {
        return numPeca;
    }

    public void setNumPeca(String numPeca) {
        this.numPeca = numPeca;
    }
    
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<DevolucaoPeca> getDevolucaoPeca() {
        return devolucaoPeca;
    }

    public void setDevolucaoPeca(List<DevolucaoPeca> devolucaoPeca) {
        this.devolucaoPeca = devolucaoPeca;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    public String getPeca() {
        return peca;
    }

    public void setPeca(String peca) {
        this.peca = peca;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

   

    public BigDecimal getPesoMedioPeca() {
        return pesoMedioPeca;
    }

    public void setPesoMedioPeca(BigDecimal pesoMedioPeca) {
        this.pesoMedioPeca = pesoMedioPeca;
    }

    public BigDecimal getQtdPeca() {
        return qtdPeca;
    }

    public void setQtdPeca(BigDecimal qtdPeca) {
        this.qtdPeca = qtdPeca;
    }

    public BigDecimal getValorPeca() {
        return valorPeca;
    }

    public void setValorPeca(BigDecimal valorPeca) {
        this.valorPeca = valorPeca;
    }
    
    
    
}

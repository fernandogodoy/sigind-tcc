/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.movimentos;


import br.com.fernandogodoy.sigind.models.cadastros.Funcionario;
import br.com.fernandogodoy.sigind.models.cadastros.Maquina;
import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name="PRODUCAO")
public class Producao implements Serializable {

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDFUNCIONARIO", nullable = false)
    private Funcionario funcionario;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDMAQUINA", nullable = false)
    private Maquina maquina;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDPECA", nullable = false)
    private Peca peca;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDPRODUCAO", nullable = false)
    private Long idProducao;

    @Column(name="QTDPRODUCAO", nullable = false)
    private BigDecimal qtdProducao;

    @Column(name="PESOPRODUCAO", nullable = false)
    private BigDecimal pesoProducao;

    @Column(name="DTPRODUCAO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtProducao;

    @Column(name="PERCAPRODUCAO", nullable = false)
    private BigDecimal qtdPercaProducao;

     @OneToMany(mappedBy="producao", targetEntity=MatPrimaProd.class)  
    private List<MatPrimaProd> matPrimaProd;

}

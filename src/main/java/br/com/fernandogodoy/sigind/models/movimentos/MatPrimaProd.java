/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.movimentos;


import br.com.fernandogodoy.sigind.models.cadastros.MatPrima;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name="MATPRIMAPROD" )
public class MatPrimaProd implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDMATPRIMAPROD", nullable = false)
    private Long idMatPrimaProd;

    @Column(name="QTDMATPRIMA", nullable = false)
    private BigDecimal qtdMatPrima;

    @OneToOne
    @JoinColumn(name="IDMATPRIMA", nullable = false)
    private MatPrima matPrima;

    @ManyToOne(targetEntity = Producao.class, fetch = FetchType.LAZY)   
    private Producao producao;


}

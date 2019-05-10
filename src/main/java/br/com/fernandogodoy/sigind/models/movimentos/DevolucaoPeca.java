/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.movimentos;

import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "DEVOLUCAOPECA" )

public class DevolucaoPeca implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDDEVOLUCAOPECA")
    private Long idDevolucaoPeca;
    
    @Column(name = "QTDDEVOLUCAO", nullable = false)
    private BigDecimal qtdDevolucao;
    
    @Column(name = "PESODEVOLUCAO", nullable = false)
    private BigDecimal pesoDevolucao;
    
    @Column(name = "VALORPECA", nullable = false)
    private BigDecimal valorPeca;
    
     @OneToOne
    private Peca peca;
    
    @ManyToOne
    private Devolucao devolucao;
    
   

   
    
}

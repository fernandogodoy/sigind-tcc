/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.movimentos;

import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "DEVOLUCAO" )
public class Devolucao implements Serializable {

    @ManyToOne
    private PecaVenda pecavenda;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDEVOLUCAO", nullable = false)
    private Long idDevolucao;
            
    @Column(name = "DTDEVOLUCAO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtDevolucao;
            
    @OneToMany(fetch = FetchType.LAZY, mappedBy= "devolucao" , targetEntity= DevolucaoPeca.class)
    private List<DevolucaoPeca> devolucaoPeca = new ArrayList<DevolucaoPeca>();
}

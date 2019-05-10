/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.cadastros;

/**
 *
 * @author Fernado
 */
public enum TipoPagamento {

    A_VISTA("A Vista"),
    CHEQUE("Cheque"),
    BOLETO("Boleto"),
    DUPLICATA("Duplicata"),
    CARTAO("Cartão"),
    DEPOSITO("Depósito");
    private String tipoPagamento;

    TipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    @Override
    public String toString() {
        return tipoPagamento;
    }
}

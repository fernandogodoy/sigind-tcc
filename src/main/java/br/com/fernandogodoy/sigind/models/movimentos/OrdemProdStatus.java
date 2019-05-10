/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernandogodoy.sigind.models.movimentos;

/**
 *
 * @author Fernando
 */
public enum OrdemProdStatus {

    EM_APROVACAO("EM APROVAÇÃO"),
    APROVADA("APROVADA"),
    REPROVADA("REPROVADA"),
    EM_PRODUCAO("EM PRODUÇÃO"),
    FINALIZADA("FINALIZADA"),
    CANCELADA("CANCELADA");
    private final String value;

    OrdemProdStatus(String value) {
        this.value = value;
    }

    public static OrdemProdStatus getValue(Integer value) {
        switch (value) {
            case 0:
                return EM_APROVACAO;
            case 1:
                return APROVADA;
            case 2:
                return REPROVADA;
            case 3:
                return EM_PRODUCAO;
            case 4:
                return FINALIZADA;
            case 5:
                return CANCELADA;
        }
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}

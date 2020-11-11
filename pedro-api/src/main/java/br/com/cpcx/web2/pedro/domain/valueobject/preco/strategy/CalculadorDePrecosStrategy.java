package br.com.cpcx.web2.pedro.domain.valueobject.preco.strategy;

import java.math.BigDecimal;

public class CalculadorDePrecosStrategy {
    private CalculadorDePrecosStrategy(){}
    public static BigDecimal realizaCalculoDePreco(BigDecimal orcamento, Desconto descontoQualquer){
        return descontoQualquer.calcula(orcamento);
    }
}

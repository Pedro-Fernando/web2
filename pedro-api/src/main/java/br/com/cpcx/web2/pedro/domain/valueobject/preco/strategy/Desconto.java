package br.com.cpcx.web2.pedro.domain.valueobject.preco.strategy;

import java.math.BigDecimal;

public interface Desconto {
    BigDecimal calcula(BigDecimal orcamento);
}

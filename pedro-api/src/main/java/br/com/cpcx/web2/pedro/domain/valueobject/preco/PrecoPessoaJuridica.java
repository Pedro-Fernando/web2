package br.com.cpcx.web2.pedro.domain.valueobject.preco;

import br.com.cpcx.web2.pedro.domain.valueobject.preco.strategy.Desconto;

import java.math.BigDecimal;

public class PrecoPessoaJuridica implements Desconto {

    @Override
    public BigDecimal calcula(BigDecimal orcamento) {
        String DESCONTO_PARA_PESSOA_FISICA = "0.2";
        BigDecimal desconto = new BigDecimal(DESCONTO_PARA_PESSOA_FISICA);

        return orcamento.subtract(orcamento.multiply(desconto));
    }
}

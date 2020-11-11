package br.com.cpcx.web2.pedro.domain.pojo;

import br.com.cpcx.web2.pedro.domain.entity.Pedido;
import br.com.cpcx.web2.pedro.domain.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoPOJO {
    private Long id;
    private Pessoa pessoa;
    private LocalDate dataCompra;
    private LocalDate dataEntrega;
    private BigDecimal percentualDesconto;

    public static Pedido gerarPedido(PedidoPOJO pojo){
        Pedido pedido = new Pedido();

        pedido.setId(pojo.getId());
        pedido.setPessoa(pojo.getPessoa());
        pedido.setPercentualDesconto(pojo.getPercentualDesconto());
        pedido.setDataCompra(pojo.getDataCompra());
        pedido.setDataEntrega(pojo.getDataEntrega());

        return pedido;
    }
}

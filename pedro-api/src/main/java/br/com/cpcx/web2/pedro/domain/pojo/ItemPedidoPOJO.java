package br.com.cpcx.web2.pedro.domain.pojo;

import br.com.cpcx.web2.pedro.domain.entity.ItemPedido;
import br.com.cpcx.web2.pedro.domain.entity.Pedido;
import br.com.cpcx.web2.pedro.domain.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoPOJO {
    private Long id;
    private Pedido pedido;
    private Produto produto;
    private Integer quantidade;

    public static ItemPedido gerarItemPedido(ItemPedidoPOJO pojo){
        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setId(pojo.getId());
        itemPedido.setPedido(pojo.getPedido());
        itemPedido.setProduto(pojo.getProduto());
        itemPedido.setQuantidade(pojo.getQuantidade());

        return itemPedido;
    }
}

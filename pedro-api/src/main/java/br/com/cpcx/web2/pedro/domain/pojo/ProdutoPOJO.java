package br.com.cpcx.web2.pedro.domain.pojo;

import br.com.cpcx.web2.pedro.domain.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPOJO {
    private Long id;
    private String descricao;
    private Integer quantidadeEstoque;
    private Integer idadePermitida;
    private BigDecimal precoCompra;
    private BigDecimal precoVenda;

    public static Produto gerarItemPedido(ProdutoPOJO pojo){
        Produto produto = new Produto();

        produto.setId(pojo.getId());
        produto.setDescricao(pojo.getDescricao());
        produto.setQuantidadeEstoque(pojo.getQuantidadeEstoque());
        produto.setIdadePermitida(pojo.getIdadePermitida());
        produto.setPrecoCompra(pojo.getPrecoCompra());
        produto.setPrecoVenda(pojo.getPrecoVenda());

        return produto;
    }

}

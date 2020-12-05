package br.com.cpcx.web2.pedro.domain.filtro.produto;

import br.com.cpcx.web2.pedro.domain.entity.Pessoa;
import br.com.cpcx.web2.pedro.domain.entity.Produto;
import br.com.cpcx.web2.pedro.domain.entity.Usuario;
import br.com.cpcx.web2.pedro.domain.pojo.ProdutoPOJO;
import br.com.cpcx.web2.pedro.domain.valueobject.preco.PrecoPessoaFisica;
import br.com.cpcx.web2.pedro.domain.valueobject.preco.PrecoPessoaJuridica;
import br.com.cpcx.web2.pedro.domain.valueobject.preco.strategy.CalculadorDePrecosStrategy;
import br.com.cpcx.web2.pedro.domain.valueobject.preco.strategy.Desconto;
import br.com.cpcx.web2.pedro.repository.PessoaRepository;
import br.com.cpcx.web2.pedro.repository.ProdutoRepository;
import br.com.cpcx.web2.pedro.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.cpcx.web2.pedro.utils.TipoPessoaUtils.isPessoaFisica;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class BuscarProdutoPorFiltro {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<ProdutoPOJO> filtrar(String descricao, BigDecimal precoMaximo, BigDecimal precoMinimo, Usuario usuario) {


        return produtoRepository.findAll().stream()
                .filter(produto -> {
                    if (nonNull(produto.getId()) && nonNull(descricao)) {
                        return produto.getDescricao().equals(descricao);
                    }else if (nonNull(produto.getId())){
                        return Boolean.TRUE;
                    }

                    return Boolean.FALSE;
                })

                .filter(produto -> {
                    if (nonNull(produto.getId()) && nonNull(usuario)) {
                        if (nonNull(produto.getIdadePermitida())){
                            return Integer.compare(DataUtils.calcularIdade(usuario.getPessoa().getDataNascimento()) , produto.getIdadePermitida()) >= 0;
                        }

                        return Boolean.FALSE;
                    }

                    return Boolean.FALSE;
                })

                .map(produto -> {
                    if (nonNull(produto.getId())){
                        return retornaProdutoPojoComAjusteDePrecoReferenteAoTipoDePessoa(produto, usuario.getPessoa());
                    }
                    return null;
                })

                .filter(produto -> {
                    if (nonNull(produto.getId()) && nonNull(precoMinimo)) {
                        return produto.getPrecoVenda().compareTo(precoMinimo) >= 0;
                    }else if (nonNull(produto.getId())){
                        return Boolean.TRUE;
                    }

                    return Boolean.FALSE;
                })

                .filter(produto -> {
                    if (nonNull(produto.getId()) && nonNull(precoMaximo)) {
                        return produto.getPrecoVenda().compareTo(precoMaximo) <= 0;
                    }else if (nonNull(produto.getId())){
                        return Boolean.TRUE;
                    }

                    return Boolean.FALSE;
                })
                .collect(Collectors.toList());
    }

    private ProdutoPOJO retornaProdutoPojoComAjusteDePrecoReferenteAoTipoDePessoa(Produto produto, Pessoa pessoa) {

        Desconto desconto;

        if (isPessoaFisica(pessoa)) {
            desconto = new PrecoPessoaFisica();
        } else {
            desconto = new PrecoPessoaJuridica();
        }

        return  ProdutoPOJO.builder()
                .id(produto.getId())
                .descricao(produto.getDescricao())
                .precoCompra(produto.getPrecoCompra())
                .precoVenda(CalculadorDePrecosStrategy.realizaCalculoDePreco(produto.getPrecoVenda(), desconto))
                .idadePermitida(produto.getIdadePermitida())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .build();
    }

}

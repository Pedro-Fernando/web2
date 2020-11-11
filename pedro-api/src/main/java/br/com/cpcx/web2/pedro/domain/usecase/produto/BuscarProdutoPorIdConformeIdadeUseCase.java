package br.com.cpcx.web2.pedro.domain.usecase.produto;

import br.com.cpcx.web2.pedro.domain.entity.Pessoa;
import br.com.cpcx.web2.pedro.domain.entity.Produto;
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

import java.time.LocalDate;

import static br.com.cpcx.web2.pedro.utils.TipoPessoaUtils.isPessoaFisica;
import static java.util.Objects.isNull;

@Service
public class BuscarProdutoPorIdConformeIdadeUseCase {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Object executar(Long idProduto, Long idPessoa) {

        Integer idadeDaPessoa = buscarIdadeDaPessoaPorId(idPessoa);

        Produto produto = produtoRepository.buscarProdutoProIdConformeAIdade(idProduto, idadeDaPessoa);

        if (isNull(produto)) {
            return ProdutoPOJO.builder().build();
        } else {
            return retornaProdutoPojoComAjusteDePrecoReferenteAoTipoDePessoa(produto, idPessoa);
        }
    }

    private ProdutoPOJO retornaProdutoPojoComAjusteDePrecoReferenteAoTipoDePessoa(Produto produto, Long idPessoa) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa).get();
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

    private Integer buscarIdadeDaPessoaPorId(Long idPessoa) {
        LocalDate dataNascimentoDaPessoa = pessoaRepository.buscarDataNascomentoPorId(idPessoa);
        return DataUtils.calcularIdade(dataNascimentoDaPessoa);
    }

}

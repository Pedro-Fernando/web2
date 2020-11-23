package br.com.cpcx.web2.pedro.domain.filtro.produto;

import br.com.cpcx.web2.pedro.domain.entity.Produto;
import br.com.cpcx.web2.pedro.domain.entity.Usuario;
import br.com.cpcx.web2.pedro.repository.ProdutoRepository;
import br.com.cpcx.web2.pedro.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class BuscarProdutoPorFiltro {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> filtrar(String descricao, BigDecimal precoMaximo, BigDecimal precoMinimo, Usuario usuario) {


        return produtoRepository.findAll().stream()
                .filter(produto -> {
                    if (nonNull(descricao)) {
                        return produto.getDescricao().equals(descricao);
                    }
                    return Boolean.TRUE;
                })

                .filter(produto -> {
                    if (nonNull(precoMinimo)) {
                        return produto.getPrecoVenda().compareTo(precoMinimo) >= 0;
                    }
                    return Boolean.TRUE;
                })

                .filter(produto -> {
                    if (nonNull(precoMaximo)) {
                        return produto.getPrecoVenda().compareTo(precoMaximo) <= 0;
                    }
                    return Boolean.TRUE;
                })

                .filter(produto -> {
                    if (nonNull(usuario)) {
                        return Integer.compare(DataUtils.calcularIdade(usuario.getPessoa().getDataNascimento()) , produto.getIdadePermitida()) >= 0;
                    }
                    return Boolean.TRUE;
                })
                .collect(Collectors.toList());
    }
}

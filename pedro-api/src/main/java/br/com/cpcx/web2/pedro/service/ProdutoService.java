package br.com.cpcx.web2.pedro.service;

import br.com.cpcx.web2.pedro.domain.entity.Produto;
import br.com.cpcx.web2.pedro.domain.entity.Usuario;
import br.com.cpcx.web2.pedro.domain.filtro.produto.BuscarProdutoPorFiltro;
import br.com.cpcx.web2.pedro.domain.pojo.ProdutoPOJO;
import br.com.cpcx.web2.pedro.domain.usecase.produto.BuscarProdutoPorIdConformeIdadeUseCase;
import br.com.cpcx.web2.pedro.domain.usecase.produto.BuscarTodosProdutosConformeIdadeUseCase;
import br.com.cpcx.web2.pedro.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private BuscarTodosProdutosConformeIdadeUseCase buscarTodosProdutosConformeIdadeUseCase;

    @Autowired
    private BuscarProdutoPorIdConformeIdadeUseCase buscarProdutoPorIdConformeIdadeUseCase;

    @Autowired
    private BuscarProdutoPorFiltro filtroProduto;

    public Object buscarPorId(Long idProduto, Long idPessoa) {
        return buscarProdutoPorIdConformeIdadeUseCase.executar(idProduto, idPessoa) ;
    }

    public Object salvar(ProdutoPOJO produtoPOJO) {
        Produto produto = ProdutoPOJO.gerarItemPedido(produtoPOJO);
        return produtoRepository.save(produto);
    }

    public Object alterar(ProdutoPOJO produtoPOJO) {
        Produto produto = ProdutoPOJO.gerarItemPedido(produtoPOJO);
        return  produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    public Object buscarTodosPorFiltro(String descricao, BigDecimal precoMaximo, BigDecimal precoMinimo, Usuario usuario) {
        return filtroProduto.filtrar(descricao, precoMaximo, precoMinimo, usuario);
    }
}

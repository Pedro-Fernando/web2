package br.com.cpcx.web2.pedro.domain.usecase.produto;

import br.com.cpcx.web2.pedro.repository.PessoaRepository;
import br.com.cpcx.web2.pedro.repository.ProdutoRepository;
import br.com.cpcx.web2.pedro.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BuscarTodosProdutosConformeIdadeUseCase {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Object executar(Long idPessoa){
        Integer idadeDaPessoa = buscarIdadeDaPessoaPorId(idPessoa);

        return produtoRepository.buscarTodosProdutosConformeAIdade(idadeDaPessoa);
    }

    private Integer buscarIdadeDaPessoaPorId(Long idPessoa) {
        LocalDate dataNascimentoDaPessoa = pessoaRepository.buscarDataNascomentoPorId(idPessoa);
        return  DataUtils.calcularIdade(dataNascimentoDaPessoa);
    }

}

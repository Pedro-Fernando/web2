package br.com.cpcx.web2.pedro.service;

import br.com.cpcx.web2.pedro.domain.enumeration.ETipoPessoa;
import br.com.cpcx.web2.pedro.domain.pojo.PessoaPOJO;
import br.com.cpcx.web2.pedro.domain.usecase.pessoa.ExcluirPessoaUseCase;
import br.com.cpcx.web2.pedro.domain.usecase.pessoa.SalvarPessoaUseCase;
import br.com.cpcx.web2.pedro.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private SalvarPessoaUseCase salvarPessoaUseCase;

    @Autowired
    private ExcluirPessoaUseCase excluirPessoaUseCase;

    public Object buscarTodos() {
        return pessoaRepository.findAll();
    }

    public Object buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public Object salvar(PessoaPOJO body) {
        return salvarPessoaUseCase.executar(body);
    }

    public Object alterar(PessoaPOJO body) {
        return salvarPessoaUseCase.executar(body);
    }

    public void deletar(Long id) {
        excluirPessoaUseCase.executar(id);
    }

    public Object buscarTodosPorTipo(ETipoPessoa tipo) {
        return pessoaRepository.findAllByTipo(tipo);
    }
}

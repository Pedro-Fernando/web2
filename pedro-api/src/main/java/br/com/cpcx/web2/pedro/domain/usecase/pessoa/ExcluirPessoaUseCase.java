package br.com.cpcx.web2.pedro.domain.usecase.pessoa;

import br.com.cpcx.web2.pedro.domain.exceptions.pessoa.ExcluirPessoaComDependentesException;
import br.com.cpcx.web2.pedro.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirPessoaUseCase {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void executar(Long idPessoaARemover){
        verificarSeExistemDependendesDaPessoaQueSeraRemovida(idPessoaARemover);
        removerPessoa(idPessoaARemover);
    }

    private void removerPessoa(Long idPessoaARemover) {
        pessoaRepository.deleteById(idPessoaARemover);
    }

    private void verificarSeExistemDependendesDaPessoaQueSeraRemovida(Long idPessoaARemover) {
        if (existeDependentes(idPessoaARemover)){
            throw new ExcluirPessoaComDependentesException("A pessoa é responsável por outra(s) pessoa(s) no sistema.");
        }
    }

    private boolean existeDependentes(Long idPessoaARemover) {
        return pessoaRepository.existsByIdResponsavel(idPessoaARemover);
    }
}

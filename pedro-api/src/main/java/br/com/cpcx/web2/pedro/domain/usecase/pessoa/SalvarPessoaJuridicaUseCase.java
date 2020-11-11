package br.com.cpcx.web2.pedro.domain.usecase.pessoa;

import br.com.cpcx.web2.pedro.domain.entity.Pessoa;
import br.com.cpcx.web2.pedro.domain.entity.PessoaJuridica;
import br.com.cpcx.web2.pedro.domain.pojo.PessoaPOJO;
import br.com.cpcx.web2.pedro.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalvarPessoaJuridicaUseCase {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public Pessoa executar(PessoaPOJO pessoaPOJO) {

        PessoaJuridica pessoaJuridica = PessoaPOJO.gerarPessoaJuridica(pessoaPOJO);

        validarCNPJ(pessoaJuridica);

        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    private void validarCNPJ(PessoaJuridica pessoaJuridica) {

    }
}

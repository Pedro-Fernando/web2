package br.com.cpcx.web2.pedro.domain.usecase.pessoa;

import br.com.cpcx.web2.pedro.domain.entity.Pessoa;
import br.com.cpcx.web2.pedro.domain.entity.PessoaFisica;
import br.com.cpcx.web2.pedro.domain.pojo.PessoaPOJO;
import br.com.cpcx.web2.pedro.domain.valueobject.pessoafisica.CPF;
import br.com.cpcx.web2.pedro.domain.valueobject.pessoafisica.RG;
import br.com.cpcx.web2.pedro.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalvarPessoaFisicaUseCase {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;


    public Pessoa executar(PessoaPOJO pessoaPOJO) {
        PessoaFisica pessoaFisica = PessoaPOJO.gerarPessoaFisica(pessoaPOJO);

        validarCPF(pessoaFisica);
        validarRG(pessoaFisica);

        return pessoaFisicaRepository.save(pessoaFisica);
    }

    private void validarRG(PessoaFisica pessoaFisica) {
        RG.validarRG(pessoaFisica.getRg());
    }

    private void validarCPF(PessoaFisica pessoaFisica) {
        CPF.validarCPF(pessoaFisica.getCpf());
    }
}

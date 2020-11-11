package br.com.cpcx.web2.pedro.domain.usecase.pessoa;

import br.com.cpcx.web2.pedro.domain.entity.Pessoa;
import br.com.cpcx.web2.pedro.domain.exceptions.pessoa.NaoExisteResponsavelException;
import br.com.cpcx.web2.pedro.domain.exceptions.pessoa.PessoaSemDataDeNascimentoException;
import br.com.cpcx.web2.pedro.domain.exceptions.pessoa.ResponsavelNaoEhMaiorDeIdadeException;
import br.com.cpcx.web2.pedro.domain.exceptions.pessoa.TipoPessoaInvalidaException;
import br.com.cpcx.web2.pedro.domain.pojo.PessoaPOJO;
import br.com.cpcx.web2.pedro.repository.PessoaRepository;
import br.com.cpcx.web2.pedro.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static br.com.cpcx.web2.pedro.utils.TipoPessoaUtils.isPessoaFisica;
import static java.util.Objects.isNull;

@Service
public class SalvarPessoaUseCase {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private SalvarPessoaFisicaUseCase salvarPessoaFisicaUseCase;

    @Autowired
    private SalvarPessoaJuridicaUseCase salvarPessoaJuridicaUseCase;

    public Pessoa executar(PessoaPOJO pessoaPOJO) {
        validarDadosBasicosDePessoa(pessoaPOJO);
        Pessoa pessoaSalva = salvarPessoa(pessoaPOJO);

        return pessoaSalva;
    }

    private Pessoa salvarPessoa(PessoaPOJO pessoaPOJO) {
        return decidirQualTipoDePessoaSalvar(pessoaPOJO);
    }

    private void validarDadosBasicosDePessoa(PessoaPOJO pessoaPOJO) {
        validarExistenciaDoParametro(pessoaPOJO);
        validarExistenciaDeTipoPessoa(pessoaPOJO);
        validarIdade(pessoaPOJO);
    }

    private void validarExistenciaDeTipoPessoa(PessoaPOJO pessoaPOJO) {
        if (isNull(pessoaPOJO.getTipoPessoa())) {
            throw new TipoPessoaInvalidaException("Para efetuar o cadastro deve ser informado o tipo da pessoa.");
        }
    }

    private void validarIdade(PessoaPOJO pessoaPOJO) {
        validarExistenciaDataDeNascimento(pessoaPOJO);


        if (isMenorDeIdade(pessoaPOJO)) {
            validarResponsavelParaMenoresDeIdade(pessoaPOJO);
        }else {
            validarResponsavelParaMaioresDeIdade(pessoaPOJO);
        }
    }

    private void validarResponsavelParaMaioresDeIdade(PessoaPOJO pessoaPOJO) {
        if (isNull(pessoaPOJO.getIdResponsavel())){
            return;
        }

        if (isNaoExisteReponsavel(pessoaPOJO)) {
            throw new NaoExisteResponsavelException("Para pessoa ser cadastrada precisa já estar cadastrado no sistema.");
        }

    }

    private void validarResponsavelParaMenoresDeIdade(PessoaPOJO pessoaPOJO) {

        if (isNull(pessoaPOJO.getIdResponsavel())) {
            throw new NaoExisteResponsavelException("Para pessoa ser cadastrada precisa possuir um responsável");
        }

        if (isNaoExisteReponsavel(pessoaPOJO)) {
            throw new NaoExisteResponsavelException("Para pessoa ser cadastrada precisa já estar cadastrado no sistema.");
        }

        if (isResponsavelNaoEhMaiorDeIdade(pessoaPOJO)) {
            throw new ResponsavelNaoEhMaiorDeIdadeException();
        }

    }

    private boolean isResponsavelNaoEhMaiorDeIdade(PessoaPOJO pessoaPOJO) {
        LocalDate dataAniversarioDoResponsavel = pessoaRepository.buscarDataNascomentoPorId(pessoaPOJO.getIdResponsavel());
        Integer idadeDoResponsavel = DataUtils.calcularIdade(dataAniversarioDoResponsavel);

        return idadeDoResponsavel < 18;
    }

    private boolean isNaoExisteReponsavel(PessoaPOJO pessoaPOJO) {
        return !isExisteReponsavel(pessoaPOJO);
    }

    private boolean isExisteReponsavel(PessoaPOJO pessoaPOJO) {
        return pessoaRepository.existsById(pessoaPOJO.getIdResponsavel());
    }

    private boolean isMenorDeIdade(PessoaPOJO pessoaPOJO) {
        return !DataUtils.isMaiorIdade(pessoaPOJO.getDataNascimento());
    }

    private void validarExistenciaDataDeNascimento(PessoaPOJO pessoaPOJO) {
        if (isNull(pessoaPOJO.getDataNascimento())) {
            throw new PessoaSemDataDeNascimentoException();
        }
    }

    private void validarExistenciaDoParametro(PessoaPOJO pessoaPOJO) {
        if (isNull(pessoaPOJO)) {
            throw new IllegalArgumentException("Deve existir Pessoa");
        }
    }

    private Pessoa decidirQualTipoDePessoaSalvar(PessoaPOJO pessoaPOJO) {
        if (isPessoaFisica(pessoaPOJO)) {
            return salvarPessoaFisicaUseCase.executar(pessoaPOJO);
        }else{
            return salvarPessoaJuridicaUseCase.executar(pessoaPOJO);
        }
    }

}

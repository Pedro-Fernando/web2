package br.com.cpcx.web2.pedro.domain.filtro.pessoa;

import br.com.cpcx.web2.pedro.domain.entity.Pessoa;
import br.com.cpcx.web2.pedro.domain.enumeration.ESituacaoPessoa;
import br.com.cpcx.web2.pedro.domain.enumeration.ETipoPessoa;
import br.com.cpcx.web2.pedro.repository.PessoaRepository;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class BuscarPessoaPorFiltro {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> filtrar(ESituacaoPessoa situacao, Long idResponsavel, String nomeResponsavel, ETipoPessoa tipo) {

        Pessoa responsavel = pessoaRepository.findByNome(nomeResponsavel);

        return pessoaRepository.findAll().stream()

                .filter(pessoa -> {
                    if (nonNull(idResponsavel)) {
                        return isNull(pessoa.getIdResponsavel()) ?  Boolean.FALSE : pessoa.getIdResponsavel().equals(idResponsavel);
                    }
                    return Boolean.TRUE;
                })

                .filter(pessoa -> {
                    if (nonNull(tipo)) {
                        return isNull(pessoa.getTipo()) ?  Boolean.FALSE :  pessoa.getTipo().equals(tipo);
                    }
                    return Boolean.TRUE;
                })

                .filter(pessoa -> {
                    if (nonNull(situacao)) {
                        return isNull(pessoa.getSituacao()) ?  Boolean.FALSE : pessoa.getSituacao().equals(situacao);
                    }
                    return Boolean.TRUE;
                })

                .filter(pessoa -> {
                    if (nonNull(responsavel)) {
                        return nonNull(pessoa.getIdResponsavel()) ? pessoa.getIdResponsavel().equals(responsavel.getId()) : Boolean.FALSE;
                    }
                    return Boolean.TRUE;
                })
                .collect(Collectors.toList());
    }

}
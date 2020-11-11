package br.com.cpcx.web2.pedro.domain.pojo;

import br.com.cpcx.web2.pedro.domain.entity.PessoaFisica;
import br.com.cpcx.web2.pedro.domain.entity.PessoaJuridica;
import br.com.cpcx.web2.pedro.domain.enumeration.ESituacaoPessoa;
import br.com.cpcx.web2.pedro.domain.enumeration.ETipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaPOJO {
    private Long id;
    private Long idResponsavel;
    private ETipoPessoa tipoPessoa;
    private ESituacaoPessoa situacaoPessoa;
    private String nome;
    private String apelido;
    private String cpf;
    private String cnpj;
    private String rg;
    private LocalDate dataNascimento;

    public static PessoaFisica gerarPessoaFisica(PessoaPOJO pojo) {
        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setId(pojo.getId());
        pessoaFisica.setNome(pojo.getNome());
        pessoaFisica.setApelido(pojo.getApelido());
        pessoaFisica.setIdResponsavel(pojo.getIdResponsavel());
        pessoaFisica.setTipo(pojo.getTipoPessoa());
        pessoaFisica.setSituacao(pojo.getSituacaoPessoa());
        pessoaFisica.setDataNascimento(pojo.getDataNascimento());
        pessoaFisica.setCpf(pojo.getCpf());
        pessoaFisica.setRg(pojo.getRg());

        return pessoaFisica;
    }

    public static PessoaJuridica gerarPessoaJuridica(PessoaPOJO pojo) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setId(pojo.getId());
        pessoaJuridica.setApelido(pojo.getApelido());
        pessoaJuridica.setNome(pojo.getNome());
        pessoaJuridica.setIdResponsavel(pojo.getIdResponsavel());
        pessoaJuridica.setTipo(pojo.getTipoPessoa());
        pessoaJuridica.setSituacao(pojo.getSituacaoPessoa());
        pessoaJuridica.setDataNascimento(pojo.getDataNascimento());
        pessoaJuridica.setCnpj(pojo.getCnpj());

        return pessoaJuridica;
    }
}

package br.com.cpcx.web2.pedro.utils;

import br.com.cpcx.web2.pedro.domain.pojo.PessoaPOJO;
import br.com.cpcx.web2.pedro.domain.entity.Pessoa;
import br.com.cpcx.web2.pedro.domain.enumeration.ETipoPessoa;

public class TipoPessoaUtils {

    private TipoPessoaUtils(){}

    public static boolean isPessoaFisica(PessoaPOJO pessoaPOJO){
        return ETipoPessoa.FISICA.equals(pessoaPOJO.getTipoPessoa());
    }

    public static boolean isPessoaJuridica(PessoaPOJO pessoaPOJO){
        return ETipoPessoa.JURIDICA.equals(pessoaPOJO.getTipoPessoa());
    }

    public static boolean isPessoaFisica(Pessoa pessoa){
        return ETipoPessoa.FISICA.equals(pessoa.getTipo());
    }

    public static boolean isPessoaJuridica(Pessoa pessoa){
        return ETipoPessoa.JURIDICA.equals(pessoa.getTipo());
    }

}

package br.com.cpcx.web2.pedro.utils;

import br.com.cpcx.web2.pedro.domain.pojo.PessoaPOJO;
import br.com.cpcx.web2.pedro.domain.entity.PessoaFisica;
import br.com.cpcx.web2.pedro.domain.entity.PessoaJuridica;
import br.com.cpcx.web2.pedro.domain.enumeration.ESituacaoPessoa;

public class SituacaoPessoaUtils {

    private SituacaoPessoaUtils(){
    }

    public static boolean isAtivo(PessoaPOJO pessoaPOJO){
        return ESituacaoPessoa.ATIVO.equals(pessoaPOJO.getSituacaoPessoa());
    }

    public static boolean isAtivo(PessoaFisica pessoaFisica){
        return ESituacaoPessoa.ATIVO.equals(pessoaFisica.getSituacao());
    }

    public static boolean isAtivo(PessoaJuridica pessoaJuridica){
        return ESituacaoPessoa.ATIVO.equals(pessoaJuridica.getSituacao());
    }

    public static boolean isInativo(PessoaPOJO pessoaPOJO){
        return ESituacaoPessoa.ATIVO.equals(pessoaPOJO.getSituacaoPessoa());
    }

    public static boolean isInativo(PessoaFisica pessoaFisica){
        return ESituacaoPessoa.ATIVO.equals(pessoaFisica.getSituacao());
    }

    public static boolean isInativo(PessoaJuridica pessoaJuridica){
        return ESituacaoPessoa.ATIVO.equals(pessoaJuridica.getSituacao());
    }
}

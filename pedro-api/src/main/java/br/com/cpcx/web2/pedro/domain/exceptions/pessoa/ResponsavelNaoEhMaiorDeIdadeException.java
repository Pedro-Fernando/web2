package br.com.cpcx.web2.pedro.domain.exceptions.pessoa;

public class ResponsavelNaoEhMaiorDeIdadeException extends RuntimeException {
    public ResponsavelNaoEhMaiorDeIdadeException() {
        super("Para pessoa ser cadastrada precisa possuir um Responsável maior de idade.");
    }
}

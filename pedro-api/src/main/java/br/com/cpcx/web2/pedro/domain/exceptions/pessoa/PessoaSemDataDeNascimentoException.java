package br.com.cpcx.web2.pedro.domain.exceptions.pessoa;

public class PessoaSemDataDeNascimentoException extends RuntimeException {
    public PessoaSemDataDeNascimentoException() {
        super("Para pessoa ser cadastrada precisa possuir data de nascimento.");
    }
}

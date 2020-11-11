package br.com.cpcx.web2.pedro.domain.exceptions.usuario;

public class UsuarioNaoFoiCadastradoAnteriormenteComoPessoaException extends RuntimeException {
    public UsuarioNaoFoiCadastradoAnteriormenteComoPessoaException() {
        super("O Usuário deve possuir um cadastro como pessoa no Sistema.");
    }
}

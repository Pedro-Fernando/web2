package br.com.cpcx.web2.pedro.domain.exceptions.usuario;

public class ConexaoInvalidaException extends RuntimeException {
    public ConexaoInvalidaException() {
        super("Login ou Senha Inválidos");
    }
}

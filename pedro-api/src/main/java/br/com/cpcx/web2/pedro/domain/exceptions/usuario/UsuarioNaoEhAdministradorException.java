package br.com.cpcx.web2.pedro.domain.exceptions.usuario;

public class UsuarioNaoEhAdministradorException extends RuntimeException {
    public UsuarioNaoEhAdministradorException() {
        super("O usuário não é Administrador sistema.");
    }
}

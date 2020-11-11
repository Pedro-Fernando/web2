package br.com.cpcx.web2.pedro.utils;

import br.com.cpcx.web2.pedro.domain.exceptions.usuario.ConexaoInvalidaException;
import br.com.cpcx.web2.pedro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidarUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void isLoginESenhaValidos(String login, String senha) throws ConexaoInvalidaException{
        if (usuarioRepository.existsUsuarioByLoginAndSenha(login,senha)){
            return;
        }

        throw new ConexaoInvalidaException();
    }
}

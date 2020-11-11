package br.com.cpcx.web2.pedro.domain.usecase.usuario;

import br.com.cpcx.web2.pedro.domain.entity.Usuario;
import br.com.cpcx.web2.pedro.domain.exceptions.usuario.ConexaoInvalidaException;
import br.com.cpcx.web2.pedro.domain.exceptions.usuario.UsuarioNaoEhAdministradorException;
import br.com.cpcx.web2.pedro.domain.exceptions.usuario.UsuarioNaoFoiCadastradoAnteriormenteComoPessoaException;
import br.com.cpcx.web2.pedro.domain.pojo.UsuarioPOJO;
import br.com.cpcx.web2.pedro.repository.PessoaRepository;
import br.com.cpcx.web2.pedro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class CadastrarUsuarioUseCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Usuario executar(UsuarioPOJO usuarioPOJO, String login, String senha){
//        validarSeUsuarioEhAdministrador(login, senha);
        verificarSeUsuarioJaFoiCadastroComoPessoaNoSistema(usuarioPOJO);

        return cadastrarNovoUsuario(usuarioPOJO);
    }

    private Usuario cadastrarNovoUsuario(UsuarioPOJO usuarioPOJO) {
        Usuario usuario = UsuarioPOJO.gerarUsuario(usuarioPOJO);
        return usuarioRepository.save(usuario);
    }

    private void validarSeUsuarioEhAdministrador(String login, String senha) throws ConexaoInvalidaException, UsuarioNaoEhAdministradorException {
//todo ver se é necessário
        Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);

        if (isNull(usuario)){
            throw new ConexaoInvalidaException();
        }

        if (!usuario.isAdministrador()){
            throw new UsuarioNaoEhAdministradorException();
        }
    }

    private void verificarSeUsuarioJaFoiCadastroComoPessoaNoSistema(UsuarioPOJO usuarioPOJO) throws UsuarioNaoFoiCadastradoAnteriormenteComoPessoaException{
        if (!pessoaRepository.existsById(usuarioPOJO.getPessoa().getId())){
            throw new UsuarioNaoFoiCadastradoAnteriormenteComoPessoaException();
        }
    }
}

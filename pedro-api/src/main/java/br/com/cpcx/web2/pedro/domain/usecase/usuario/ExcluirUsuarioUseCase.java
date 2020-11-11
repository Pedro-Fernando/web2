package br.com.cpcx.web2.pedro.domain.usecase.usuario;

import br.com.cpcx.web2.pedro.domain.exceptions.usuario.ConexaoInvalidaException;
import br.com.cpcx.web2.pedro.domain.exceptions.usuario.UsuarioNaoEhAdministradorException;
import br.com.cpcx.web2.pedro.domain.exceptions.usuario.UsuarioNaoFoiCadastradoAnteriormenteComoPessoaException;
import br.com.cpcx.web2.pedro.repository.PessoaRepository;
import br.com.cpcx.web2.pedro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirUsuarioUseCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public void executar(Long idUsuario, String login, String senha) {
        validarLoginESenha(login, senha);
        verificarSeUsuarioJaFoiCadastroComoPessoaNoSistema(idUsuario);
        validarSeUsuarioEhAdministrador(idUsuario);

        excluir(idUsuario);
    }

    private void excluir(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    private void validarSeUsuarioEhAdministrador(Long id) {
        if (!usuarioRepository.isAdministrador(id)) {
            throw new UsuarioNaoEhAdministradorException();
        }
    }

    private void verificarSeUsuarioJaFoiCadastroComoPessoaNoSistema(Long idUsuario) {
        if (!pessoaRepository.existsById(idUsuario)) {
            throw new UsuarioNaoFoiCadastradoAnteriormenteComoPessoaException();
        }
    }

    private void validarLoginESenha(String login, String senha) {
        if (conexaoInvalida(login, senha)) {
            throw new ConexaoInvalidaException();
        }
    }

    private boolean conexaoInvalida(String login, String senha) {
        return !usuarioRepository.existsUsuarioByLoginAndSenha(login, senha);
    }
}

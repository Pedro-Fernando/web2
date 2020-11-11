package br.com.cpcx.web2.pedro.service;

import br.com.cpcx.web2.pedro.domain.pojo.UsuarioPOJO;
import br.com.cpcx.web2.pedro.domain.usecase.usuario.CadastrarUsuarioUseCase;
import br.com.cpcx.web2.pedro.domain.usecase.usuario.ExcluirUsuarioUseCase;
import br.com.cpcx.web2.pedro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    @Autowired
    private ExcluirUsuarioUseCase excluirUsuarioUseCase;

    public Object buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Object buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Object salvar(UsuarioPOJO body, String login, String senha) {
        return cadastrarUsuarioUseCase.executar(body, login, senha);
    }

    public Object alterar(UsuarioPOJO body, String login, String senha) {
        return cadastrarUsuarioUseCase.executar(body, login, senha);
    }

    public void deletar(Long id, String login, String senha) {
        excluirUsuarioUseCase.executar(id, login, senha);
    }
}

package br.com.cpcx.web2.pedro.domain.pojo;

import br.com.cpcx.web2.pedro.domain.entity.Pessoa;
import br.com.cpcx.web2.pedro.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPOJO {
    private Long id;
    private String login;
    private String senha;
    private boolean isAdministrador;
    private Pessoa pessoa;

    public static Usuario gerarUsuario(UsuarioPOJO pojo){
        Usuario usuario = new Usuario();

        usuario.setId(pojo.getId());
        usuario.setLogin(pojo.getLogin());
        usuario.setSenha(pojo.getSenha());
        usuario.setAdministrador(pojo.isAdministrador());
        usuario.setPessoa(pojo.getPessoa());

       return usuario;
    }
}

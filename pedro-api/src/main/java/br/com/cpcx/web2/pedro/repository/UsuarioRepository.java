package br.com.cpcx.web2.pedro.repository;

import br.com.cpcx.web2.pedro.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsUsuarioByLoginAndSenha(String login, String senha);

    @Query("select us.isAdministrador from Usuario us where us.id = :idUsuario")
    boolean isAdministrador(@Param("idUsuario") Long idUsuario);

    Usuario findByLoginAndSenha(String login, String senha);
}

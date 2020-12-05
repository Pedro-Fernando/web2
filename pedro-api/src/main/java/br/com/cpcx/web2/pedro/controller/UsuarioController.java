package br.com.cpcx.web2.pedro.controller;

import br.com.cpcx.web2.pedro.domain.pojo.UsuarioPOJO;
import br.com.cpcx.web2.pedro.repository.UsuarioRepository;
import br.com.cpcx.web2.pedro.service.UsuarioService;
import br.com.cpcx.web2.pedro.utils.ValidarUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ValidarUsuario validarUsuario;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodos(@RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {

        validarUsuario.isLoginESenhaValidos(login, senha);
        return new ResponseEntity(usuarioService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/autenticar")
    @ResponseBody
    public Object isUsuarioAutorizado(@RequestHeader("login") String login, @RequestHeader("senha") String senha) {
        HashMap<String, Boolean> autenticacao = new HashMap<>();
        autenticacao.put("isUsuarioAutenticado", usuarioRepository.existsUsuarioByLoginAndSenha(login,senha));
        return autenticacao;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {

        validarUsuario.isLoginESenhaValidos(login, senha);
        return new ResponseEntity(usuarioService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody UsuarioPOJO body,
                                    @RequestHeader("login") String login,
                                    @RequestHeader("senha") String senha) {

        validarUsuario.isLoginESenhaValidos(login, senha);
        return new ResponseEntity(usuarioService.salvar(body, login, senha), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody UsuarioPOJO body,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {

        validarUsuario.isLoginESenhaValidos(login, senha);
        return new ResponseEntity(usuarioService.alterar(body, login, senha), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {

        validarUsuario.isLoginESenhaValidos(login, senha);
        usuarioService.deletar(id, login, senha);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

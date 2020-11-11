package br.com.cpcx.web2.pedro.controller;

import br.com.cpcx.web2.pedro.domain.entity.Usuario;
import br.com.cpcx.web2.pedro.domain.pojo.ProdutoPOJO;
import br.com.cpcx.web2.pedro.repository.UsuarioRepository;
import br.com.cpcx.web2.pedro.service.ProdutoService;
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

@Controller
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ValidarUsuario validarUsuario;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @ResponseBody
//    @IsUsuarioAutorizado
    public ResponseEntity<?> buscarTodos(@RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {
        validarUsuario.isLoginESenhaValidos(login, senha);
        Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);
        return new ResponseEntity(produtoService.buscarTodos(usuario.getPessoa().getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
//    @IsUsuarioAutorizado
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idProduto,
                                         @RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {
        validarUsuario.isLoginESenhaValidos(login, senha);
        Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);
        return new ResponseEntity(produtoService.buscarPorId(idProduto, usuario.getPessoa().getId()), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
//    @IsUsuarioAutorizado
    public ResponseEntity<?> salvar(@RequestBody ProdutoPOJO body,
                                    @RequestHeader("login") String login,
                                    @RequestHeader("senha") String senha) {
        validarUsuario.isLoginESenhaValidos(login, senha);
        return new ResponseEntity(produtoService.salvar(body), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ResponseBody
//    @IsUsuarioAutorizado
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody ProdutoPOJO body,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        validarUsuario.isLoginESenhaValidos(login, senha);
        return new ResponseEntity(produtoService.alterar(body), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    @ResponseBody
//    @IsUsuarioAutorizado
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        validarUsuario.isLoginESenhaValidos(login, senha);
        produtoService.deletar(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

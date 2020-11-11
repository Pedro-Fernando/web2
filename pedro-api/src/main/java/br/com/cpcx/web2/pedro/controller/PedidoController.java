package br.com.cpcx.web2.pedro.controller;

import br.com.cpcx.web2.pedro.domain.pojo.PedidoPOJO;
import br.com.cpcx.web2.pedro.service.PedidoService;
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
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ValidarUsuario validarUsuario;

    @GetMapping
    @ResponseBody
//    @IsUsuarioAutorizado
    public ResponseEntity<?> buscarTodos(@RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {
        validarUsuario.isLoginESenhaValidos(login, senha);
        return new ResponseEntity(pedidoService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
//    @IsUsuarioAutorizado
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {
        validarUsuario.isLoginESenhaValidos(login, senha);
        return new ResponseEntity(pedidoService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
//    @IsUsuarioAutorizado
    public ResponseEntity<?> salvar(@RequestBody PedidoPOJO body,
                                    @RequestHeader("login") String login,
                                    @RequestHeader("senha") String senha) {
        validarUsuario.isLoginESenhaValidos(login, senha);
        return new ResponseEntity(pedidoService.salvar(body), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ResponseBody
//    @IsUsuarioAutorizado
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody PedidoPOJO body,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        validarUsuario.isLoginESenhaValidos(login, senha);
        return new ResponseEntity(pedidoService.alterar(body), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    @ResponseBody
//    @IsUsuarioAutorizado
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        validarUsuario.isLoginESenhaValidos(login, senha);
        pedidoService.deletar(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

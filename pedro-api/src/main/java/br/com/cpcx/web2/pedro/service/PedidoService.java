package br.com.cpcx.web2.pedro.service;

import br.com.cpcx.web2.pedro.domain.entity.Pedido;
import br.com.cpcx.web2.pedro.domain.pojo.PedidoPOJO;
import br.com.cpcx.web2.pedro.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Object buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Object buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Object salvar(PedidoPOJO body) {
        Pedido pedido = PedidoPOJO.gerarPedido(body);
        return pedidoRepository.save(pedido);
    }

    public Object alterar(PedidoPOJO body) {
        Pedido pedido = PedidoPOJO.gerarPedido(body);
        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}

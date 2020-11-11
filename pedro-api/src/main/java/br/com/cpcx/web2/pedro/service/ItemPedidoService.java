package br.com.cpcx.web2.pedro.service;

import br.com.cpcx.web2.pedro.domain.entity.ItemPedido;
import br.com.cpcx.web2.pedro.domain.pojo.ItemPedidoPOJO;
import br.com.cpcx.web2.pedro.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Object buscarTodos() {
        return itemPedidoRepository.findAll();
    }

    public Object buscarPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    public Object salvar(ItemPedidoPOJO body) {
       ItemPedido itemPedido = ItemPedidoPOJO.gerarItemPedido(body);
        return itemPedidoRepository.save(itemPedido);
    }

    public Object alterar(ItemPedidoPOJO body) {
        ItemPedido itemPedido = ItemPedidoPOJO.gerarItemPedido(body);
        return itemPedidoRepository.save(itemPedido);
    }

    public void deletar(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}

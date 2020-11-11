package br.com.cpcx.web2.pedro.repository;

import br.com.cpcx.web2.pedro.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}

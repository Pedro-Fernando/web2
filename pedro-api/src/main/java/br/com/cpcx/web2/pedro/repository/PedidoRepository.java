package br.com.cpcx.web2.pedro.repository;

import br.com.cpcx.web2.pedro.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}

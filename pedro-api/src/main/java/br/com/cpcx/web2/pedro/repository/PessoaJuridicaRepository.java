package br.com.cpcx.web2.pedro.repository;

import br.com.cpcx.web2.pedro.domain.entity.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
}

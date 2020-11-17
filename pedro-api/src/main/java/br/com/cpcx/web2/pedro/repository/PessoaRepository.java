package br.com.cpcx.web2.pedro.repository;

import br.com.cpcx.web2.pedro.domain.entity.Pessoa;
import br.com.cpcx.web2.pedro.domain.enumeration.ETipoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("select p.dataNascimento from Pessoa p where p.id = :idPessoa")
    LocalDate buscarDataNascomentoPorId(@Param("idPessoa") Long idPessoa);

    boolean existsByIdResponsavel(Long idPessoaARemover);

    List<Pessoa> findAllByTipo(ETipoPessoa tipo);

    Pessoa findByNome(String nomeResponsavel);
}

package br.com.cpcx.web2.pedro.repository;

import br.com.cpcx.web2.pedro.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select pr from Produto pr where pr.idadePermitida <= :idadeDaPessoa")
    List<Produto> buscarTodosProdutosConformeAIdade(@Param("idadeDaPessoa") Integer idadeDaPessoa);

    @Query("select pr from Produto pr where pr.id = :idProduto and pr.idadePermitida <= :idadeDaPessoa")
    Produto buscarProdutoProIdConformeAIdade(@Param("idProduto")Long idProduto,@Param("idadeDaPessoa") Integer idadeDaPessoa);
}

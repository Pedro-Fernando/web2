package br.com.cpcx.web2.pedro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Long id;

    @Column(name = "PRO_DESCRICAO")
    private String descricao;

    @Column(name = "PRO_QUANTIDADE_ESTIQUE")
    private Integer quantidadeEstoque;

    @Column(name = "PRO_IDADE_PERMITIDA")
    private Integer idadePermitida;

    @Column(name = "PRO_PRECO_COMPRA")
    private BigDecimal precoCompra;

    @Column(name = "PRO_PRECO_VENDA")
    private BigDecimal precoVenda;

}

package br.com.cpcx.web2.pedro.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PED_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PES_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Pessoa pessoa;

    @Column(name = "PED_DATECOMPRA")
    private LocalDate dataCompra;

    @Column(name = "PED_DATEENTREGA")
    private LocalDate dataEntrega;

    @Column(name = "PED_DESCONTO")
    private BigDecimal percentualDesconto;

}

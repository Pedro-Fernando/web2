package br.com.cpcx.web2.pedro.domain.entity;

import br.com.cpcx.web2.pedro.domain.enumeration.ESituacaoPessoa;
import br.com.cpcx.web2.pedro.domain.enumeration.ETipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PESSOA")
@DiscriminatorColumn(name="PES_TIPO", length = 20)
@DiscriminatorValue("PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @Column(name = "PES_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PES_ID_RESPONSAVEL")
    private Long idResponsavel;

    @Enumerated(EnumType.STRING)
    @Column(name = "PES_TIPO", insertable = false, updatable = false)
    private ETipoPessoa tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "PES_SITUACAO")
    private ESituacaoPessoa situacao;

    @Column(name = "PES_NOME")
    private String nome;

    @Column(name = "PES_APELIDO")
    private String apelido;

    @Column(name = "PES_DATA_NASCIMENTO")
    private LocalDate dataNascimento;

}

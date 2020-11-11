package br.com.cpcx.web2.pedro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PESSOA_FISICA")
@DiscriminatorValue("FISICA")
@PrimaryKeyJoinColumn(name = "PES_ID")
public class PessoaFisica  extends Pessoa {

    @NotNull(message = "RG Não pode ser Nulo")
    @Column(name = "PF_RG")
    private String rg;

    @NotNull(message = "CPF Não pode ser Nulo")
    @Column(name = "PF_CPF")
    private String cpf;

}

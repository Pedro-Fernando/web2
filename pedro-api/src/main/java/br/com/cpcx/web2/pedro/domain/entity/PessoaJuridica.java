package br.com.cpcx.web2.pedro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PESSOA_JURIDICA")
@DiscriminatorValue("JURIDICA")
@PrimaryKeyJoinColumn(name = "PES_ID")
public class PessoaJuridica extends Pessoa{

    @Column(name = "PJ_CNPJ")
    private String cnpj;

}

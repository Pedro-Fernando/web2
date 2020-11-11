package br.com.cpcx.web2.pedro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IP_ID")
    private Long id;

    @Column(name = "US_IS_ADMINISTRADOR")
    private boolean isAdministrador;

    @Column(name = "US_LOGIN")
    private String login;

    @Column(name = "US_SENHA")
    private String senha;

    @OneToOne
    @JoinColumn(name="PES_ID")
    private Pessoa pessoa;
}

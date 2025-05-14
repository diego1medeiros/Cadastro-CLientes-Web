package com.cadastroclientes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa um funcionário no sistema.
 * Contém informações de login, senha e nome.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    /**
     * Identificador único do funcionário.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nome de login do funcionário (usuário).
     */
    private String login;

    /**
     * Senha do funcionário para autenticação.
     */
    private String senha;

    /**
     * Nome completo do funcionário.
     */
    private String nomeFuncionario;
}

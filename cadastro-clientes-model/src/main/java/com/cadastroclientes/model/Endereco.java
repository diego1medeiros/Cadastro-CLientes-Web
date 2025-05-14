package com.cadastroclientes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa um endereço no sistema.
 * Cada endereço está vinculado a um cliente.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

    /**
     * Identificador único do endereço.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nome da rua ou logradouro.
     */
    private String rua;

    /**
     * Nome do bairro.
     */
    private String bairro;

    /**
     * Nome da cidade.
     */
    private String cidade;

    /**
     * Sigla do estado (UF).
     */
    private String estado;

    /**
     * Número do imóvel (residência, comércio, etc.).
     */
    private String numero;

    /**
     * Código de Endereçamento Postal (CEP).
     */
    private String cep;

    /**
     * Cliente associado a este endereço.
     * Relacionamento muitos-para-um.
     */
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente = new Cliente();
}

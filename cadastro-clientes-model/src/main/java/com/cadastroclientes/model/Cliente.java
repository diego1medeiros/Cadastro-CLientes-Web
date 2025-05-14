package com.cadastroclientes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa um cliente no sistema.
 * Contém informações como nome, email, caminho da imagem e uma lista de endereços associados.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    /**
     * Identificador único do cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nome do cliente.
     */
    private String nome;

    /**
     * Email do cliente. Deve ser único.
     */
    @Column(unique = true)
    private String email;

    /**
     * Caminho da imagem (logotipo ou avatar) do cliente, armazenado como String.
     */
    @Column(name = "caminho_imagem")
    private String caminhoImagem;

    /**
     * Lista de endereços associados ao cliente.
     * Relacionamento de um-para-muitos com a entidade Endereco.
     * Cascade ALL garante que persistência/remoção do cliente afete também os endereços.
     * OrphanRemoval true garante que endereços órfãos sejam removidos automaticamente.
     */
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, orphanRemoval = false)
    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();
}

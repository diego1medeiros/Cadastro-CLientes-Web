package com.cadastroclientes.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.cadastroclientes.model.Endereco;

@Repository
public class EnderecoDao {

	@PersistenceContext
	private EntityManager manager;

	public EnderecoDao() {
		super();
	}

	/**
	 * Método para cadastrar um novo endereço no banco de dados.
	 * 
	 * @param endereco - Objeto contendo os dados do endereço a ser cadastrado.
	 * @return O endereço cadastrado com os dados persistidos.
	 */
	@Transactional
	public Endereco cadastrarEndereco(Endereco endereco) {
		manager.persist(endereco);
		return endereco;
	}

	/**
	 * Método para listar todos os endereços cadastrados no banco de dados.
	 * 
	 * @return Lista de endereços cadastrados.
	 */
	@Transactional
	public List<Endereco> listaDadosDosEnderecos() {
		List<Endereco> listaEnderecos = null;
		listaEnderecos = manager.createQuery("select e from Endereco e", Endereco.class).getResultList();
		return listaEnderecos;
	}

	/**
	 * Método para remover um endereço do banco de dados.
	 * 
	 * @param id - ID do endereço a ser removido.
	 * @return O endereço removido.
	 */
	@Transactional
	public Endereco removerEndereco(Long id) {
		Endereco endereco = manager.find(Endereco.class, id);
		manager.remove(endereco);
		return endereco;
	}

	/**
	 * Método para editar os dados de um endereço existente no banco de dados.
	 * 
	 * @param endereco - Objeto contendo os dados atualizados do endereço.
	 * @return O endereço atualizado.
	 */
	@Transactional
	public Endereco editarEndereco(Endereco endereco) {
		manager.merge(endereco);
		return endereco;
	}

}

package com.cadastroclientes.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.cadastroclientes.model.Cliente;
import javax.persistence.Query;

@Repository
public class ClienteDao {

	@PersistenceContext
	private EntityManager manager;

	public ClienteDao() {
		super();
	}

	/**
	 * Método para cadastrar um novo cliente no banco de dados utilizando uma stored
	 * procedure. Cria uma query nativa para chamar a stored procedure de cadastro
	 * de cliente
	 * 
	 * @param cliente - Objeto contendo os dados do cliente a ser cadastrado.
	 * @return O cliente cadastrado com os dados persistidos.
	 */
	@Transactional
	public Cliente cadastrarClienteComFunction(Cliente cliente) {
		Query query = manager.createNativeQuery("EXEC cadastrar_cliente :nome, :email, :caminhoImagem", Cliente.class);
		query.setParameter("nome", cliente.getNome());
		query.setParameter("email", cliente.getEmail());
		query.setParameter("caminhoImagem", cliente.getCaminhoImagem());
		return (Cliente) query.getSingleResult();
	}

	/**
	 * Método para listar todos os clientes cadastrados no banco de dados.
	 * 
	 * @return Lista de clientes cadastrados.
	 */
	@Transactional
	public List<Cliente> listaDadosDosClientes() {
		List<Cliente> listaClientes = null;
		listaClientes = manager.createQuery("select c from Cliente c", Cliente.class).getResultList();
		return listaClientes;
	}

	/**
	 * Método para remover um cliente do banco de dados.
	 * 
	 * @param id - ID do cliente a ser removido.
	 * @return O cliente removido.
	 */
	@Transactional
	public Cliente removerCliente(Long id) {
		Cliente cliente = manager.find(Cliente.class, id);
		manager.remove(cliente);
		return cliente;
	}

	/**
	 * Método para editar os dados de um cliente existente no banco de dados.
	 * 
	 * @param cliente - Objeto contendo os dados atualizados do cliente.
	 * @return O cliente atualizado.
	 */
	@Transactional
	public Cliente editarCliente(Cliente cliente) {
		manager.merge(cliente);
		return cliente;
	}

	
}

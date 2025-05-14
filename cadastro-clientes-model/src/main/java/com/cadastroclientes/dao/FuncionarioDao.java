package com.cadastroclientes.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.cadastroclientes.model.Funcionario;

@Repository
public class FuncionarioDao {

	@PersistenceContext
	private EntityManager manager;

	public FuncionarioDao() {
		super();
	}

	/**
	 * Cadastra um novo funcionário no banco de dados.
	 *
	 * @param funcionario Objeto Funcionario com os dados a serem persistidos
	 * @return O funcionário cadastrado
	 */
	@Transactional
	public Funcionario cadastrarFuncionario(Funcionario funcionario) {
		manager.persist(funcionario);
		return funcionario;
	}

	/**
	 * Método para listar todos os funcionário cadastrados no banco de dados.
	 * 
	 * @return Lista de funcionário cadastrados.
	 */
	@Transactional
	public List<Funcionario> listaDadosDosFuncionarios() {
		List<Funcionario> listaFuncionarios = null;
		listaFuncionarios = manager.createQuery("select f from Funcionario f", Funcionario.class).getResultList();
		return listaFuncionarios;
	}

	/**
	 * Método para remover um funcionário do banco de dados.
	 * 
	 * @param id - ID do funcionário a ser removido.
	 * @return O funcionário removido.
	 */
	@Transactional
	public Funcionario removerFuncionario(Long id) {
		Funcionario funcionario = manager.find(Funcionario.class, id);
		manager.remove(funcionario);
		return funcionario;
	}

	/**
	 * Método para editar os dados de um funcionário existente no banco de dados.
	 * 
	 * @param endereco - Objeto contendo os dados atualizados do funcionário.
	 * @return O funcionário atualizado.
	 */
	@Transactional
	public Funcionario editarFuncionario(Funcionario funcionario) {
		manager.merge(funcionario);
		return funcionario;
	}

}

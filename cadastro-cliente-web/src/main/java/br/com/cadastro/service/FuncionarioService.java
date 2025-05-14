package br.com.cadastro.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import br.com.cadastro.dto.FuncionarioDto;



public class FuncionarioService {

	
	private static final String BASE_URI = "http://localhost:8082";

	
	/**
	 * Lista todos os funcionários.
	 * @return JSON com os funcionários
	 */
	public String listarFuncionario() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/cadastro-clientes-model/funcionario/cadastro");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return response;
	}

	/**
	 * Cadastra um novo funcionário.
	 * @param dto Objeto FuncionarioDto
	 * @return Resposta JSON
	 */
	public String cadastrarFuncionario(FuncionarioDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/cadastro-clientes-model/funcionario/cadastro");
		String response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}

	/**
	 * Exclui um funcionário por ID.
	 * @param id ID do funcionário
	 * @return Resposta JSON
	 */
	public String excluirFuncionario(Long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI)
				.path("/cadastro-clientes-model/funcionario/cadastro/{id}").resolveTemplate("id", id);
		String response = target.request(MediaType.APPLICATION_JSON).delete(String.class);
		return response;
	}

	/**
	 * Atualiza os dados de um funcionário.
	 * @param dto Objeto FuncionarioDto com os dados atualizados
	 * @return Resposta JSON
	 */
	public String atualizarrFuncionario(FuncionarioDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/cadastro-clientes-model/funcionario/cadastro");
		String response = target.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}
}

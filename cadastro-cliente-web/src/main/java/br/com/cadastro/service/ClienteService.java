package br.com.cadastro.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import br.com.cadastro.dto.ClienteDto;

public class ClienteService {

	private static final String BASE_URI = "http://localhost:8082";

	public String listarCliente() {

		/**
		 * Lista todos os clientes.
		 * 
		 * @return JSON com a lista de clientes
		 */
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/cadastro-clientes-model/cliente/cadastro");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return response;
	}

	/**
	 * Cadastra um novo cliente.
	 * 
	 * @param dto ClienteDto com os dados do cliente
	 * @return JSON com a resposta da operação
	 */
	public String cadastrarCliente(ClienteDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/cadastro-clientes-model/cliente/cadastro");
		String response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}

	/**
	 * Exclui um cliente pelo ID.
	 * 
	 * @param id ID do cliente
	 * @return JSON com a resposta da operação
	 */
	public String excluirCliente(Long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/cadastro-clientes-model/cliente/cadastro/{id}")
				.resolveTemplate("id", id);
		String response = target.request(MediaType.APPLICATION_JSON).delete(String.class);
		return response;
	}

	/**
	 * Atualiza os dados de um cliente existente.
	 * 
	 * @param dto ClienteDto com os dados atualizados
	 * @return JSON com a resposta da operação
	 */
	public String atualizarCliente(ClienteDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/cadastro-clientes-model/cliente/cadastro");
		String response = target.request(MediaType.APPLICATION_JSON).put(Entity.entity(dto, MediaType.APPLICATION_JSON),
				String.class);
		return response;
	}

}

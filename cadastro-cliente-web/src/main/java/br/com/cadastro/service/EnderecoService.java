package br.com.cadastro.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import br.com.cadastro.dto.EnderecoDto;


public class EnderecoService {

	private static final String BASE_URI = "http://localhost:8082";

	
	/**
	 * Lista todos os endereços.
	 * @return JSON com os endereços
	 */
	public String listarEndereco() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/cadastro-clientes-model/endereco/cadastro");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return response;
	}

	/**
	 * Cadastra um novo endereço.
	 * @param dto Objeto EnderecoDto com os dados
	 * @return Resposta JSON da operação
	 */
	public String cadastrarEndereco(EnderecoDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/cadastro-clientes-model/endereco/cadastro");
		String response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}

	
	/**
	 * Exclui um endereço pelo ID.
	 * @param id ID do endereço
	 * @return Resposta JSON da operação
	 */
	public String excluirEndereco(Long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI)
				.path("/cadastro-clientes-model/endereco/cadastro/{id}").resolveTemplate("id", id);
		String response = target.request(MediaType.APPLICATION_JSON).delete(String.class);
		return response;
	}

	/**
	 * Atualiza os dados de um endereço.
	 * @param dto Objeto EnderecoDto atualizado
	 * @return Resposta JSON da operação
	 */
	public String atualizarEndereco(EnderecoDto dto) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("/cadastro-clientes-model/endereco/cadastro");
		String response = target.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(dto, MediaType.APPLICATION_JSON), String.class);
		return response;
	}
	
	
}

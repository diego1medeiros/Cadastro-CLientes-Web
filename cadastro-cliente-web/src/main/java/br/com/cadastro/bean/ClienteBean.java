package br.com.cadastro.bean;

import java.io.FileNotFoundException;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import com.google.gson.Gson;

import br.com.cadastro.dto.ClienteDto;
import br.com.cadastro.service.ClienteService;
import br.com.cadastro.utils.CarregarImagens;
import br.com.cadastro.utils.Message;
import br.com.cadastro.utils.UploadImagem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named("clientebean")
@RequestScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClienteDto[] listaDeClientes;

	@Inject
	private ClienteService clienteService ;

	@Inject
	private ClienteDto clienteDto ;

	private UploadedFile file;

	/**
	 * Cadastra ou atualiza um cliente.
	 * - Verifica se o e-mail informado já está cadastrado para outro cliente.
	 * - Realiza o upload da imagem e salva o caminho no banco.
	 * - Exibe mensagens de sucesso ou erro conforme o caso.
	 *
	 * @return null (permanece na mesma página)
	 */
	public String cadastrarCliente() {
		try {
			listarClientes();

			// Verificar se o e-mail já existe
			boolean emailExistente = Arrays.stream(listaDeClientes)
					.anyMatch(c -> c.getEmail().equalsIgnoreCase(clienteDto.getEmail())
							&& (clienteDto.getId() == null || !c.getId().equals(clienteDto.getId())));

			if (emailExistente) {
				Message.erro("Erro", "E-mail já cadastrado para outro cliente!");
				return null;
			}

			if (clienteDto.getId() == null) {
				Path destino = UploadImagem.uploadImagem(file);
				clienteDto.setCaminhoImagem(destino.toString());
				clienteService.cadastrarCliente(clienteDto);
				Message.info("Cadastro", "Cliente cadastrado com Sucesso!");
			} else if (clienteDto.getCaminhoImagem() == null) {
				Message.warr("A imagem é obrigatória!", "");
			} else {
				Path destino = UploadImagem.uploadImagem(file);
				clienteDto.setCaminhoImagem(destino.toString());
				clienteService.atualizarCliente(clienteDto);
				Message.info("Atualização", "Cliente atualizado com Sucesso!");
			}

			listarClientes();
			limpar();
		} catch (Exception e) {
			Message.erro("Erro", "Erro ao cadastrar ou atualizar cliente.");
		}
		return null;
	}

	// caminho da imagem recuperado do banco de dados
	/**
	 * A imagem do cliente é carregada com o método carregarImagens, que retorna um
	 * StreamedContent para exibição.
	 * @param clienteDto
	 * @return
	 * @throws FileNotFoundException
	 */
	public StreamedContent carregarImagens(ClienteDto clienteDto) throws FileNotFoundException {
		return CarregarImagens.carregarImagens(clienteDto);
	}

	/**
	 * Lista todos os clientes ao inicializar o bean (chamado automaticamente após a construção).
	 */
	@PostConstruct
	public void listarClientes() {
		String json = clienteService.listarCliente();
		Gson gson = new Gson();
		listaDeClientes = gson.fromJson(json, ClienteDto[].class);
	}


	/**
	 * Exclui um cliente do banco de dados com base no ID.
	 *
	 * @param clienteDto Cliente a ser excluído.
	 */
	public void excluirCliente(ClienteDto clienteDto) {
		try {
			clienteService.excluirCliente(clienteDto.getId());
			Message.info("Excluir", "Cliente excluido com Sucesso!!!");
			listarClientes();
			limpar();
		} catch (Exception e) {
			Message.warr("Erro ao Excluir", "Cliente excluido com Sucesso!!!");

		}
	}

	/**
	 * Limpa os dados do cliente na tela.
	 */
	public void limpar() {
		clienteDto = new ClienteDto();
	}

	/**
	 * Retorna uma lista de clientes formatada como itens de seleção (SelectItem) para uso em componentes JSF.
	 *
	 * @return Lista de SelectItem com ID e nome dos clientes.
	 */
	public List<SelectItem> getLista() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		String json = clienteService.listarCliente();
		Gson gson = new Gson();
		ClienteDto[] listaClientes = gson.fromJson(json, ClienteDto[].class);
		for (ClienteDto cliente : listaClientes) {
			lista.add(new SelectItem(cliente.getId(), cliente.getNome()));
		}
		return lista;
	}
}

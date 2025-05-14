package br.com.cadastro.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.google.gson.Gson;

import br.com.cadastro.dto.EnderecoDto;
import br.com.cadastro.service.EnderecoService;
import br.com.cadastro.utils.BuscaCep;
import br.com.cadastro.utils.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named("enderecobean")
@RequestScoped
public class EnderecoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private EnderecoDto[] listaEnderecos;

	@Inject
	private EnderecoService enderecoService;

	@Inject
	private EnderecoDto enderecoDto;

	/**
	 * Cadastra ou atualiza um endereço com base no ID do objeto. Se o ID for nulo,
	 * um novo endereço é criado. Caso contrário, o endereço existente é atualizado.
	 */
	public String cadastrarEndereco() {
		try {
			if (enderecoDto.getId() == null) {
				enderecoService.cadastrarEndereco(enderecoDto);
				Message.info("Cadastro", "Endereco  cadastrado com Sucesso!!!");
			} else {
				enderecoService.atualizarEndereco(enderecoDto);
				Message.info("Atualização", "Endereco  atualizado com Sucesso!!!");
			}
			listarEnderecos();
			limpar();
		} catch (Exception e) {
		}
		return null;
	}

	// busca Cep dos clientes
	/**
	 * Realiza a busca do endereço com base no CEP informado. Utiliza a classe
	 * utilitária BuscaCep para consultar os dados.
	 */
	public void encontraCEP() {
		BuscaCep buscaCep = new BuscaCep(enderecoDto.getCep());
		if (buscaCep.getResultado() == 1) {
			enderecoDto.setRua(buscaCep.getTipoLogradouro() + " " + buscaCep.getLogradouro());
			enderecoDto.setEstado(buscaCep.getEstado());
			enderecoDto.setCidade(buscaCep.getCidade());
			enderecoDto.setBairro(buscaCep.getBairro());
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "CEP não foi encontrado", "CEP não foi encontrado"));
		}
	}

	// excluir endereços do banco de dados
	/**
	 * Realiza a busca do endereço com base no CEP informado. Utiliza a classe
	 * utilitária BuscaCep para consultar os dados.
	 */
	public void excluirEndereco(EnderecoDto enderecoDto) {
		try {
			enderecoService.excluirEndereco(enderecoDto.getId());
			Message.info("Excluir", "Endereço excluido com Sucesso!!!");
			listarEnderecos();
			limpar();
		} catch (Exception e) {
		}
	}

	// limpar dados da tela
	/**
	 * Limpa os campos do formulário, reinicializando o DTO.
	 */
	public void limpar() {
		enderecoDto = new EnderecoDto();
	}

	/**
	 * Carrega a lista de endereços ao inicializar o bean. Esse método é executado
	 * automaticamente após a construção do bean.
	 */
	@PostConstruct
	public void listarEnderecos() {
		String json = enderecoService.listarEndereco();
		Gson gson = new Gson();
		listaEnderecos = gson.fromJson(json, EnderecoDto[].class);
	}

}

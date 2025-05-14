package br.com.cadastro.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;

import br.com.cadastro.dto.FuncionarioDto;
import br.com.cadastro.service.FuncionarioService;
import br.com.cadastro.utils.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "funcionariobean")
@RequestScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioDto funcionario;

	@Inject
	private FuncionarioService funcionarioService;

	private FuncionarioDto[] listaFuncionarios;

	/**
	 * Cadastra um novo funcionário usando o serviço FuncionarioService. Após o
	 * cadastro, limpa o formulário e exibe mensagem de sucesso.
	 */
	
	
	public String cadastrarFuncionario() {
		try {
			if (funcionario.getId() == null) {
				funcionarioService.cadastrarFuncionario(funcionario);				
				Message.info("Cadastro", "Funcionario cadastrado com Sucesso!!!");
			} else {
				funcionarioService.atualizarrFuncionario(funcionario);
				Message.info("Atualização", "Funcionario atualizado com Sucesso!!!");
			}
			listarFuncionarios();
			limpar();
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Valida o login e senha informados pelo usuário. Se os dados forem válidos,
	 * armazena o funcionário na sessão e redireciona para o menu principal. Caso
	 * contrário, exibe uma mensagem de erro.
	 *
	 * @param login Login informado
	 * @param senha Senha informada
	 * @return Navegação JSF: redireciona ou permanece na tela
	 */
	public String isLoginSenhaValida(String login, String senha) {
		try {
			List<FuncionarioDto> listaFuncionario = Arrays.asList(listaFuncionarios);
			for (FuncionarioDto funcionario : listaFuncionario) {
				if (login.equals(funcionario.getLogin()) && senha.equals(funcionario.getSenha())) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().getSessionMap().put("funcionarioLogado", funcionario);
					Message.info("Login e Senha valida!!! Funcionario " + funcionario.getNomeFuncionario() + " Logado",
							null);
					return "menuPrincipal?faces-redirect-true";
				}
			}
		} catch (Exception e) {
		}
		Message.erro("Login e Senha errada!!!", "");
		return null;
	}

	// lista Funcionarios do banco de dados
	/**
	 * Método executado ao inicializar o bean. Carrega a lista de funcionários a
	 * partir do serviço e converte o JSON para array de DTOs.
	 */
	@PostConstruct
	public void listarFuncionarios() {
		String json = funcionarioService.listarFuncionario();
		Gson gson = new Gson();
		listaFuncionarios = gson.fromJson(json, FuncionarioDto[].class);

	}

	/**
	 * Recupera o funcionário logado da sessão.
	 *
	 * @return Objeto FuncionarioDto da sessão ou null se não houver ninguém logado.
	 */
	public FuncionarioDto getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		FuncionarioDto funcionarioLogado = (FuncionarioDto) context.getExternalContext().getSessionMap()
				.get("funcionarioLogado");
		return funcionarioLogado;
	}

	// deslogar do sistema
	/**
	 * Remove o funcionário logado da sessão, efetivamente realizando o logout.
	 *
	 * @return Navegação JSF para a página de login.
	 */
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("funcionarioLogado");
		return "login?faces-redirect-true";
	}

	// limpar dados da tela
	/**
	 * Limpa os dados do formulário, reinicializando o objeto FuncionarioDto.
	 */
	public void limpar() {
		funcionario = new FuncionarioDto();
	}
	
	// excluir Funcionarios do banco de dados
		public void excluirFuncionario(FuncionarioDto funcionario) {
			funcionarioService.excluirFuncionario(funcionario.getId());
			listarFuncionarios();
			limpar();
			Message.info("Excluir", "Funcionario  excluido com Sucesso!!!");

		}

	
	
}

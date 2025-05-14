package br.com.cadastro.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe utilitária para exibir mensagens no contexto JSF.
 */
public class Message {

	  /**
     * Exibe uma mensagem de informação.
     * @param titulo Título da mensagem.
     * @param mensagem Conteúdo da mensagem.
     */
	public static void info(String info, String mesangem) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, info, mesangem);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void erro(String erro, String mesangem) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, erro, mesangem);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void warr(String warr, String mesangem) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, warr, mesangem);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}

package br.com.cadastro.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.file.UploadedFile;

/**
 * Utilitário responsável por realizar o upload de imagens para o diretório da aplicação.
 */
public class UploadImagem {

	
	/**
     * Realiza o upload de uma imagem para o diretório de recursos da aplicação.
     *
     * @param file Arquivo recebido via upload.
     * @return Caminho final do arquivo salvo.
     */
	public static Path uploadImagem(UploadedFile file) throws IOException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String diretorioRecursos = servletContext.getRealPath("/resources/imagens/");
		String nomeArquivo = file.getFileName();
		Path destino = Paths.get(diretorioRecursos, nomeArquivo);
		Files.createDirectories(destino.getParent());
		Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

		return destino;
	}

}

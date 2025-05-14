package br.com.cadastro.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.cadastro.dto.ClienteDto;

/**
 * Utilitário para carregar imagens dos clientes a partir do caminho armazenado.
 */
public class CarregarImagens {

	public static StreamedContent carregarImagens(ClienteDto cliente) throws FileNotFoundException {
		InputStream stream = new FileInputStream(cliente.getCaminhoImagem());
		StreamedContent imagem = DefaultStreamedContent.builder().stream(() -> stream).build();
		return imagem;
	}

}
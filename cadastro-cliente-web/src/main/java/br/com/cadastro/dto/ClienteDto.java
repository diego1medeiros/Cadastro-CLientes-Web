package br.com.cadastro.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String email;
	private String caminhoImagem;
	
}

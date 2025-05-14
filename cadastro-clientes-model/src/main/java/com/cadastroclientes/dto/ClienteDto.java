package com.cadastroclientes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
	
	private Long id;
	private String nome;
	private String email;
	private String caminhoImagem;
	
}

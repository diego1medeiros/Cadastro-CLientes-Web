package com.cadastroclientes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {
	private Long id;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String numero;
	private String cep;
	private ClienteDto cliente = new ClienteDto();

}

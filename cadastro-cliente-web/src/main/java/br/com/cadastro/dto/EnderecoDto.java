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
public class EnderecoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String numero;
	private String cep;
	private ClienteDto cliente =new ClienteDto();
}

package com.cadastroclientes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastroclientes.dao.EnderecoDao;
import com.cadastroclientes.dto.EnderecoDto;
import com.cadastroclientes.model.Endereco;

/**
 * Serviço responsável por conter a lógica de negócio relacionada aos endereços.
 * Realiza operações como cadastro, listagem, atualização e exclusão.
 */
@Service
public class EnderecoService {

	@Autowired
	private EnderecoDao enderecoDao;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Cadastra um novo endereço no banco de dados.
	 *
	 * @param dto Objeto EnderecoDto com os dados a serem cadastrados.
	 * @return EnderecoDto com os dados do endereço cadastrado.
	 */
	public EnderecoDto cadastrarEndereco(EnderecoDto dto) {
		Endereco endereco = modelMapper.map(dto, Endereco.class);
		enderecoDao.cadastrarEndereco(endereco);
		return modelMapper.map(endereco, EnderecoDto.class);
	}

	/**
	 * Lista todos os endereços cadastrados no sistema.
	 *
	 * @return Lista de EnderecoDto com os dados dos endereços.
	 */
	public List<EnderecoDto> listarEndereco() {
		List<Endereco> enderecos = enderecoDao.listaDadosDosEnderecos();
		return enderecos.stream().map(endereco -> modelMapper.map(endereco, EnderecoDto.class))
				.collect(Collectors.toList());
	}

	/**
	 * Exclui um endereço com base no ID.
	 *
	 * @param id Identificador do endereço a ser removido.
	 */
	public void excluirEndereco(Long id) {
		enderecoDao.removerEndereco(id);
	}

	/**
	 * Atualiza os dados de um endereço.
	 *
	 * @param dto Objeto EnderecoDto com os dados atualizados.
	 * @return EnderecoDto com os dados após atualização.
	 */
	public EnderecoDto atualizarEndereco(EnderecoDto dto) {
		Endereco endereco = modelMapper.map(dto, Endereco.class);
		endereco.setId(dto.getId());
		endereco = enderecoDao.editarEndereco(endereco);
		return modelMapper.map(endereco, EnderecoDto.class);
	}
}

package com.cadastroclientes.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cadastroclientes.dto.FuncionarioDto;
import com.cadastroclientes.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	/**
	 * Método responsável por listar todos os funcionários cadastrados.
	 * 
	 * @return ResponseEntity com a lista de funcionários.
	 */
	@GetMapping("/cadastro")
	public ResponseEntity<List<FuncionarioDto>> listarFuncionarios() {
		return ResponseEntity.ok(service.listarFuncionarios());
	}

	/**
	 * Método responsável por cadastrar um novo funcionário.
	 * 
	 * @param dto                  - Dados do funcionário a ser cadastrado.
	 * @param uriComponentsBuilder - Builder para criar a URI do novo funcionário.
	 * @return ResponseEntity com o funcionário criado e URI associada.
	 */
	@PostMapping("/cadastro")
	public ResponseEntity<FuncionarioDto> cadastrarFuncionario(@RequestBody FuncionarioDto dto,
			UriComponentsBuilder uriComponentsBuilder) {
		FuncionarioDto funcionarioDto = service.cadastrarFuncionario(dto);
		URI uri = uriComponentsBuilder.path("/listarfuncionario/{id}").buildAndExpand(funcionarioDto.getId()).toUri();
		return ResponseEntity.created(uri).body(funcionarioDto);
	}

	/**
	 * Método responsável por excluir um funcionário pelo seu ID.
	 * 
	 * @param id - ID do funcionário a ser excluído.
	 * @return ResponseEntity com código HTTP 204 (No Content) indicando que a
	 *         exclusão foi realizada.
	 */
	@DeleteMapping("/cadastro/{id}")
	public ResponseEntity<FuncionarioDto> excluir(@PathVariable Long id) {
		service.excluirFuncionario(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Método responsável por atualizar as informações de um funcionário.
	 * 
	 * @param dto - Dados atualizados do funcionário.
	 * @return ResponseEntity com o funcionário atualizado.
	 */
	@PutMapping("/cadastro")
	public ResponseEntity<FuncionarioDto> atualizar(@RequestBody FuncionarioDto dto) {
		FuncionarioDto atualizado = service.atualizarFuncionario(dto);
		return ResponseEntity.ok(atualizado);
	}

}

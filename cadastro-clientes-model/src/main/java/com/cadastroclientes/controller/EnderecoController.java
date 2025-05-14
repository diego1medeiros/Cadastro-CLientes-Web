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

import com.cadastroclientes.dto.ClienteDto;
import com.cadastroclientes.dto.EnderecoDto;
import com.cadastroclientes.service.EnderecoService;


@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	 /**
     * Método responsável por cadastrar um novo endereço.
     * 
     * @param dto - Dados do endereço a ser cadastrado.
     * @param uriComponentsBuilder - Builder para criar a URI do novo endereço.
     * @return ResponseEntity com o endereço criado e URI associada.
     */
   @PostMapping("/cadastro")
	public ResponseEntity<EnderecoDto> cadastrarEndereco(@RequestBody EnderecoDto dto,
			UriComponentsBuilder uriComponentsBuilder) {
		EnderecoDto enderecoDto = enderecoService.cadastrarEndereco(dto);
		URI uri = uriComponentsBuilder.path("/listarendereco/{id}").buildAndExpand(enderecoDto.getId()).toUri();
		return ResponseEntity.created(uri).body(enderecoDto);
	}
	
   /**
    * Método responsável por listar todos os endereços cadastrados.
    * 
    * @return ResponseEntity com a lista de endereços.
    */
	  @GetMapping("/cadastro")
		public ResponseEntity<List<EnderecoDto>> listarEnderecos() {
			return ResponseEntity.ok(enderecoService.listarEndereco());
		}
		
	  /**
	     * Método responsável por excluir um endereço pelo seu ID.
	     * 
	     * @param id - ID do endereço a ser excluído.
	     * @return ResponseEntity com código HTTP 204 (No Content) indicando que a exclusão foi realizada.
	     */
		@DeleteMapping("/cadastro/{id}")
		public ResponseEntity<ClienteDto> excluirEndereco(@PathVariable Long id) {
			enderecoService.excluirEndereco(id);
			return ResponseEntity.noContent().build();
		}

		
		 /**
	     * Método responsável por atualizar um endereço existente.
	     * 
	     * @param dto - Dados atualizados do endereço.
	     * @return ResponseEntity com o endereço atualizado.
	     */
		@PutMapping("/cadastro")
		public ResponseEntity<?> atualizarEndereco(@RequestBody EnderecoDto dto) {
			EnderecoDto atualizadoEndereco = enderecoService.atualizarEndereco(dto);
			return ResponseEntity.ok(atualizadoEndereco);
		}
}

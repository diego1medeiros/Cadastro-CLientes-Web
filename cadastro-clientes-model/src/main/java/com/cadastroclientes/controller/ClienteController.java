package com.cadastroclientes.controller;

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
import com.cadastroclientes.service.ClienteService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")

public class ClienteController {

 @Autowired   
private ClienteService clienteService;
 
 
 /**
  * Método responsável por cadastrar um novo cliente.
  * 
  * @param dto - Dados do cliente a ser cadastrado.
  * @param uriComponentsBuilder - Builder para criar a URI do novo cliente.
  * @return ResponseEntity com o cliente criado e URI associada.
  */
    @PostMapping("/cadastro")
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody ClienteDto dto,
			UriComponentsBuilder uriComponentsBuilder) {
		ClienteDto clienteDto = clienteService.cadastrarCliente(dto);
		URI uri = uriComponentsBuilder.path("/listarcliente/{id}").buildAndExpand(clienteDto.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteDto);
	}
    
    /**
     * Método responsável por listar todos os clientes cadastrados.
     * 
     * @return ResponseEntity com a lista de clientes.
     */
    @GetMapping("/cadastro")
	public ResponseEntity<List<ClienteDto>> listarClientes() {
		return ResponseEntity.ok(clienteService.listarCliente());
	}
	
    /**
     * Método responsável por excluir um cliente pelo seu ID.
     * 
     * @param id - ID do cliente a ser excluído.
     * @return ResponseEntity com código HTTP 204 (No Content) indicando que a exclusão foi realizada.
     */
	@DeleteMapping("/cadastro/{id}")
	public ResponseEntity<ClienteDto> excluirCliente(@PathVariable Long id) {
		clienteService.excluirCliente(id);
		return ResponseEntity.noContent().build();
	}

	
	  /**
     * Método responsável por atualizar um cliente existente.
     * 
     * @param dto - Dados atualizados do cliente.
     * @return ResponseEntity com o cliente atualizado.
     */
	@PutMapping("/cadastro")
	public ResponseEntity<?> atualizarCliente(@RequestBody ClienteDto dto) {
		ClienteDto atualizadoCliente = clienteService.atualizarCliente(dto);
		return ResponseEntity.ok(atualizadoCliente);
	}
	
}

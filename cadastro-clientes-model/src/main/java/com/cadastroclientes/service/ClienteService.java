package com.cadastroclientes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastroclientes.dao.ClienteDao;
import com.cadastroclientes.dto.ClienteDto;
import com.cadastroclientes.model.Cliente;

/**
 * Serviço responsável por conter a lógica de negócio relacionada aos clientes.
 * Realiza operações como cadastro, listagem, atualização e exclusão.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Cadastra um novo cliente utilizando uma stored procedure (function).
     *
     * @param dto Objeto ClienteDto com os dados a serem cadastrados.
     * @return ClienteDto com os dados do cliente cadastrado.
     */
    public ClienteDto cadastrarCliente(ClienteDto dto) {
        Cliente cliente = modelMapper.map(dto, Cliente.class);
        clienteDao.cadastrarClienteComFunction(cliente);
        return modelMapper.map(cliente, ClienteDto.class);
    }

    /**
     * Lista todos os clientes cadastrados no sistema.
     *
     * @return Lista de ClienteDto com os dados dos clientes.
     */
    public List<ClienteDto> listarCliente() {
        List<Cliente> clientes = clienteDao.listaDadosDosClientes();
        return clientes.stream()
                       .map(cliente -> modelMapper.map(cliente, ClienteDto.class))
                       .collect(Collectors.toList());
    }

    /**
     * Exclui um cliente com base no ID.
     *
     * @param id Identificador do cliente a ser removido.
     */
    public void excluirCliente(Long id) {
        clienteDao.removerCliente(id);
    }

    /**
     * Atualiza os dados de um cliente.
     *
     * @param dto Objeto ClienteDto com os dados atualizados.
     * @return ClienteDto com os dados após atualização.
     */
    public ClienteDto atualizarCliente(ClienteDto dto) {
        Cliente cliente = modelMapper.map(dto, Cliente.class);
        cliente.setId(dto.getId());
        cliente = clienteDao.editarCliente(cliente);
        return modelMapper.map(cliente, ClienteDto.class);
    }
}

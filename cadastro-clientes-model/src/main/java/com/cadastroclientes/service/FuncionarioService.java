package com.cadastroclientes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastroclientes.dao.FuncionarioDao;
import com.cadastroclientes.dto.FuncionarioDto;
import com.cadastroclientes.model.Funcionario;

/**
 * Serviço responsável por gerenciar a lógica de negócio relacionada aos funcionários.
 * Realiza operações como cadastro, listagem, atualização e exclusão.
 */
@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioDao funcionarioDao;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Cadastra um novo funcionário no banco de dados.
     *
     * @param dto Objeto FuncionarioDto com os dados a serem cadastrados.
     * @return FuncionarioDto com os dados do funcionário cadastrado.
     */
    public FuncionarioDto cadastrarFuncionario(FuncionarioDto dto) {
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);
        funcionarioDao.cadastrarFuncionario(funcionario);
        return modelMapper.map(funcionario, FuncionarioDto.class);
    }

    /**
     * Lista todos os funcionários cadastrados no sistema.
     *
     * @return Lista de FuncionarioDto com os dados dos funcionários.
     */
    public List<FuncionarioDto> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioDao.listaDadosDosFuncionarios();
        return funcionarios.stream()
                           .map(funcionario -> modelMapper.map(funcionario, FuncionarioDto.class))
                           .collect(Collectors.toList());
    }

    /**
     * Exclui um funcionário com base no ID.
     *
     * @param id Identificador do funcionário a ser removido.
     */
    public void excluirFuncionario(Long id) {
        funcionarioDao.removerFuncionario(id);
    }

    /**
     * Atualiza os dados de um funcionário existente.
     *
     * @param dto Objeto FuncionarioDto com os dados atualizados.
     * @return FuncionarioDto com os dados após a atualização.
     */
    public FuncionarioDto atualizarFuncionario(FuncionarioDto dto) {
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);
        funcionario.setId(dto.getId());
        funcionario = funcionarioDao.editarFuncionario(funcionario);
        return modelMapper.map(funcionario, FuncionarioDto.class);
    }
}

package com.api.senac.service;

import com.api.senac.data.FuncionarioEntity;
import com.api.senac.data.FuncionarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.senac.exception.ResourceNotFoundException;
@Service
public class FuncionarioService {
    
    //Diz ao Spring que essa é a classe Repository.
    @Autowired
    FuncionarioRepository funcionarioRepository;
    
    //Criando Método que Retorna uma Lista de Funcionarios.
    //O service se comunica com o repository para soliciar os funcionarios.
    public List<FuncionarioEntity> listarTodosFuncionarios(){
     
        //Retorna lista de funcionarios.
        return funcionarioRepository.findAll();
    }
    
    
    public FuncionarioEntity getFuncionarioId(Integer funcId){
        
        //Retorna funcionario pelo ID.
   return funcionarioRepository.findById(funcId).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado " + funcId));                

    }
    
    //Método para retornar funcionario por nome.
    public List<FuncionarioEntity> getFuncionarioPorNome(String nome){
        //Retornando lista de funcionario que contenha em algum lugar o nome.
        return funcionarioRepository.findByNomeContaining(nome);
    }
    
    public FuncionarioEntity criarFuncionario(FuncionarioEntity func){
        
        func.setId(null);
        
        funcionarioRepository.save(func);
        
        return func;
    }
    
    //Retorna os Funcionarios com maiores salarios.
    public List<FuncionarioEntity> getMaioresSalarios() {
    return funcionarioRepository.findAllMaiorSalario();
    }
    
    public FuncionarioEntity atualizarFuncionario(Integer funcId, FuncionarioEntity funcionarioRequest){
        
        //Manda os Dados do Funcionario recebido pelo Id do Parametro.
        FuncionarioEntity func = getFuncionarioId(funcId);
        
        //Mandando os Novos dados do Request para o funcionario do banco.
        func.setNome(funcionarioRequest.getNome());
        func.setCpf(funcionarioRequest.getCpf());
        func.setTelefone(funcionarioRequest.getTelefone());
        func.setEmail(funcionarioRequest.getEmail());
        func.setSalario(funcionarioRequest.getSalario());
        
        //Salvando as alterações pelo Repository no banco.
        funcionarioRepository.save(func);
        
        //Retorna o funcionario atualizado.
        return func;
    }
    
    public void deletarFuncionario(Integer funcId){
        
        //Busca o Funcionario pelo id do parametro.
        FuncionarioEntity func = getFuncionarioId(funcId);
        
        //Condição if para verificar se funcionario existe no banco.
        if(func != null){   
            //Exclui o funcionário pelo ID.
            funcionarioRepository.deleteById(func.getId());
        }
    }
}

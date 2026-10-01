package com.api.senac.controller;

import com.api.senac.data.FuncionarioEntity;
import com.api.senac.service.FuncionarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
 
    //Anotação @Autowired para o Spring fazer a ligação de criar o Objeto() Corretamente.
    @Autowired
    FuncionarioService funcionarioService;
    
    //Método para Retornar todos os Funcionarios.
    @GetMapping("/listar")
    public ResponseEntity<List> getAllFuncioarios(){
        //Manda o service chamar a método de buscar todos funcionarios do banco.
        
        List<FuncionarioEntity> funcionarios = funcionarioService.listarTodosFuncionarios();
        //Retorna a lista de Funcionarios e os Status.
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }
    
    //Método para retorna um funcionario por ID.
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FuncionarioEntity> getFuncionarioById(@PathVariable Integer id){
        
        //Encontra o funcionario pelo ID.
        FuncionarioEntity funcionario = funcionarioService.getFuncionarioId(id);
        
        //Retornando o funcionario encontrado.
        return new ResponseEntity<>(funcionario, HttpStatus.OK);
    }
    
    //Método para pesquisar pelo nome.
    @GetMapping("/pesquisar-nome/{nome}")
    public ResponseEntity<List> getPesquisarPorNomeFuncionarios(@PathVariable String nome){
        
        //Pega o funcionario encontrado no banco pelo service, e coloca na lista do objeto.
        List<FuncionarioEntity> funcionarios = funcionarioService.getFuncionarioPorNome(nome);
    
        //Retorna os Funcionarios Encontrados e o Status.
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }
    
    //Retorna o maior funcionario, com mais salario.
    @GetMapping("/maiores-salarios") 
    public ResponseEntity<List<FuncionarioEntity>> getMaioresSalarios() { 

    List<FuncionarioEntity> funcionarios = funcionarioService.getMaioresSalarios(); 
    return new ResponseEntity<>(funcionarios, HttpStatus.OK); 

    }
    
    //Método para adicionar Funcionario no Banco pelo POSTMAN.
    @PostMapping("cadastrar")
    public ResponseEntity<FuncionarioEntity> addFuncionario(@Valid @RequestBody FuncionarioEntity func){
        
        //Manda o Service criar o Funcionario e colocar na varivel. 
        var novoFuncionario = funcionarioService.criarFuncionario(func);
        //Retorna o status e o funcionario cadastrado.
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }
    
    //Método para atualizar dados do Funcionario do banco.
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FuncionarioEntity> atualizarFuncionario(@PathVariable Integer id, @RequestBody FuncionarioEntity funcionario){
        
       //Atualizando funcionario com os dados do Postman.
       var funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionario);
       //Retornando funcionario atualizado.
       return new ResponseEntity<>(funcionarioAtualizado,HttpStatus.OK);
    }
    
    //Método para deletar dados do banco.
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarFuncionario(@PathVariable Integer id){
        
        //Deletando o funcionario do banco pelo ID do Parametro POSTMAN.
        funcionarioService.deletarFuncionario(id);
        
        //retorna o status.
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

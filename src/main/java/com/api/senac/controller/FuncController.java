package com.api.senac.controller;

import com.api.senac.data.FuncionarioEntity; 
import com.api.senac.service.FuncionarioService; 
import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 


@Controller //Diz ao Spring que essa é uma classe de controle.
public class FuncController {
    
     @Autowired //Faz a ligação com a classe Service.
     FuncionarioService funcionarioService;
     
     //Método Get para mostrar Lista de Todos os funcionario na pagina HTML.
     @GetMapping("/")
     public String viewHomePage(Model model) {
         //Pega todos os funcionarios no banco e coloca no model.
        model.addAttribute(
        "listarFuncionarios",
        funcionarioService.listarTodosFuncionarios()
    );
    //Abre a pagina da tabela dos funcionarios.
    return "index";
    }
     
     //Método para criar funcinario vazio e abrir a pagina html.
     @GetMapping("/criarFuncionarioForm")
     public String criarFuncionarioForm(Model model) {
         
         //Criar o funcionario vazio.
        FuncionarioEntity func = new FuncionarioEntity();
        //Manda o funcionario vazio para o model, para ser preenchido na pagina.
        model.addAttribute("funcionario", func);
        //Abre a pagina html.
        return "inserir";
    }
     
     //Método para atualizar funcionario pelo ID da URL.
     @GetMapping("/atualizarFuncionarioForm/{id}") 
        public String atualizarFuncionarioForm(@PathVariable(value = "id") Integer id, Model model) {
            
            //Pega os dados do banco pelo id da URL.
            //E coloca os dados no novo objeto funcionario.
            FuncionarioEntity func = funcionarioService.getFuncionarioId(id);
            
            //Coloca o funcioanro encontrado no Model.
            model.addAttribute("funcionario", func);
            
            //Abre a pagina de formulário atualizar.
            return "atualizar";
        }
     
        //Método para deletrar funcionario do banco pelo ID.
        @GetMapping("/deletarFuncionario/{id}")
        public String deletarFuncionario(@PathVariable(value = "id") Integer id) {
            
            //Manda o service excluir o funcionario do banco.
            funcionarioService.deletarFuncionario(id);
            
            //Volta para a pagina inicial.
            return "redirect:/";
        }
        
        //Método que salva ou atualiza o funcionario no banco.
     @PostMapping("/salvarFuncionario")
     public String salvarFuncionario(@Valid @ModelAttribute("funcionario") FuncionarioEntity func,
                                     BindingResult result){
         
         //Validaçáo se algum campo estiver errado, volta para a pagina.
         if(result.hasErrors()){
             return "inserir";
         }
         
         //Validação se o id do funcionario estiver vazio, cria um novo funcionario.
         if(func.getId() == null){
             funcionarioService.criarFuncionario(func);
         }else{
         //Se o id for encontrado, então atualiza os dados do funcionario.
             funcionarioService.atualizarFuncionario(func.getId(), func);
         }
         //Retorna para a pagina inicial.
         return "redirect:/";
     }
     
}
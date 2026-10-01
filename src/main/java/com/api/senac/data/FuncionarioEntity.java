package com.api.senac.data;
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.Table; 
import lombok.Data; 
//Imports de validação.
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
//Importação de CPF
import org.hibernate.validator.constraints.br.CPF;

//Criando Classe Entity com suas Anotações e Seus Atributos.

@Data //Para gerar os gets e sets automatimente pelo Lombok
@Entity //Para dizer a JPA que essa é uma classe de Entidade persistente.
@Table(name="Funcionario") //Para dizer a JPA qual tabela do Banco essa classe Pertence.
public class FuncionarioEntity {
    
    @Id //Para definir o Id da Tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para Gerar o Id automaticamente.
    
    //Atributs da Classe Entity.
    private Integer id;
    //Criar campo de Válidação.
    @CPF(message="CPF inválido")
    private String cpf;
    //Nome no minimo 2 caracteres.
    @Size(min=2, message="Informe ao menos 2 caracteres para o campo nome")
    private String nome;
    
    //O campo telefone não pode ficar vazio,
    @NotBlank(message="Telefone Obrigatório")
    private String telefone;
    
    //Campo email Obrigatorio.
    @NotBlank(message="E-mail deve ser obrigatorio")
    //Email deve ser valido.
    @Email(message="E-mail inválido")
    private String email;
    //Campo salario obrigatorio.
    @NotNull(message="Salário deve ser obrigatorio")
    private double salario;   
}
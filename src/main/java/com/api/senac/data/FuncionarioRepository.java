package com.api.senac.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository; 
import java.util.List;

//Essa interface é feita para se comunicar com o banco de dados e manipular a classe Entity.
@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer>{
        
    //Criando Métodos proprios para o SpringLer.
    FuncionarioEntity findByNome(String nome);
    FuncionarioEntity findByEmail(String email);
    FuncionarioEntity findByTelefone(String telefone);
    
    //Método para procurar por dois atributos. nome or email.
    List<FuncionarioEntity> findByNomeOrEmail(String nome, String email);
    List<FuncionarioEntity> findByTelefoneOrEmail(String telefone, String email);
    
    //Método para procurar dois valores igual, utilize o and.
    List<FuncionarioEntity> findByNomeAndEmail(String nome, String email);
    
    //Método que inicia com o algum valor
    List<FuncionarioEntity> findByNomeStartingWith(String nome);
    //Método que termina com algum valor.
    List<FuncionarioEntity> findByNomeEndingWith(String nome);
    //Método que contém em algum lugar do atributo o valor do parametro.
    List<FuncionarioEntity> findByNomeContaining(String nome);
    //Método para trazer funcionario ordenado de A - Z
    List<FuncionarioEntity> findByOrderByNomeAsc();
    //Método para trazer funcionario ordenado decresente de Z - A.
    List<FuncionarioEntity> findByOrderByNomeDesc();
    
    //Método para fazer por consulta SQL.
    @Query(value="select * from Funcionario", nativeQuery=true)
    
    List<FuncionarioEntity> algumaConsulta();
    
    //Retorna o maior funcionario.
        @Query(value = """
        SELECT *
        FROM Funcionario
        WHERE salario = (
            SELECT MAX(salario)
            FROM Funcionario
        )
        """, nativeQuery = true)
    List<FuncionarioEntity> findAllMaiorSalario();
}
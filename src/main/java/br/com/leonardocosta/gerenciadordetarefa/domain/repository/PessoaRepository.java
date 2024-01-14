package br.com.leonardocosta.gerenciadordetarefa.domain.repository;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "SELECT p.departamento AS nome, " +
            "COUNT(DISTINCT p.id) AS quantidadePessoas, " +
            "COUNT(DISTINCT t.id) AS quantidadeTarefas " +
            "FROM pessoa p " +
            "LEFT JOIN tarefa t ON p.id = t.pessoa_id " +
            "GROUP BY p.departamento", nativeQuery = true)
    List<Object[]> listarDepartamentosComQuantidadeDePessoasETarefas();

}

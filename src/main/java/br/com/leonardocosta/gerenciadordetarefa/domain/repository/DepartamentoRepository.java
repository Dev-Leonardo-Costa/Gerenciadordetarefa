package br.com.leonardocosta.gerenciadordetarefa.domain.repository;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoProjection;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    @Query(value = "SELECT " +
            "d.nome AS departamento, " +
            "COUNT(DISTINCT p.id) AS qtdPessoas, " +
            "COUNT(DISTINCT t.id) AS qtdTarefas " +
            "FROM departamento d " +
            "LEFT JOIN pessoa p ON d.id = p.id_departamento " +
            "LEFT JOIN tarefa t ON d.id = t.id_departamento " +
            "GROUP BY d.nome", nativeQuery = true)
    List<DepartamentoProjection> listarDepartamentosComQuantidadeDePessoasETarefas();

}

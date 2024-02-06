package br.com.leonardocosta.gerenciadordetarefa.domain.repository;

import br.com.leonardocosta.gerenciadordetarefa.api.response.TarefaListarAntigasResponse;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query(value = "SELECT " +
            "t.titulo, " +
            "t.prazo, " +
            "d.nome AS departamento, " +
            "t.finalizado " +
            "FROM Tarefa t " +
            "JOIN Departamento d ON d.id = t.id_departamento " +
            "WHERE t.id_pessoa IS NULL " +
            "ORDER BY t.prazo ASC " +
            "LIMIT 3", nativeQuery = true)
    List<TarefaListarAntigasResponse> listarTarefasPendentesSemPessoa();

}


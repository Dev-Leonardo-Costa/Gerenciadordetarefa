package br.com.leonardocosta.gerenciadordetarefa.domain.repository;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}

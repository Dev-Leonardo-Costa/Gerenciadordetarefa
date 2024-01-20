package br.com.leonardocosta.gerenciadordetarefa.domain.repository;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "SELECT " +
            "p.id, p.nome, " +
            "d.nome AS departamento, " +
            "COALESCE(SUM(t.duracao), 0) AS total_horas " +
            "FROM pessoa p " +
            "LEFT JOIN tarefa t ON p.id = t.id_pessoa " +
            "LEFT JOIN departamento d ON p.id_departamento = d.id " +
            "GROUP BY p.id, p.nome, d.nome " +
            "ORDER BY p.id", nativeQuery = true)
    List<Pessoa> listarPessoas();

}

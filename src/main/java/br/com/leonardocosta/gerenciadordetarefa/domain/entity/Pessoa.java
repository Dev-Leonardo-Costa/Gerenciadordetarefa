package br.com.leonardocosta.gerenciadordetarefa.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String departamento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();

    public int horas() {
        return tarefas.stream()
                .mapToInt(Tarefa::getDuracao)
                .sum();
    }
}

package br.com.leonardocosta.gerenciadordetarefa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String departamento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();

}

package br.com.leonardocosta.gerenciadordetarefa.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TAREFA")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private String departamento;

    private Date prazo;

    private int duracao;

    private boolean finalizado;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

}

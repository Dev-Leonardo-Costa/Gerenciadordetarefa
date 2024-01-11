package br.com.leonardocosta.gerenciadordetarefa.domain.entity;


import br.com.leonardocosta.gerenciadordetarefa.request.TarefaRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Tarefa(String titulo, String descricao, String departamento, Date prazo, int duracao, boolean finalizado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.departamento = departamento;
        this.prazo = prazo;
        this.duracao = duracao;
        this.finalizado = finalizado;
    }

    public static Tarefa fromTo(TarefaRecord tarefa) {
        var entidade = new Tarefa(
                tarefa.titulo(),
                tarefa.descricao(),
                tarefa.departamento(),
                tarefa.prazo(),
                tarefa.duracao(),
                tarefa.finalizado()
        );
        return entidade;
    }

    public boolean isFinalizada() {
        return finalizado;
    }

}

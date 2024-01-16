package br.com.leonardocosta.gerenciadordetarefa.domain.entity;


import br.com.leonardocosta.gerenciadordetarefa.request.TarefaCreateRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TAREFA")
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date prazo;

    private String departamento;

    private int duracao;

    private Boolean finalizado;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Tarefa(String titulo, String descricao, Date prazo, String departamento, int duracao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.departamento = departamento;
        this.duracao = duracao;
        this.finalizado = false;
    }

    public static Tarefa fromTo(TarefaCreateRecord tarefa) {
        var entidade = new Tarefa(
                tarefa.titulo(),
                tarefa.descricao(),
                tarefa.prazo(),
                tarefa.departamento(),
                tarefa.duracao()
        );
        return entidade;
    }

    public Boolean isFinalizado() {
        return finalizado;
    }

}

package br.com.leonardocosta.gerenciadordetarefa.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
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

    private int duracao;

    private boolean finalizado;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    public Boolean isFinalizado() {
        return finalizado = false;
    }

    public Tarefa(Long id, String titulo, String descricao, Date prazo, int duracao, Pessoa pessoa, Departamento departamento) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.duracao = duracao;
        this.finalizado = false;
        this.pessoa = pessoa;
        this.departamento = departamento;
    }

}

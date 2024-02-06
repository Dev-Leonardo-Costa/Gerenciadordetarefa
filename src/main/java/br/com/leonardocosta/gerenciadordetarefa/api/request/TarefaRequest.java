package br.com.leonardocosta.gerenciadordetarefa.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaRequest {

    private String titulo;
    private String descricao;
    private Date prazo;
    private Long departamento;
    private int duracao;

}

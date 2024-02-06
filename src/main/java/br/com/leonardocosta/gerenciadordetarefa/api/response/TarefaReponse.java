package br.com.leonardocosta.gerenciadordetarefa.api.response;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TarefaReponse {

    private Long id;
    private String titulo;
    private String descricao;
    private Date prazo;
    private Long departamento;
    private int duracao;
    private Boolean finalizado;

}

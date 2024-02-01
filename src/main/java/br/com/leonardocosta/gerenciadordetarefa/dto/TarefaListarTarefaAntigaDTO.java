package br.com.leonardocosta.gerenciadordetarefa.dto;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TarefaListarTarefaAntigaDTO {

    private String titulo;

    private Date prazo;

    private Departamento departamento;

    private Boolean finalizado;


}

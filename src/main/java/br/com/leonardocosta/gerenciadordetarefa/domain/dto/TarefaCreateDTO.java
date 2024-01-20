package br.com.leonardocosta.gerenciadordetarefa.domain.dto;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TarefaCreateDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Date prazo;
    private Long departamento;
    private int duracao;
    private Boolean finalizado;


    public static Tarefa toModel(TarefaCreateDTO tarefaDTO) {
        Departamento departamento = new Departamento();
        departamento.setId(tarefaDTO.getDepartamento());
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(tarefaDTO.getTitulo());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setPrazo(tarefaDTO.getPrazo());
        tarefa.setDepartamento(departamento);
        tarefa.setDuracao(tarefaDTO.getDuracao());
        tarefa.setFinalizado(false);
        return tarefa;
    }

    public static TarefaCreateDTO fromModel(Tarefa tarefa) {
        TarefaCreateDTO dto = new TarefaCreateDTO();
        dto.setId(tarefa.getId());
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setPrazo(tarefa.getPrazo());
        dto.setDepartamento(tarefa.getDepartamento().getId());
        dto.setDuracao(tarefa.getDuracao());
        dto.setFinalizado(tarefa.getFinalizado());
        return dto;
    }

}

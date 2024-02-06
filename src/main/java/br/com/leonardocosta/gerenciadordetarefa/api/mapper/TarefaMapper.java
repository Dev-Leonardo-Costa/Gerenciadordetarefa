package br.com.leonardocosta.gerenciadordetarefa.api.mapper;

import br.com.leonardocosta.gerenciadordetarefa.api.request.TarefaRequest;
import br.com.leonardocosta.gerenciadordetarefa.api.response.TarefaReponse;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;

public class TarefaMapper {

    public static Tarefa toTarefa(TarefaRequest request) {
        Departamento departamento = new Departamento();
        departamento.setId(request.getDepartamento());
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(request.getTitulo());
        tarefa.setDescricao(request.getDescricao());
        tarefa.setPrazo(request.getPrazo());
        tarefa.setDepartamento(departamento);
        tarefa.setDuracao(request.getDuracao());
        return tarefa;
    }

    public static TarefaReponse toTarefaResponse(Tarefa tarefa) {
        TarefaReponse reponse = new TarefaReponse();
        reponse.setId(tarefa.getId());
        reponse.setTitulo(tarefa.getTitulo());
        reponse.setDescricao(tarefa.getDescricao());
        reponse.setPrazo(tarefa.getPrazo());
        reponse.setDepartamento(tarefa.getDepartamento().getId());
        reponse.setDuracao(tarefa.getDuracao());
        reponse.setFinalizado(tarefa.getFinalizado());
        return reponse;
    }
}

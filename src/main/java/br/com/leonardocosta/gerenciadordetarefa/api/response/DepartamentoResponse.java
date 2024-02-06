package br.com.leonardocosta.gerenciadordetarefa.api.response;

import br.com.leonardocosta.gerenciadordetarefa.api.request.DepartamentoRequest;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoResponse {

    private String departamento;

    private Integer qtdPessoas;

    private Integer qtdTarefas;

    public static List<DepartamentoResponse> getDepartamentoResponses(List<DepartamentoRequest> projections) {
        return projections.stream()
                .map(projection -> new DepartamentoResponse(
                        projection.getDepartamento(),
                        projection.getQtdPessoas(),
                        projection.getQtdTarefas()
                ))
                .collect(Collectors.toList());
    }

}

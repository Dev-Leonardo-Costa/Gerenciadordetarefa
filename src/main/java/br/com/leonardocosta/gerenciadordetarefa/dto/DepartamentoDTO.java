package br.com.leonardocosta.gerenciadordetarefa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoDTO {

    private String departamento;

    private Integer qtdPessoas;

    private Integer qtdTarefas;


    public static List<DepartamentoDTO> getDepartamentoDTOS(List<DepartamentoProjection> projections) {
        return projections.stream()
                .map(projection -> new DepartamentoDTO(
                        projection.getDepartamento(),
                        projection.getQtdPessoas(),
                        projection.getQtdTarefas()
                ))
                .collect(Collectors.toList());
    }
}

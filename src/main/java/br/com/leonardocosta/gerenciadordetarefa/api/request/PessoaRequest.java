package br.com.leonardocosta.gerenciadordetarefa.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequest {

    private String nome;

    private Long departamento;

}

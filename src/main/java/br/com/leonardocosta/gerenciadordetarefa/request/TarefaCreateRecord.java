package br.com.leonardocosta.gerenciadordetarefa.request;

import java.util.Date;

public record TarefaCreateRecord(

        String titulo,

        String descricao,

        String departamento,

        Date prazo,

        int duracao

) { }

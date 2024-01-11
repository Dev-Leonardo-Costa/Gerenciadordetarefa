package br.com.leonardocosta.gerenciadordetarefa.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record TarefaRecord(

        String titulo,

        String descricao,

        String departamento,

        Date prazo,

        int duracao,

        boolean finalizado
) { }

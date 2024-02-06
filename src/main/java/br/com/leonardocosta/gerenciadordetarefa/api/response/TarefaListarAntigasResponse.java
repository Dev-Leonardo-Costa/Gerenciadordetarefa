package br.com.leonardocosta.gerenciadordetarefa.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public interface TarefaListarAntigasResponse {

     String getTitulo();
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
     Date getPrazo();

     String getDepartamento();
     Boolean getFinalizado();

}

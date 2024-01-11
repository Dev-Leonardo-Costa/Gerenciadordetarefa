package br.com.leonardocosta.gerenciadordetarefa.domain.exception;

public class TarefaFinalizadaException extends RuntimeException{

    public TarefaFinalizadaException(String message) {
        super(message);
    }

}

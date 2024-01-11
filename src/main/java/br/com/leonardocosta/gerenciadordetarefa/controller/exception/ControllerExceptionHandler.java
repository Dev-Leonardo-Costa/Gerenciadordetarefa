package br.com.leonardocosta.gerenciadordetarefa.controller.exception;

import br.com.leonardocosta.gerenciadordetarefa.domain.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.StandarError;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.TarefaFinalizadaException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<StandarError> handleNotFoundException(final NotFoundException ex, final HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(
                        StandarError.builder()
                                .timestamp(now())
                                .status(NOT_FOUND.value())
                                .error(NOT_FOUND.getReasonPhrase())
                                .message(ex.getMessage())
                                .path(request.getRequestURI())
                                .build()
                );
    }

    @ExceptionHandler(TarefaFinalizadaException.class)
    ResponseEntity<StandarError> handleTarefaFinalizadaException(final TarefaFinalizadaException ex, final HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT)
                .body(
                        StandarError.builder()
                                .timestamp(now())
                                .status(CONFLICT.value())
                                .error(CONFLICT.getReasonPhrase())
                                .message(ex.getMessage())
                                .path(request.getRequestURI())
                                .build()
                );
    }



}

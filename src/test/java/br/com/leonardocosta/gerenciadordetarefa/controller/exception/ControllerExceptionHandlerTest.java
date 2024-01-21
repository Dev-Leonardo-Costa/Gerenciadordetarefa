package br.com.leonardocosta.gerenciadordetarefa.controller.exception;


import br.com.leonardocosta.gerenciadordetarefa.domain.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.StandarError;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.TarefaFinalizadaException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ControllerExceptionHandlerTest {

    private final ControllerExceptionHandler controllerExceptionHandler = new ControllerExceptionHandler();

    @Test
    void handleNotFoundException_ShouldReturnNotFoundResponse() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        NotFoundException notFoundException = new NotFoundException("Resource not found");

        ResponseEntity<StandarError> responseEntity = controllerExceptionHandler.handleNotFoundException(notFoundException, request);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Not Found", responseEntity.getBody().getError());
        assertEquals("Resource not found", responseEntity.getBody().getMessage());
        assertEquals(request.getRequestURI(), responseEntity.getBody().getPath());
    }

    @Test
    void handleTarefaFinalizadaException_ShouldReturnConflictResponse() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        var tarefaFinalizadaException = new TarefaFinalizadaException("Task already finished");

        ResponseEntity<StandarError> responseEntity = controllerExceptionHandler.handleTarefaFinalizadaException(tarefaFinalizadaException, request);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Conflict", responseEntity.getBody().getError());
        assertEquals("Task already finished", responseEntity.getBody().getMessage());
        assertEquals(request.getRequestURI(), responseEntity.getBody().getPath());
    }

}
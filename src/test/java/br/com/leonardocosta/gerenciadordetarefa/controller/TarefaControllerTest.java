package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.controller.impl.TarefaControllerImpl;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.TarefaCreateDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.TarefaListarTarefaAntigaProjection;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.TarefaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class TarefaControllerTest {

    public static final Long TAREFA_ID = 1L;
    public static final Long PESSOA_ID = 2L;
    @InjectMocks
    private TarefaService mockService;

    @Mock
    private TarefaControllerImpl tarefaController;

    @BeforeEach
    public void setUp() {
        mockService = mock(TarefaService.class);
        tarefaController = new TarefaControllerImpl(mockService);
    }

    @Test
    public void testRegistrarTarefa() {
        TarefaCreateDTO tarefaCreateDTO = new TarefaCreateDTO();
        when(mockService.salvar(any(TarefaCreateDTO.class))).thenReturn(tarefaCreateDTO);

        ResponseEntity<TarefaCreateDTO> response = tarefaController.registrarTarefa(tarefaCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tarefaCreateDTO, response.getBody());

        verify(mockService, times(1)).salvar(any(TarefaCreateDTO.class));
    }

    @Test
    public void testAlocar() {

        Tarefa tarefaAlocada = new Tarefa();

        // Ajuste para retornar um objeto Tarefa ao alocar
        when(mockService.alocar(eq(TAREFA_ID), eq(PESSOA_ID))).thenReturn(tarefaAlocada);

        ResponseEntity<Tarefa> response = tarefaController.alocar(TAREFA_ID, PESSOA_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(mockService, times(1)).alocar(eq(TAREFA_ID), eq(PESSOA_ID));
    }

    @Test
    public void testFinalizarTarefa() {
        Tarefa tarefaFinalizada = new Tarefa();

        when(mockService.finalizarTarefa(eq(TAREFA_ID))).thenReturn(tarefaFinalizada);

        ResponseEntity<Tarefa> response = tarefaController.finalizarTarefa(TAREFA_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(mockService, times(1)).finalizarTarefa(eq(TAREFA_ID));
    }

    @Test
    public void testListarTarefasPendentes() {
        List<TarefaListarTarefaAntigaProjection> tarefasPendentes = Arrays.asList();

        when(mockService.listarTarefasPendentesMaisAntigas()).thenReturn(tarefasPendentes);

        ResponseEntity<List<TarefaListarTarefaAntigaProjection>> response = tarefaController.listarTarefasPendentes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefasPendentes, response.getBody());

        verify(mockService, times(1)).listarTarefasPendentesMaisAntigas();
    }

}
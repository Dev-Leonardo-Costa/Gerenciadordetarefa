package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.TarefaCreateDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.TarefaListarTarefaAntigaProjection;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.TarefaFinalizadaException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Profile("test")
@SpringBootTest
class TarefaServiceTest {

    public static final Long TAREFA_ID = 1L;
    public static final Long PESSOA_ID = 2L;
    @InjectMocks
    private TarefaService tarefaService;

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        tarefaService = new TarefaService(tarefaRepository, pessoaService);
    }

    @Test
    void deveSalvarTarefaComSucesso() {
        TarefaCreateDTO tarefaCreateDTO = new TarefaCreateDTO();
        tarefaCreateDTO.setFinalizado(false);
        Tarefa tarefaSalva = new Tarefa();
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefaSalva);

        TarefaCreateDTO result = tarefaService.salvar(tarefaCreateDTO);

        assertNotNull(result);
        assertEquals(tarefaCreateDTO, result);
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void deveAlocarUmaTarefaParaPessoaComSucesso() {
        Tarefa tarefaExistente = new Tarefa();
        Pessoa pessoaExistente = new Pessoa();

        when(tarefaRepository.findById(TAREFA_ID)).thenReturn(java.util.Optional.of(tarefaExistente));
        when(pessoaService.buscarPorId(PESSOA_ID)).thenReturn(pessoaExistente);
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefaExistente);

        Tarefa result = tarefaService.alocar(TAREFA_ID, PESSOA_ID);

        assertNotNull(result);
        assertEquals(pessoaExistente, result.getPessoa());
        verify(tarefaRepository, times(1)).findById(TAREFA_ID);
        verify(pessoaService, times(1)).buscarPorId(PESSOA_ID);
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void deveBuscarUmaTarefaPorId() {
        Tarefa tarefaExistente = new Tarefa();
        when(tarefaRepository.findById(TAREFA_ID)).thenReturn(java.util.Optional.of(tarefaExistente));

        Tarefa result = tarefaService.buscarPorId(TAREFA_ID);

        assertNotNull(result);
        assertEquals(tarefaExistente, result);
        verify(tarefaRepository, times(1)).findById(TAREFA_ID);
    }
    @Test
    void deveRetonarUmaExcecaoQuandoBuscarUmaTarefaPorIdInexistente() {
        when(tarefaRepository.findById(TAREFA_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> tarefaService.buscarPorId(TAREFA_ID));
        verify(tarefaRepository, times(1)).findById(TAREFA_ID);
    }


    @Test
    void deveListarTarefasPendentesMaisAntigas() {
        List<TarefaListarTarefaAntigaProjection> tarefasPendentes = Collections.singletonList(mock(TarefaListarTarefaAntigaProjection.class));
        when(tarefaRepository.listarTarefasPendentesSemPessoa()).thenReturn(tarefasPendentes);

        List<TarefaListarTarefaAntigaProjection> result = tarefaService.listarTarefasPendentesMaisAntigas();

        verify(tarefaRepository, times(1)).listarTarefasPendentesSemPessoa();
        assertNotNull(result);

    }

    @Test
    void deveLancarNotFoundExceptionAoFinalizarTarefaInexistente() {
        when(tarefaRepository.findById(TAREFA_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> tarefaService.finalizarTarefa(TAREFA_ID));
    }

    @Test
    void deveLancarTarefaFinalizadaExceptionAoFinalizarTarefaJaConcluida() {

        Tarefa tarefa = new Tarefa();
        tarefa.setFinalizado(true);
        when(tarefaRepository.findById(TAREFA_ID)).thenReturn(Optional.of(tarefa));

        assertThrows(TarefaFinalizadaException.class, () -> tarefaService.finalizarTarefa(TAREFA_ID));
    }

}
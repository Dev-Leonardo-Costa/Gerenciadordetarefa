package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.api.controller.PessoaController;
import br.com.leonardocosta.gerenciadordetarefa.api.controller.impl.PessoaControllerImpl;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class PessoaControllerTest {

    public static final Long PESSOA_ID = 1L;
    PessoaController pessoaController;

    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        pessoaService = mock(PessoaService.class);
        pessoaController = new PessoaControllerImpl(pessoaService);
    }

//    @Test
//    public void deveRegistrarUmaPessoaComSucesso() {
//        PessoaCreateDTO pessoaCreateDTO = new PessoaCreateDTO();
//        when(pessoaService.salvar(any(PessoaCreateDTO.class))).thenReturn(pessoaCreateDTO);
//        ResponseEntity<PessoaCreateDTO> response = pessoaController.registrar(pessoaCreateDTO);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(pessoaCreateDTO, response.getBody());
//        verify(pessoaService, times(1)).salvar(any(PessoaCreateDTO.class));
//    }
//
//
//    @Test
//    public void deveRemoverUmaPessoa() {
//        Pessoa pessoaExiste = new Pessoa();
//        when(pessoaService.buscarPorId(eq(4L))).thenReturn(pessoaExiste);
//        ResponseEntity<Void> response = pessoaController.remover(4L);
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        verify(pessoaService, times(1)).removerPessoa(eq(pessoaExiste.getId()));
//    }
//
//    @Test
//    public void deveListarPessoa() {
//        List<PessoaDTO> pessoasDTO = new ArrayList<>(); // Inicialize a lista
//        when(pessoaService.listarInformacoesPessoas()).thenReturn(pessoasDTO);
//        ResponseEntity<List<PessoaDTO>> response = pessoaController.listarPessoa();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(pessoasDTO, response.getBody());
//        verify(pessoaService, times(1)).listarInformacoesPessoas();
//        verifyNoMoreInteractions(pessoaService);
//    }
//
//    @Test
//    public void deveCalcularMediaHorasPorTarefa() {
//        String nome = "Nome";
//        Date dataInicio = new Date();
//        Date dataFim = new Date();
//        List<PessoaPorNomeEPeriodoProjection> resultadoSimulado = new ArrayList<>();
//
//        when(pessoaService.buscarPessoasPorNomeEPeriodo(eq(nome), eq(dataInicio), eq(dataFim)))
//                .thenReturn(resultadoSimulado);
//
//        ResponseEntity<List<PessoaPorNomeEPeriodoProjection>> response =
//                pessoaController.calcularMediaHorasPorTarefa(nome, dataInicio, dataFim);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(resultadoSimulado, response.getBody());
//        verify(pessoaService, times(1)).buscarPessoasPorNomeEPeriodo(eq(nome), eq(dataInicio), eq(dataFim));
//        verifyNoMoreInteractions(pessoaService);
//    }

}
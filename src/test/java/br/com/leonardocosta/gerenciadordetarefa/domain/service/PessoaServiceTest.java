package br.com.leonardocosta.gerenciadordetarefa.domain.service;


import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaCreateDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaPorNomeEPeriodoProjection;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
@SpringBootTest
class PessoaServiceTest {

    private PessoaService pessoaService;
    private PessoaRepository pessoaRepository;
    private DepartamentoService departamentoService;

    @BeforeEach
    void setUp() {
        pessoaRepository = mock(PessoaRepository.class);
        departamentoService = mock(DepartamentoService.class);
        pessoaService = new PessoaService(pessoaRepository, departamentoService);
    }

    @Test
    void deveSalvarPessoaComSucesso() {
        PessoaCreateDTO pessoaDTO = new PessoaCreateDTO();
        pessoaDTO.setDepartamento(new Departamento().getId());
        when(departamentoService.buscarPorIdDepartamentoParaPessoa(anyLong())).thenReturn(new Departamento());
        when(pessoaRepository.save(any())).thenReturn(new Pessoa());

        PessoaCreateDTO resultDTO = pessoaService.salvar(pessoaDTO);

        assertNotNull(resultDTO);
    }


    @Test
    void deveBuscarPessoaPorIdComSucesso() {
        Long pessoaId = 1L;
        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(new Pessoa()));

        Pessoa result = pessoaService.buscarPorId(pessoaId);

        assertNotNull(result);
    }

    @Test
    void deveRemoverPessoaComSucesso() {
        Long pessoaId = 1L;

        pessoaService.removerPessoa(pessoaId);

        verify(pessoaRepository, times(1)).deleteById(pessoaId);
    }

    @Test
    void deveAlterarPessoaComSucesso() {
        Long pessoaId = 1L;
        PessoaCreateDTO pessoaDTO = new PessoaCreateDTO();
        pessoaDTO.setNome("Novo Nome");
        pessoaDTO.setDepartamento(new Departamento().getId());
        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(new Pessoa()));
        when(departamentoService.buscarPorId(any())).thenReturn(new Departamento());
        when(pessoaRepository.save(any())).thenReturn(new Pessoa());

        Pessoa result = pessoaService.alterar(pessoaId, pessoaDTO);
        assertNotNull(result);
    }

//    @Test
//    void deveListarInformacoesPessoasComSucesso() {
//        when(pessoaRepository.listarPessoas()).thenReturn(Collections.singletonList(new PessoaDTO()));
//
//        List<PessoaDTO> result = pessoaService.listarInformacoesPessoas();
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//    }

    @Test
    void deveBuscarPessoasPorNomeEPeriodoComSucesso() {
        String nome = "John Doe";
        Date dataInicio = new Date();
        Date dataFim = new Date();
        when(pessoaRepository.buscarPessoasPorNomeEPeriodo(nome, dataInicio, dataFim)).thenReturn(
                Collections.singletonList(mock(PessoaPorNomeEPeriodoProjection.class))
        );

        List<PessoaPorNomeEPeriodoProjection> result = pessoaService.buscarPessoasPorNomeEPeriodo(nome, dataInicio, dataFim);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

}
package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaCreateDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaGastosDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PessoaServiceTest {

    public static final Long PESSOA_ID = 1L;
    @InjectMocks
    private PessoaService service;

    @Mock
    private PessoaRepository repository;

//    @Test
//    void salvarPessoaDeveRetornarPessoaSalva() {
//        PessoaCreateDTO pessoaParaSalvar = new PessoaCreateDTO();
//        when(repository.save(any(Pessoa.class))).thenReturn(new Pessoa());
//
//        Pessoa pessoaSalva = service.salvar(pessoaParaSalvar).toModel();
//
//        assertNotNull(pessoaSalva);
//        verify(repository, times(1)).save(any(Pessoa.class));
//    }

    @Test
    void buscarPorIdPessoaExistenteDeveRetornarPessoa() {

        Pessoa pessoaExistente = new Pessoa();
        when(repository.findById(PESSOA_ID)).thenReturn(Optional.of(pessoaExistente));

        Pessoa pessoaRetornada = service.buscarPorId(PESSOA_ID);

        assertNotNull(pessoaRetornada);
        verify(repository, times(1)).findById(PESSOA_ID);
    }

    @Test
    void buscarPorIdPessoaNaoExistenteDeveLancarNotFoundException() {

        when(repository.findById(PESSOA_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.buscarPorId(PESSOA_ID));
        verify(repository, times(1)).findById(PESSOA_ID);
    }

    @Test
    void removerPessoaDeveChamarRepositoryDeleteById() {

        service.removerPessoa(PESSOA_ID);

        verify(repository, times(1)).deleteById(PESSOA_ID);
    }

//    @Test
//    void alterarPessoaExistenteDeveRetornarPessoaAlterada() {
//        Pessoa pessoaExistente = new Pessoa();
//        Pessoa pessoaAlterada = new Pessoa();
//        when(repository.findById(PESSOA_ID)).thenReturn(Optional.of(pessoaExistente));
//        when(repository.save(any(Pessoa.class))).thenReturn(pessoaAlterada);
//
//        Pessoa pessoaRetornada = service.alterar(PESSOA_ID, new Pessoa());
//
//        assertNotNull(pessoaRetornada);
//        verify(repository, times(1)).findById(PESSOA_ID);
//        verify(repository, times(1)).save(any(Pessoa.class));
//    }

//    @Test
//    void alterarPessoaNaoExistenteDeveLancarNotFoundException() {
//        when(repository.findById(PESSOA_ID)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> service.alterar(PESSOA_ID, new Pessoa()));
//        verify(repository, times(1)).findById(PESSOA_ID);
//        verify(repository, never()).save(any(Pessoa.class));
//    }

    @Test
    void listarInformacoesPessoasDeveRetornarListaDePessoaDTO() {
        List<Pessoa> pessoas = new ArrayList<>();
        when(repository.findAll()).thenReturn(pessoas);

        List<PessoaDTO> pessoasDTO = service.listarInformacoesPessoas();

        assertNotNull(pessoasDTO);
        verify(repository, times(1)).findAll();
    }

    @Test
    void buscarPessoasPorNomeEPeriodoDeveRetornarListaDeDTOs() {
        when(repository.buscarPessoasPorNomeEPeriodo(anyString(), any(Date.class), any(Date.class))).thenReturn(getResultadosMock());

        // Execução do método a ser testado
        List<PessoaGastosDTO> dtos = service.buscarPessoasPorNomeEPeriodo("Nome", new Date(), new Date());

        assertNotNull(dtos);
        assertFalse(dtos.isEmpty());
        assertEquals(2, dtos.size());

        assertEquals("Nome1", dtos.get(0).getNome());
        assertEquals(10.0, dtos.get(0).getMediaHorasGastas());

        assertEquals("Nome2", dtos.get(1).getNome());
        assertEquals(15.0, dtos.get(1).getMediaHorasGastas());
    }

    private List<Object[]> getResultadosMock() {
        List<Object[]> resultados = new ArrayList<>();
        resultados.add(new Object[]{"Nome1", 10.0});
        resultados.add(new Object[]{"Nome2", 15.0});
        return resultados;
    }
}
package br.com.leonardocosta.gerenciadordetarefa.controller.impl;

import br.com.leonardocosta.gerenciadordetarefa.controller.PessoaController;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaGastosDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;


@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaControllerImpl implements PessoaController {

    private final PessoaService service;

    @Override
    public ResponseEntity<Void> registrar(final Pessoa pessoa) {
        service.salvar(pessoa);
        return ResponseEntity.status(CREATED.value()).build();
    }

    @Override
    public ResponseEntity<Pessoa> alterar(final Long pessoaId, final Pessoa pessoa) {
        service.alterar(pessoaId, pessoa);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<?> remover(final Long pessoaId) {
        Pessoa pessoaExiste = service.buscarPorId(pessoaId);
        service.removerPessoa(pessoaExiste.getId());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<PessoaDTO>> listarPessoa() {
        List<PessoaDTO> pessoasDTO = service.listarInformacoesPessoas();
        return ResponseEntity.ok(pessoasDTO);
    }

    @Override
    public ResponseEntity<List<PessoaGastosDTO>> buscarPessoasPorNomeEPeriodo(String nome, Date dataInicio, Date dataFim) {
        List<PessoaGastosDTO> pessoasGastos = service.buscarPessoasPorNomeEPeriodo(nome, dataInicio, dataFim);
        return ResponseEntity.ok(pessoasGastos);
    }


}

package br.com.leonardocosta.gerenciadordetarefa.api.controller.impl;

import br.com.leonardocosta.gerenciadordetarefa.api.controller.PessoaController;
import br.com.leonardocosta.gerenciadordetarefa.api.mapper.PessoaMapper;
import br.com.leonardocosta.gerenciadordetarefa.api.request.PessoaRequest;
import br.com.leonardocosta.gerenciadordetarefa.api.response.PessoaPorNomeEPeriodoResponse;
import br.com.leonardocosta.gerenciadordetarefa.api.response.PessoaResponse;
import br.com.leonardocosta.gerenciadordetarefa.api.response.PessoaResponseList;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;


@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaControllerImpl implements PessoaController {

    private final PessoaService service;

    @Override
    public ResponseEntity<PessoaResponse> registrar(PessoaRequest request) {
        Pessoa pessoa = PessoaMapper.toPessoa(request);
        Pessoa pessoaSalva = service.salvar(pessoa);
        PessoaResponse response = PessoaMapper.toPessoaResponse(pessoaSalva);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<PessoaResponse> alterar(Long pessoaId, PessoaRequest request) {
        Pessoa pessoa = PessoaMapper.toPessoa(request);
        Pessoa pessoaAlterada = service.alterar(pessoaId, pessoa);
        PessoaResponse response = PessoaMapper.toPessoaResponse(pessoaAlterada);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<Void> remover(final Long pessoaId) {
        Pessoa pessoaExiste = service.buscarPorId(pessoaId);
        service.removerPessoa(pessoaExiste.getId());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<PessoaResponseList>> listarPessoa() {
        return ResponseEntity.ok(service.listarInformacoesPessoas());
    }

    @Override
    public ResponseEntity<List<PessoaPorNomeEPeriodoResponse>> buscarMediaHorasPorTarefa(String nome, Date dataInicio, Date dataFim) {
        return ResponseEntity.ok(service.buscarPessoasPorNomeEPeriodo(nome, dataInicio, dataFim));
    }

}

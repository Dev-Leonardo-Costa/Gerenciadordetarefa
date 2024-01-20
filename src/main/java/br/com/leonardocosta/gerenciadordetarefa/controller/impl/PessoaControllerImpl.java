package br.com.leonardocosta.gerenciadordetarefa.controller.impl;

import br.com.leonardocosta.gerenciadordetarefa.controller.PessoaController;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaCreateDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;


@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaControllerImpl implements PessoaController {

    private final PessoaService service;

    @Override
    public ResponseEntity<PessoaCreateDTO> registrar(final PessoaCreateDTO pessoa) {
        final PessoaCreateDTO pessoaSalvo = service.salvar(pessoa);
        return ResponseEntity.status(CREATED).body(pessoaSalvo);
    }

    @Override
    public ResponseEntity<PessoaCreateDTO> alterar(final Long pessoaId, final PessoaCreateDTO pessoaCreateDTO) {
        final Pessoa alterar = service.alterar(pessoaId, pessoaCreateDTO);
        final PessoaCreateDTO createDTO = PessoaCreateDTO.fromModel(alterar);
        return ResponseEntity.ok(createDTO);
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
    public List<Pessoa> listarPessoas() {
        return service.listarPessoas();
    }

}

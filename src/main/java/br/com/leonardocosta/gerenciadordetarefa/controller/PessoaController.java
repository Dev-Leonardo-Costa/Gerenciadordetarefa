package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface PessoaController {

    @PostMapping
    ResponseEntity<Void> registrar(@RequestBody final Pessoa pessoa);

    @GetMapping("/{pessoaId}")
    ResponseEntity<Pessoa> buscar(@PathVariable final Long pessoaId);

    @PutMapping("/{pessoaId}")
    ResponseEntity<Pessoa> alterar(@PathVariable final Long pessoaId, @RequestBody final Pessoa pessoa);

}

package br.com.leonardocosta.gerenciadordetarefa.api.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    public Pessoa registrar(@RequestBody Pessoa pessoa) {
        return service.salvar(pessoa);
    }

    @GetMapping("/{pessoaId}")
    public Pessoa buscarPessoa(@PathVariable Long pessoaId) {
        return service.buscar(pessoaId);
    }

}

package br.com.leonardocosta.gerenciadordetarefa.controller.impl;

import br.com.leonardocosta.gerenciadordetarefa.controller.PessoaController;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestController
@RequestMapping("/pessoas")
public class PessoaControllerImpl implements PessoaController {
    private final PessoaService service;

    @Autowired
    public PessoaControllerImpl(PessoaService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Void> registrar(final Pessoa pessoa) {
        service.salvar(pessoa);
        return ResponseEntity.status(CREATED.value()).build();
    }

    //Regra é essa => Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.
    @Override
    public ResponseEntity<Pessoa> buscar(final Long pessoaId) {
        return ResponseEntity.ok(service.buscarPorId(pessoaId));
    }

    @Override
    public ResponseEntity<Pessoa> alterar(final Long pessoaId, final Pessoa pessoa) {

        Pessoa pessoaExistente = service.buscarPorId(pessoaId);

        if (pessoaExistente != null) {
            pessoaExistente.setNome(pessoa.getNome());
            service.salvar(pessoaExistente);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> remover(final Long pessoaId) {
        Pessoa pessoaExiste = service.buscarPorId(pessoaId);
        service.removerPessoa(pessoaExiste.getId());
        return ResponseEntity.noContent().build();
    }

}

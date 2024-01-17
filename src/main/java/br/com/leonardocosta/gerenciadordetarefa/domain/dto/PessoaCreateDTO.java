package br.com.leonardocosta.gerenciadordetarefa.domain.dto;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class PessoaCreateDTO {

    private String nome;
    private String departamento;

    public Pessoa toModel() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setDepartamento(departamento);
        return pessoa;
    }

    public PessoaCreateDTO fromModel(Pessoa pessoaSalva) {
        nome = pessoaSalva.getNome();
        departamento = pessoaSalva.getDepartamento();
        return this;
    }
}

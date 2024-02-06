package br.com.leonardocosta.gerenciadordetarefa.api.mapper;

import br.com.leonardocosta.gerenciadordetarefa.api.request.PessoaRequest;
import br.com.leonardocosta.gerenciadordetarefa.api.response.PessoaResponse;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;

public class PessoaMapper {

    public static Pessoa toPessoa(PessoaRequest request) {
        Departamento departamento = new Departamento();
        departamento.setId(request.getDepartamento());

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.getNome());
        pessoa.setDepartamento(departamento);
        return pessoa;
    }

    public static PessoaResponse toPessoaResponse(Pessoa pessoa) {
        PessoaResponse response = new PessoaResponse();
        response.setId(pessoa.getId());
        response.setNome(pessoa.getNome());
        response.setDepartamento(pessoa.getDepartamento().getId());
        return response;
    }


}

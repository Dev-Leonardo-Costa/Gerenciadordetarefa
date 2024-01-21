package br.com.leonardocosta.gerenciadordetarefa.domain.dto;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import ch.qos.logback.classic.spi.LoggingEventVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PessoaCreateDTO {

    private String nome;
    private Long departamento;

    public static Pessoa toModel(PessoaCreateDTO createDTO) {
       Pessoa pessoa = new Pessoa();
       Departamento departamento = new Departamento();
       departamento.setId(createDTO.getDepartamento());
       pessoa.setNome(createDTO.getNome());
       pessoa.setDepartamento(departamento);
       return pessoa;
    }
    public static PessoaCreateDTO fromModel(Pessoa pessoa) {
       PessoaCreateDTO dto = new PessoaCreateDTO();
       dto.setNome(pessoa.getNome());
       dto.setDepartamento(pessoa.getDepartamento().getId());
       return dto;
    }

}

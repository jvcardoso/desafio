package br.com.jvcardoso.desafio.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscricaoDTO {
    private Long id;
    @NotNull(message = "O ID do Aluno deve ser informado")
    private Long alunoId;
    @NotNull(message = "O ID do Curso deve ser informado")
    private Long cursoId;
    private LocalDate dataInscricao;
}

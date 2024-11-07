package br.com.jvcardoso.desafio.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoDTO {
    private Long id;
    private String nome;
    private String email;
    private LocalDate dataCadastro;
}

package br.com.jvcardoso.desafio.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoDTO {
     private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataCriacao;
}

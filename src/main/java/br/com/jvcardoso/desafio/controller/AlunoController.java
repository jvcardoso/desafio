package br.com.jvcardoso.desafio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import br.com.jvcardoso.desafio.dto.AlunoDTO;
import br.com.jvcardoso.desafio.model.Aluno;
import br.com.jvcardoso.desafio.populator.InscricaoPopulator;
import br.com.jvcardoso.desafio.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private InscricaoPopulator populator;
    
    @PostMapping
    public ResponseEntity<Object> cadastrarAluno(@Valid @RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.cadastrarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(populator.toAlunoDTO(novoAluno));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        List<AlunoDTO> alunos = alunoService.listarTodosAlunos().stream()
            .map(populator::toAlunoDTO)
            .collect(Collectors.toList());
        if (alunos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alunos);
    }
}

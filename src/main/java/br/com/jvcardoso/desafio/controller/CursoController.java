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

import br.com.jvcardoso.desafio.dto.CursoDTO;
import br.com.jvcardoso.desafio.model.Curso;
import br.com.jvcardoso.desafio.populator.InscricaoPopulator;
import br.com.jvcardoso.desafio.service.CursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {
     @Autowired
    private CursoService cursoService;
    @Autowired
    private InscricaoPopulator populator;

    @PostMapping
    public ResponseEntity<Object> cadastrarCurso(@Valid @RequestBody Curso curso) {
        Curso novoCurso = cursoService.cadastrarCurso(curso);
        //return ResponseEntity.ok(populator.toCursoDTO(novoCurso));
        return ResponseEntity.status(HttpStatus.CREATED).body(populator.toCursoDTO(novoCurso));
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarCursos() {
        List<CursoDTO> cursos = cursoService.listarTodosCursos().stream()
            .map(populator::toCursoDTO)
            .collect(Collectors.toList());
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }    
        return ResponseEntity.ok(cursos);
    }
}

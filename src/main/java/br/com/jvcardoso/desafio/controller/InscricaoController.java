package br.com.jvcardoso.desafio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jvcardoso.desafio.dto.AlunoDTO;
import br.com.jvcardoso.desafio.dto.CursoDTO;
import br.com.jvcardoso.desafio.dto.InscricaoRequestDTO;
import br.com.jvcardoso.desafio.exception.AlunoNotFoundException;
import br.com.jvcardoso.desafio.exception.CursoNotFoundException;
import br.com.jvcardoso.desafio.exception.InscricaoException;
import br.com.jvcardoso.desafio.messgage.ErrorResponse;
import br.com.jvcardoso.desafio.model.Inscricao;
import br.com.jvcardoso.desafio.populator.InscricaoPopulator;
import br.com.jvcardoso.desafio.service.InscricaoService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {
     @Autowired
    private InscricaoService inscricaoService;
    @Autowired
    private InscricaoPopulator populator;

    @PostMapping
    public ResponseEntity<Object> inscreverAluno(@Valid @RequestBody InscricaoRequestDTO inscricaoRequest) {
        try {
            Inscricao inscricao = inscricaoService.inscreverAluno(inscricaoRequest.getAlunoId(), inscricaoRequest.getCursoId());
            //return ResponseEntity.ok(populator.toInscricaoDTO(inscricao));
            return ResponseEntity.status(HttpStatus.CREATED).body(populator.toInscricaoDTO(inscricao));

        }catch (AlunoNotFoundException | CursoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(ErrorResponse.errorResponse(e.getMessage()));
        } catch (InscricaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(ErrorResponse.errorResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao processar dados recebidos."));
        }
        
    }

    
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<CursoDTO>> listarCursosDoAluno(@PathVariable Long alunoId) {
        List<CursoDTO> cursos = inscricaoService.listarInscricoesPorAluno(alunoId).stream()
            .map(populator::toCursoDTOInscricao) 
            .collect(Collectors.toList());
        
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<AlunoDTO>> listarAlunosDoCurso(@PathVariable Long cursoId) {
        List<AlunoDTO> alunos = inscricaoService.listarInscricoesPorCurso(cursoId).stream()
            .map(populator::toAlunoDTOInscricao)
            .collect(Collectors.toList());
        
        if (alunos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }    
        return ResponseEntity.ok(alunos);
    }
}

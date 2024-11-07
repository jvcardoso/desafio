package br.com.jvcardoso.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jvcardoso.desafio.model.Curso;
import br.com.jvcardoso.desafio.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso cadastrarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> listarTodosCursos() {
        return cursoRepository.findAll();
    }
    
}

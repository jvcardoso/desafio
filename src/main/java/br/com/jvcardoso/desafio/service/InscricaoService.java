package br.com.jvcardoso.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jvcardoso.desafio.exception.AlunoNotFoundException;
import br.com.jvcardoso.desafio.exception.CursoNotFoundException;
import br.com.jvcardoso.desafio.model.Aluno;
import br.com.jvcardoso.desafio.model.Curso;
import br.com.jvcardoso.desafio.model.Inscricao;
import br.com.jvcardoso.desafio.repository.AlunoRepository;
import br.com.jvcardoso.desafio.repository.CursoRepository;
import br.com.jvcardoso.desafio.repository.InscricaoRepository;

@Service
public class InscricaoService {
     @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public Inscricao inscreverAluno(Long alunoId, Long cursoId) {
            Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoNotFoundException("ID informado de Aluno não esta cadastrado, não foi possivel realizar a inscrição"));
            Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new CursoNotFoundException("ID informado de Curso não esta cadastrado, não foi possivel realizar a inscrição"));

            Inscricao inscricao = new Inscricao();
            inscricao.setAluno(aluno);
            inscricao.setCurso(curso);
            return inscricaoRepository.save(inscricao);    
    }

    public List<Inscricao> listarInscricoesPorAluno(Long alunoId) {
        return inscricaoRepository.findByAlunoId(alunoId);
    }

    public List<Inscricao> listarInscricoesPorCurso(Long cursoId) {
        return inscricaoRepository.findByCursoId(cursoId);
    }
}

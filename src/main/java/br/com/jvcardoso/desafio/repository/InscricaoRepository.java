package br.com.jvcardoso.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jvcardoso.desafio.model.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long>{

     List<Inscricao> findByAlunoId(Long alunoId);
     List<Inscricao> findByCursoId(Long cursoId);

}

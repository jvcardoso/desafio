package br.com.jvcardoso.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jvcardoso.desafio.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}

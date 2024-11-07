package br.com.jvcardoso.desafio.populator;

import org.springframework.stereotype.Component;

import br.com.jvcardoso.desafio.model.Aluno;
import br.com.jvcardoso.desafio.dto.AlunoDTO;
import br.com.jvcardoso.desafio.model.Curso;
import br.com.jvcardoso.desafio.dto.CursoDTO;
import br.com.jvcardoso.desafio.model.Inscricao;
import br.com.jvcardoso.desafio.dto.InscricaoDTO;

@Component
public class InscricaoPopulator {

     public AlunoDTO toAlunoDTO(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        dto.setDataCadastro(aluno.getDataCadastro());
        return dto;
    }

    public CursoDTO toCursoDTO( Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNome(curso.getNome());
        dto.setDescricao(curso.getDescricao());
        dto.setDataCriacao(curso.getDataCriacao());
        return dto;
    }

    public AlunoDTO toAlunoDTOInscricao(Inscricao inscricao) {
        Aluno aluno = inscricao.getAluno();
        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        dto.setDataCadastro(aluno.getDataCadastro());
        return dto;
    }

    public CursoDTO toCursoDTOInscricao(Inscricao inscricao) {
        Curso curso = inscricao.getCurso(); 
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNome(curso.getNome());
        dto.setDescricao(curso.getDescricao());
        dto.setDataCriacao(curso.getDataCriacao());
        return dto;
    }

    public InscricaoDTO toInscricaoDTO(Inscricao inscricao) {
        InscricaoDTO dto = new InscricaoDTO();
        dto.setId(inscricao.getId());
        dto.setAlunoId(inscricao.getAluno().getId());
        dto.setCursoId(inscricao.getCurso().getId());
        dto.setDataInscricao(inscricao.getDataInscricao());
        return dto;
    }
}

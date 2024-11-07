package br.com.jvcardoso.desafio.dto;

public class InscricaoRequestDTO {
    private Long alunoId;
    private Long cursoId;

    // Getters e setters
    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}

package beforg.lumostudy.api.domain.materia;

import jakarta.validation.constraints.NotBlank;

public record MateriaDTO(@NotBlank String nome, String categoria, String cod, int estudosRegistrados) {
    public MateriaDTO(Materia materia) {
        this(materia.getNome(), materia.getCategoria(), materia.getCod(), materia.getEstudosRegistrados());
    }
}

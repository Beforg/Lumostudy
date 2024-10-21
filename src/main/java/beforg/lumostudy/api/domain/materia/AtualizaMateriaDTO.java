package beforg.lumostudy.api.domain.materia;

import jakarta.validation.constraints.NotBlank;

public record AtualizaMateriaDTO(@NotBlank String cod, @NotBlank String nome, String categoria) {
}
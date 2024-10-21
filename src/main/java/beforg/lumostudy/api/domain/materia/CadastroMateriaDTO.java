package beforg.lumostudy.api.domain.materia;

import jakarta.validation.constraints.NotBlank;

public record CadastroMateriaDTO(@NotBlank String nome, String categoria) {
}

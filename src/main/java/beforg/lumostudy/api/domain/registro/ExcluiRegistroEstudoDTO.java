package beforg.lumostudy.api.domain.registro;

import jakarta.validation.constraints.NotBlank;

public record ExcluiRegistroEstudoDTO(@NotBlank String codRegistro) {
}

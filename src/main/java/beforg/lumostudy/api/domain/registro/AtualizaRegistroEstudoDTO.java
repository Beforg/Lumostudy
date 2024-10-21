package beforg.lumostudy.api.domain.registro;

import jakarta.validation.constraints.NotBlank;

public record AtualizaRegistroEstudoDTO(
        @NotBlank String codRegistro,
        @NotBlank String conteudo,
        @NotBlank String descricao,
        @NotBlank String codMateria) {
}

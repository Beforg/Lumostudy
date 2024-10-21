package beforg.lumostudy.api.domain.registro;

import jakarta.validation.constraints.NotBlank;

public record CadastroRegistroEstudoDTO(
        @NotBlank String tempo,
        @NotBlank String conteudo,
        @NotBlank String descricao,
        @NotBlank String codMateria) {
}

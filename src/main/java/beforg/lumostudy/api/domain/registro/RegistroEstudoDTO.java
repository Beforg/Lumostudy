package beforg.lumostudy.api.domain.registro;

import jakarta.validation.constraints.NotBlank;

public record RegistroEstudoDTO(
        @NotBlank String tempo,
        @NotBlank String conteudo,
        @NotBlank String descricao,
        @NotBlank String codMateria,
        @NotBlank String codRegistro) {
    public RegistroEstudoDTO(RegistroEstudo r) {
        this(r.getTempo(), r.getConteudo(), r.getDescricao(), r.getMateria().getCod(), r.getCod());
    }
}

package beforg.lumostudy.api.domain.registro;

import jakarta.validation.constraints.NotBlank;

public record ReesDTO(
        @NotBlank String tempo,
        @NotBlank String conteudo,
        @NotBlank String descricao,
        @NotBlank String codMateria,
        @NotBlank String codRegistro,
        @NotBlank String nomeMateria,
        @NotBlank String data) {
    public ReesDTO(Rees r) {
        this(r.getTempo(), r.getConteudo(), r.getDescricao(), r.getMateria().getCod(), r.getCod(), r.getMateria().getNome(), r.getData());
    }
}

package beforg.lumostudy.api.domain.cronograma;

public record CronogramaDTO(String cod, String data, String conteudo, String descricao, String materiaCod, boolean concluido) {
    public CronogramaDTO(Cronograma cronograma) {
        this(cronograma.getCod(), cronograma.getData(), cronograma.getConteudo(), cronograma.getDescricao(), cronograma.getMateria().getCod(), cronograma.isConcluido());
    }
}

package beforg.lumostudy.api.infra.exception;

public class CronogramaNotFoundException extends RuntimeException {
    public CronogramaNotFoundException(String message) {
        super(message);
    }
}

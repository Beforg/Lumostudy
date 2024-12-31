package beforg.lumostudy.api.infra.exception;

public class ContaNotFoundException extends RuntimeException {
    public ContaNotFoundException(String message) {
        super(message);
    }
}

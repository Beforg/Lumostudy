package beforg.lumostudy.api.domain.user;

public enum UpdateType {
    PASSWORD("senha"),
    EMAIL("email"),
    NAME("nome"),
    USERNAME("username");

    public final String value;

    UpdateType(String value) {
        this.value = value;
    }

    public static UpdateType fromValue(String value) {
        for (UpdateType updateType : UpdateType.values()) {
            if (updateType.value.equals(value)) {
                return updateType;
            }
        }
        throw new IllegalArgumentException("Tipo de operação inválida: " + value);
    }
}

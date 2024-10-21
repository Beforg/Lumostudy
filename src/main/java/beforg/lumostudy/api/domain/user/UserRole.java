package beforg.lumostudy.api.domain.user;

public enum UserRole {
    ADMIN("admin"),
    DEFAULT("default"),
    PREMIUM("premium");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

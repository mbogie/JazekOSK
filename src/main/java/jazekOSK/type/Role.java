package jazekOSK.type;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    STUDENT("ROLE_STUDENT"),
    INSTRUCTOR("ROLE_INSTRUCTOR");

    private String role;

    Role(String role) {
        this.role=role;
    }

    public String getRole() {
        return role;
    }
}

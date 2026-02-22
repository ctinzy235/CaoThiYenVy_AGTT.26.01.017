package EnumRailway;

public enum RegisterField {
	EMAIL("email"),
    PASSWORD("password"),
    CONFIRM_PASSWORD("confirmPassword"),
    PID("pid");

    private String id;

    RegisterField(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

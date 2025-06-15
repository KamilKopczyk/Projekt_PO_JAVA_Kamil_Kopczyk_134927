package Services.Lakes;

public enum Enum {
    SPININGOWE(3),
    SPLAWIKOWE(1),
    GRUNTOWE(2);

    private final int value;

    Enum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Enum fromValue(int value) {
        for (Enum type : Enum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Nieprawidłowa wartość: " + value);
    }
}
package Services.Wędki;

public enum Enum2 {
    SPININGOWA(1),
    SPLAWIKOWA(2),
    GRUNTOWA(3);

    private final int value;

    Enum2(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Enum2 fromValue(int value) {
        for (Enum2 type : Enum2.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Nieprawidłowa wartość: " + value);
    }
}
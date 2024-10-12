package enums;

public enum VAT {
    CLUE(10),
    DECORATION(21);

    final int value;

    VAT(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

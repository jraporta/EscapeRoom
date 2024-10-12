package enums;

public enum Difficulty implements Printable {

    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String difficulty;

    Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String print() {
        return difficulty;
    }
}

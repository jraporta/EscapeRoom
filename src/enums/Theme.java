package enums;

public enum Theme implements Printable {
    HORROR("Horror"),
    CRIME("Crime-solving"),
    GORE("Gore and blood-lust"),
    FANTASY("Fantasy"),
    SCI_FI("Science fiction");

    private final String theme;

    Theme(String theme) {
        this.theme = theme;
    }

    @Override
    public String print() {
        return theme;
    }
}

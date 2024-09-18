package objects;

public class Clue extends Element {

    public enum Theme{
        HORROR,
        CRIME,
        GORE,
        FANTASY,
        SCIFI;
    }

    private static final int IVA = 10;
    private int expectedSeconds;
    private Clue.Theme theme;

    public Clue(String name, double price, int expectedSeconds, Clue.Theme theme) {
        super(name, price * Clue.IVA / 100);
        this.expectedSeconds = expectedSeconds;
        this.theme = theme;
    }

    public int getExpectedSeconds() {
        return expectedSeconds;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setExpectedSeconds(int expectedSeconds) {
        this.expectedSeconds = expectedSeconds;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Clue{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", price=" + super.getPrice() +
                "expectedSeconds=" + expectedSeconds +
                ", theme=" + theme +
                '}';
    }
}

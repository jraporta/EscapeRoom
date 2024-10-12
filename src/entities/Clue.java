package entities;

import enums.Theme;
import enums.VAT;

public class Clue extends Element{

    private static int staticId;
    private final int expectedMinutesToSolve;
    private final Theme theme;

    public Clue(String name, double price, int expectedMinutesToSolve, Theme theme) {
        super(++staticId, name, price * (100 + VAT.CLUE.getValue()) / 100.0);
        this.expectedMinutesToSolve = expectedMinutesToSolve;
        this.theme = theme;
    }

    public int getExpectedMinutesToSolve() {
        return expectedMinutesToSolve;
    }

    public Theme getTheme() {
        return theme;
    }

}

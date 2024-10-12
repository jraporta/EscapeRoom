package enums;

public enum Material implements Printable {
    PLASTIC("Plastic"),
    METAL("Metal"),
    FOAM("Foam"),
    CARDBOARD("Cardboard or paper"),
    STONE("Stone");

    private final String material;

    Material(String material) {
        this.material = material;
    }

    @Override
    public String print() {
        return material;
    }
}

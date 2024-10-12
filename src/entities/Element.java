package entities;

public abstract class Element implements HasName, HasId{

    private final int id;
    private final String name;
    private final double price;

    public Element(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

}

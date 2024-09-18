package objects;

public class Decoration extends Element {

    public enum Material{
        PLASTIC,
        METAL,
        FOAM,
        CARDBOARD,
        STONE;
    }

    private static final int IVA = 21;
    private Decoration.Material material;

    public Decoration(String name, double price, Decoration.Material material) {
        super(name, price * Decoration.IVA / 100);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Decoration{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", price=" + super.getPrice() +
                "material=" + material +
                '}';
    }
}

package entities;

import enums.Material;
import enums.VAT;

public class Decoration extends Element {

    private static int staticId;
    private final Material material;

    public Decoration(String name, double price, Material material) {
        super(++staticId, name, price * (100 + VAT.DECORATION.getValue()) / 100.0);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

}

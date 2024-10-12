package entities;

public class InventoryEntry<T extends Element> {

    private final T element;
    private int quantity;

    public InventoryEntry(T element, int quantity) {
        this.element = element;
        this.quantity = quantity;
    }

    public T getElement() {
        return element;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

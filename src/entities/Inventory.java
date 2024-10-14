package entities;

import exceptions.InsufficientQuantityException;
import exceptions.CheckedNoSuchElementException;

import java.util.*;

public class Inventory {

    private final List<InventoryEntry<Clue>> clueInventory;
    private final List<InventoryEntry<Decoration>> decorationInventory;

    {
        clueInventory = new ArrayList<>();
        decorationInventory = new ArrayList<>();
    }

    public List<InventoryEntry<Clue>> getClueInventory() {
        return clueInventory;
    }

    public List<InventoryEntry<Decoration>> getDecorationInventory() {
        return decorationInventory;
    }

    public <T extends Element> void addElement(T element, int quantity) {
        if (element instanceof Clue){
            addClue((Clue) element, quantity);
        }else {
            addDecoration((Decoration) element, quantity);
        }
    }

    private void addClue(Clue clue, int quantity) {
        try {
            InventoryEntry<Clue> inventoryEntry = getInventoryEntry(clue, clueInventory);
            inventoryEntry.setQuantity(inventoryEntry.getQuantity() + quantity);
        } catch (CheckedNoSuchElementException e) {
            clueInventory.add(new InventoryEntry<>(clue, quantity));
            clueInventory.sort(Comparator.comparingInt(c -> (c.getElement().getId())));
        }
    }

    private void addDecoration(Decoration decoration, int quantity) {
        try {
            InventoryEntry<Decoration> inventoryEntry = getInventoryEntry(decoration, decorationInventory);
            inventoryEntry.setQuantity(inventoryEntry.getQuantity() + quantity);
        } catch (CheckedNoSuchElementException e) {
            decorationInventory.add(new InventoryEntry<>(decoration, quantity));
            decorationInventory.sort(Comparator.comparingInt(d -> (d.getElement().getId())));
        }
    }

    public void removeClue(Clue clue, int quantity) throws CheckedNoSuchElementException, InsufficientQuantityException {
        InventoryEntry<Clue> inventoryEntry = getInventoryEntry(clue, clueInventory);
        if (inventoryEntry.getQuantity() > quantity){
            inventoryEntry.setQuantity(inventoryEntry.getQuantity() - quantity);
        }else if (inventoryEntry.getQuantity() == quantity){
            clueInventory.remove(inventoryEntry);
        }else {
            throw new InsufficientQuantityException(
                    String.format("Cancelled: delete order of %d but only %d %s is present.",
                            quantity, inventoryEntry.getQuantity(), inventoryEntry.getElement().getName()));
        }
        System.out.printf("%d elements deleted.", quantity);
    }

    public void removeDecoration(Decoration decoration, int quantity)
            throws CheckedNoSuchElementException, InsufficientQuantityException {
        InventoryEntry<Decoration> inventoryEntry = getInventoryEntry(decoration, decorationInventory);
        if (inventoryEntry.getQuantity() > quantity){
            inventoryEntry.setQuantity(inventoryEntry.getQuantity() - quantity);
        }else if (inventoryEntry.getQuantity() == quantity){
            decorationInventory.remove(inventoryEntry);
        }else {
            throw new InsufficientQuantityException(
                    String.format("Cancelled: delete order of %d but only %d %s is present.",
                            quantity, inventoryEntry.getQuantity(), inventoryEntry.getElement().getName()));
        }
        System.out.printf("%d elements deleted.", quantity);
    }

    private <T extends Element> InventoryEntry<T> getInventoryEntry(T element, List<InventoryEntry<T>> inventory)
            throws CheckedNoSuchElementException {
        Optional<InventoryEntry<T>> inventoryEntry = inventory.stream()
                .filter(entry -> entry.getElement().getId() == element.getId())
                .findFirst();
        if (inventoryEntry.isPresent()){
            return inventoryEntry.get();
        }else {
            throw new CheckedNoSuchElementException("Room has no " + element.getName());
        }
    }
}

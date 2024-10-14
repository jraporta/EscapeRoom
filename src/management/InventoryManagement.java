package management;

import entities.*;
import exceptions.EmptyInventoryException;
import exceptions.EmptyListException;

import java.util.List;

public class InventoryManagement {

    public static void printDetailedClues(Inventory inventory) throws EmptyInventoryException {
        if (inventory.getClueInventory().isEmpty()){
            throw new EmptyInventoryException("There are no clues.");
        }
        printCluesTable(inventory.getClueInventory());
    }

    public static void printDetailedDecoration(Inventory inventory) throws EmptyInventoryException {
        if (inventory.getDecorationInventory().isEmpty()){
            throw new EmptyInventoryException("There are no decoration objects.");
        }
        printDecorationTable(inventory.getDecorationInventory());
    }

    private static void printCluesTable(List<InventoryEntry<Clue>> clueInventory) {
        int[] columnWidth = {5, 35, 25, 12, 10, 8, 15};
        String[] fSpecifier = {"d", "s", "s", "d", ".2f", "d", ".2f"};
        String[] headers = {"ID", "NAME", "THEME", "TIME(min)", "PRICE", "QTY", "TOTAL"};
        StringBuilder rowTemplate = new StringBuilder();
        for (int i = 0; i < headers.length; i++){
            rowTemplate.append("%-").append(columnWidth[i]).append(fSpecifier[i]);
        }
        rowTemplate.append("%n");
        for (int i = 0; i < headers.length; i++){
            System.out.printf("%-" + columnWidth[i] + "s", headers[i]);
        }
        System.out.println();
        clueInventory.forEach(x -> System.out.printf(rowTemplate.toString(),
                x.getElement().getId(),
                x.getElement().getName(),
                x.getElement().getTheme().print(),
                x.getElement().getExpectedMinutesToSolve(),
                x.getElement().getPrice(),
                x.getQuantity(),
                x.getQuantity() * x.getElement().getPrice()
                ));
    }

    private static void printDecorationTable(List<InventoryEntry<Decoration>> decorationInventory) {
        int[] columnWidth = {5, 35, 25, 10, 8, 15};
        String[] fSpecifier = {"d", "s", "s", ".2f", "d", ".2f"};
        String[] headers = {"ID", "NAME", "MATERIAL", "PRICE", "QTY", "TOTAL"};
        StringBuilder rowTemplate = new StringBuilder();
        for (int i = 0; i < headers.length; i++){
            rowTemplate.append("%-").append(columnWidth[i]).append(fSpecifier[i]);
        }
        rowTemplate.append("%n");
        for (int i = 0; i < headers.length; i++){
            System.out.printf("%-" + columnWidth[i] + "s", headers[i]);
        }
        System.out.println();
        decorationInventory.forEach(x -> System.out.printf(rowTemplate.toString(),
                x.getElement().getId(),
                x.getElement().getName(),
                x.getElement().getMaterial().print(),
                x.getElement().getPrice(),
                x.getQuantity(),
                x.getQuantity() * x.getElement().getPrice()
        ));
    }

    public static <T extends Element> void printSimplifiedElementList(Inventory invent, Class<T> className)
            throws EmptyListException {
        if (className == Clue.class) {
            List<InventoryEntry<Clue>> inventory = invent.getClueInventory();
            if (inventory.isEmpty()) {
                throw new EmptyListException("There are no clues.");
            }
            inventory.forEach(entry -> System.out.printf("%d. %s (qty: %d)%n",
                    entry.getElement().getId(), entry.getElement().getName(), entry.getQuantity() ));
        } else {
            List<InventoryEntry<Decoration>> inventory = invent.getDecorationInventory();
            if (inventory.isEmpty()) {
                throw new EmptyListException("There are no decoration items.");
            }
            inventory.forEach(entry -> System.out.printf("%d. %s (qty: %d)%n",
                    entry.getElement().getId(), entry.getElement().getName(), entry.getQuantity() ));
        }
    }
}

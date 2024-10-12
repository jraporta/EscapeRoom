package management;

import entities.*;
import exceptions.CheckedNoSuchElementException;
import exceptions.*;
import util.ListHelper;

import java.util.ArrayList;
import java.util.List;

public class RoomManagement {

    private final List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void printRoomList() throws EmptyListException {
        try {
            ListHelper.printNumeratedList(rooms);
        } catch (EmptyListException e) {
            throw new EmptyListException("There are no rooms.");
        }
    }

    public Room getRoomById(int roomId) throws exceptions.CheckedNoSuchElementException {
        return ListHelper.getElementById(roomId, rooms);
    }

    public boolean isEmpty() {
        return rooms.isEmpty();
    }

    public <T extends Element> void assign(Room room, T element, int quantity) {
        room.getInventory().addElement(element, quantity);
    }

    public static void showClueInventory(Room room) {
        try {
            InventoryManagement.showDetailedClues(room.getInventory());
        } catch (EmptyInventoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showDecorationInventory(Room room) {
        try {
            InventoryManagement.showDetailedDecoration(room.getInventory());
        } catch (EmptyInventoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteRoom(Room room) {
        rooms.remove(room);
    }

    public static <T extends Element> void showRoomsElementList(Room room, Class<T> className)
            throws EmptyListException {
        InventoryManagement.showSimplifiedElementList(room.getInventory(), className);
    }

    public static <T extends Element> void removeElement(Room room, T element, int quantity)
            throws CheckedNoSuchElementException, InsufficientQuantityException {
        if (element instanceof Clue clue) {
            room.getInventory().removeClue(clue, quantity);
        }else if(element instanceof Decoration decoration){
            room.getInventory().removeDecoration(decoration, quantity);
        }
    }

    public void printDetailedRoomList() throws EmptyListException {
        if (rooms.isEmpty()){
            throw new EmptyListException("There are no rooms.");
        }
        printRoomTable();
    }

    private void printRoomTable() {
        int[] columnWidth = {5, 35, 15, 15, 15, 15};
        String[] fSpecifier = {"d", "s", "s", "d", "d", ".2f"};
        String[] headers = {"ID", "NAME", "DIFFICULTY", "ITEM QTY", "DECOR QTY", "PRICE"};
        StringBuilder rowTemplate = new StringBuilder();
        for (int i = 0; i < headers.length; i++){
            rowTemplate.append("%-").append(columnWidth[i]).append(fSpecifier[i]);
        }
        rowTemplate.append("%n");
        for (int i = 0; i < headers.length; i++){
            System.out.printf("%-" + columnWidth[i] + "s", headers[i]);
        }
        System.out.println();
        rooms.forEach(room -> System.out.printf(rowTemplate.toString(),
                room.getId(),
                room.getName(),
                room.getDifficulty().print(),
                room.getInventory().getClueInventory().stream().mapToInt(InventoryEntry::getQuantity).sum(),
                room.getInventory().getDecorationInventory().stream().mapToInt(InventoryEntry::getQuantity).sum(),
                room.getInventory().getClueInventory().stream()
                        .mapToDouble(entry -> entry.getElement().getPrice())
                        .sum()
                        +
                room.getInventory().getDecorationInventory().stream()
                        .mapToDouble(entry -> entry.getElement().getPrice())
                        .sum()
        ));
    }
}

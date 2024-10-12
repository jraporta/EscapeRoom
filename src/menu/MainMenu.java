package menu;

import entities.Clue;
import entities.Decoration;
import entities.Element;
import entities.Room;
import enums.*;
import exceptions.CheckedNoSuchElementException;
import exceptions.EmptyListException;
import exceptions.InsufficientQuantityException;
import management.ElementCatalogManagement;
import management.RoomManagement;
import util.IOHelper;

import static util.EnumHelper.readEnumChoice;

public class MainMenu {

    private static final RoomManagement roomManagement = new RoomManagement();
    private static final ElementCatalogManagement catalog = new ElementCatalogManagement();

    public void run() {

        boolean exit = false;

        do {
            switch (menu()) {
                case 0 -> exit = true;
                case 1 -> {
                    createNewRoom();
                    System.out.println("Room created!");
                }
                case 2 -> {
                    createNewElement();
                    System.out.println("Element created!");
                }
                case 3 -> {
                    try {
                        assignElementToRoom(Clue.class);
                        System.out.println("Clues assigned to room.");
                    } catch (EmptyListException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        assignElementToRoom(Decoration.class);
                        System.out.println("Decoration assigned to room.");
                    } catch (EmptyListException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        showInventory();
                    } catch (EmptyListException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6 -> {
                    try {
                        deleteDialog();
                    } catch (EmptyListException e) {
                        System.out.println("There are no rooms.");
                    }
                }
                case 7 -> showRoomDetails();
                default -> System.out.println("Invalid Choice");
            }
        }while(!exit);
        System.out.println("bye bye");
    }

    public int menu() {
        return IOHelper.readInt("Choose an option:" +
                "\n1. Create a new room." +
                "\n2. Create a new clue or decoration element." +
                "\n3. Add clues to a room." +
                "\n4. Add decoration items to a room." +
                "\n5. Show detailed inventory." +
                "\n6. Delete elements." +
                "\n7. Show available rooms." +
                "\n0. Exit\n");
    }

    private void createNewRoom() {
        String name = IOHelper.readWord("Room's name: ", 30);
        System.out.println("Choose a difficulty:");
        Difficulty difficulty = readEnumChoice(Difficulty.class);
        roomManagement.addRoom(new Room(name, difficulty));
    }

    private void createNewElement() {
        int option;
        System.out.println("Choose an option:");
        do{
            option = IOHelper.readInt("1. Clue.\n2. Decoration element.\n");
        }while(option < 1 || option > 2);
        if (option == 1){
            createNewClue();
        } else{
            createNewDecoration();
        }
    }

    private void createNewClue() {
        String name = IOHelper.readWord("Clue's name: ", 30);
        double price = IOHelper.readBoundedPositiveDouble("Price: ", 1000);
        int minutes = IOHelper.readBoundedPositiveInt("Expected resolve time in minutes: ", 60);
        System.out.println("Theme: ");
        Theme theme = readEnumChoice(Theme.class);
        catalog.add(new Clue(name, price, minutes, theme));
    }

    private void createNewDecoration() {
        String name = IOHelper.readWord("Decoration's name: ", 30);
        double price = IOHelper.readBoundedPositiveDouble("Price: ", 1000);
        System.out.println("Material: ");
        Material material = readEnumChoice(Material.class);
        catalog.add(new Decoration(name, price, material));
    }

    private <T extends Element> void assignElementToRoom(Class<T> className) throws EmptyListException {
        if (roomManagement.isEmpty()) throw new EmptyListException("Create a new room first.");
        if (catalog.catalogIsEmpty(className)) throw new EmptyListException("Create some elements first.");
        Room room = roomSelectDialog();
        System.out.println("Select an option:");
        catalog.printCatalogList(className);
        T element = elementSelectDialog(className);
        int quantity = IOHelper.readBoundedPositiveInt("Quantity: ", 50);
        roomManagement.assign(room, element, quantity);
    }

    private Room roomSelectDialog() throws EmptyListException {
        Room room = null;
        boolean success;
        System.out.println("Choose a room:");
        roomManagement.printRoomList();
        do{
            try {
                room = roomManagement.getRoomById(IOHelper.readInt(">"));
                success = true;
            } catch (exceptions.CheckedNoSuchElementException e) {
                System.out.println(e.getMessage());
                success = false;
            }
        }while (!success);
        return room;
    }

    private <T extends Element> T elementSelectDialog(Class<T> className) throws EmptyListException {
        T element = null;
        boolean success;
        do{
            try {
                element = catalog.getElementById(className, IOHelper.readInt(">"));
                success = true;
            } catch (exceptions.CheckedNoSuchElementException e) {
                System.out.println(e.getMessage());
                success = false;
            }
        }while (!success);
        return element;
    }

    private void deleteDialog() throws EmptyListException {
        int option;
        Room room = roomSelectDialog();
        System.out.println("Room selected.");
        System.out.println("""
                Select an option:
                1. Delete the room with all its elements.
                2. Delete a clue.
                3. Delete a decoration item.""");
        do{
            option = IOHelper.readInt(">");
        }while (option < 1 || option > 3);
        switch (option){
            case 1 -> {
                if (IOHelper.readYesOrNo("The room may contain elements. Are you sure you want to proceed (Y/N): ")) {
                    roomManagement.deleteRoom(room);
                    System.out.println("Room deleted.");
                }
            }
            case 2 -> elementDeleteDialog(room, Clue.class);
            case 3 -> elementDeleteDialog(room, Decoration.class);
        }

    }

    private <T extends Element> void elementDeleteDialog(Room room, Class<T> elementClass) {
        try {
            System.out.println("Select what to delete:");
            RoomManagement.showRoomsElementList(room, elementClass);
            Element element= elementSelectDialog(elementClass);
            int quantity = IOHelper.readBoundedPositiveInt("Select quantity: ", 1000000);
            RoomManagement.removeElement(room, element, quantity);
        } catch (EmptyListException e) {
            System.out.println("Room has no " + (elementClass == Clue.class ? "clues." : "decoration items."));
        } catch (CheckedNoSuchElementException | InsufficientQuantityException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showInventory() throws EmptyListException {
        if (roomManagement.isEmpty()) throw new EmptyListException("There are no rooms.");
        Room room = roomSelectDialog();
        System.out.println("Clue inventory:");
        RoomManagement.showClueInventory(room);
        System.out.println("Decoration inventory:");
        RoomManagement.showDecorationInventory(room);
    }

    private void showRoomDetails() {
        try {
            roomManagement.printDetailedRoomList();
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
    }
}
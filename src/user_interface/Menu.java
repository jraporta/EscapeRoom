package user_interface;

import exceptions.*;
import objects.Clue;
import objects.Decoration;
import objects.Element;
import objects.Room;
import util.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Menu {

    public static Room createNewRoom() {
        System.out.println("Creating a new room, input the following fields:");
        Room.Difficulty difficulty = null;
        String name = io.readString("Name: ");
        do {
            switch (io.readInt("Difficulty (1.LOW, 2.MEDIUM, 3.HIGH):")) {
                case 1:
                    difficulty = Room.Difficulty.LOW;
                    break;
                case 2:
                    difficulty = Room.Difficulty.MEDIUM;
                    break;
                case 3:
                    difficulty = Room.Difficulty.HIGH;
                    break;
            }
        }while(difficulty == null);
        return new Room(name, difficulty);
    }

    public static Room getRoom(ArrayList<Room> rooms) throws NonExistingRoomException {
        int id = io.readInt("Type the id of the room: ");
        Room r = rooms.stream().filter(x -> x.getId() == id).findFirst().orElseGet(()->null);
        if (r == null) {
            throw new NonExistingRoomException("There isn't a room whith this id.");
        }
        return r;
    }

    public static Clue getClue(HashMap<Element, Integer> inventory)
            throws ElementNotInInventoryException, NotAClueException {
        int id = io.readInt("Input the id: ");
        Element e = inventory.keySet().stream().filter(x->x.getId() == id).findFirst().orElseGet(()->null);
        if (e == null){
            throw new ElementNotInInventoryException("There is no element with the selected ID in the inventory.");
        } if (e instanceof Clue){
            return (Clue) e;
        }
        throw new NotAClueException("The selected element is not a clue.");
    }

    public static Decoration getDecoration(HashMap<Element, Integer> inventory)
            throws ElementNotInInventoryException, NotADecorationException {
        int id = io.readInt("Input the id: ");
        Element e = inventory.keySet().stream().filter(x->x.getId() == id).findFirst().orElseGet(()->null);
        if (e == null){
            throw new ElementNotInInventoryException("There is no element with the selected ID in the inventory.");
        } if (e instanceof Decoration){
            return (Decoration) e;
        }
        throw new NotADecorationException("The selected element is not a decoration.");
    }

    public static void showInventory(Map<Element, Integer> inventory) {
        if(inventory.isEmpty()) {
            System.out.println("The inventory is empty");
        }else{
            System.out.println(
                    inventory
                            .keySet()
                            .stream()
                            .map(k -> String.format("ItemID: %d, ItemName: %s, Quantity: %s, Price: %.2f€",
                                    k.getId(), k.getName(), inventory.get(k), inventory.get(k)*k.getPrice()))
                            .collect(Collectors.joining("\n"))
            );
        }
    }

    public static void deleteFromInventory(HashMap<Element, Integer> inventory) {
        int id = io.readInt("Type the id of the element to remove: ");
        Element e = inventory.keySet().stream().filter(x -> x.getId() == id).findFirst().orElseGet(null);
        if(e != null){
            int quantity = io.readInt("Input the quantity to remove: ");
            if (inventory.get(e) > quantity){
                inventory.put(e, inventory.get(e) - quantity);
            }else{
                inventory.remove(e);
            }
        }else{
            System.out.println("The element is not present in the inventory.");
        }
    }

    public static void showRooms(ArrayList<Room> rooms) {
        System.out.println(
                rooms.stream().map(Room::toString).collect(Collectors.joining("\n"))
        );
    }

    public static void addToInventory(HashMap<Element, Integer> inventory)
            throws NonExistingThemeException, NonExistingMaterialException {
        String name = "";
        double price = 0;
        int quantity = 0;

        switch (io.readInt("Select item to add:" +
                "\n1.Clue." +
                "\n2. Decoration.")){
            case 1:
                name = io.readString("Name: ");
                price = io.readDouble("Price: ");
                int expectedSeconds = io.readInt("Expected time to solve (s): ");
                try{
                    Clue.Theme theme = Clue.Theme.valueOf(io.readString("Theme: "));
                    quantity = io.readInt("Quantity: ");
                    inventory.put(new Clue(name, price, expectedSeconds, theme), quantity);
                }catch (IllegalArgumentException e){
                    throw new NonExistingThemeException("The selected theme is not valid");
                }
                break;
            case 2:
                name = io.readString("Name: ");
                price = io.readDouble("Price: ");
                try{
                    Decoration.Material material = Decoration.Material.valueOf(io.readString("Material: "));
                    quantity = io.readInt("Quantity: ");
                    inventory.put(new Decoration(name, price, material), quantity);
                }catch(IllegalArgumentException e){
                    throw new NonExistingMaterialException("The selected material is not valid");
                }
                break;
            default:
                System.out.println("Bad selection.");
        }
    }
}

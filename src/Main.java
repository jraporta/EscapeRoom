import exceptions.NonExistingMaterialException;
import exceptions.NonExistingRoomException;
import exceptions.NonExistingThemeException;
import objects.Element;
import objects.Room;
import user_interface.MainMenu;
import user_interface.Menu;
import util.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    static ArrayList<Room> rooms = new ArrayList<>();
    static HashMap<Element, Integer> inventory = new HashMap<>();

    public static void main(String[] args) {
        boolean exit = false;
        do {
            switch (MainMenu.menu()) {
                case 0:
                    exit = true;
                    System.out.println("bye bye");
                    break;
                case 1:
                    rooms.add(Menu.createNewRoom());
                    break;
                case 2:
                    try {
                        Menu.getRoom(rooms).addClue(Menu.getClue(inventory));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        Menu.getRoom(rooms).addDecoration(Menu.getDecoration(inventory));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    Menu.showInventory(inventory);
                    break;
                case 5:
                    Menu.deleteFromInventory(inventory);
                    break;
                case 6:
                    try {
                        Menu.addToInventory(inventory);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    Menu.showRooms(rooms);
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }while(!exit);

    }




}
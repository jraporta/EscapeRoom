package user_interface;

import util.io;

public class MainMenu {

    public static int menu() {
        return io.readInt("Choose an option:" +
                "\n1. Create a new room." +
                "\n2. Add clues to a room." +
                "\n3. Add objects to a room." +
                "\n4. Show inventory." +
                "\n5. Remove elements from the inventory." +
                "\n6. Add elements to inventory." +
                "\n7. Show available rooms." +
                "\n0. Exit");
    }

}

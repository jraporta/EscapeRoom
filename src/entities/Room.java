package entities;

import enums.Difficulty;

public class Room implements HasName, HasId{

    private static int staticId;
    private final int id;
    private final String name;
    private final Difficulty difficulty;
    private final Inventory inventory;

    public Room(String name, Difficulty difficulty) {
        this.id = ++Room.staticId;
        this.name = name;
        this.difficulty = difficulty;
        this.inventory = new Inventory();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Inventory getInventory() {
        return inventory;
    }

}

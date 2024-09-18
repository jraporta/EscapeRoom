package objects;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Room{

    public enum Difficulty{
        HIGH,
        MEDIUM,
        LOW;
    }

    private static int staticId;
    private int id;
    private String name;
    private Room.Difficulty difficulty;
    private double value;
    private final ArrayList<Clue> clues;
    private final ArrayList<Decoration> decoration;

    static{
        Room.staticId = 0;
    }

    public Room(String name, Room.Difficulty difficulty) {
        this.id = ++Room.staticId;
        this.name = name;
        this.difficulty = difficulty;
        this.clues = new ArrayList<>();
        this.decoration = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void addClue(Clue clue) {
        this.clues.add(clue);
    }

    public void addDecoration(Decoration decoration) {
        this.decoration.add(decoration);
    }

    @Override
    public String toString(){
        return String.format("RoomId: %d" +
                "Room name: %s" +
                "%nClues: %s" +
                "%nDecoration Elements: %s" +
                "%nTotal Cost: %.2f",
                this.id, this.name,
                this.clues.stream().map(c->c.getId() + ". " + c.getName()).collect(Collectors.joining(", ")),
                this.decoration.stream().map(c->c.getId() + ". " + c.getName()).collect(Collectors.joining(", ")),
                this.clues.stream().map(Element::getPrice).reduce(Double::sum).orElseGet(()-> (double) 0) +
                        this.decoration.stream().map(Element::getPrice).reduce(Double::sum).orElseGet(()-> (double) 0)
        );
    }

}

package exceptions;

public class NonExistingRoomException extends Exception{
    public NonExistingRoomException(String s) {
        super(s);
    }
}

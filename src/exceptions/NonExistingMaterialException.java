package exceptions;

public class NonExistingMaterialException extends Exception{
    public NonExistingMaterialException(String s) {
        super(s);
    }
}

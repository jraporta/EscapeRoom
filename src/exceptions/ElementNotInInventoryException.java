package exceptions;

public class ElementNotInInventoryException extends Exception{
    public ElementNotInInventoryException(String s) {
        super(s);
    }
}

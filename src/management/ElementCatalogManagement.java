package management;

import entities.Clue;
import entities.Decoration;
import entities.Element;
import exceptions.CheckedNoSuchElementException;
import exceptions.EmptyListException;
import util.ListHelper;

import java.util.ArrayList;
import java.util.List;

public class ElementCatalogManagement {

    List<Clue> clueCatalog = new ArrayList<>();
    List<Decoration> decorationCatalog = new ArrayList<>();

    public void addElement(Element element){
        if(element instanceof Clue){
            clueCatalog.add((Clue) element);
        }else {
            decorationCatalog.add((Decoration) element);
        }
    }

    public <T extends Element> boolean catalogIsEmpty(Class<T> className){
        if (className == Clue.class){
            return clueCatalog.isEmpty();
        }else {
            return decorationCatalog.isEmpty();
        }
    }

    public <T extends Element> T getElementById(Class<T> className, int id) throws CheckedNoSuchElementException {
        if (className == Clue.class){
            Clue clue = ListHelper.getElementById(id, clueCatalog);
            return className.cast(clue);
        }else {
            Decoration decoration = ListHelper.getElementById(id, decorationCatalog);
            return className.cast(decoration);
        }
    }

    public <T extends Element> void printCatalogItemsNames(Class<T> className) throws EmptyListException {
        if (className == Clue.class){
            try {
                ListHelper.printNumeratedList(clueCatalog);
            } catch (EmptyListException e) {
                throw new EmptyListException("There are no clues.");
            }
        }else {
            try {
                ListHelper.printNumeratedList(decorationCatalog);
            } catch (EmptyListException e) {
                throw new EmptyListException("There are no decoration objects.");
            }
        }
    }

}

package util;

import entities.HasId;
import entities.HasName;
import exceptions.CheckedNoSuchElementException;
import exceptions.EmptyListException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListHelper {

    public static void printNumeratedList(List<? extends HasName> list) throws EmptyListException {
        if (list.isEmpty()) {
            throw new EmptyListException("The list is empty.");
        } else {
            AtomicInteger i = new AtomicInteger(1);
            list.forEach(elem -> System.out.printf("%d. %s%n", i.getAndIncrement(), elem.getName()));
        }
    }

    public static <T extends HasId>T getElementById(int id, List<T> list) throws CheckedNoSuchElementException {
        return list.stream()
                .filter(element -> element.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CheckedNoSuchElementException("No room found with such id."));
    }

}

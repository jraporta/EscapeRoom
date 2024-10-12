package util;

import enums.Printable;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class EnumHelper {

    public static  <E extends Enum<E> & Printable> E readEnumChoice(Class<E> enumClass) {
        E[] constants = enumClass.getEnumConstants();
        StringBuilder s = new StringBuilder();
        AtomicInteger i = new AtomicInteger(1);
        int option;
        Arrays.stream(constants)
                .forEach(constant -> s.append(String.format("%d. %s.%n", i.getAndIncrement(), constant.print())));
        do{
            option = IOHelper.readInt(s.toString());
        }while (option < 1 || option > constants.length);
        return constants[option - 1];
    }

}

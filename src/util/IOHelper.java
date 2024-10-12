package util;

import java.util.InputMismatchException;
import java.util.Scanner;


public class IOHelper {

    private static final Scanner input;

    static {
        input = new Scanner(System.in);
    }

    public static int readInt(String message) {
        boolean success = false;
        int myInt = 0;
        do {
            try {
                System.out.print(message);
                myInt = input.nextInt();
                success = true;
            }catch(InputMismatchException e) {
                System.out.println("Format error.");
                input.nextLine();
            }
        }while(!success);
        input.nextLine();
        return myInt;
    }

    public static double readDouble(String message) {
        boolean success = false;
        double myDouble = 0;
        do {
            try {
                System.out.print(message);
                myDouble = input.nextDouble();
                success = true;
            }catch(InputMismatchException e) {
                System.out.println("Format error.");
                input.nextLine();
            }
        }while(!success);
        input.nextLine();
        return myDouble;
    }

    public static String readString(String message){
        boolean success = false;
        String myString =  "";
        do {
            try {
                System.out.print(message);
                myString = input.nextLine();
                success = true;
            }catch(Exception e) {
                System.out.println("Error");
                input.nextLine();
            }
        }while(!success);
        return myString;
    }

    public static String readWord(String message, int maxLength){
        String word;
        boolean check1, check2 = false;
        do {
            word = readString(message).strip();
            check1 = word.matches("^[\\p{Alnum}\\s'_()!?&#~]*$");
            if (!check1) {
                word = word.replaceAll("[\\p{Alnum}'\\s_()!?&#~]*", "");
                System.out.printf("Wrong format: %s are not accepted characters.%n", word);
            }else {
                check2 = word.matches(String.format("^.{4,%d}$", maxLength));
                if (!check2) System.out.printf("Wrong format: accepted length of 4-%d characters.%n", maxLength);
            }
        } while (!check1 || !check2);
        return word;
    }

    public static int readBoundedPositiveInt(String message, int maxValue){
        int num;
        boolean check1, check2 = false;
        do {
            num = readInt(message);
            check1 = num > 0;
            if (!check1) {
                System.out.println("Wrong format: number must be greater than 0.");
            }else {
                check2 = num <= maxValue;
                if (!check2) System.out.printf("Wrong format: numbers greater than %d are not accepted.%n", maxValue);
            }
        } while (!check1 || !check2);
        return num;
    }

    public static double readBoundedPositiveDouble(String message, int maxValue){
        double num;
        boolean check1, check2 = false;
        do {
            num = readDouble(message);
            check1 = num >= 0;
            if (!check1) {
                System.out.println("Wrong format: negative numbers are not accepted.");
            }else {
                check2 = num <= maxValue;
                if (!check2) System.out.printf("Wrong format: numbers greater than %d are not accepted.%n", maxValue);
            }
        } while (!check1 || !check2);
        return num;
    }

    public static boolean readYesOrNo(String message){
        boolean success = false;
        boolean bool = false;
        do {
            String input = readString(message);
            if (input.equalsIgnoreCase("Y")){
                bool = true;
                success = true;
            } else if (input.equalsIgnoreCase("N")){
                bool = false;
                success = true;
            }
        }while(!success);
        return bool;
    }

}

package util;

import java.util.Scanner;

public class Input {
    public static double getDouble(Scanner sc) {
        String in = sc.next();
        try{
            return Double.parseDouble(in);
        } catch (Exception e) {
            System.out.println("Wrong Input! Reason: " + e.getMessage());
            return 0;
        }
    }
}

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

    public static void inputMenu() {
        System.out.println("输入买卖的种类:");
        System.out.println("   1.   买入");
        System.out.println("   2.   卖出");
        System.out.println("  其他.  停止输入");
    }
}

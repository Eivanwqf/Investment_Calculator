import Service.*;
import Service.Impl.*;
import entity.Trade;
import util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //导入一个扫描对象输入
        TradeService tradeService = new TradeServiceImpl();//声明一个交易服务接口对象
        List<Trade> trades = new ArrayList<>();//声明一个列表对象，存储所有交易

        while (true) {
            Print.mainMenu();

            String typeMain = sc.next();
            if (typeMain.equals("1")) {
                while (Input.tradeIn(sc, trades)) {
                }
                Print.printRevenue(sc, trades);
            }
            else if (typeMain.equals("2")) {
                Print.PrintAllHistory();
            }
            else if (typeMain.equals("3")) {
                Print.printRevenue(sc);
            }
            else if (typeMain.equals("0")) {
                break;
            }
        }
    }
}
package util;

import Service.Impl.TradeServiceImpl;
import Service.TradeService;
import entity.Trade;
import java.util.List;
import java.util.Scanner;

public class Print {
    public static void PrintAllHistory() {
        TradeService tradeService = new TradeServiceImpl();
        List<Trade> allTrades = tradeService.loadTrades();

        System.out.println("==========历史账单=========");
        for (Trade tr: allTrades) {
            System.out.print(tr);
        }
        System.out.println("==========================");
    }
    public static void mainMenu(){
        System.out.println("┌======================┐");
        System.out.println("│       这是主菜单       │");
        System.out.println("│1--------------输入账单│");
        System.out.println("│2--------------展示账单│");
        System.out.println("│3--------------处理账单│");
        System.out.println("│0-----------------结束│");
        System.out.println("└======================┘");
    }

    public static void printRevenue(Scanner sc,List<Trade> trades){
        TradeService tradeService = new TradeServiceImpl();

        System.out.println("请输入当前的价格:");
        double currentPrice = Input.getDouble(sc);
        double result = tradeService.calculateValue(trades,currentPrice);
        System.out.print("最终的收益为：");
        System.out.printf("%.2f\n", result);
    }
    public static void printRevenue(Scanner sc){
        TradeService tradeService = new TradeServiceImpl();
        List<Trade> allTrades = tradeService.loadTrades();
        System.out.println("请输入当前的价格:");
        double currentPrice = Input.getDouble(sc);
        double result = tradeService.calculateValue(allTrades,currentPrice);
        System.out.print("最终的收益为：");
        System.out.printf("%.2f\n", result);
    }
}


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
    public static void mainMenu() {
        // 定义颜色常量
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String RED = "\u001B[31m";
        String CYAN = "\u001B[36m";

        System.out.println("╔═══════════════════════════════╗" + RESET);
        System.out.println("║       INVESTMENT MANAGER      ║" + RESET);
        System.out.println("╠═══════════════════════════════╣" + RESET);
        System.out.println("║  " + GREEN  + "1. 输入账单 （Input）   " + RESET + "       ║");
        System.out.println("║  " + YELLOW + "2. 展示账单 （Show）    " + RESET + "       ║");
        System.out.println("║  " + CYAN   + "3. 处理账单 （Process） " + RESET + "       ║");
        System.out.println("║  " + RED    + "0. 退出程序 （Exit）    " + RESET + "       ║");
        System.out.println("╚═══════════════════════════════╝");
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


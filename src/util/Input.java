package util;

import Service.Impl.TradeServiceImpl;
import Service.TradeService;
import entity.Trade;

import java.util.List;
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

    public static boolean tradeIn(Scanner sc, List<Trade> trades) {
        TradeService tradeService = new TradeServiceImpl();
        Input.inputMenu();
        Trade.TradeType tt = null;

        String typeInput = sc.next();
        if (typeInput.equals("1")) { //开始统计买
            tt = Trade.TradeType.BUY;
        }
        else if (typeInput.equals("2")) { //开始统计卖
            tt = Trade.TradeType.SELL;
        }
        else  {
            return false;
        }

        System.out.print((tt.equals(Trade.TradeType.BUY)?"买入 ":"卖出 ")+"的时候价格是:");
        double price = Input.getDouble(sc);
        System.out.print((tt.equals(Trade.TradeType.BUY)?"买入":"卖出")+"了多少单位的？请输入。");
        double quantity = Input.getDouble(sc);

        Trade tempTrade = new Trade(tt, price, quantity);
        trades.add(tempTrade); //在main里创建的列表数组
        System.out.println("成功按 " + price + " 的价格" +
                (tt == Trade.TradeType.BUY ? "买入" : "卖出") + "了 " +
                quantity + " 单位!");
        tradeService.saveTradeToFile(tempTrade);
        return true;
    }
}

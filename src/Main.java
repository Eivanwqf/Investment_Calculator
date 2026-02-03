import Service.Impl.TradeServiceImpl;
import entity.Trade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Trade> trades = new ArrayList<>();

        while (true) {
            System.out.println("输入买卖的种类:");
            System.out.println("   0.   买入");
            System.out.println("   1.   卖出");
            System.out.println("  其他.  停止输入");
            Trade.TradeType tt = null;

            String typeInput = sc.next();
            if (typeInput.equals("0")) { //开始统计买
                tt = Trade.TradeType.BUY;
            }
            else if (typeInput.equals("1")) { //开始统计卖
                tt = Trade.TradeType.SELL;
            }
            else  {
//                System.out.println("DEBUG: Stop the input.");
                break;
            }

            System.out.print(tt.toString()+"的时候价格是:");
            double price = sc.nextDouble();
            System.out.print("接下来"+(tt)+"多少单位的呢？请输入。");
            int quantity = sc.nextInt();

            Trade tempTrade = new Trade(tt, price, quantity);
            trades.add(tempTrade); //在main里创建的列表数组
            System.out.println("成功按 " + price + " 的价格" +
                    (tt == Trade.TradeType.BUY ? "买入" : "卖出") + "了 " +
                    quantity + " 单位!");
        }

        //Processing unit
        TradeServiceImpl tp = new TradeServiceImpl();
        double result = tp.calculateValue(trades);
        System.out.print("最终的收益为：");
        System.out.printf("%.2f", result);
    }
}
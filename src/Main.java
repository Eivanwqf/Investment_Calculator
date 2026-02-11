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
        List<Trade> trades = new ArrayList<>();//声明一个列表对象，存储所有交易
        TradeService tradeService = new TradeServiceImpl();//声明一个交易服务接口对象

        while (true) {
            //一级菜单开始
            System.out.println("=======================");
            System.out.println("这是主菜单");
            System.out.println("1------输入账单");
            System.out.println("2------展示账单");
            System.out.println("3------处理账单");
            System.out.println("0------结束");
            System.out.println("=======================");
            String typeMain = sc.next();
            if (typeMain.equals("1")) {
                while (true) {
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
                        break;
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
                }
            }
            else if (typeMain.equals("2")) {
                Print.PrintAllHistory();
            }
            else if (typeMain.equals("3")) {
                System.out.println("请输入当前的价格:");
                double currentPrice = Input.getDouble(sc);
                List<Trade> loadTradesList = new ArrayList<>();
                loadTradesList = tradeService.loadTrades();
                double result = tradeService.calculateValue(loadTradesList, currentPrice);
                System.out.print("最终的收益为：");
                System.out.printf("%.2f", result);
            }
            else if (typeMain.equals("0")) {
                break;
            }
        }

        //Processing unit

        /*

            此处添加输入当前价格,计算剩余含金量

        */
        System.out.println("请输入当前的价格:");
        double currentPrice = Input.getDouble(sc);

        double result = tradeService.calculateValue(trades,currentPrice);
        System.out.print("最终的收益为：");
        System.out.printf("%.2f", result);
    }
}
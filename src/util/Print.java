package util;

import Service.Impl.TradeServiceImpl;
import Service.TradeService;
import entity.Trade;
import java.util.List;
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


}


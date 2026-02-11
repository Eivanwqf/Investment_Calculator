package Service;

import entity.Trade;
import java.util.List;

public interface TradeService {
    //定义业务功能：计算总收益
    double calculateValue(List<Trade> tr, double currentPrice);
    void saveTradeToFile(Trade tr);
    List<Trade> loadTrades();
}

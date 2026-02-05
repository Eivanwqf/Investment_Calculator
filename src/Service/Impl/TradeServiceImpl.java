package Service.Impl;

import entity.Trade;
import Service.TradeService;
import Repository.tradeRepository;
import java.util.List;

public class TradeServiceImpl implements TradeService{
    private final tradeRepository tradeRepo = new tradeRepository();

    @Override
    public double calculateValue(List<Trade> trades) {
        double buyTotal = 0.0;
        double sellTotal = 0.0;

        for (Trade tr: trades) {
            if (tr.getType() == Trade.TradeType.BUY) {
                buyTotal += tr.calcValue();
            }
            else if (tr.getType() == Trade.TradeType.SELL) {
                sellTotal += tr.calcValue();
            }
        }
        return sellTotal - buyTotal;
    }



    @Override
    public void saveTradeToFile(Trade tr) {
        tradeRepo.save(tr);
    }

    @Override
    public List<Trade> loadTrades(){
        return tradeRepo.load();
    }
}

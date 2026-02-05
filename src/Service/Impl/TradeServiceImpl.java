package Service.Impl;

import entity.Trade;
import Service.TradeService;
import Repository.tradeRepository;
import java.util.List;

public class TradeServiceImpl implements TradeService{
    private final tradeRepository tradeRepo = new tradeRepository();
    long maxId = 0;
    //构造函数, 启动时就同步一次ID进度
    public TradeServiceImpl() {
        List<Trade> currentTradeList = tradeRepo.load();
        if (currentTradeList != null) {

            for (Trade readIdTrades : currentTradeList) {
                if (maxId < readIdTrades.getId()) {
                    maxId = readIdTrades.getId();
                }
            }
            //2.在最大ID的基础上+1，赋予给当前的交易对象
        }
    }


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

    //realTotal = sold - bought + current gold networth
    public double calculateRealProfit(List<Trade> trades) {

        return 0.0;
    }

    @Override
    public void saveTradeToFile(Trade tr) {
        tr.setId(++maxId);
        tradeRepo.save(tr);
    }

    @Override
    public List<Trade> loadTrades(){
        return tradeRepo.load();
    }
}

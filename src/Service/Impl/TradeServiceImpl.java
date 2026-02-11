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
    public double calculateValue(List<Trade> trades, double currentPrice) {
        double buyTotal = 0.0; //买入所有价格
        double sellTotal = 0.0;//卖出所有价格
        double buyAmount = 0.0;
        double sellAmount = 0.0;

        for (Trade tr: trades) {
            if (tr.getType() == Trade.TradeType.BUY) { //读取所有买入
                buyTotal += tr.calcValue();
                buyAmount += tr.getAmount();
            }
            else if (tr.getType() == Trade.TradeType.SELL) { //读取所有卖出
                sellTotal += tr.calcValue();
                sellAmount += tr.getAmount();
            }
        }
        if (sellAmount > buyAmount) {
            System.out.println("错误！卖出比买入要多，这不对了。");
            return 0;
        }
        for (Trade tr: trades) {
            System.out.println("DEBUG: 正在计算 -> " + tr.getType() + " 价值:" + tr.calcValue() + " 数量:" + tr.getAmount());
            // ... 原有的逻辑
        }
        return sellTotal - buyTotal + (currentPrice * (buyAmount - sellAmount));
    }
    //realTotal = sold - bought + current gold net worth

//    public double calculateRealProfit(List<Trade> trades) {
//    }

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

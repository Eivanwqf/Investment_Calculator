package entity;

import java.time.LocalDateTime;

public class Trade {
    public enum TradeType{
        BUY,
        SELL
    };
    private TradeType type;
    private double price;
    private int amount;
    private LocalDateTime time;

//    public entity.Trade(){
//        System.out.println("【debug】一个新的交易已创建");
//    };

    public Trade(TradeType type, double price, int amount){
        this.type = type;
        this.price = price;
        this.amount = amount;
    }

    public TradeType getType() {
        return type;
    }
    public void setType(TradeType type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAmount(){
        return amount;
    }

    public double calcValue(){
        return price * amount;
    }
}
/*用户输入数据 -> 变成trade对象 -> 存list<array>数组，交给计算模块*/
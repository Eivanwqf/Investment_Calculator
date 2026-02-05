package entity;

import java.time.LocalDateTime;

public class Trade {
    public enum TradeType{
        BUY,
        SELL
    };
    private TradeType type;
    private double price;
    private double amount;
    private LocalDateTime time;
    private String strTime;
    private long id;

//    public entity.Trade(){
//        System.out.println("【debug】一个新的交易已创建");
//    };

    public Trade(TradeType type, double price, double amount){
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    public Trade(String strTime, TradeType type, double price, double amount, double value){
        this.strTime = strTime;
        this.type = type;
        this.price = price;
        this.amount = amount;
        calcValue();
    }//用于读取csv表格中的数据.

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getAmount(){
        return amount;
    }

    public LocalDateTime getTime(){
        return time;
    }

    public double calcValue(){
        return price * amount;
    }

    @Override
    public String toString(){
        return String.format("时间：%s | 类型：%5s | 数量：%.3f | 单价：%.2f | 总价：%.2f\n",
                strTime, type, amount, price, calcValue());
    }
}
/*用户输入数据 -> 变成trade对象 -> 存list<array>数组，交给计算模块*/
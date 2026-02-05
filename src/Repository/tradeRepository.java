package Repository;
import entity.Trade;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class tradeRepository {
    private final String fileName = "MyTrades.csv";//csv?
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public void save(Trade tr){
        //1. 先读取现有数据，找出目前最大的id
        List<Trade> currentTradesList = load();


        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw)){
            String formattedTime = tr.getTime().format(dtf);

            String data = String.format("%d,%s,%s,%f,%.2f,%f\n",
                    tr.getId(),
                    formattedTime,
                    tr.getType(),
                    tr.getAmount(),
                    tr.getPrice(),
                    tr.calcValue());
            bw.write(data);
            System.out.println("DEBUG: Data saved!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Trade> load(){
        List<Trade> tradesList = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return tradesList;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 6){
                    continue;
                }
                long id = Long.parseLong(parts[0]);
                String time = parts[1];
//                LocalDateTime dateTime = LocalDateTime.parse(parts[1], dtf);
                Trade.TradeType tradeType = Trade.TradeType.valueOf(parts[2]);
                double amount = Double.parseDouble(parts[3]);
                double price = Double.parseDouble(parts[4]);

                Trade tr = new Trade(id, time, tradeType, price, amount);//后面还有一个在Trade下重写的toString用来算总价格
                tradesList.add(tr);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return tradesList;
    }
}

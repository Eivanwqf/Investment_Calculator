package Repository;
import entity.Trade;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class tradeRepository {
    private final String fileName = "MyTrades.csv";//csv?
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public void save(Trade tr){
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw)){
            String formattedTime = tr.getTime().format(dtf);
            String data = String.format("%s,%s,%f,%.2f,%f\n",
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
                if (parts.length != 5){
                    continue;
                }
                String time = parts[0];
                LocalDateTime dateTime = LocalDateTime.parse(parts[0], dtf);
                Trade.TradeType tradeType = Trade.TradeType.valueOf(parts[1]);
                double amount = Double.parseDouble(parts[2]);
                double price = Double.parseDouble(parts[3]);
                double value = Double.parseDouble(parts[4]);

                Trade tr = new Trade(time, tradeType, price, amount, value);
                tradesList.add(tr);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return tradesList;
    }
}

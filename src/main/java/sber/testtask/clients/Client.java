package sber.testtask.clients;

import sber.testtask.orders.Order;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/* ### Файл `clients.txt`

Файл списка клиетов имеет следующие поля:
 * Имя клиента
 * Баланс клиента по долларам
 * Баланс клиента по ценной бумаге "A" в штуках
 * Баланс по ценной бумаге "B"
 * Баланс по ценной бумаге "C"
 * Баланс по ценной бумаге "D"
*/

public class Client {

    private String name;
    public Integer clientBalanceUSD;

    private Map<String, Client> clientsList = new TreeMap<>();
    private Map<String, Client.Stock> stocksList = new HashMap<>();

    public enum Stocks {
        A,
        B,
        C,
        D
    }

    public Client(String clientsPath) throws IOException {
        this.clientsList = readFromFile(clientsPath);
    }

    public Client(String[] items) {
        this.name = items[0];
        this.clientBalanceUSD = Integer.valueOf(items[1]);

        this.stocksList.put(Stocks.A.toString(), new Client.Stock(Integer.valueOf(items[2])));
        this.stocksList.put(Stocks.B.toString(), new Client.Stock(Integer.valueOf(items[3])));
        this.stocksList.put(Stocks.C.toString(), new Client.Stock(Integer.valueOf(items[4])));
        this.stocksList.put(Stocks.D.toString(), new Client.Stock(Integer.valueOf(items[5])));

    }

    public Client getClientByName(String name) { return clientsList.get(name); }

    public static class Stock {
        public Integer qty;

        public Stock(Integer qty) {
            this.qty = qty;
        }

        public Integer getQty() {
            return qty;
        }

        public void increment(Integer qty) {
            this.qty += qty;
        }

        public void decrement(Integer qty) {
            this.qty -= qty;
        }

        @Override
        public String toString() {
            return qty.toString();
        }
    }

    public Map getStocks() {
        return stocksList;
    }

    private Map readFromFile(String clientsPath) throws IOException {
        Map<String, Client> clients = new TreeMap<>();

        List<String> lines = Files.readAllLines(Paths.get(clientsPath));
        for (String line : lines) {
            String[] s = line.split("\t");
            clients.put(s[0], new Client(s));
        }

        return clients;
    }

    public void writeResult(String fileName) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();

        for (Map.Entry<String, Client> client : clientsList.entrySet()) {
            //System.out.println(client.getKey());
            stringBuffer.append(client.getValue().toString()).append("\r\n");
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(fileName);
            byte[] buff = stringBuffer.toString().getBytes();
            fos.write(buff, 0, buff.length);
        }
        finally {
            if (fos != null)
                fos.close();
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(name)
        .append("\t")
        .append(clientBalanceUSD)
        .append("\t")
        .append(stocksList.get(Stocks.A.toString()).getQty())
        .append("\t")
        .append(stocksList.get(Stocks.B.toString()).getQty())
        .append("\t")
        .append(stocksList.get(Stocks.C.toString()).getQty())
        .append("\t")
        .append(stocksList.get(Stocks.D.toString()).getQty());

        return sb.toString();
    }
}

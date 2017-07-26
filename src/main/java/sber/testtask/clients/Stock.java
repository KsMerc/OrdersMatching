package sber.testtask.clients;

import java.util.HashMap;
import java.util.Map;

public class Stock {

    private Map<String, Stock> stocksList = new HashMap<>();

    private enum Stocks {
        A,
        B,
        C,
        D
    }

    private Integer qty;

    public Stock(String[] items) {
        this.stocksList.put(Stocks.A.toString(), new Stock(items[2]));
        this.stocksList.put(Stocks.B.toString(), new Stock(items[3]));
        this.stocksList.put(Stocks.C.toString(), new Stock(items[4]));
        this.stocksList.put(Stocks.D.toString(), new Stock(items[5]));
    }

    public Stock(String qty) {
        this.qty = Integer.valueOf(qty);
    }

    public void increment(Integer qty) {
        this.qty += qty;
    }

    public void decrement(Integer qty) {
        this.qty -= qty;
    }

    public Map getStocks() {
        return stocksList;
    }

    @Override
    public String toString() {
        return qty.toString();
    }
}


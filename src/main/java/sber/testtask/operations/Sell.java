package sber.testtask.operations;

import sber.testtask.Processing;
import sber.testtask.clients.Client;
import sber.testtask.clients.Stock;

import java.util.Map;

public class Sell extends AbstractOperation {

    public Sell(String clientName, String stock, Integer price, Integer quantity) {
        super(clientName, stock, price, quantity);
    }

    @Override
    public void addOrderToProcessing(Processing processing) {
        processing.sell(this);
    }

    public void applyOperation(Client clients) {
        Client client = clients.getClientByName(clientName);
        Map<String, Stock> stocks = client.getStocks();
        stocks.get(getOrder().getStock()).decrement(getOrder().getQuantity());

        Integer currentBalance = client.getClientBalanceUSD();
        client.setClientBalanceUSD(currentBalance + getOrder().getQuantity() * getOrder().getOrderPrice());
    }
}

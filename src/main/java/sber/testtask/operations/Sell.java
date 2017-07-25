package sber.testtask.operations;

import sber.testtask.Processing;
import sber.testtask.clients.Client;

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

        ((Client.Stock)client.getStocks().get(stock)).decrement(quantity);
        client.clientBalanceUSD += quantity * price;
    }
}

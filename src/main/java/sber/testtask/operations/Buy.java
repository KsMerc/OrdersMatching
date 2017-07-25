package sber.testtask.operations;

import sber.testtask.Processing;
import sber.testtask.clients.Client;

public class Buy extends AbstractOperation {

    public Buy (String clientName, String stock, Integer price, Integer quantity) {
        super(clientName, stock, price, quantity);
    }

    @Override
    public void addOrderToProcessing(Processing processing) {
        processing.buy(this);
    }

    public void applyOperation(Client clients) {
        Client client = clients.getClientByName(clientName);

        ((Client.Stock)client.getStocks().get(stock)).increment(quantity);
        clients.getClientByName(clientName).clientBalanceUSD -= quantity * price;
    }
}

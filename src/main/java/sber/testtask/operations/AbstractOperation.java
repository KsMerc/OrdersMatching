package sber.testtask.operations;

import sber.testtask.Processing;
import sber.testtask.clients.Client;
import sber.testtask.orders.Order;

public abstract class AbstractOperation {

    protected String clientName;

    private Order order;

    public AbstractOperation(String clientName, String stock, Integer price, Integer quantity) {
        this.clientName = clientName;
        order = new Order(stock, Integer.valueOf(price), Integer.valueOf(quantity));
    }

    public Order getOrder() { return order;}

    public abstract void applyOperation (Client customers);

    public abstract void addOrderToProcessing(Processing processing);
}

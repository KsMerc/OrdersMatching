package sber.testtask.operations;

import sber.testtask.Processing;
import sber.testtask.clients.Client;
import sber.testtask.orders.Order;

public abstract class AbstractOperation {

    String clientName;
    String stock;
    Integer price;
    Integer quantity;

    Order order;

    public AbstractOperation(String clientName, String stock, Integer price, Integer quantity) {
        this.clientName = clientName;
        this.stock = stock;
        this.price = Integer.valueOf(price);
        this.quantity = Integer.valueOf(quantity);

        order = new Order(stock, price, quantity);

    }

    public Order getOrder() { return order;}

    public abstract void applyOperation (Client customers);

    public abstract void addOrderToProcessing(Processing processing);
}

package sber.testtask;


import sber.testtask.clients.Client;
import sber.testtask.operations.AbstractOperation;
import sber.testtask.orders.Order;

import java.util.*;

public class Processing {

    Client clients;

    private Map<Order, Queue<AbstractOperation>> sellQ = new HashMap<>();
    private Map<Order, Queue<AbstractOperation>> buyQ = new HashMap<>();

    public Processing(Client clients) {
        this.clients = clients;
    }

    public Client getClients() { return clients;}

    public void buy(AbstractOperation operation) {
        if (checkIfExists(sellQ, operation.getOrder())) {
            // System.out.println("Buy");
            operation.applyOperation(clients);
            removeOrder(operation.getOrder(), sellQ).applyOperation(clients);
        }
        else {
           // System.out.println("Buy: add order to queue");
            pushOrder(operation, buyQ);
        }
    }

    public void sell(AbstractOperation operation) {
        if (checkIfExists(buyQ, operation.getOrder())) {
           // System.out.println("Sell");
            operation.applyOperation(clients);
            removeOrder(operation.getOrder(), buyQ).applyOperation(clients);
        }
        else {
           // System.out.println("Sell: add order to queue");
            pushOrder(operation, sellQ);
        }
    }

    public void pushOrder(AbstractOperation operation, Map<Order, Queue<AbstractOperation>> queue) {
        Order order = operation.getOrder();
        if (checkIfExists(queue, order)) {
            Queue<AbstractOperation> q = queue.get(order);
            q.add(operation);
        }
        else {
            Queue<AbstractOperation> q = new LinkedList<>();
            q.add(operation);
            queue.put(order, q);
        }
    }

    public AbstractOperation removeOrder(Order order, Map<Order, Queue<AbstractOperation>> queue) {
        Queue<AbstractOperation> q =  queue.get(order);

        if (q == null)
            return null;

        AbstractOperation result = q.remove();
        if (q.isEmpty()) {
            queue.remove(order);
        }
        return result;
    }

    public boolean checkIfExists(Map<Order, Queue<AbstractOperation>> queue, Order order) {
        return queue.containsKey(order);
    }
}

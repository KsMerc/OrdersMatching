package sber.testtask;

import sber.testtask.operations.AbstractOperation;
import sber.testtask.orders.Order;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class QueueHelper {

    private Map<Order, Queue<AbstractOperation>> queue = new HashMap<>();

    public void pushOrder(AbstractOperation operation) {
        Order order = operation.getOrder();

        if (checkIfExists(order)) {
            Queue<AbstractOperation> q = queue.get(order);
            q.add(operation);
        }
        else {
            Queue<AbstractOperation> q = new LinkedList<>();
            q.add(operation);
            queue.put(order, q);
        }
    }

    public AbstractOperation removeOrder(Order order) {
        Queue<AbstractOperation> q =  queue.get(order);

        if (q == null)
            return null;

        AbstractOperation result = q.remove();
        if (q.isEmpty()) {
            queue.remove(order);
        }
        return result;
    }

    public boolean checkIfExists(Order order) {
        return queue.containsKey(order);
    }
}

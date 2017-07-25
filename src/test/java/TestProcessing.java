import junit.framework.TestCase;
import org.junit.Test;
import sber.testtask.Processing;
import sber.testtask.clients.Client;
import sber.testtask.operations.AbstractOperation;
import sber.testtask.operations.Buy;
import sber.testtask.orders.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class TestProcessing extends TestCase {
    private Map<Order, Queue<AbstractOperation>> queue = new HashMap<>();

    @Test
    public void testCheckIfExists() {
        String[] s = {"C9", "7250", "190", "190", "0", "280"};
        Client cl = new Client(s);
        Processing processing = new Processing(cl);

        Order order = new Order("A", 20, 15);
        assertFalse(processing.checkIfExists(queue, order));
    }

    @Test
    public void testAddOrder() {
        String[] s = {"C9", "7250", "190", "190", "0", "280"};
        Client cl = new Client(s);
        Processing processing = new Processing(cl);
        AbstractOperation operation = new Buy("C9", "A", 20, 15);

        processing.pushOrder(operation, queue);

        assertTrue(processing.checkIfExists(queue, operation.getOrder()));
    }

    @Test
    public void testRemoveOrder() {
        String[] s = {"C9", "7250", "190", "190", "0", "280"};
        Client cl = new Client(s);

        Processing processing = new Processing(cl);
        AbstractOperation operation = new Buy("C9", "A", 20, 15);

        processing.removeOrder(operation.getOrder(), queue);

        assertFalse(processing.checkIfExists(queue, operation.getOrder()));

    }


}

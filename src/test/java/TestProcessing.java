import junit.framework.TestCase;
import org.junit.Test;
import sber.testtask.Processing;
import sber.testtask.QueueHelper;
import sber.testtask.clients.Client;
import sber.testtask.operations.AbstractOperation;
import sber.testtask.operations.Buy;
import sber.testtask.orders.Order;

public class TestProcessing extends TestCase {

    private QueueHelper queue = new QueueHelper();

    @Test
    public void testCheckIfExists() {
        String[] s = {"C9", "7250", "190", "190", "0", "280"};
        Client cl = new Client(s);
        Processing processing = new Processing(cl);

        Order order = new Order("A", 20, 15);
        assertFalse(queue.checkIfExists(order));
    }

    @Test
    public void testAddOrder() {
        String[] s = {"C9", "7250", "190", "190", "0", "280"};
        Client cl = new Client(s);
        Processing processing = new Processing(cl);
        AbstractOperation operation = new Buy("C9", "A", 20, 15);

        queue.pushOrder(operation);

        assertTrue(queue.checkIfExists(operation.getOrder()));
    }

    @Test
    public void testRemoveOrder() {
        String[] s = {"C9", "7250", "190", "190", "0", "280"};
        Client cl = new Client(s);

        Processing processing = new Processing(cl);
        AbstractOperation operation = new Buy("C9", "A", 20, 15);

        queue.removeOrder(operation.getOrder());

        assertFalse(queue.checkIfExists(operation.getOrder()));

    }


}

import junit.framework.TestCase;
import org.junit.Test;
import sber.testtask.clients.Client;

public class TestBuy extends TestCase {

    @Test
    public void testApplyOperation() {
        String[] s = {"C9", "7250", "190", "190", "0", "280"};
        Client client = new Client(s);

        String stock = "C";
        Integer quantity = 3;
        Integer price = 3;

        ((Client.Stock)client.getStocks().get(stock)).increment(quantity);
        client.clientBalanceUSD -= quantity * price;

        assertEquals(Integer.valueOf(7241), client.clientBalanceUSD);
        assertEquals("3", client.getStocks().get(stock).toString());
    }

}

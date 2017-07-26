import junit.framework.TestCase;
import org.junit.Test;
import sber.testtask.clients.Client;
import sber.testtask.clients.Stock;

public class TestSell extends TestCase {


    @Test
    public void testApplyOperation() {
        String[] s = {"C9", "7250", "190", "190", "0", "280"};
        Client client = new Client(s);

        String stock = "D";
        Integer quantity = 9;
        Integer price = 12;

        ((Stock)client.getStocks().get(stock)).decrement(quantity);
        Integer currentBalance = client.getClientBalanceUSD();
        client.setClientBalanceUSD(currentBalance + quantity * price);

        assertEquals(Integer.valueOf(7358), client.getClientBalanceUSD());
        assertEquals("271", client.getStocks().get(stock).toString());
    }
}

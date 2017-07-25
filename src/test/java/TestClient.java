import junit.framework.TestCase;
import org.junit.Test;
import sber.testtask.clients.Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestClient extends TestCase {

    @Test
    public void testClient() throws IOException {

        Client client = new Client("src/main/resources/clients.txt");

        String[] s = {"C9", "7250", "190", "190", "0", "280"};
        Client cl = new Client(s);
        assertEquals(cl.toString(), client.getClientByName("C9").toString());

        Map<String, Client.Stock> stocksList = new HashMap<>();
        stocksList.put(Client.Stocks.A.toString(), new Client.Stock(190));
        stocksList.put(Client.Stocks.B.toString(), new Client.Stock(190));
        stocksList.put(Client.Stocks.C.toString(), new Client.Stock(0));
        stocksList.put(Client.Stocks.D.toString(), new Client.Stock(280));

        assertEquals(stocksList.toString(), cl.getStocks().toString());
    }
}

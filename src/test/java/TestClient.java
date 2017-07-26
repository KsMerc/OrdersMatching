import junit.framework.TestCase;
import org.junit.Test;
import sber.testtask.clients.Client;

import java.io.IOException;

public class TestClient extends TestCase {

    @Test
    public void testClient() throws IOException {

        Client client = new Client("src/main/resources/clients.txt");

        String[] s = {"C9", "7250", "190", "190", "0", "280"};
        Client cl = new Client(s);
        assertEquals(cl.toString(), client.getClientByName("C9").toString());
        assertEquals("7250", cl.getClientBalanceUSD().toString());
        client.setClientBalanceUSD(8000);
        assertEquals("8000", client.getClientBalanceUSD().toString());
    }
}

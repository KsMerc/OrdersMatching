package sber.testtask.orders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sber.testtask.OperationResolver;
import sber.testtask.Processing;
import sber.testtask.clients.Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class Order {

    private static Logger log = LoggerFactory.getLogger(Order.class);

    private Processing processing;

    private String stock;
    private Integer orderPrice;
    private Integer quantity;

    public Order(String ordersPath, Processing processing) throws IOException {
        this.processing = processing;
        applyOrderForClient(ordersPath);
    }

    public Order(String stock, Integer price, Integer quantity) {
        this.stock = stock;
        this.orderPrice = price;
        this.quantity = quantity;
    }

    public String getStock() { return stock; }

    public Integer getOrderPrice() { return orderPrice; }

    public Integer getQuantity() { return quantity; }

    private void applyOrderForClient(String ordersPath) throws IOException {
        try(Stream<String> stream = Files.lines(Paths.get(ordersPath))) {
            stream.forEach(lines -> {
                String[] item = lines.split("\t");
                new OperationResolver().resolveOperation(item).addOrderToProcessing(processing);
            });
        }
        catch(IOException e) {
            log.error("Cannot read from " + ordersPath, e);
            throw new IOException(e);
        }

        writeResult("src/main/resources/result.txt");
    }

    private void writeResult(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();

        Map<String, Client> clientsList = processing.getClients().getClientsList();
        for (Map.Entry<String, Client> client : clientsList.entrySet()) {
            sb.append(client.getValue().toString()).append("\r\n");
        }

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] buff = sb.toString().getBytes();
            fos.write(buff, 0, buff.length);
        }
        catch(IOException e) {
            log.error("Cannot write to " + fileName, e);
            throw new IOException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return (orderPrice != null && orderPrice.equals(order.orderPrice) &&
                quantity != null && quantity.equals(order.quantity) &&
                stock != null && stock.equals(order.stock));
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 17;
        result = prime * result + (stock == null ? 0 : stock.hashCode());
        result = prime * result + (orderPrice == null ? 0 : orderPrice.hashCode());
        result = prime * result + (quantity == null ? 0 : quantity.hashCode());
        return result;
    }
}

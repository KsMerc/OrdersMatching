package sber.testtask.orders;

import sber.testtask.OperationResolver;
import sber.testtask.Processing;
import sber.testtask.clients.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*### Файл `orders.txt`

Файл списка заявок имеет формат:

 * Имя клиента выставившего заявку
 * Символ операции: "s" - продажа или "b" - покупка.
 * Наименование ценной бумаги
 * Цена заявки (целое число за одну штуку ценной бумаги)
 * Количество продаваемых или покупаемых ценных бумаг
*/

public class Order {

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

    private Map applyOrderForClient(String clientsPath) throws IOException {
        Map orders = new HashMap<String, Client>();

        List<String> lines = Files.readAllLines(Paths.get(clientsPath));
        for (String line : lines) {
            String[] item = line.split("\t");
            new OperationResolver().resolveOperation(item).addOrderToProcessing(processing);
        }

        processing.getClients().writeResult("src/main/resources/result.txt");

        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Order order = (Order) o;
        if (orderPrice != order.orderPrice) return false;
        if (quantity != order.quantity) return false;

        return stock.equals(order.stock);

    }

    @Override
    public int hashCode() {
        int prime = (int) Math.random();
        int result = prime * stock.hashCode() + orderPrice;
        result = prime * result + quantity;
        return result;
    }
}

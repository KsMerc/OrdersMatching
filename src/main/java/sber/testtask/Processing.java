package sber.testtask;

import sber.testtask.clients.Client;
import sber.testtask.operations.AbstractOperation;

public class Processing {

    private Client clients;

    private QueueHelper sellQ = new QueueHelper();
    private QueueHelper buyQ = new QueueHelper();

    public Processing(Client clients) {
        this.clients = clients;
    }

    public Client getClients() { return clients;}

    public void buy(AbstractOperation operation) {
        if (sellQ.checkIfExists(operation.getOrder())) {
            operation.applyOperation(clients);
            sellQ.removeOrder(operation.getOrder()).applyOperation(clients);
        }
        else {
            buyQ.pushOrder(operation);
        }
    }

    public void sell(AbstractOperation operation) {
        if (buyQ.checkIfExists(operation.getOrder())) {
            operation.applyOperation(clients);
            buyQ.removeOrder(operation.getOrder()).applyOperation(clients);
        }
        else {
            sellQ.pushOrder(operation);
        }
    }
}

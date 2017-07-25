package sber.testtask;

import sber.testtask.clients.Client;
import sber.testtask.orders.Order;

import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException {

        Client clients = new Client("src/main/resources/clients.txt");

        new Order("src/main/resources/orders.txt", new Processing(clients));
    }

}



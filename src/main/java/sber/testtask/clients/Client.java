package sber.testtask.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Client {

    private static Logger log = LoggerFactory.getLogger(Client.class);

    private String name;
    private Integer clientBalanceUSD;
    private Map<String, Stock> stocks;

    private Map<String, Client> clientsList = new TreeMap<>();

    public Client(String clientsPath) throws IOException {
        this.clientsList = readFromFile(clientsPath);
    }

    public Client(String[] items) {
        this.name = items[0];
        this.clientBalanceUSD = Integer.valueOf(items[1]);

        this.stocks = new Stock(items).getStocks();
    }

    public void setClientBalanceUSD(Integer clientBalanceUSD) {
        this.clientBalanceUSD = clientBalanceUSD;
    }

    public Integer getClientBalanceUSD() {
        return clientBalanceUSD;
    }

    public Client getClientByName(String name) { return clientsList.get(name); }

    public Map getClientsList() { return clientsList; }

    public Map getStocks() { return stocks; }

    private Map readFromFile(String clientsPath) throws IOException {
        Map<String, Client> clients = new TreeMap<>();

        try(Stream<String> stream = Files.lines(Paths.get(clientsPath)))
        {
            stream.forEach(lines ->  {
                String[] items = lines.split("\t");
                clients.put(items[0], new Client(items));
            });
        }
        catch (IOException e) {
            log.error("Cannot read from " + clientsPath, e);
            throw new IOException(e);
        }

        return clients;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append("\t").append(clientBalanceUSD).append("\t");

        for (Map.Entry<String, Stock> e : stocks.entrySet()) {
            sb.append(e.getKey()).append("\t").append(e.getValue()).append("\t");
        }

        return sb.toString();
    }
}

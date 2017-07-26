package sber.testtask;

import sber.testtask.operations.AbstractOperation;
import sber.testtask.operations.Buy;
import sber.testtask.operations.Sell;

public class OperationResolver {

    public AbstractOperation resolveOperation(String[] items) {
        String operation = items[1];

        AbstractOperation op;

        switch (operation) {
            case "s":
                op = new Sell(items[0], items[2], Integer.valueOf(items[3]), Integer.valueOf(items[4]));
                break;
            case "b":
                op = new Buy(items[0], items[2], Integer.valueOf(items[3]), Integer.valueOf(items[4]));
                break;
            default:
                throw new IllegalStateException("The operation " + operation + " is not supported.");
        }

        return op;

    }
}

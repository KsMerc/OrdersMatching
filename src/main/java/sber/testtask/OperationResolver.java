package sber.testtask;

import sber.testtask.operations.AbstractOperation;
import sber.testtask.operations.Buy;
import sber.testtask.operations.Sell;

public class OperationResolver {

    public AbstractOperation resolveOperation(String[] items) {
        String operation = items[1];

        AbstractOperation iOp;

        switch (operation) {
            case "s":
                iOp = new Sell(items[0], items[2], Integer.valueOf(items[3]), Integer.valueOf(items[4]));
                break;
            case "b":
                iOp = new Buy(items[0], items[2], Integer.valueOf(items[3]), Integer.valueOf(items[4]));
                break;
            default:
                throw new IllegalStateException("The operation " + operation + " is not supported.");
        }

        return iOp;

    }
}

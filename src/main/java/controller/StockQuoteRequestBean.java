package controller;

import java.io.Serializable;

/**
 * Bean for form binding - used by getStockQuoteController.
 *
 * @author Initial: amark
 * @version 1.0
 */
public class StockQuoteRequestBean implements Serializable {

    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

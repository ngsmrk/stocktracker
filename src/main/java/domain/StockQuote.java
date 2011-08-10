package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Persistent object that represents a quote.
 *
 * @author Initial: amark
 * @version 1.0
 */

@Entity
public class StockQuote {

    @Id
    @GeneratedValue
    private Long id;

    private String symbol;

    private double askingPrice;

    private double bidPrice;

    public StockQuote() {
    }

    public StockQuote(String symbol, double askingPrice, double bidPrice) {
        this.symbol = symbol;
        this.askingPrice = askingPrice;
        this.bidPrice = bidPrice;
    }


    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getAskingPrice() {
        return askingPrice;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setAskingPrice(double askingPrice) {
        this.askingPrice = askingPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o instanceof StockQuote) {

            final StockQuote otherStockQuote = (StockQuote) o;
            Long otherId = otherStockQuote.getId();
            if (otherId != null && this.id != null) {
                return otherId.equals(this.id);
            } else {
                return super.equals(o);
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "StockQuote{" +
                "symbol='" + symbol + '\'' +
                ", id=" + id +
                '}';
    }

    public double getPrice() {

        double price = bidPrice;

        price += (askingPrice - bidPrice) / 2;

        return price;
    }

    public boolean isValid() {
        return Double.isNaN(getPrice()) == false;
    }
}

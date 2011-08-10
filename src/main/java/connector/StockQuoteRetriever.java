package connector;

import domain.StockQuote;
import exceptions.StockQuoteRetrievalException;

/**
 * Interface for classes that retrieve stock quotes.
 *
 * @author Initial: amark
 * @version 1.0
 */
public interface StockQuoteRetriever {
    StockQuote getStockQuote(String symbol) throws StockQuoteRetrievalException;
}

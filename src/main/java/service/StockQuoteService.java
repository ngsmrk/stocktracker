package service;

import domain.StockQuote;
import exceptions.StockQuoteRetrievalException;

import java.util.List;

/**
 * Interface for interacting with persistent stock quotes.
 *
 * @author Initial: amark
 * @version 1.0
 */
public interface StockQuoteService {

    List<StockQuote> getStockQuotes();

    StockQuote getStockQuote(String symbol) throws StockQuoteRetrievalException;

    void save(StockQuote quote);

}

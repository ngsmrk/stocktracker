package service;

import domain.StockQuote;
import exceptions.StockQuoteRetrievalException;

import java.util.List;

/**
 * Service for interacting with persistent stock quotes.
 *
 * @author Initial: amark
 * @version 1.0
 */
public class StockQuoteServiceImpl implements StockQuoteService {

    private StockQuoteDAO stockQuoteDAO;

    public void setStockQuoteDAO(StockQuoteDAO stockQuoteDAO) {
        this.stockQuoteDAO = stockQuoteDAO;
    }

    public List<StockQuote> getStockQuotes() {
        return stockQuoteDAO.getStockQuotes();
    }

    public StockQuote getStockQuote(String symbol) throws StockQuoteRetrievalException {
        return stockQuoteDAO.getStockQuote(symbol);
    }

    public void save(StockQuote quote) {

        // check for persisted quote to be updated
        StockQuote persistentQuote = stockQuoteDAO.getStockQuote(quote.getSymbol());
        if (persistentQuote != null) {
            persistentQuote.setAskingPrice(quote.getAskingPrice());
            persistentQuote.setBidPrice(quote.getBidPrice());
            stockQuoteDAO.save(persistentQuote);
        }

        else {
            stockQuoteDAO.save(quote);
        }
    }
}

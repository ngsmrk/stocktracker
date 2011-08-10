package exceptions;

/**
 * Base exception.
 *
 * @author Initial: amark
 * @version 1.0
 */
public class StockQuoteRetrievalException extends Exception {

    public StockQuoteRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockQuoteRetrievalException(String message) {
        super(message);
    }
}

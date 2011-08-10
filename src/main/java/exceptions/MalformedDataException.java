package exceptions;


/**
 * Exception that indicates that stock quote data could not be parsed.
 *
 * @author Initial: amark
 * @version 1.0
 */
public class MalformedDataException extends StockQuoteRetrievalException {

    public MalformedDataException(String message) {
        super(message);
    }
}

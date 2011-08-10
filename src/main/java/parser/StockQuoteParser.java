package parser;

import domain.StockQuote;
import exceptions.MalformedDataException;

/**
 * Interface for stock quote parsing classes.
 *
 * @author Initial: amark
 * @version 1.0
 */
public interface StockQuoteParser {

    public StockQuote parse(String input) throws MalformedDataException;
}

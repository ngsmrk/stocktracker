package parser;

import domain.StockQuote;
import exceptions.MalformedDataException;

/**
 * Yahoo-specific version of StockQuoteParser.
 *
 * @author Initial: amark
 * @version 1.0
 */
public class YahooStockQuoteParser implements StockQuoteParser {

    public StockQuote parse(String input) throws MalformedDataException {

        if (input == null || input.length() == 0) {
            throw new MalformedDataException("Unable to parse stock data - no data supplied");
        }

        String[] values = input.split(",");
        if (values.length != 3) {
            throw new MalformedDataException("Unable to parse stock data - missing data");
        }

        String symbol = values[0].trim();
        symbol = symbol.replace("\"", "");
        double askingPrice = parseDoubleValue(values[1]);
        double bidPrice = parseDoubleValue(values[2]);

        final StockQuote stockQuote = new StockQuote(symbol, askingPrice, bidPrice);

        return stockQuote;

    }

    /**
     * Protected to allow testing.
     *
     * @param value
     * @return value or Double.NaN
     */
    double parseDoubleValue(String value) {

        double doubleValue = Double.NaN;

        try {
            doubleValue = Double.parseDouble(value.trim());
        } catch (NumberFormatException ignore) {

        }

        return doubleValue;
    }
}

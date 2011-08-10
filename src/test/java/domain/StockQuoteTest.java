package domain;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class StockQuoteTest {

    @Test
    public void testConstructor() {

        StockQuote quote = new StockQuote("MSFT", 10.50, 12.50);
        assertEquals("MSFT", quote.getSymbol());
        assertEquals(10.50, quote.getAskingPrice());
        assertEquals(12.50, quote.getBidPrice());
    }

    @Test
    public void testGetPrice() {

        StockQuote quote = new StockQuote("MSFT", 24.61, 24.55);
        assertEquals(24.58, quote.getPrice());
    }
}

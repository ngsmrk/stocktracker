package connector;

import domain.StockQuote;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import parser.YahooStockQuoteParser;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class HttpStockQuoteRetrieverTest {

    HttpStockQuoteRetriever retriever;

    @Before
    public void setUp() {
        retriever = new HttpStockQuoteRetriever();
        retriever.setBaseURL("http://finance.yahoo.com/d/quotes.csv?s=<SYMBOL>&f=sb2b3");
        retriever.setParser(new YahooStockQuoteParser());
    }

    @Test(timeout = 1000)
    public void testConnection() throws Exception {

        String symbol = "MSFT";
        StockQuote quote = retriever.getStockQuote(symbol);

        assertEquals(symbol, quote.getSymbol());
        assertNotNull(quote.getAskingPrice());
        assertNotNull(quote.getBidPrice());
        assertNotNull(quote.getPrice());
    }

    @Test(timeout = 1000)
    public void testInvalidSymbol() throws Exception {

        String symbol = "LALA";
        retriever.getStockQuote(symbol);
        StockQuote quote = retriever.getStockQuote(symbol);

        assertEquals(symbol, quote.getSymbol());
        assertEquals(Double.NaN, quote.getAskingPrice());
        assertEquals(Double.NaN, quote.getBidPrice());
        assertEquals(Double.NaN, quote.getPrice());
    }
}

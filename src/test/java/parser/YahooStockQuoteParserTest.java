package parser;

import domain.StockQuote;
import exceptions.MalformedDataException;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class YahooStockQuoteParserTest {

    private YahooStockQuoteParser parser;

    @Before
    public void setUp() {
        parser = new YahooStockQuoteParser();
    }

    @Test(expected = MalformedDataException.class)
    public void testNullData() throws Exception {

        parser.parse(null);
    }

    @Test(expected = MalformedDataException.class)
    public void testBlankData() throws Exception {

        parser.parse("");
    }

    @Test
    public void testDataParsing() throws Exception {

        StockQuote quote = parser.parse("\"MSFT\",24.61,24.55");
        assertEquals("MSFT", quote.getSymbol());
        assertEquals(24.61, quote.getAskingPrice());
        assertEquals(24.55, quote.getBidPrice());
    }

    @Test
    public void testDataParsingWithWhiteSpace() throws Exception {

        StockQuote quote = parser.parse(" \"MSFT\" , 24.61 , 24.55 ");
        assertEquals("MSFT", quote.getSymbol());
        assertEquals(24.61, quote.getAskingPrice());
        assertEquals(24.55, quote.getBidPrice());
    }

    @Test(expected = MalformedDataException.class)
    public void testDataParsingWithMissingData() throws Exception {

        StockQuote quote = parser.parse(" \"MSFT\" , 24.61");
    }

    @Test
    public void testGetDoubleValue() {
        assertTrue(Double.isNaN(parser.parseDoubleValue("aaaa")));
        assertEquals(12.55, parser.parseDoubleValue("12.55"));
    }
}

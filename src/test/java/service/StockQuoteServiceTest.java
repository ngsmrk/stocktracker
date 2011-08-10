package service;

import domain.StockQuote;
import exceptions.StockQuoteRetrievalException;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.util.List;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class StockQuoteServiceTest extends AbstractDependencyInjectionSpringContextTests {

    static final String VALID_SYMBOL = "MSFT";

    private StockQuoteServiceImpl service;

    private StockQuote stockQuote;

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:/WEB-INF/spring/application-context-services.xml"};
    }

    public void onSetUp() throws Exception {
        super.onSetUp();

        stockQuote = new StockQuote(VALID_SYMBOL, 10.50, 11.50);
        service.save(stockQuote);
    }

    public void setService(StockQuoteServiceImpl service) {
        this.service = service;
    }

    public void testGetStockQuotes() {

        List<StockQuote> stockQuotes = service.getStockQuotes();
        assertEquals(1, stockQuotes.size());
        assertEquals(stockQuote, stockQuotes.get(0));
    }

    public void testGetStockQuote() throws StockQuoteRetrievalException {

        final StockQuote retrievedQuote = service.getStockQuote(VALID_SYMBOL);
        assertEquals(VALID_SYMBOL, retrievedQuote.getSymbol());
        assertNotNull(retrievedQuote.getId());
    }
}

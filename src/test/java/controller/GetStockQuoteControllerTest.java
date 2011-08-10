package controller;

import domain.StockQuote;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class GetStockQuoteControllerTest extends AbstractDependencyInjectionSpringContextTests {

    private GetStockQuoteController controller;

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:/WEB-INF/spring/application-context.xml",
                "classpath:/WEB-INF/spring/application-context-services.xml"};
    }

    public void setController(GetStockQuoteController controller) {
        this.controller = controller;
    }

    public void testFormBackingObject() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        StockQuoteRequestBean fbo = (StockQuoteRequestBean) controller.formBackingObject(request);
        assertNull(fbo.getSymbol());
    }

    public void testSubmit() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        final String symbol = "MSFT";
        StockQuoteRequestBean fbo = new StockQuoteRequestBean();
        fbo.setSymbol(symbol);

        BindException e = null;
        ModelAndView view = controller.onSubmit(request, response, fbo, e);
        assertEquals("displayquote", view.getViewName());
        StockQuote stockQuote = (StockQuote) view.getModel().get("stockQuote");
        assertEquals(symbol, stockQuote.getSymbol());
        assertTrue(stockQuote.isValid());
    }

    public void testSubmitWithInvalidStockSymbol() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        final String symbol = "LALA";
        StockQuoteRequestBean fbo = new StockQuoteRequestBean();
        fbo.setSymbol(symbol);

        BindException e = null;
        ModelAndView view = controller.onSubmit(request, response, fbo, e);
        assertEquals("displayquote", view.getViewName());
        StockQuote stockQuote = (StockQuote) view.getModel().get("stockQuote");
        assertEquals(symbol, stockQuote.getSymbol());
        assertFalse(stockQuote.isValid());
    }
}

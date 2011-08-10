package controller;

import domain.StockQuote;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import service.StockQuoteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller that loads all persistent stock quotes for display.
 *
 * @author Initial: amark
 * @version 1.0
 */
public class ViewStockQuotesController implements Controller {

    private StockQuoteService service;

    public void setService(StockQuoteService service) {
        this.service = service;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<StockQuote> quotes = service.getStockQuotes();

        return new ModelAndView("displayquotes", "quotes", quotes);
    }
}

package controller;

import connector.StockQuoteRetriever;
import domain.StockQuote;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StockQuoteService;

/**
 * Controller that loads stock quotes.
 *
 * @author Initial: amark
 * @version 1.0
 */
public class GetStockQuoteController extends SimpleFormController {

    private StockQuoteRetriever retriever;

    private StockQuoteService service;

    public void setRetriever(StockQuoteRetriever retriever) {
        this.retriever = retriever;
    }

    public void setService(StockQuoteService service) {
        this.service = service;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, BindException e) throws Exception {

        final ModelAndView modelAndView = new ModelAndView(getSuccessView());
        final StockQuoteRequestBean bean = (StockQuoteRequestBean) o;

        final StockQuote stockQuote = retriever.getStockQuote(bean.getSymbol());
        if (stockQuote.isValid()) service.save(stockQuote);

        modelAndView.addObject("stockQuote", stockQuote);

        return modelAndView;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest httpServletRequest) throws Exception {
        return new StockQuoteRequestBean();
    }
}

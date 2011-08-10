package validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import controller.StockQuoteRequestBean;

/**
 * Validator that checks that a symbol has been supplied.
 *
 * @author Initial: amark
 * @version 1.0
 */
public class StockQuoteValidator implements Validator {

    public boolean supports(Class aClass) {
       return aClass.isAssignableFrom(StockQuoteRequestBean.class);
    }

    public void validate(Object o, Errors errors) {

        StockQuoteRequestBean bean = (StockQuoteRequestBean) o;
        final String symbol = bean.getSymbol();

        if (symbol == null || symbol.trim().length() == 0) {
            errors.rejectValue("symbol", "form.symbol.required", "Symbol must be supplied");
        }
    }
}

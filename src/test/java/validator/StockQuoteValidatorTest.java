package validator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.validation.BindException;
import controller.StockQuoteRequestBean;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class StockQuoteValidatorTest {

    private StockQuoteValidator validator;

    @Before
    public void setUp() {
        validator = new StockQuoteValidator();
    }

    @Test
    public void testNullValue() {

        StockQuoteRequestBean bean = new StockQuoteRequestBean();
        BindException errors = new BindException(bean, "quote");

        validator.validate(bean, errors);
        assertTrue(errors.hasFieldErrors());

        assertEquals("form.symbol.required", errors.getFieldError("symbol").getCode());
    }

    @Test
    public void testBlankValue() {

        StockQuoteRequestBean bean = new StockQuoteRequestBean();
        bean.setSymbol("  ");
        BindException errors = new BindException(bean, "quote");

        validator.validate(bean, errors);
        assertTrue(errors.hasFieldErrors());

        assertEquals("form.symbol.required", errors.getFieldError("symbol").getCode());
    }

    @Test
    public void testSupportsClass() {

        assertTrue(validator.supports(StockQuoteRequestBean.class));

        assertFalse(validator.supports(Integer.class));
    }
}

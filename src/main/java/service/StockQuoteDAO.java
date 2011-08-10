package service;

import domain.StockQuote;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * DAO for StockQuote.
 *
 * @author Initial: amark
 * @version 1.0
 */
public class StockQuoteDAO extends HibernateDaoSupport {


    public List<StockQuote> getStockQuotes() {
        return getHibernateTemplate().loadAll(StockQuote.class);
    }

    public StockQuote getStockQuote(String symbol) {

        final Criteria criteria = getSession().createCriteria(StockQuote.class);
        criteria.add(Restrictions.eq("symbol", symbol));

        List<StockQuote> results = criteria.list();
        return results.isEmpty() ? null : results.get(0);
    }

    public void save(StockQuote quote) {
        getSession().saveOrUpdate(quote);
        getSession().flush();
    }
}



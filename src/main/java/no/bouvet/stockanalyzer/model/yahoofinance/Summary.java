package no.bouvet.stockanalyzer.model.yahoofinance;

import no.bouvet.stockanalyzer.exception.StockAnalyzerException;
import no.bouvet.stockanalyzer.utils.Config;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 18/03/15
 * Time: 09:31
 */
public class Summary extends CommonStock {

    private BigDecimal stockPrice;

    public Summary(String property, String symbol, int timeout) {
        super(property, symbol, timeout);
    }

    @Override
    protected void fillAllFields() throws StockAnalyzerException {
        this.stockPrice = get(Property.summary_stockPrice);
    }


    public BigDecimal getStockPrice() {
        return stockPrice;
    }
}

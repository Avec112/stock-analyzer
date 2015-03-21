package no.bouvet.stockanalyzer.service.model;

import no.bouvet.stockanalyzer.exception.StockAnalyzerException;
import no.bouvet.stockanalyzer.model.yahoofinance.CommonStock;
import no.bouvet.stockanalyzer.model.yahoofinance.Property;
import no.bouvet.stockanalyzer.utils.Config;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 20/03/15
 * Time: 17:04
 */
public class KeyStatistics extends CommonStock {
    
    private BigInteger sharesOutstading;
    
    public KeyStatistics(String property, String symbol, int timeout) {
        super(property, symbol, timeout);
    }

    @Override
    protected void fillAllFields() throws StockAnalyzerException {
        this.sharesOutstading = get(Property.keyStatistics_sharesOutstanding).toBigInteger();
    }

    public BigInteger getSharesOutstanding() {
        return sharesOutstading;
    }
}

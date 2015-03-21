package no.bouvet.stockanalyzer.model.yahoofinance;

import no.bouvet.stockanalyzer.exception.StockAnalyzerException;
import no.bouvet.stockanalyzer.utils.Config;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 18/03/15
 * Time: 09:30
 */
public class BalanceSheet extends CommonStock {

    private BigDecimal totalAssets;
    
    public BalanceSheet(String property, String symbol, int timeout) {
        super(property, symbol, timeout);
    }

    @Override
    protected void fillAllFields() throws StockAnalyzerException {
        this.totalAssets = get(Property.balanceSheet_totalAssetsLatest);
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }
}

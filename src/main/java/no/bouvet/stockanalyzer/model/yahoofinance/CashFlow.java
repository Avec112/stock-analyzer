package no.bouvet.stockanalyzer.model.yahoofinance;

import no.bouvet.stockanalyzer.exception.StockAnalyzerException;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 20/03/15
 * Time: 13:29
 */
public class CashFlow extends CommonStock {

    private BigDecimal cashFlowFromOperatingActivitiesLatest;
    
    public CashFlow(String property, String symbol,int timeout) {
        super(property, symbol, timeout);
    }

    @Override
    protected void fillAllFields() throws StockAnalyzerException {
        this.cashFlowFromOperatingActivitiesLatest = get(Property.cashFlow_cashFlowFromOperatingActivitiesLatest);
    }

    public BigDecimal getCashFlowFromOperatingActivitiesLatest() {
        return cashFlowFromOperatingActivitiesLatest;
    }
}

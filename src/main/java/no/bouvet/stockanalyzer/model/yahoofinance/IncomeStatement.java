package no.bouvet.stockanalyzer.model.yahoofinance;

import no.bouvet.stockanalyzer.exception.StockAnalyzerException;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 18/03/15
 * Time: 09:30
 */
public class IncomeStatement extends CommonStock {
    
    private BigDecimal netIncome;
    
    public IncomeStatement(String url, String symbol, int timeout) {
        super(url, symbol, timeout);
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }


    @Override
    protected void fillAllFields() throws StockAnalyzerException {
        this.netIncome = get(Property.incomeStatement_netIncomeLatest);
    }

}

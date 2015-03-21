package no.bouvet.stockanalyzer.model.yahoofinance;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 18/03/15
 * Time: 11:11
 * 
 * Use SuperSelector to find correct css query
 *  
 */
public enum Property {
    
    keyStatistics_sharesOutstanding("yahoo.finance.keyStatistics.sharesOutstanding", 'B'),
    summary_stockPrice("yahoo.finance.summary.stockPrice", '1'),
    cashFlow_cashFlowFromOperatingActivitiesLatest("yahoo.finance.cashFlow.cashFlowFromOperatingActivitiesLatest", 'K'),
    balanceSheet_totalAssetsLatest("yahoo.finance.balanceSheet.totalAssetsLatest", 'K'),
    incomeStatement_netIncomeLatest("yahoo.finance.incomeStatement.netIncomeLatest", 'K');

    private final String key;
    private final char multiplier;

    Property(String key, char multiplier) {
        this.key = key;
        this.multiplier = multiplier;
        
    }

    @Override
    public String toString() {
        return this.getKey();
    }
    
    public char getMultiplier() {
        return this.multiplier;
        
    }

    public String getKey() {
        return this.key;

    }
}

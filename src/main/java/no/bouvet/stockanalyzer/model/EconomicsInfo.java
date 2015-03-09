package no.bouvet.stockanalyzer.model;

import com.yahoo.finance.types.balancesheet.Balancesheet;
import com.yahoo.finance.types.incomestatement.Incomestatement;
import com.yahoo.finance.types.quotes.Quotes;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 09/03/15
 * Time: 08:03
 */
public class EconomicsInfo {
    
    private Incomestatement incomestatement;
    private Balancesheet balancesheet;
    private Quotes quotes;

    public EconomicsInfo(Incomestatement incomestatement, Balancesheet balancesheet, Quotes quotes) {
        this.incomestatement = incomestatement;
        this.balancesheet = balancesheet;
        this.quotes = quotes;
    }

    public Incomestatement getIncomestatement() {
        return incomestatement;
    }

    public void setIncomestatement(Incomestatement incomestatement) {
        this.incomestatement = incomestatement;
    }

    public Balancesheet getBalancesheet() {
        return balancesheet;
    }

    public void setBalancesheet(Balancesheet balancesheet) {
        this.balancesheet = balancesheet;
    }

    public Quotes getQuotes() {
        return quotes;
    }

    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
    }
}

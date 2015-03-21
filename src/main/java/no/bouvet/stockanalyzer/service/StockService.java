package no.bouvet.stockanalyzer.service;

//import no.bouvet.stockanalyzer.model.Stock;

import no.bouvet.stockanalyzer.model.StockQuote;
import no.bouvet.stockanalyzer.model.yahoofinance.BalanceSheet;
import no.bouvet.stockanalyzer.model.yahoofinance.CashFlow;
import no.bouvet.stockanalyzer.model.yahoofinance.IncomeStatement;
import no.bouvet.stockanalyzer.model.yahoofinance.Summary;
import no.bouvet.stockanalyzer.service.model.KeyStatistics;
import no.bouvet.stockanalyzer.utils.AnalysisHelper;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bouvet.ness
 * Date: 06/03/15
 * Time: 10:18
 */

@Service("StockService")
public class StockService {

    private static final Logger log = LoggerFactory.getLogger(StockService.class);

    @Value("${html.timeout}")
    private int timeout = 3000;

    @Value("${yahoo.finance.incomeStatementUrl}")
    private String incomeStatementUrl;

    @Value("${yahoo.finance.balanceSheetUrl}")
    private String balanceSheetUrl;

    @Value("${yahoo.finance.summaryUrl}")
    private String summaryUrl;

    @Value("${yahoo.finance.cashFlowUrl}")
    private String cashFlowUrl;

    @Value("${yahoo.finance.keyStatisticsUrl}")
    private String keyStatisticsUrl;
    
    private KeyStatistics keyStatistics;
    private CashFlow cashFlow;
    private BalanceSheet balanceSheet;
    private Summary summary;
    private IncomeStatement incomeStatement;
    
    @PostConstruct
    public void init() {
        // Initialize needed classes   
        
    }
    


    public List<StockQuote> getStockQuotes(String symbol) {
        throw new NotImplementedException();
    }
    
    public IncomeStatement getIncomeStatement(String symbol) {
        if(incomeStatement == null) 
            incomeStatement = new IncomeStatement(incomeStatementUrl, symbol, timeout);
        log.debug(incomeStatement.toString());
        return incomeStatement;
    }
    
    public Summary getSummary(String symbol) {
        if(summary == null)
            summary = new Summary(summaryUrl, symbol, timeout);
        log.debug(summary.toString());
        return summary; 
    }

    public BalanceSheet getBalanceSheet(String symbol) {
        if(balanceSheet == null)
            balanceSheet = new BalanceSheet(balanceSheetUrl, symbol, timeout);
        log.debug(balanceSheet.toString());
        return balanceSheet;
    }

    public CashFlow getCashFlow(String symbol) {
        if(cashFlow == null)
            cashFlow = new CashFlow(cashFlowUrl, symbol, timeout);
        log.debug(cashFlow.toString());
        return cashFlow;
    }

    public KeyStatistics getKeyStatistics(String symbol) {
        if(keyStatistics == null)
            keyStatistics = new KeyStatistics(keyStatisticsUrl, symbol, timeout);
        log.debug(keyStatistics.toString());
        return keyStatistics;
    }
    
    
    

    public StockQuote getStockQuote(String symbol) {
        StockQuote quote = new StockQuote();
        
        quote.setSymbol(symbol.toUpperCase());
        
        quote.setNetIncome(getIncomeStatement(symbol).getNetIncome());
        quote.setPrice(getSummary(symbol).getStockPrice());
        quote.setVolume(getKeyStatistics(symbol).getSharesOutstanding());
        quote.setCashFlow(getCashFlow(symbol).getCashFlowFromOperatingActivitiesLatest());
        
        // TODO Hmmm, rekkefølge er viktig...burde sende inn hele objektet, og få satt direkte!
        quote.setMarketCap(AnalysisHelper.marketCap(quote.getPrice(), quote.getVolume()));
        quote.setEps(AnalysisHelper.eps(quote.getNetIncome(), quote.getVolume()));
        quote.setPe(AnalysisHelper.pe(quote.getPrice(), quote.getEps()));
        quote.setPcf(AnalysisHelper.pcf(quote.getMarketCap(), quote.getCashFlow()));
        
        return quote;
    }
    

    
    


}

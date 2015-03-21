package no.bouvet.stockanalyzer.service;

import no.bouvet.stockanalyzer.model.StockQuote;
import no.bouvet.stockanalyzer.model.yahoofinance.BalanceSheet;
import no.bouvet.stockanalyzer.model.yahoofinance.CashFlow;
import no.bouvet.stockanalyzer.model.yahoofinance.IncomeStatement;
import no.bouvet.stockanalyzer.model.yahoofinance.Summary;
import no.bouvet.stockanalyzer.service.model.KeyStatistics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/resources/application-context.xml")
//@RunWith(MockitoJUnitRunner.class)
//@PrepareForTest(StockService.class)

public class StockServiceTest {

    @Spy
    private StockService stockService;
    
    private String symbol;
    

    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        symbol = "aapl";
//        stockService = PowerMockito.spy(new StockService());

        Whitebox.setInternalState(stockService, "incomeStatementUrl", "http://finance.yahoo.com/q/is?s={0}&annual");
        Whitebox.setInternalState(stockService, "summaryUrl", "http://finance.yahoo.com/q?s={0}&annual");
        Whitebox.setInternalState(stockService, "balanceSheetUrl", "http://finance.yahoo.com/q/bs?s={0}&annual");
        Whitebox.setInternalState(stockService, "cashFlowUrl", "http://finance.yahoo.com/q/cf?s={0}&annual");
        Whitebox.setInternalState(stockService, "keyStatisticsUrl", "http://finance.yahoo.com/q/ks?s={0}");
    }

    @Test
    public void testIncomeStatement() throws Exception {
        
        IncomeStatement incomeStatement = stockService.getIncomeStatement(symbol);
        assertNotEquals("", incomeStatement.getNetIncome());
    }
    
    @Test
    public void testSummary() throws Exception {
        
        Summary summary = stockService.getSummary(symbol);
        assertNotEquals("", summary.getStockPrice());
    }

    @Test
    public void testBalanceSheet() throws Exception {

        BalanceSheet bs = stockService.getBalanceSheet(symbol);
        assertNotEquals("", bs.getTotalAssets());
    }

    @Test
    public void testCashFlow() throws Exception {

        CashFlow cf = stockService.getCashFlow(symbol);
        assertNotEquals("", cf.getCashFlowFromOperatingActivitiesLatest());
    }

    @Test
    public void testKeyStatistics() throws Exception {
        
        KeyStatistics ks = stockService.getKeyStatistics(symbol);
        assertNotEquals("", ks.getSharesOutstanding());
    }

    @Test
    public void testCompanyAnalysis() throws Exception {
        StockQuote stockQuote = stockService.getStockQuote(symbol);
        
        assertNotNull(stockQuote);
        
        System.out.println(stockQuote);

    }
}
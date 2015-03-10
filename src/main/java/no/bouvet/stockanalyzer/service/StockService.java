package no.bouvet.stockanalyzer.service;

import no.bouvet.stockanalyzer.model.EconomicsInfo;
import no.bouvet.stockanalyzer.model.Stock;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bouvet.ness
 * Date: 06/03/15
 * Time: 10:18
 */

@Service("StockService")
public class StockService {

    @Value("${yahoo.finance.incomestatement}")
    private String incomestatementUrl;

    @Value("${yahoo.finance.balancesheet}")
    private String balancesheetUrl;

    @Value("${yahoo.finance.quotes}")
    private String quotesUrl;
    
    private static final Logger log = LoggerFactory.getLogger(StockService.class);

    public List<Stock> lookupStock(String ticker) throws IOException {
        
        log.debug("ticker=", ticker);
        log.debug("yahoo.finance.quotes=", quotesUrl);
        
        List<Stock> stocks = new ArrayList<Stock>();
        
        stocks.add(getStock("bouvet"));
        stocks.add(getStock("aapl"));

        
        
        return stocks;
        
        
    }
    
    public EconomicsInfo getEconomicsInfo(String tickers) throws IOException {
        
        throw new NotImplementedException();
    }

    
    private Stock getStock(String ticker) {
        Stock stock = new Stock();
        stock.setTicker(ticker);
        return stock;
        
    }


}

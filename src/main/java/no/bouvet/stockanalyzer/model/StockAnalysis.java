package no.bouvet.stockanalyzer.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 08/03/15
 * Time: 20:54
 */
public class StockAnalysis {
    
    private Logger log = LoggerFactory.getLogger(StockAnalysis.class);
    
    private String ticker;
    private Date date;
    
    private BigDecimal stockPrice; // gjeldende aksjekurs
    private BigDecimal stockVolume; // antall utestående aksjer TODO hva med utvannede?
    private BigDecimal netProfit; // netto resultat
    private BigDecimal cashFlow; // kontantstrøm fra drift
    
    private BigDecimal PE;
//    private double PB;
//    private double PS;
    private BigDecimal PCF;
    private BigDecimal PRICE;
    private BigDecimal EPS;


    /**
     *  
     * @param stock (Yahoo Finance stock)
     */
    public StockAnalysis(Stock stock) {
        StockQuote quote = stock.getQuote();
        this.ticker = quote.getSymbol();
        this.date = quote.getLastTradeTime().getTime(); 
//        this.date = stock.getHistory().get(0).getDate().getTime();
        this.stockPrice = quote.getPrice();
        this.stockVolume = new BigDecimal(quote.getVolume());
        this.EPS = stock.getStats().getEps();
        this.PE = stock.getStats().getPe();

        calculate();
//        this.netProfit = stock.getStats()
    }


    /*public StockAnalysis(String ticker, Date date, BigDecimal stockPrice, BigDecimal stockVolume, BigDecimal netProfit, BigDecimal cashFlow) {
        this.ticker = ticker;
        this.date = date;
        this.stockPrice = stockPrice;
        this.stockVolume = stockVolume;
        this.netProfit = netProfit;
        this.cashFlow = cashFlow;
        
        init();
    }*/

    public void init() {     
        log.debug("init()");        
        validate();



//        stockPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
//        stockVolume.setScale(0);
//        netProfit.setScale(2, BigDecimal.ROUND_HALF_UP);
//        cashFlow.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        calculate();
        
//        PRICE.setScale(0, BigDecimal.ROUND_HALF_UP);
        EPS.setScale(2, BigDecimal.ROUND_HALF_UP);
        PE.setScale(2, BigDecimal.ROUND_HALF_UP);
//        PCF.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private void validate() {
        // TODO implement
    }

    private void calculate() {
        log.debug("calculate()");
        this.PRICE = stockPrice.multiply(stockVolume);
//        this.EPS = netProfit.divide(stockVolume);
//        this.PE = stockPrice.divide(EPS);
//        this.PCF = PRICE.divide(cashFlow);
    }


    public Date getDate() {
        return date;
    }

    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    public BigDecimal getStockVolume() {
        return stockVolume;
    }

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public BigDecimal getCashFlow() {
        return cashFlow;
    }

    public BigDecimal getPE() {
        return PE;
    }

    public BigDecimal getPCF() {
        return PCF;
    }

    public BigDecimal getPRICE() {
        return PRICE;
    }

    public BigDecimal getEPS() {
        return EPS;
    }

    public String getTicker() {
        return ticker;
    }

    @Override
    public String toString() {
        return "StockAnalysis{" +
                "ticker='" + ticker + '\'' +
                ", date=" + date +
                ", stockPrice=" + stockPrice +
                ", stockVolume=" + stockVolume +
                ", PE=" + PE +
                ", PRICE=" + PRICE +
                ", EPS=" + EPS +
                '}';
    }
}

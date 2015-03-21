package no.bouvet.stockanalyzer.model.yahoofinance;

import no.bouvet.stockanalyzer.exception.StockAnalyzerException;
import no.bouvet.stockanalyzer.utils.Config;
import no.bouvet.stockanalyzer.utils.HtmlHelper;
import no.bouvet.stockanalyzer.utils.NumberHelper;
import no.bouvet.stockanalyzer.utils.PropertyHelper;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 18/03/15
 * Time: 09:57
 */
public abstract class CommonStock {
    
    protected Logger LOG = LoggerFactory.getLogger(this.getClass());
    private String url;
    private String symbol;
    private Date date;
    

    private HtmlHelper htmlHelper;
    
    private CommonStock(){}

    public CommonStock(String property, String symbol, int timeout) {
        this.url = getUrl(property, symbol);
        this.htmlHelper = new HtmlHelper(this.url, timeout);
        this.symbol = symbol;
        try {
            fillAllFields();
        } catch (StockAnalyzerException e) {
            LOG.error(e.getMessage());
        }

    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    private String getValueByCssQuery(String cssQuery) throws StockAnalyzerException {
        String value = htmlHelper.getValueByCssQuery(cssQuery);
//        LOG.debug(value);

        return value;
        
    }
    
    public BigDecimal get(Property property) throws StockAnalyzerException {
        String value = this.getValueByCssQuery(Config.getProperty(property));
        BigDecimal bigDecimal = NumberHelper.getBigDecimal(value, property);
        LOG.debug("'{}' -> '{}'", property.getKey(), NumberHelper.format(bigDecimal));
        return bigDecimal;
//        return  NumberHelper.convertStringToBigDecimal(value);

    }

    /*public BigDecimal get(String cssQuery, char multiplier) throws StockAnalyzerException {
        String value = this.getValueByCssQuery(cssQuery).trim();
        return  NumberHelper.getBigDecimal(value, multiplier);
//        return  NumberHelper.convertStringToBigDecimal(value);

    }*/

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    private String getUrl(String property, String symbol) {
        return PropertyHelper.format(property, symbol);                
    }
    
    protected abstract void fillAllFields() throws StockAnalyzerException;
}

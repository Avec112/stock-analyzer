package no.bouvet.stockanalyzer.model;


import no.bouvet.stockanalyzer.utils.CustomToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 09/03/15
 * Time: 08:03
 */
@XmlRootElement
public class StockQuote {
    
    private String symbol;
    
    private BigDecimal netIncome;
    private BigDecimal price;
    private BigInteger volume;
    private BigDecimal cashFlow;
    
    private BigDecimal marketCap;
    private BigDecimal pe;
    private BigDecimal pcf;
    private BigDecimal eps;

    public StockQuote() {
    }

    public String getSymbol() {
        return symbol;
    }

    @XmlAttribute
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPe() {
        return pe;
    }

    @XmlElement
    public void setPe(BigDecimal pe) {
        this.pe = pe;
    }

    public BigDecimal getPcf() {
        return pcf;
    }

    @XmlElement
    public void setPcf(BigDecimal pcf) {
        this.pcf = pcf;
    }

    public BigDecimal getEps() {
        return eps;
    }

    @XmlElement
    public void setEps(BigDecimal eps) {
        this.eps = eps;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    @XmlElement
    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigInteger getVolume() {
        return volume;
    }

    @XmlElement
    public void setVolume(BigInteger volume) {
        this.volume = volume;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    @XmlElement
    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public BigDecimal getCashFlow() {
        return cashFlow;
    }

    @XmlElement
    public void setCashFlow(BigDecimal cashFlow) {
        this.cashFlow = cashFlow;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, new CustomToStringStyle());

    }
}

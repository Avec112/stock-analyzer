package no.bouvet.stockanalyzer.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 21/03/15
 * Time: 00:22
 */
public class AnalysisHelper {
    
    // Market cap (price x volume)
    public static BigDecimal marketCap(BigDecimal price, BigInteger volume) {
        return price.multiply(new BigDecimal(volume));
    }

    // EPS (earnings / volume aka netIncome/volume) TODO burde være trailing (TTM)
    public static BigDecimal eps(BigDecimal earnings, BigInteger volume) {
        return earnings.divide(new BigDecimal(volume), 2, BigDecimal.ROUND_HALF_UP);
    }
    
    // PE (Price / EPS) TODO price burde være gjennomsnittlig TTM eller siste 3-6 mnd kanskje?
    public static BigDecimal pe(BigDecimal price, BigDecimal eps) {
        return price.divide(eps, 2, BigDecimal.ROUND_HALF_UP);
        
    }

    // P/CF (Price / Cach Flow)
    public static BigDecimal pcf(BigDecimal marketCap, BigDecimal cashFlow) {
        return marketCap.divide(cashFlow, 2, BigDecimal.ROUND_HALF_UP);

    }
}

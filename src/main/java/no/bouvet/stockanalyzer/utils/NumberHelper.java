package no.bouvet.stockanalyzer.utils;

import no.bouvet.stockanalyzer.exception.StockAnalyzerException;
import no.bouvet.stockanalyzer.model.yahoofinance.Property;
import org.apache.commons.lang.NotImplementedException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 19/03/15
 * Time: 12:44
 */
public class NumberHelper {

    
    public static boolean validateNumber(String value) {
        throw new NotImplementedException();
        
    }
    
    public static BigDecimal convertStringToBigDecimal(String value) throws StockAnalyzerException {
        return convertStringToBigDecimal(value, '.', ',');
    }
    
    public static BigDecimal convertStringToBigDecimal(String value, Character decimalSeparator, Character groupingSeparator) throws StockAnalyzerException {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(decimalSeparator);
        symbols.setGroupingSeparator(groupingSeparator);
        df.setDecimalFormatSymbols(symbols);
        double d = 0;
        try {
            d = df.parse(value).doubleValue();
        } catch (ParseException e) {
            throw new StockAnalyzerException("Could not parse value = '" + value + "'", e);
        }

        BigDecimal bd = new BigDecimal(d).setScale(0, BigDecimal.ROUND_HALF_UP);
        return bd;
        
    }
    


    public static final BigDecimal HUNDRED = new BigDecimal(100);
    public static final BigDecimal THOUSAND = new BigDecimal(1000);
    public static final BigDecimal MILLION = new BigDecimal(1000000);
    public static final BigDecimal BILLION = new BigDecimal(1000000000);

    public static String join(String[] data, String d) {
        if (data.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i;

        for (i = 0; i < (data.length - 1); i++) {
            sb.append(data[i]).append(d);
        }
        return sb.append(data[i]).toString();
    }

    private static String cleanNumberString(String data) {
        return join(data.trim().split(","), "");
    }

    private static boolean isParseable(String data) {
        return !(data == null || data.equals("N/A") || data.equals("-") || data.equals(""));
    }

    public static BigDecimal getBigDecimal(String data, Property property) {
        BigDecimal result = BigDecimal.ZERO;
        if (!isParseable(data)) {
            return result;
        }
        try {
            data = cleanNumberString(data);
            BigDecimal multiplier = BigDecimal.ONE;
            switch (property.getMultiplier()) {
                case 'B':
                    multiplier = BILLION;
                    break;
                case 'M':
                    multiplier = MILLION;
                    break;
                case 'K' :
                    multiplier = THOUSAND;
                    break;                
            }
            if(data.matches(".*\\D$")) { // \D = non digit
                data = data.substring(0, data.length() - 1);
//                System.out.println("Fjernet siste bokstav fra verdien");
            }
            result = new BigDecimal(data).multiply(multiplier);
        } catch (NumberFormatException e) {
//            log(Level.INFO, "Failed to parse: " + data, e);
            e.printStackTrace();
        }
        return result;
    }
    
    public static String format(Number value) {
        return format(value, "#,##0.##", ',', ' ');
//        return format(value, loc, "#,##0.00#");

    }
    
    public static String format(Number value, String pattern, Character decimalSeparator, Character groupingSeparator) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(decimalSeparator);
        symbols.setGroupingSeparator(groupingSeparator);
        DecimalFormat df = new DecimalFormat(pattern, symbols);
        df.applyPattern(pattern);
        String output = df.format(value);
        return output;
    }
    
  
}

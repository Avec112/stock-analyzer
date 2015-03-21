package no.bouvet.stockanalyzer.utils;

import java.text.MessageFormat;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 18/03/15
 * Time: 09:50
 */
public class PropertyHelper {
    
    public static String format(String property, String symbol) {
        return MessageFormat.format(property, symbol);
    }
}

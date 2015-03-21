package no.bouvet.stockanalyzer.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 20/03/15
 * Time: 12:22
 */
public class StockAnalyzerException extends Exception {

    public StockAnalyzerException(String message) {
        super(message);
    }

    public StockAnalyzerException(String message, Throwable cause) {
        super(message, cause);
    }
}

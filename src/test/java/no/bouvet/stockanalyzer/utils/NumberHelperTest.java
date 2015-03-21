package no.bouvet.stockanalyzer.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Locale;

import static org.junit.Assert.*;

public class NumberHelperTest {

    @Test
    public void testConvertStringToBigDecimal() throws Exception {
        assertEquals("123456", NumberHelper.convertStringToBigDecimal("123,456").toString());
        assertEquals("123456", NumberHelper.convertStringToBigDecimal("123,456.49").toString());
        assertEquals("123457", NumberHelper.convertStringToBigDecimal("123,456.50").toString());
    }

    @Test
    public void testFormat() throws Exception {
        assertEquals("123 456", NumberHelper.format(new BigDecimal("123456.0")));
        assertEquals("123 456,51", NumberHelper.format(new BigDecimal("123456.512")));
        assertEquals("123 456", NumberHelper.format(new BigDecimal("123456")));
    }
}
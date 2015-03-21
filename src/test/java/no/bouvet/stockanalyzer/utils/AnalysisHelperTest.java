package no.bouvet.stockanalyzer.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class AnalysisHelperTest {

    @Test
    public void testMarketCap() throws Exception {
        BigDecimal marketCap = AnalysisHelper.marketCap(new BigDecimal(3.50), new BigInteger("1000000"));
        assertEquals(3500000, marketCap.intValue());
    }

    @Test
    public void testEps() throws Exception {
        BigDecimal eps = AnalysisHelper.eps(new BigDecimal(350000), new BigInteger("1000000"));
        assertEquals(new BigDecimal("0.35"), eps);
    }

    @Test
    public void testPe() throws Exception {
        BigDecimal pe = AnalysisHelper.pe(new BigDecimal(3.50), new BigDecimal(0.35));
        assertEquals(new BigDecimal("10.00"), pe);
    }

    @Test
    public void testPCF() throws Exception {
        BigDecimal pcf = AnalysisHelper.pcf(new BigDecimal(3500000), new BigDecimal(100000));
        assertEquals(new BigDecimal("35.00"), pcf);
    }
}
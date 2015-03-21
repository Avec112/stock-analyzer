package no.bouvet.stockanalyzer.model.yahoofinance;

import no.bouvet.stockanalyzer.utils.HtmlHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(IncomeStatement.class)
public class IncomeStatementTest {

//    @Mock
    private IncomeStatement incomeStatement;

//    @Mock
    private HtmlHelper htmlHelper;
    
    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);

//        Whitebox.setInternalState(incomeStatement, "htmlHelper", htmlHelper);
       
//        incomeStatement = spy(new IncomeStatement(anyString(), anyString()));
//        incomeStatement = mock(IncomeStatement.class);
//        htmlHelper = mock(HtmlHelper.class);

    }

    @Test
    public void testGetNetIncome() throws Exception {
//        BigDecimal netIncome = new BigDecimal(123456);
//        String netIncomeString = "123,456";
//        Mockito.when(incomeStatement.getNetIncome()).thenReturn(netIncome);
//        PowerMockito.whenNew(HtmlHelper.class).withAnyArguments().thenReturn(htmlHelper);
//        PowerMockito.whenNew(IncomeStatement.class).withAnyArguments().thenReturn(incomeStatement);
//        incomeStatement.fillAllFields();
//        Mockito.when(htmlHelper.getValueByCssQuery(IncomeStatementCssQuery.incomeStatement_netIncomeLatest.getKey())).thenReturn(netIncomeString);
//        Mockito.when(incomeStatement.getValueByCssQuery(IncomeStatementCssQuery.incomeStatement_netIncomeLatest.getKey())).thenReturn(netIncomeString);

        
//        assertEquals(netIncome, incomeStatement.getNetIncome());
    }
}
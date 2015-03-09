package no.bouvet.stockanalyzer.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yahoo.finance.types.incomestatement.Incomestatement;
import no.bouvet.stockanalyzer.model.EconomicsInfo;
import no.bouvet.stockanalyzer.model.Stock;
import org.apache.commons.lang.NotImplementedException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bouvet.ness
 * Date: 06/03/15
 * Time: 10:18
 */

@Service("StockService")
public class StockService {

    @Value("${yahoo.finance.incomestatemen}")
    private String incomestatementUrl;

    @Value("${yahoo.finance.balancesheet}")
    private String balancesheetUrl;

    @Value("${yahoo.finance.quotes}")
    private String quotesUrl;
    
    private CloseableHttpClient httpClient;
    
    private static final Logger log = LoggerFactory.getLogger(StockService.class);

    public List<Stock> lookupStock(String ticker) throws IOException {
        
        log.debug("ticker=", ticker);
        log.debug("yahoo.finance.quotes=", quotesUrl);
        
        List<Stock> stocks = new ArrayList<Stock>();
        
        stocks.add(getStock("bouvet"));
        stocks.add(getStock("aapl"));

        
        
        return stocks;
        
        
    }
    
    public EconomicsInfo getEconomicsInfo(String tickers) throws IOException {

        String quotes = getJsonFromUrl(quotesUrl);
        String incomeStatementJson = getJsonFromUrl(incomestatementUrl);
        String balancesheetJson = getJsonFromUrl(balancesheetUrl);
        
        Incomestatement incomestatement = getObjectFromJson(incomeStatementJson, new Incomestatement());
        
//        EconomicsInfo economics = new EconomicsInfo();
        
        throw new NotImplementedException();
    }
    
    private <T> T getObjectFromJson(String json, T obj) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);
        parser.nextToken();
        
        // and then each time, advance to opening START_OBJECT
        while (parser.nextToken() == JsonToken.START_OBJECT) {
            T t = (T)mapper.readValue(parser, obj.getClass());  // Incomestatement.class
                    
            return t;
        }
        throw new NotImplementedException("This went straight for the woods.");
    }
    
    private Stock getStock(String ticker) {
        Stock stock = new Stock();
        stock.setTicker(ticker);
        return stock;
        
    }
    
    
    private String getJsonFromUrl(String url) throws IOException {
        if(httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
        
        String json = null;
        
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        
        try {
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new IOException("Yahoo API tilkoplingen feilet. " + response.getStatusLine().getStatusCode());
            }

            HttpEntity entity = response.getEntity();
            JsonFactory factory = new JsonFactory();
//            JsonParser parser = factory.createParser(entity.getContent());
//            Object json = parser.getInputSource();
//            Object json = parser.getEmbeddedObject();
            if(entity != null) {
                json = EntityUtils.toString(entity);
//                log.debug("Hva sa Yahoo? {}", json);
//                String prettyJson = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(json);
//                log.debug("Hva sa Yahoo? (pretty) {}", prettyJson);
            }
            EntityUtils.consume(entity);

            return json;
            
        } finally {
            if(response != null) {
                response.close();
            }
        }
        
    }

}

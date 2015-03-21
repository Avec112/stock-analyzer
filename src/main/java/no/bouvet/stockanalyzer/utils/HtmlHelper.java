package no.bouvet.stockanalyzer.utils;

import no.bouvet.stockanalyzer.exception.StockAnalyzerException;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 18/03/15
 * Time: 09:32
 */
public class HtmlHelper {

    
    private Logger LOG = LoggerFactory.getLogger(HtmlHelper.class);
    private Document document;
    
    public HtmlHelper(String url, int timeout) {
        LOG.info("Connecting to url({}ms) '{}'",timeout, url);
        try {
//            int tout = Integer.parseInt((timeout == null)?"3000":timeout);
//            LOG.debug("htmlHelper timeout = {}", timeout);
            this.document = Jsoup.connect(url).timeout(timeout).get();
        } catch (IOException e) {
            LOG.error("Could not create document from url '{}'", url, e);
        }
    }

      
    public String getValueByCssQuery(String cssQuery) throws StockAnalyzerException {
        Elements elements = document.select(cssQuery);
        String text = elements.text().trim().replace("\u00a0", "");
//        LOG.debug("'{}' -> '{}'", cssQuery, text);
        if(StringUtils.isBlank(text)) {
            throw new StockAnalyzerException("No value found for query '"+ cssQuery +"'");
        }
        return text;
    }
       
}

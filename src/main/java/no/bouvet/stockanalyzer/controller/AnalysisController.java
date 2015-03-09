package no.bouvet.stockanalyzer.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import no.bouvet.stockanalyzer.service.StockService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 06/03/15
 * Time: 09:59
 */
@Controller
//@RequestMapping("/stockanalyzer")
public class AnalysisController {

    private static final Logger log = LoggerFactory.getLogger(AnalysisController.class);
    
    @Autowired private StockService stockService;


    @RequestMapping(method = RequestMethod.GET, value = "/analyze/{ticker}")
    @ApiOperation(value="/analyze/:ticker?(format=json|xml)", notes = "Whatever")
    public void analyzeTicker(
//            @RequestParam(value = "amount") Integer amount,
            @PathVariable(value = "ticker") String ticker,
            @RequestParam(value = "format", required = false) String format,
//            @PathVariable(value = "format") String format,
            HttpServletResponse response) {

        log.info("/analyze/{} is accessed.", ticker);
        log.debug("format=" + format);

        try {

            if(StringUtils.isNotBlank(ticker)) {
                StopWatch watch = new StopWatch();
                watch.start();
                Result.asFormat(stockService.lookupStock(ticker), response, format);
                watch.stop();
                log.debug("Creating {} {} objects took {}", ticker, (format == null)?"json":format, watch.toString());
            } else {
                Result.asFormat("Sorry ticker '{}' is not found.", response, format);
            }
        } catch (IOException e) {
            log.error("Oh, snap!", e);
            // todo returner noe til klienten?
        }

    }


    @RequestMapping(method = RequestMethod.GET, value = "/status"/*, headers = "Content-type=application/json,text/xml"*/)
    @ApiOperation(value="/status", notes = "Whats up?")
    public void getStatus(
            @RequestParam(value = "format", required = false) String format,
            HttpServletResponse response) {

        log.info("/status is called!");
        try {
            Result.asFormat("The REST service is up!", response, format);
        } catch (IOException e) {
            log.error("Oh, snap!", e);
            // todo returner noe til klienten?
        }

    }
}

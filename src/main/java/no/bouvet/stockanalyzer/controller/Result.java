package no.bouvet.stockanalyzer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 06/03/15
 * Time: 11:11
 */
public class Result {
    private static ObjectMapper mapper = new ObjectMapper();
    
    public static void asJSON(Object obj, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(response.getOutputStream(), obj);        
    }
    
    
    public static void asFormat(Object obj,HttpServletResponse response, String format) throws IOException {
        
        if(StringUtils.isBlank(format) || format.equalsIgnoreCase("json")) {
            asJSON(obj, response);
            
        } else if(format.equalsIgnoreCase("xml")) {

            throw new NotImplementedException();
            
            /*try {
                MyCharacterResponse myCharacterResponse = new MyCharacterResponse((List<MyCharacter>)obj);
                JDOMResult result = new JDOMResult();

                JAXBContext jaxbContext = JAXBContext.newInstance(MyCharacterResponse.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                // output pretty printed
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                
                jaxbMarshaller.marshal(myCharacterResponse, result);

                asXml(result.getDocument(), response);
                
            } catch(JAXBException e) {
                e.printStackTrace();
            } */
        } else {  // error
            asJSON("parameter format=" + format + " is not supported.", response);     
        }
    }

    public static void asXml(Document document, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        new XMLOutputter(Format.getPrettyFormat()).output(document, response.getOutputStream());
        
    }
}

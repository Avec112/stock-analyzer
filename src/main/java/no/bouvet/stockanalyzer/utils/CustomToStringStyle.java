package no.bouvet.stockanalyzer.utils;

import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 21/03/15
 * Time: 03:17
 */
public class CustomToStringStyle extends ToStringStyle {
    
    
    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        if(value instanceof Number) {
            value = NumberHelper.format((Number)value);
        }
        buffer.append(value);
    }

    @Override
    protected void appendFieldStart(StringBuffer buffer, String fieldName) {
        buffer.append("\n").append(fieldName).append('=');
    }
    
    

}

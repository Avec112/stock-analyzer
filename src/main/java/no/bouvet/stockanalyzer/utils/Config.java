package no.bouvet.stockanalyzer.utils;

import no.bouvet.stockanalyzer.model.yahoofinance.Property;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ronny.ness
 * Date: 20/03/15
 * Time: 20:40
 */
public class Config {

    private final static Logger logger = LoggerFactory.getLogger(Config.class);
    private final static String PROPERTIES_FILE = "cssQuery.properties";
    private static Configuration config = null;

    static {
        initialize();
    }

    private Config() {
    }

    private static void initialize() {
        if (config == null) {
            try {
                config = new PropertiesConfiguration(PROPERTIES_FILE);
                logger.info("****************************************************************");
                logger.info("Properties found:");
                Iterator<String> iterator = config.getKeys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String[] values = config.getStringArray(key);
                    if (values.length > 1) {
                        String value = ArrayUtils.toString(values);
                        value = StringUtils.removeStart(value, "{");
                        value = StringUtils.removeEnd(value, "}");
                        logger.info("{} = {}", key, value);
                    } else {
                        logger.info("{} = {}", key, config.getString(key));
                    }
                }
                logger.info("****************************************************************");
            } catch (ConfigurationException e) {
                logger.error("Cannot find configuration file '{}'", PROPERTIES_FILE, e);
            }
        }
    }

    /**
     * Get a property (key=value).
     *
     * @param property the key in the properties file.
     * @return the value of the property (Strings only)
     */
    public static String getProperty(Property property) {

        if (!config.containsKey(property.getKey())) {
            logger.error("Property '{}' not found in file '{}'", property, PROPERTIES_FILE);
            throw new IllegalArgumentException("Property '" + property + "' is not found");
        }

        String value = config.getString(property.getKey());
        if (StringUtils.isBlank(value)) {
            logger.error("Please configure av value for property '{}'", property);
            throw new IllegalArgumentException("Property '" + property + "' is not found");
        }

        return value;
    }


    /**
     * Get property with comma separated values
     *
     * @param property the property containing csv values
     * @return list of values
     */
    @SuppressWarnings("SameParameterValue")
    public static List<String> getCsvProperty(final Property property) {
        // validate
        getProperty(property);

        List<Object> tmpList = config.getList(property.getKey());
        List<String> list = new ArrayList<String>();
        for (Object object : tmpList) {
            list.add(object != null ? object.toString() : null);
        }

        if (list.size() == 0) {
            logger.error("Property {} has no values.", property);
            System.exit(0);
        }

        return list;
    }
}

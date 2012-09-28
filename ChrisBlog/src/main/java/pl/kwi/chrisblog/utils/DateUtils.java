package pl.kwi.chrisblog.utils;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Class with utils for Date.
 * 
 * @author Krzysztof Wisniewski
 */
public class DateUtils {
	
	
	private static final Logger LOG = Logger.getLogger(DateUtils.class);

	
	/**
	 * Method converts object String in format "YYYYMMDDHHMMSS" to object Calendar.
	 * 
	 * @param str object String in format "YYYYMMDDHHMMSS"
	 * @return object Calendar basing on str
	 */
	public static Calendar convertStringToCalendarYYYYMMDDHHMMSS(String str) {

		Calendar cal = null;
		Date dateTimeParsed = null;

		try {
			dateTimeParsed = new SimpleDateFormat("yyyyMMddHHmmss").parse(str);
			cal = Calendar.getInstance();
			cal.setTime(dateTimeParsed);
		} catch (ParseException ex) {
			LOG.error(MessageFormat.format("Can not convert String to Calendar for String: {0}", str), ex);
		}

		return cal;

	}
	
	
    /**
     * Method converts object Calendar to object String in format "yyyy-MM-dd HH:mm:ss".
     * 
     * @param cal object Calendar which is converted do object String
     * @return object String in format "yyyy-MM-dd HH:mm:ss"
     */
    public static String convertCalendarToStringWithDashesAndColons(Calendar cal) {

        String result = null;

        try {
        	Date date = cal.getTime();
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (Exception ex) {
        	LOG.error("Can not convert Calendar to String", ex);
        }

        return result;

    }
    
    /**
     * Method converts object Calendar to object String with month as text. 
     * For instance: '26 December, 2011'. 
     * 
     * @param cal object Calendar which is converted do object String
     * @param locale object Locale for month as text
     * @return object String with month as text
     */
    public static String convertCalendarToStringWithMonthAsText(Calendar cal, Locale locale) {

        String result = null;
        
        if(cal == null){
        	LOG.error("Can not convert Calendar to String. Calendar is null.");
        	return result;
        }
        
        if(locale == null){
        	LOG.error("Can not convert Calendar to String. Locale is null.");
        	return result;
        }

        try {
        	Date date = cal.getTime();
            result = new SimpleDateFormat("MMMM dd, yyyy", locale).format(date);
        } catch (Exception ex) {
        	LOG.error("Can not convert Calendar to String", ex);
        }
        
        if(result != null){
        	result = StringUtils.capitalize(result);
        }

        return result;

    }

}

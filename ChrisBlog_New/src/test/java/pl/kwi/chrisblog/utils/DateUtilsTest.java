package pl.kwi.chrisblog.utils;

import java.util.Calendar;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.Test;

public class DateUtilsTest {
	
	@Test
	public void convertStringToCalendarYYYYMMDDHHMMSS(){
		
		String str = "19991225174553";
		
		Calendar cal = DateUtils.convertStringToCalendarYYYYMMDDHHMMSS(str);
		
		Assert.assertEquals(Integer.valueOf(1999), Integer.valueOf(cal.get(Calendar.YEAR)));
		Assert.assertEquals(Integer.valueOf(11), Integer.valueOf(cal.get(Calendar.MONTH)));
		Assert.assertEquals(Integer.valueOf(25), Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		Assert.assertEquals(Integer.valueOf(17), Integer.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
		Assert.assertEquals(Integer.valueOf(45), Integer.valueOf(cal.get(Calendar.MINUTE)));
		Assert.assertEquals(Integer.valueOf(53), Integer.valueOf(cal.get(Calendar.SECOND)));
		
	}
	
	@Test
	public void convertStringToCalendarYYYYMMDDHHMMSS_wrongSecond(){
		
		String str = "text";
		
		Calendar cal = DateUtils.convertStringToCalendarYYYYMMDDHHMMSS(str);
		
		Assert.assertNull(cal);
		
	}
	
	@Test
	public void convertCalendarToStringWithDashesAndColons(){
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1999);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 25);
		cal.set(Calendar.HOUR_OF_DAY, 17);
		cal.set(Calendar.MINUTE, 45);
		cal.set(Calendar.SECOND, 53);
		
		String str = DateUtils.convertCalendarToStringWithDashesAndColons(cal);
		
		Assert.assertEquals("1999-12-25 17:45:53", str);
		
	}
	
	@Test
	public void convertCalendarToStringWithDashesAndColons_calendarNull(){
		
		Calendar cal = null;
		
		String str = DateUtils.convertCalendarToStringWithDashesAndColons(cal);
		
		Assert.assertNull(str);
		
	}
	
	@Test
	public void convertCalendarToStringWithMonthAsText(){
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2011);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 26);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		
		Locale locale = Locale.ENGLISH;
		
		String str = DateUtils.convertCalendarToStringWithMonthAsText(cal, locale);
		
		Assert.assertEquals("December 26, 2011", str);
		
	}
	
	@Test
	public void convertCalendarToStringWithMonthAsText_polishLocale(){
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2011);
		cal.set(Calendar.MONTH, 01);
		cal.set(Calendar.DAY_OF_MONTH, 26);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		
		Locale locale = new Locale("pl", "PL");
		
		String str = DateUtils.convertCalendarToStringWithMonthAsText(cal, locale);
		
		Assert.assertEquals("Luty 26, 2011", str);
		
	}
	
	@Test
	public void convertCalendarToStringWithMonthAsText_calendarNull(){
		
		Calendar cal = null;
		Locale locale = Locale.ENGLISH;
		
		String str = DateUtils.convertCalendarToStringWithMonthAsText(cal, locale);
		
		Assert.assertNull(str);
		
	}
	
	@Test
	public void convertCalendarToStringWithMonthAsText_localeNull(){
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2011);
		cal.set(Calendar.MONTH, 01);
		cal.set(Calendar.DAY_OF_MONTH, 26);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		
		Locale locale = null;
		
		String str = DateUtils.convertCalendarToStringWithMonthAsText(cal, locale);
		
		Assert.assertNull(str);
		
	}

}

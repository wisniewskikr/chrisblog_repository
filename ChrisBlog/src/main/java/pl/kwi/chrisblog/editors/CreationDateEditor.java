package pl.kwi.chrisblog.editors;

import java.beans.PropertyEditorSupport;
import java.util.Calendar;
import java.util.Locale;

import pl.kwi.chrisblog.utils.DateUtils;

/**
 * Class edites object Calendar of creation date from form.
 * 
 * @author Krzysztof Wisniewski
 */
public class CreationDateEditor extends PropertyEditorSupport{
	
	
	private Locale loc;
			
	public CreationDateEditor(Locale loc) {
		super();
		this.loc = loc;
	}
	

	@Override
	public String getAsText() {
		
		Calendar cal = (Calendar)getValue();
		
		if(cal == null){
			return null;
		}
		
		return DateUtils.convertCalendarToStringWithMonthAsText(cal, loc);
		
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		Calendar cal = DateUtils.convertStringWithMonthAsTextToCalendar(text, loc);
		setValue(cal);
	}

}

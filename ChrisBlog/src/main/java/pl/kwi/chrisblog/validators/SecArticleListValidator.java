package pl.kwi.chrisblog.validators;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import pl.kwi.chrisblog.commands.BlogCommand;
import pl.kwi.chrisblog.exceptions.SecArticleException;

/**
 * Class validates article list in secured area.
 * 
 * @author Krzysztof Wisniewski
 */
@Service
public class SecArticleListValidator {
		
	/**
	 * Method validates article list in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param bindingResult object BindingResult with result from page
	 * @param pageName object String with page name
	 * @return true if validation is ok. Otherwise false.
	 * @throws Exception
	 */
	public boolean validate(BlogCommand command, BindingResult bindingResult, String pageName) throws Exception{
		
		boolean isValid = true;
		
		if("view-article".equals(pageName)){
			isValid = validateViewArticleOnList(command, bindingResult);
		}else if("edit-article".equals(pageName)){
			isValid = validateEditArticleOnList(command, bindingResult);
		}else if("delete-article".equals(pageName)){
			isValid = validateDeleteArticleOnList(command, bindingResult);
		}else{
			throw new SecArticleException(MessageFormat.format("Action with name {0} is not sopported", pageName));
		}
		
		return isValid;
		
	}
		
	/**
	 * Method validates page with view of article.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param bindingResult object BindingResult with result from page
	 * @return true if validation is ok. Otherwise false.
	 */
	protected boolean validateViewArticleOnList(BlogCommand command, BindingResult bindingResult){
		
		boolean isValid = true;
		
		String selectedRows = command.getSelectedRows();
		
		if(StringUtils.isBlank(selectedRows)){
			bindingResult.reject("error.table.nothing.selected");
			return false;
		}
		
		String[] selectedRowsTab = selectedRows.split(",");		
		if(selectedRowsTab.length > 1){
			bindingResult.reject("error.table.to.many.selected");
			return false;
		}
		
		return isValid;
		
	}
	
	/**
	 * Method validates page with edit of article.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param bindingResult object BindingResult with result from page
	 * @return true if validation is ok. Otherwise false.
	 */
	protected boolean validateEditArticleOnList(BlogCommand command, BindingResult bindingResult){
		
		boolean isValid = true;
		
		String selectedRows = command.getSelectedRows();
		
		if(StringUtils.isBlank(selectedRows)){
			bindingResult.reject("error.table.nothing.selected");
			return false;
		}
		
		String[] selectedRowsTab = selectedRows.split(",");		
		if(selectedRowsTab.length > 1){
			bindingResult.reject("error.table.to.many.selected");
			return false;
		}
		
		return isValid;
		
	}
	
	/**
	 * Method validates page with delete of article.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param bindingResult object BindingResult with result from page
	 * @return true if validation is ok. Otherwise false.
	 */
	protected boolean validateDeleteArticleOnList(BlogCommand command, BindingResult bindingResult){
		
		boolean isValid = true;
		
		String selectedRows = command.getSelectedRows();
		
		if(StringUtils.isBlank(selectedRows)){
			bindingResult.reject("error.table.nothing.selected");
			isValid = false;
		}
		
		return isValid;
		
	}
	

}

package pl.kwi.chrisblog.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import pl.kwi.chrisblog.commands.BlogCommand;

/**
 * Class of controller for secured blog.
 * 
 * @author Krzysztof Wisniewski
 */
@Controller
@RequestMapping(value="/secured")
public class SecuredBlogController{
	
	
	private static final Logger LOG = Logger.getLogger(SecuredBlogController.class);
	
	@Value("${path.host}")
	private String pathHost;
	
	@Value("${path.context}")
	private String pathContext;
	
	@Autowired
	private LocaleResolver localeResolver;

	
	/**
	 * Method displays list of articles in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/article-list")
	protected ModelAndView displaySecArticleList(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		command.setDisplaySecArticleList(true);
		
		command.setPathHost(pathHost);
		command.setPathContext(pathContext);
		command.setWindowTitle("Title");
		
		if(request != null){
			Locale loc = localeResolver.resolveLocale(request);
			command.setLocale(loc);
		}
		
		return new ModelAndView("blogJsp");
		
	}
		
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //
	
	
	public void setPathHost(String pathHost) {
		this.pathHost = pathHost;
	}

	public void setPathContext(String pathContext) {
		this.pathContext = pathContext;
	}

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
	
	
}

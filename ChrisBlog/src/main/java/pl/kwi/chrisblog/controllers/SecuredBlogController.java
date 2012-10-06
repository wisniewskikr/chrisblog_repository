package pl.kwi.chrisblog.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.kwi.chrisblog.commands.BlogCommand;

/**
 * Class of controller for secured blog.
 * 
 * @author Krzysztof Wisniewski
 */
@Controller
@RequestMapping(value="/secured")
public class SecuredBlogController extends AbstractController{
	
	
	private static final Logger LOG = Logger.getLogger(SecuredBlogController.class);

	
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
		
		handleCommand(command, request);
		
		return new ModelAndView("blogJsp");
		
	}
	
	
}

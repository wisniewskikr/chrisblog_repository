package pl.kwi.chrisblog.controllers;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.chrisblog.commands.BlogCommand;
import pl.kwi.chrisblog.services.ExplanationService;

/**
 * Class of controller for blog.
 * 
 * @author Krzysztof Wisniewski
 */
@Controller
public class BlogController extends AbstractController{
	
	
	private static final Logger LOG = Logger.getLogger(BlogController.class);
	
	@Autowired
	private ExplanationService explanationService;

	
	/**
	 * Method handles page with article list for page one. By page one 
	 * should be no page number in url. This is also default method. 
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/")
	public ModelAndView displayArticleListPageOne(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		return displayArticleList(command, request, response, 1);
		
	}
	
	/**
	 * Method handles page with article list for pages other then one.
	 * By pages other then one should be page number in url.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageNumber int with current page number
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping("/page/{pageNumber}")
	public ModelAndView displayArticleListPageNotOne(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable int pageNumber) throws Exception{
		
		if(pageNumber == 1){
			return new ModelAndView(new RedirectView("/" , true, true, true));
		}else{
			return displayArticleList(command, request, response, pageNumber);
		}
		
	}
	
	/**
	 * Method handles page with article list.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageNumber int with current page number
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	protected ModelAndView displayArticleList(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable int pageNumber) throws Exception{
		
		command.setDisplayArticleList(true);		
		handleCommand(command, request);
		
		command.setArticleList(articleService.findAllActiveSortedByDateDesc(pageNumber, command.getLocale()));
		
		handleArticleListPagenation(command, pageNumber);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles page with single article.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping("/article/{uniqueName}")
	protected ModelAndView displayArticle(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable String uniqueName) throws Exception{
		
		command.setDisplayArticle(true);
		handleCommand(command, request);
		
		command.setArticle(articleService.getArticleByUniqueName(uniqueName, command.getLocale()));
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles page with article list marked by tag for page one. By page one 
	 * should be no page number in url. 
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param tagUniqueName object String with unique name of tag
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/tag/{tagUniqueName}")
	public ModelAndView displayArticleListWithTagPageOne(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String tagUniqueName) throws Exception{
		
		return displayArticleListWithTag(command, request, response, 1, tagUniqueName);
		
	}
	
	/**
	 * Method handles page with article list marked by tag for pages other then one.
	 * By pages other then one should be page number in url.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageNumber int with current page number
	 * @param tagUniqueName object String with unique name of tag
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping("/tag/page/{pageNumber}/{tagUniqueName}")
	public ModelAndView displayArticleListWithTagPageNotOne(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable int pageNumber,
			@PathVariable String tagUniqueName) throws Exception{
		
		if(pageNumber == 1){
			return new ModelAndView(new RedirectView("/tag/" + tagUniqueName , true, true, true));
		}else{
			return displayArticleListWithTag(command, request, response, pageNumber, tagUniqueName);
		}
		
	}
	
	/**
	 * Method handles page with article list marked by tag.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageNumber int with current page number
	 * @param tagUniqueName object String with unique name of tag
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	protected ModelAndView displayArticleListWithTag(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable int pageNumber,
			@PathVariable String tagUniqueName) throws Exception{
		
		command.setDisplayArticleListWithTag(true);		
		handleCommand(command, request);
		
		command.setArticleTag((articleTagService.getArticleTagByUniqueName(tagUniqueName)));
		command.setArticleList(articleService.findAllWithTagsSortedByDateDesc(pageNumber, command.getArticleTag(), command.getLocale()));
		
		handleArticleListWithTagPagenation(command, pageNumber);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles explanations - theoretical introduction
	 * from articles.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of explanation
	 * @return object ModelAndView with model and view of page
	 */
	@RequestMapping("/explanation/{uniqueName}")
	public ModelAndView displayExplanation(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String uniqueName) throws Exception{
				
		command.setDisplayExplanation(true);
		handleCommand(command, request);
		
		command.setExplanation(explanationService.getExplanationByUniqueName(uniqueName));
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method displays section 'About me'.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 */
	@RequestMapping("/about_me")
	public ModelAndView displayAboutMe(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
				
		command.setDisplayAboutMe(true);
		handleCommand(command, request);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method displays exceptions.
	 * 
	 * @param e object Exception with exception
	 * @return object ModelAndView with model and view of page
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView displayException(Exception e){
		
		LOG.error("Error occured during processing", e);
		
		BlogCommand command = new BlogCommand();
		
		command.setDisplayException(true);
		try {
			handleCommand(command, null);			
		} catch (Exception e2) {
			LOG.error("Error occured during processing", e2);
		}
		
		ModelMap model = new ModelMap();
		model.addAttribute("command", command);
				
		return new ModelAndView("blogJsp", model);
		
	}
	
	/**
	 * Method displays errors by code.
	 * 
	 * @param e object Exception with exception
	 * @param errorCode object String with code of error
	 * @return object ModelAndView with model and view of page
	 */
	@RequestMapping("/errors/{errorCode}")
	public ModelAndView displayError(Exception e, 
			@PathVariable String errorCode){
				
		LOG.error(MessageFormat.format("Error occured during processing. Error code: {0}.", errorCode));
		
		BlogCommand command = new BlogCommand();
		
		command.setDisplayException(true);
		try {
			handleCommand(command, null);			
		} catch (Exception e2) {
			LOG.error("Error occured during processing", e2);
		}
		
		ModelMap model = new ModelMap();
		model.addAttribute("command", command);
		
		return new ModelAndView("blogJsp", model);
				
	}
	
	/**
	 * Method handles failed login.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping("/login-failed")
	public ModelAndView loginFailed(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		command.getErrorMsgs().add(messageSource.getMessage("error.credentials", null, localeResolver.resolveLocale(request)));
		
		return displayArticleList(command, request, response, 1);
		
	}
	
	/**
	 * Method handles situation when user want to get access to secured resources
	 * but he is not authenticated.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping("/secured-resource")
	public ModelAndView securedResource(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		command.getInfoMsgs().add(messageSource.getMessage("info.resource.secured", null, localeResolver.resolveLocale(request)));
				
		return displayArticleList(command, request, response, 1);
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //

	
	/**
	 * Method handles pagenation for all articles.
	 * 
	 * @param command object BlogCommand with page data
	 * @param pageNumber int with number of current page
	 * @throws Exception
	 */
	protected void handleArticleListPagenation(BlogCommand command, int pageNumber) throws Exception {
		
		Integer pageCurrent = pageNumber;
		Integer pagesCount = articleService.getPagesCountOfAllArticles();
		
		handlePagenation(command, pageCurrent, pagesCount);
		
	}
	
	/**
	 * Method handles pagenation for articles with specified tag.
	 * 
	 * @param command object BlogCommand with page data
	 * @param pageNumber int with number of current page
	 * @throws Exception
	 */
	protected void handleArticleListWithTagPagenation(BlogCommand command, int pageNumber) throws Exception {
		
		Integer pageCurrent = pageNumber;
		Integer pagesCount = articleService.getPagesCountArticlesWithTag(command.getArticleTag());
		
		handlePagenation(command, pageCurrent, pagesCount);
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //
	
	
	public void setExplanationService(ExplanationService explanationService) {
		this.explanationService = explanationService;
	}	
		

}

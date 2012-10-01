package pl.kwi.chrisblog.controllers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.chrisblog.commands.BlogCommand;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.exceptions.ArticleException;
import pl.kwi.chrisblog.exceptions.PagenationException;
import pl.kwi.chrisblog.services.ArticleService;
import pl.kwi.chrisblog.services.ArticleTagService;
import pl.kwi.chrisblog.services.ExplanationService;

/**
 * Class of controller for blog.
 * 
 * @author Krzysztof Wisniewski
 */
@Controller
public class BlogController{
	
	
	private static final Logger LOG = Logger.getLogger(BlogController.class);
	
	@Value("${path.host}")
	private String pathHost;
	
	@Value("${path.context}")
	private String pathContext;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ExplanationService explanationService;
	
	@Autowired
	private ArticleTagService articleTagService;
	
	@Autowired
	private LocaleResolver localeResolver;

	
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
		
		command.setArticleList(articleService.getArticleListSortedByDateDesc(pageNumber, command.getLocale()));
		
		handleArticleListPagenation(command, pageNumber);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles page with single article. By page one 
	 * should be no page number in url.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageNumber int with current page number
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping("/article/{uniqueName}")
	public ModelAndView displayArticlePageOne(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable String uniqueName) throws Exception{
		
		return displayArticle(command, request, response, 1, uniqueName);
		
	}
	
	/**
	 * Method handles page with single article. 
	 * By pages other then one should be page number in url.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageNumber int with current page number
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping("/article/page/{pageNumber}/{uniqueName}")
	public ModelAndView displayArticlePageNotOne(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable int pageNumber, 
			@PathVariable String uniqueName) throws Exception{
		
		if(pageNumber == 1){
			return new ModelAndView(new RedirectView("/article/" + uniqueName , true, true, true));
		}else{
			return displayArticle(command, request, response, pageNumber, uniqueName);
		}
		
	}
	
	/**
	 * Method handles page with single article.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageNumber int with current page number
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	protected ModelAndView displayArticle(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable int pageNumber, 
			@PathVariable String uniqueName) throws Exception{
		
		command.setDisplayArticle(true);
		handleCommand(command, request);
		
		command.setArticle(articleService.getArticleByUniqueName(uniqueName, command.getLocale()));
				
		handleArticlePagenation(command, pageNumber);
		
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
		command.setArticleList(articleService.getArticleListWithTagSortedByDateDescWithExp(pageNumber, command.getArticleTag(), command.getLocale()));
		
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
		
		command.getErrorMsgs().add("Incorrect username or password.");
		
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
		
		command.getInfoMsgs().add("These resource are secured. You have to authenticate first.");
		
		return displayArticleList(command, request, response, 1);
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //

	
	/**
	 * Method handles object BlogCommand. All common fields are automatically set.
	 * 
	 * @param command object BlogCommand where all common fields are automatically set
	 * @param request object HttpServletRequest with request from page 
	 * @throws Exception
	 */
	public void handleCommand(BlogCommand command, HttpServletRequest request) throws Exception {
		
		command.setPathHost(pathHost);
		command.setPathContext(pathContext);
		command.setWindowTitle(getWindowTitle(command));
		
		if(request != null){
			Locale loc = localeResolver.resolveLocale(request);
			command.setLocale(loc);
			command.setTagsCloudFooter(articleTagService.getTagsCloudFooter(articleService.getAllArticleList(loc)));			
			command.setTagsCloudRightSpace(articleTagService.getTagsCloudRightSpace(articleService.getAllArticleList(loc)));			
		}		
		
	}
	
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
	 * Method handles pagenation for article.
	 * 
	 * @param command object BlogCommand with page data
	 * @param pageNumber int with number of current page
	 * @throws Exception
	 */
	protected void handleArticlePagenation(BlogCommand command, int pageNumber) throws Exception {
		
		Integer pageCurrent = pageNumber;
		Integer pagesCount = command.getArticle().getPagesCount();
		
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
	
	/**
	 * Method handles common pagenation.
	 * 
	 * @param command object BlogCommand with page data
	 * @param pageCurrent object Integer with number of current page
	 * @param pagesCount object Integer with count of all pages
	 * @throws Exception
	 */
	protected void handlePagenation(BlogCommand command, Integer pageCurrent, Integer pagesCount) throws Exception {
		
		if(pageCurrent == null || pageCurrent == 0){
			throw new PagenationException("Current page is null or 0");
		}
		
		if(pagesCount == null || pagesCount == 0){
			throw new PagenationException("Pages count is null or 0");
		}
		
		if(pageCurrent < 0){
			throw new PagenationException("Current page is less then 0");
		}
		
		if(pageCurrent > pagesCount){
			throw new PagenationException("Current page in URL is higher than pages count");
		}
		
		command.setPageCurrent(pageCurrent);
		command.setPagesCount(pagesCount);
		
	}
	
	/**
	 * Method gets title of browser window.
	 * 
	 * @param command object BlogCommand with page data
	 * @return object String with title of browser window
	 * @throws Exception
	 */
	protected String getWindowTitle(BlogCommand command) throws Exception{
		
		if(command == null){
			throw new Exception("Can not get title of browser window. Object BlogCommand is null.");
		}
		
		String title = null;
		
		if(command.isDisplayArticleList()) {
			title = "List of Articles";
		}else if(command.isDisplayArticle()){
			title = "Article";			
		}else if(command.isDisplayArticleListWithTag()){
			title = "List of Articles marked by Tag";			
		}else if(command.isDisplayExplanation()){
			title = "Explanation";			
		}else if(command.isDisplayAboutMe()){
			title = "About Me";			
		}else if(command.isDisplayException()){
			title = "Exception";			
		}else{
			throw new Exception("Can not get title of browser window. Can not find display mode.");
		}
		
		return title;
		
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
	
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}	

	public void setExplanationService(ExplanationService explanationService) {
		this.explanationService = explanationService;
	}	

	public void setArticleTagService(ArticleTagService articleTagService) {
		this.articleTagService = articleTagService;
	}

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}	

}

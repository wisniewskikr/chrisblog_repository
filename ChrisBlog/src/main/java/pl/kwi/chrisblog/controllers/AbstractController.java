package pl.kwi.chrisblog.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

import pl.kwi.chrisblog.commands.BlogCommand;
import pl.kwi.chrisblog.exceptions.PagenationException;
import pl.kwi.chrisblog.services.ArticleService;
import pl.kwi.chrisblog.services.ArticleTagService;

/**
 * Abstract class for all controllers.
 * 
 * @author Krzysztof Wisniewski
 */
public abstract class AbstractController {
	
	
	@Value("${path.host}")
	private String pathHost;
	
	@Value("${path.context}")
	private String pathContext;
	
	@Autowired
	protected LocaleResolver localeResolver;
	
	@Autowired
    protected MessageSource messageSource;
	
	@Autowired
	protected ArticleService articleService;
	
	@Autowired
	protected ArticleTagService articleTagService;
	
	
	
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
		}else if(command.isDisplaySecArticleList()){
			title = "Secured List of Articles";			
		}else if(command.isDisplaySecViewArticle()){
			title = "Secured View of Article";			
		}else if(command.isDisplaySecEditArticle()){
			title = "Secured Edit of Article";			
		}else if(command.isDisplaySecCreateArticle()){
			title = "Secured Create of Article";			
		}else if(command.isDisplaySecDeleteArticle()){
			title = "Secured Delete of Article";			
		}else if(command.isDisplaySecInfo()){
			title = "Secured Info";			
		}else if(command.isDisplaySecConfirmation()){
			title = "Secured Confirmation";			
		}else{
			throw new Exception("Can not get title of browser window. Can not find display mode.");
		}
		
		return title;
		
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

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}	

	public void setArticleTagService(ArticleTagService articleTagService) {
		this.articleTagService = articleTagService;
	}
	

}

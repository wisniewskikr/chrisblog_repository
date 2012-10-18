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
import pl.kwi.chrisblog.visitors.impl.AboutMePage;
import pl.kwi.chrisblog.visitors.impl.ArticleListPage;
import pl.kwi.chrisblog.visitors.impl.ArticleListWithTagPage;
import pl.kwi.chrisblog.visitors.impl.ArticlePage;
import pl.kwi.chrisblog.visitors.impl.ExceptionPage;
import pl.kwi.chrisblog.visitors.impl.ExplanationPage;
import pl.kwi.chrisblog.visitors.impl.PageTitleVisitor;
import pl.kwi.chrisblog.visitors.impl.SecArticleListPage;
import pl.kwi.chrisblog.visitors.impl.SecConfirmationPage;
import pl.kwi.chrisblog.visitors.impl.SecCreateArticlePage;
import pl.kwi.chrisblog.visitors.impl.SecDeleteArticlePage;
import pl.kwi.chrisblog.visitors.impl.SecEditArticlePage;
import pl.kwi.chrisblog.visitors.impl.SecViewArticlePage;

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
		PageTitleVisitor v = new PageTitleVisitor();
		
		if(command.isDisplayArticleList()) {
			v.visit(new ArticleListPage());
			title = v.getPageTitle();
		}else if(command.isDisplayArticle()){
			v.visit(new ArticlePage());
			title = v.getPageTitle();			
		}else if(command.isDisplayArticleListWithTag()){
			v.visit(new ArticleListWithTagPage());
			title = v.getPageTitle();			
		}else if(command.isDisplayExplanation()){
			v.visit(new ExplanationPage());
			title = v.getPageTitle();			
		}else if(command.isDisplayAboutMe()){
			v.visit(new AboutMePage());
			title = v.getPageTitle();		
		}else if(command.isDisplayException()){
			v.visit(new ExceptionPage());
			title = v.getPageTitle();			
		}else if(command.isDisplaySecArticleList()){
			v.visit(new SecArticleListPage());
			title = v.getPageTitle();		
		}else if(command.isDisplaySecViewArticle()){
			v.visit(new SecViewArticlePage());
			title = v.getPageTitle();		
		}else if(command.isDisplaySecEditArticle()){
			v.visit(new SecEditArticlePage());
			title = v.getPageTitle();			
		}else if(command.isDisplaySecCreateArticle()){
			v.visit(new SecCreateArticlePage());
			title = v.getPageTitle();			
		}else if(command.isDisplaySecDeleteArticle()){
			v.visit(new SecDeleteArticlePage());
			title = v.getPageTitle();			
		}else if(command.isDisplaySecConfirmation()){
			v.visit(new SecConfirmationPage());
			title = v.getPageTitle();		
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

package pl.kwi.chrisblog.controllers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mcavallo.opencloud.Cloud;
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
import pl.kwi.chrisblog.entities.CategoryEntity;
import pl.kwi.chrisblog.entities.ExplanationEntity;
import pl.kwi.chrisblog.exceptions.ArticleException;
import pl.kwi.chrisblog.exceptions.CategoryException;
import pl.kwi.chrisblog.services.intf.IArticleService;
import pl.kwi.chrisblog.services.intf.IArticleTagService;
import pl.kwi.chrisblog.services.intf.ICategoryService;
import pl.kwi.chrisblog.services.intf.IExplanationService;

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
	private ICategoryService categoryService;
	
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private IExplanationService explanationService;
	
	@Autowired
	private IArticleTagService articleTagService;
	
	@Autowired
	private LocaleResolver localeResolver;

	
//	@RequestMapping("/")
//	public ModelAndView displayArticleListPageOne(@ModelAttribute("command")BlogCommand command,
//			HttpServletRequest request, HttpServletResponse response) throws Exception{
//		
//		return displayArticleList(command, request, response, 1);
//		
//	}
//	
//	@RequestMapping("/page/{pageNumber}")
//	public ModelAndView displayArticleListPageNotOne(@ModelAttribute("command")BlogCommand command,
//			HttpServletRequest request, HttpServletResponse response,
//			@PathVariable int pageNumber) throws Exception{
//		
//		if(pageNumber == 1){
//			return new ModelAndView(new RedirectView("/" , true, true, true));
//		}else{
//			return displayArticleList(command, request, response, pageNumber);
//		}
//		
//	}
	
	@RequestMapping(value="/init")
	public ModelAndView init(@ModelAttribute("command")BlogCommand command, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
							
		return new ModelAndView(new RedirectView("/page/1" , true, true, true));
		
	}
	
	@RequestMapping("/page/{pageNumber}")
	public ModelAndView displayArticleList(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable int pageNumber) throws Exception{
		
		clearDisplays(command);
		command.setDisplayArticleList(true);		
		Locale loc = localeResolver.resolveLocale(request);
		
		command.setPathHost(pathHost);
		command.setPathContext(pathContext);
		command.setTagsCloud(articleTagService.getTagsCloud(articleService.getAllArticleList(loc)));
		
		// TODO KWi: implement tag entity
		ArticleTagEntity tag = null;
		
		command.setArticleList(articleService.getArticleListByPageTagAndLocal(pageNumber, tag, loc));
		
		handleArticleListPagenation(command, pageNumber);
		
		return new ModelAndView("blogJsp");
		
	}
	
	@RequestMapping("/article/page/{pageNumber}/{articleUniqueName}")
	public ModelAndView displayArticle(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable int pageNumber, 
			@PathVariable String articleUniqueName) throws Exception{
		
		clearDisplays(command);
		command.setDisplayArticle(true);
		Locale loc = localeResolver.resolveLocale(request);
		
		command.setPathHost(pathHost);
		command.setPathContext(pathContext);
		command.setTagsCloud(articleTagService.getTagsCloud(articleService.getAllArticleList(loc)));
		
		command.setArticle(articleService.getArticleByUniqueName(articleUniqueName, loc));
				
		handleArticlePagenation(command, pageNumber);
		
		return new ModelAndView("blogJsp");
		
	}
	

	
	/**
	 * Method handles explanations - theoretical introduction
	 * from articles.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param selectedExplanationUniqueName object String with unique name of explanation
	 * @return object ModelAndView with model and view of page
	 */
	@RequestMapping("/explanations/{selectedExplanationUniqueName}")
	public ModelAndView handleExplanationSelection(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String selectedExplanationUniqueName) throws Exception{
				
		clearDisplays(command);
		command.setDisplaySelectedExplanation(true);
		Locale loc = localeResolver.resolveLocale(request);
		
		fillCommand(command, loc);
		
		ExplanationEntity explanation = explanationService.getExplanationByUniqueName(selectedExplanationUniqueName);
		command.setSelectedExplanationId(explanation.getId());
		command.setSelectedExplanationUniqueName(explanation.getUniqueName());
		command.setSelectedExplanation(explanation);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles section 'About me'.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 */
	@RequestMapping("/about_me")
	public ModelAndView handleAboutMe(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
				
		clearDisplays(command);
		command.setDisplayAboutMe(true);
		Locale loc = localeResolver.resolveLocale(request);
		
		fillCommand(command, loc);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles exceptions.
	 * 
	 * @param e object Exception with exception
	 * @return object ModelAndView with model and view of page
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e){
		
		LOG.error("Error occured during processing", e);
		
		BlogCommand command = new BlogCommand();
		
		ModelMap model = new ModelMap();
		model.addAttribute("command", command);
		
		clearDisplays(command);
		command.setDisplayException(true);
		
		command.setPathHost(pathHost);
		command.setPathContext(pathContext);
		
		return new ModelAndView("blogJsp", model);
		
	}
	
	/**
	 * Method handles errors by code.
	 * 
	 * @param e object Exception with exception
	 * @param errorCode object String with code of error
	 * @return object ModelAndView with model and view of page
	 */
	@RequestMapping("/errors/{errorCode}")
	public ModelAndView handleError(Exception e, 
			@PathVariable String errorCode){
				
		LOG.error(MessageFormat.format("Error occured during processing. Error code: {0}.", errorCode));
		
		BlogCommand command = new BlogCommand();
		
		ModelMap model = new ModelMap();
		model.addAttribute("command", command);
		
		clearDisplays(command);
		command.setDisplayException(true);
		
		command.setPathHost(pathHost);
		command.setPathContext(pathContext);
		
		return new ModelAndView("blogJsp", model);
				
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	/**
	 * Method fills object BlogCommand with data.
	 * 
	 * @param command object BlogCommand with page data
	 * @param loc object Locale with international localization
	 * @throws Exception 
	 */
	protected void fillCommand(BlogCommand command, Locale loc) throws Exception{
		
		command.setPathHost(pathHost);
		command.setPathContext(pathContext);
		
		List<CategoryEntity> categoryList = categoryService.getAllCategoriesList(loc);
		command.setCategoryList(categoryList);
		
		command.setTagsCloud(getTagsCloud(categoryList));
		
		String selectedCategoryUniqueName = command.getSelectedCategoryUniqueName();			
		CategoryEntity selectedCategory = categoryService.getCategoryFromListByUniqueName(categoryList, selectedCategoryUniqueName);
		command.setSelectedCategory(selectedCategory);
		
		
		if(selectedCategory == null){
			return;
		}	
		
		
		String selectedArticleUniqueName = command.getSelectedArticleUniqueName();
		ArticleEntity selectedArticle = articleService.getArticleFromListByUniqueName(command.getSelectedCategory().getArticleList(), selectedArticleUniqueName);
		command.setSelectedArticle(selectedArticle);
						
	}
	
	/**
	 * Method gets cloud of tags. Tags are topics connected
	 * with specified article. For instance article "Hello World Servlet"
	 * can have tags like: Java, Servlet, Jsp etc.
	 * 
	 * @param categoryList list of categories with articles and tags
	 * @return object Cloud with tags cloud
	 * @throws Exception 
	 */
	protected Cloud getTagsCloud(List<CategoryEntity> categoryList) throws Exception{
		
		if(categoryList == null){
			throw new CategoryException("Error category handling. List of categories is null");
		}
		
		Cloud cloud = new Cloud();
		cloud.setMinWeight(10.0);
		cloud.setMaxWeight(20.0);
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
				
		for (CategoryEntity category : categoryList) {
			articleList.addAll(category.getArticleList());
		}
		
		for (ArticleEntity article : articleList) {
			articleTagList.addAll(article.getArticleTagList());
		}
		
		for (ArticleTagEntity articleTag : articleTagList) {
			cloud.addTag(articleTag.getName());
		}
		
		return cloud;
		
	}
	
	
	/**
	 * Method clears all display flags for central part.
	 * 
	 * @param command object BlogCommand with page data
	 */
	protected void clearDisplays(BlogCommand command){
		
		command.setDisplayArticleList(false);
		command.setDisplayArticle(false);
		
		command.setDisplaySelectedCategory(false);
		command.setDisplaySelectedArticle(false);
		command.setDisplayAboutMe(false);
		command.setDisplaySelectedExplanation(false);
		command.setDisplayException(false);
		
	}
	
	/**
	 * Method handles pagenation for all articles.
	 * 
	 * @param command object BlogCommand with page data
	 * @param pageNumber int with number of current page
	 * @throws Exception
	 */
	protected void handleArticleListPagenation(BlogCommand command, int pageNumber) throws Exception {
		
		int pageCurrent = pageNumber;
		int pagesCount = articleService.getPagesCountOfAllArticles();
		
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
		
		int pageCurrent = pageNumber;
		int pagesCount = command.getArticle().getPagesCount();
		
		handlePagenation(command, pageCurrent, pagesCount);
		
	}
	
	/**
	 * Method handles common pagenation.
	 * 
	 * @param command object BlogCommand with page data
	 * @param pageCurrent int with number of current page
	 * @param pagesCount int with count of all pages
	 * @throws Exception
	 */
	protected void handlePagenation(BlogCommand command, int pageCurrent, int pagesCount) throws Exception {
		
		if(pageCurrent > pagesCount){
			throw new ArticleException("Current page in URL is higher than pages count");
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
	
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}	

	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}	

	public void setExplanationService(IExplanationService explanationService) {
		this.explanationService = explanationService;
	}	

	public void setArticleTagService(IArticleTagService articleTagService) {
		this.articleTagService = articleTagService;
	}

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}	

}

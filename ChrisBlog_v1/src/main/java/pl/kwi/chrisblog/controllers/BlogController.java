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
	private LocaleResolver localeResolver;


	/**
	 * Method inits blog.
	 * 
	 * @param model object ModelMap with model
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception 
	 */
	@RequestMapping(value="/init")
	public ModelAndView init(@ModelAttribute("command")BlogCommand command, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			
		clearDisplays(command);
		command.setDisplaySelectedCategory(true);		
		Locale loc = localeResolver.resolveLocale(request);
		
		initSelected(command, loc);
		
		fillCommand(command, loc);	
				
		return new ModelAndView(new RedirectView("/categories/" + command.getSelectedCategoryPageCurrent() + "/" + command.getSelectedCategoryUniqueName() , true, true, true));
		
	}
	
	/**
	 * Method handles user category selection on page.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param selectedCategoryPageCurrent object String with current page of selected category
	 * @param selectedCategoryUniqueName object String with unique name of category
	 * @return object ModelAndView with model and view of page
	 */
	@RequestMapping("/categories/{selectedCategoryPageCurrent}/{selectedCategoryUniqueName}")
	public ModelAndView handleCategorySelection(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable String selectedCategoryPageCurrent,
			@PathVariable String selectedCategoryUniqueName) throws Exception{
		
		clearDisplays(command);
		command.setDisplaySelectedCategory(true);		
		Locale loc = localeResolver.resolveLocale(request);
		
		command.setSelectedCategoryPageCurrent(Integer.valueOf(selectedCategoryPageCurrent));
		command.setSelectedCategoryUniqueName(selectedCategoryUniqueName);
		
		fillCommand(command, loc);
		
		handleCategoryPagenation(command);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles user article selection on page.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param selectedCategoryPageCurrent object String with current page of selected category
	 * @param selectedCategoryUniqueName object String with unique name of category
	 * @param selectedArticlePageCurrent object String with current article page
	 * @param selectedArticleUniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 */
	@RequestMapping("/articles/{selectedCategoryPageCurrent}/{selectedCategoryUniqueName}/{selectedArticlePageCurrent}/{selectedArticleUniqueName}")
	public ModelAndView handleArticleSelection(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable String selectedCategoryPageCurrent,
			@PathVariable String selectedCategoryUniqueName,
			@PathVariable String selectedArticlePageCurrent, 
			@PathVariable String selectedArticleUniqueName) throws Exception{
		
		clearDisplays(command);
		command.setDisplaySelectedArticle(true);		
		Locale loc = localeResolver.resolveLocale(request);
		
		command.setSelectedCategoryPageCurrent(Integer.valueOf(selectedCategoryPageCurrent));
		command.setSelectedCategoryUniqueName(selectedCategoryUniqueName);
		command.setSelectedArticlePageCurrent(Integer.valueOf(selectedArticlePageCurrent));
		command.setSelectedArticleUniqueName(selectedArticleUniqueName);
		
		fillCommand(command, loc);
		
		handleCategoryPagenation(command);
		handleArticlePagenation(command);
		
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
	 * Method inits selected variables.
	 * 
	 * @param command object BlogCommand with page data
	 * @param loc object Locale with international localization
	 * @throws Exception 
	 */
	protected void initSelected(BlogCommand command, Locale loc) throws Exception{
		
		List<CategoryEntity> categoryList = categoryService.getAllCategoriesList(loc);
		
		if(categoryList == null){
			throw new CategoryException("Error category handling. List of categories is null");
		}
		
		if(categoryList.isEmpty()){
			return;
		}
		
		command.setSelectedCategoryId(categoryList.get(0).getId());
		command.setSelectedCategoryPageCurrent(1);
		command.setSelectedCategoryUniqueName(categoryList.get(0).getUniqueName());
		command.setSelectedCategory(categoryList.get(0));
		
	}
	
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
		
		command.setDisplaySelectedCategory(false);
		command.setDisplaySelectedArticle(false);
		command.setDisplayAboutMe(false);
		command.setDisplaySelectedExplanation(false);
		command.setDisplayException(false);
		
	}
	
	/**
	 * Method handles pagenation for category.
	 * 
	 * @param command object BlogCommand with page data
	 * @throws Exception
	 */
	protected void handleCategoryPagenation(BlogCommand command) throws Exception{
		
		CategoryEntity selectedCategory = command.getSelectedCategory();
		
		int selectedCategoryPagesCount = categoryService.getCategoryPagesCount(selectedCategory.getCountArticlesOnPage(), selectedCategory.getArticleList().size());
		int selectedCategoryPageCurrent = command.getSelectedCategoryPageCurrent();
		if(selectedCategoryPageCurrent > selectedCategoryPagesCount){
			throw new CategoryException("Current category page in URL is higher than pages count");
		}
		
		command.setSelectedCategoryPagesCount(selectedCategoryPagesCount);		
		articleService.countPageNumberForEveryArticleFromList(selectedCategory.getArticleList(), selectedCategory.getCountArticlesOnPage());
		
	}
	
	/**
	 * Method handles pagenation for article.
	 * 
	 * @param command object BlogCommand with page data
	 * @throws Exception
	 */
	protected void handleArticlePagenation(BlogCommand command) throws Exception{
		
		ArticleEntity selectedArticle = command.getSelectedArticle();
		
		int selectedArticlePagesCount = selectedArticle.getPagesCount();
		int selectedArticlePageCurrent = command.getSelectedArticlePageCurrent();
		if(selectedArticlePageCurrent > selectedArticlePagesCount){
			throw new ArticleException("Current article page in URL is higher than pages count");
		}
		
		command.setSelectedArticlePagesCount(selectedArticlePagesCount);
		
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

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}	

}

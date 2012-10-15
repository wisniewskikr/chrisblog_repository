package pl.kwi.chrisblog.controllers;

import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.chrisblog.commands.BlogCommand;
import pl.kwi.chrisblog.editors.ArticleTagEditor;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.utils.DateUtils;

/**
 * Class of controller for secured blog.
 * 
 * @author Krzysztof Wisniewski
 */
@Controller
@RequestMapping(value="/secured")
public class SecuredBlogController extends AbstractController{
	
	
	private static final Logger LOG = Logger.getLogger(SecuredBlogController.class);

	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(ArticleTagEntity.class, new ArticleTagEditor());
    }
	
	/**
	 * Method handles page with article list for page one in secured area. 
	 * By page one should be no page number in url. This is also default method. 
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/article-list")
	public ModelAndView displaySecArticleListPageOne(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		return displaySecArticleList(command, request, response, 1);
		
	}
	
	/**
	 * Method handles page with article list for pages other then one in secured area.
	 * By pages other then one should be page number in url.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageNumber int with current page number
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping("article-list/page/{pageNumber}")
	public ModelAndView displaySecArticleListPageNotOne(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable int pageNumber) throws Exception{
		
		if(pageNumber == 1){
			return new ModelAndView(new RedirectView("/" , true, true, true));
		}else{
			return displaySecArticleList(command, request, response, pageNumber);
		}
		
	}
	
	/**
	 * Method handles page with article list in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageNumber int with current page number
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	protected ModelAndView displaySecArticleList(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable int pageNumber) throws Exception{
		
		command.setDisplaySecArticleList(true);		
		handleCommand(command, request);
		
		command.setArticleList(articleService.findAllSortedByDateDesc(pageNumber, command.getLocale()));
		
		handleSecArticleListPagenation(command, pageNumber);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles page with article view in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/view-article/{uniqueName}")
	public ModelAndView displaySecViewArticle(
			ModelMap model,
			@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable String uniqueName) throws Exception{
		
		command.setDisplaySecViewArticle(true);		
		handleCommand(command, request);
		
		command.setArticle(articleService.getArticleByUniqueName(uniqueName, command.getLocale()));
		model.addAttribute("articleTagList", articleTagService.findAll());
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles editing page with article view in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/edit-article/{uniqueName}")
	public ModelAndView displaySecEditArticle(
			ModelMap model, 
			@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable String uniqueName) throws Exception{
		
		command.setDisplaySecEditArticle(true);		
		handleCommand(command, request);
		
		command.setArticle(articleService.getArticleByUniqueName(uniqueName, command.getLocale()));
		model.addAttribute("articleTagList", articleTagService.findAll());
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles updating page with article view in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-edit-article", method=RequestMethod.POST)
	public ModelAndView handleSecEditArticle(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ArticleEntity article = command.getArticle();
		Locale loc = localeResolver.resolveLocale(request);
		Calendar creationDate = DateUtils.convertStringWithMonthAsTextToCalendar(article.getCreationDateAsString(), loc);
		
		article.setCreationDate(creationDate);
		articleService.update(article);
		
		return new ModelAndView(new RedirectView("/secured/view-article/" + article.getUniqueName() , true, true, true));
		
	}
	
	/**
	 * Method displays creating page with article view in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/create-article")
	public ModelAndView displaySecCreateArticle(
			ModelMap model,
			@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		command.setDisplaySecCreateArticle(true);		
		handleCommand(command, request);
		
		command.setArticle(new ArticleEntity());
		model.addAttribute("articleTagList", articleTagService.findAll());
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles creating page with article view in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-create-article", method=RequestMethod.POST)
	public ModelAndView handleSecCreateArticle(@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ArticleEntity article = command.getArticle();
				
		Locale loc = localeResolver.resolveLocale(request);
		Calendar creationDate = DateUtils.convertStringWithMonthAsTextToCalendar(article.getCreationDateAsString(), loc);
		
		article.setCreationDate(creationDate);
		articleService.create(article);
		
		return new ModelAndView(new RedirectView("/secured/article-list" , true, true, true));
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //

	
	/**
	 * Method handles pagenation for all articles in secured area.
	 * 
	 * @param command object BlogCommand with page data
	 * @param pageNumber int with number of current page
	 * @throws Exception
	 */
	protected void handleSecArticleListPagenation(BlogCommand command, int pageNumber) throws Exception {
		
		Integer pageCurrent = pageNumber;
		Integer pagesCount = articleService.getPagesCountOfAllArticles();
		
		handlePagenation(command, pageCurrent, pagesCount);
		
	}
	
	
}

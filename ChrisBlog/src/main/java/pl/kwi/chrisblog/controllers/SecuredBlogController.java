package pl.kwi.chrisblog.controllers;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.chrisblog.commands.BlogCommand;
import pl.kwi.chrisblog.editors.ArticleTagListEditor;
import pl.kwi.chrisblog.editors.CreationDateEditor;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.exceptions.SecArticleException;

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
		binder.registerCustomEditor(List.class, "articleTagList", new ArticleTagListEditor(List.class));
		
		Locale loc = localeResolver.resolveLocale(request);
		binder.registerCustomEditor(Calendar.class, new CreationDateEditor(loc));
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
	 * Method displays creating page with article view in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param article object ArticleEntity with article
	 * @param isValidation boolean indicates if this is callback with validation errors.
	 * If yes then existing article should be used
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/create-article")
	public ModelAndView displaySecCreateArticle(
			ModelMap model,
			@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			ArticleEntity article,
			boolean isValidation) throws Exception{
		
		command.setDisplaySecCreateArticle(true);		
		handleCommand(command, request);
		
		if(!isValidation){
			article.setCreationDate(Calendar.getInstance());
		}
		
		model.addAttribute("article", article);
		model.addAttribute("articleTagList", articleTagService.findAll());
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles creating page with article view in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page
	 * @param article object ArticleEntity with article
	 * @param bindingResult object BindingResult with result from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-create-article", method=RequestMethod.POST)
	public ModelAndView handleSecCreateArticle(
			ModelMap model,
			@ModelAttribute("command")BlogCommand command,
			@Valid @ModelAttribute("article")ArticleEntity article,
			BindingResult bindingResult,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws Exception{
		
		if(bindingResult.hasErrors()){
			return displaySecCreateArticle(model, command, request, response, article, true);
		}
		
		articleService.create(article);
		
		return new ModelAndView(new RedirectView("/secured/info/create-article" , true, true, true));
		
	}
	
	/**
	 * Method handles page with article view in secured area.
	 * 
	 * @param model object ModelMap with model
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
		
		model.addAttribute("article", articleService.getArticleByUniqueName(uniqueName, command.getLocale()));
		model.addAttribute("articleTagList", articleTagService.findAll());
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles editing page with article view in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @param article object ArticleEntity with article
	 * @param isValidation boolean indicates if this is callback with validation errors.
	 * If yes then existing article should be used
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/edit-article/{uniqueName}")
	public ModelAndView displaySecEditArticle(
			ModelMap model, 
			@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable String uniqueName,
			ArticleEntity article,
			boolean isValidation) throws Exception{
		
		command.setDisplaySecEditArticle(true);		
		handleCommand(command, request);
		
		if(!isValidation){
			article = articleService.getArticleByUniqueName(uniqueName, command.getLocale());
		}
		
		model.addAttribute("article", article);
		model.addAttribute("articleTagList", articleTagService.findAll());
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles updating page with article view in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page
	 * @param article object ArticleEntity with article
	 * @param bindingResult object BindingResult with result from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-edit-article", method=RequestMethod.POST)
	public ModelAndView handleSecEditArticle(
			ModelMap model, 
			@ModelAttribute("command")BlogCommand command,
			@Valid @ModelAttribute("article")ArticleEntity article,
			BindingResult bindingResult,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		if(bindingResult.hasErrors()){
			return displaySecEditArticle(model, command, request, response, article.getUniqueName(), article, true);
		}
		
		articleService.update(article);
		
		return new ModelAndView(new RedirectView("/secured/info/edit-article" , true, true, true));
		
	}
	
	/**
	 * Method handles deleting page with article view in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/delete-article/{uniqueName}")
	public ModelAndView displaySecDeleteArticle(
			ModelMap model, 
			@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable String uniqueName) throws Exception{
		
		command.setDisplaySecDeleteArticle(true);		
		handleCommand(command, request);
		
		model.addAttribute("article", articleService.getArticleByUniqueName(uniqueName, command.getLocale()));
		model.addAttribute("articleTagList", articleTagService.findAll());
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles deleting page with article view in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-delete-article/{uniqueName}", method=RequestMethod.GET)
	public ModelAndView handleSecDeleteArticle(
			@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String uniqueName) throws Exception{
				
		articleService.deleteByUniqueName(uniqueName);
		
		return new ModelAndView(new RedirectView("/secured/article-list" , true, true, true));
		
	}
	
	/**
	 * Method displays info page in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageName object String with page name
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/info/{pageName}")
	public ModelAndView displaySecInfo(
			ModelMap model, 
			@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable String pageName) throws Exception{
		
		command.setDisplaySecInfo(true);		
		handleCommand(command, request);
		
		if("create-article".equals(pageName)){
			model.addAttribute("infoCreateArticle", true);
			model.addAttribute("title", messageSource.getMessage("info.title", null, command.getLocale()));
			model.addAttribute("content", messageSource.getMessage("info.content.create.article", null, command.getLocale()));
		}else if("edit-article".equals(pageName)){
			model.addAttribute("infoEditArticle", true);
			model.addAttribute("title", messageSource.getMessage("info.title", null, command.getLocale()));
			model.addAttribute("content", messageSource.getMessage("info.content.edit.article", null, command.getLocale()));
		}else{
			throw new SecArticleException("There is no info for action: " + pageName);
		}
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method displays confirmation page in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageName object String with page name
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/confirmation/{pageName}/{uniqueName}")
	public ModelAndView displaySecConfirmation(
			ModelMap model, 
			@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable String pageName,
			@PathVariable String uniqueName) throws Exception{
		
		command.setDisplaySecConfirmation(true);		
		handleCommand(command, request);
		
		if("delete-article".equals(pageName)){
			model.addAttribute("confirmDeleteArticle", true);
			model.addAttribute("title", messageSource.getMessage("confirmation.title", null, command.getLocale()));
			model.addAttribute("content", messageSource.getMessage("confirmation.content.delete.article", null, command.getLocale()));
			model.addAttribute("uniqueName", uniqueName);
		}
		
		return new ModelAndView("blogJsp");
		
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

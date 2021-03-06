package pl.kwi.chrisblog.controllers;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import pl.kwi.chrisblog.exceptions.SecArticleException;
import pl.kwi.chrisblog.validators.SecArticleListValidator;

/**
 * Class of controller for secured blog.
 * 
 * @author Krzysztof Wisniewski
 */
@Controller
@RequestMapping(value="/secured")
public class SecuredBlogController extends AbstractController{
	
	
	private static final Logger LOG = Logger.getLogger(SecuredBlogController.class);
	
	@Autowired
	private SecArticleListValidator secArticleListValidator;
	
	
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
	 * Method handles list of article. It concentrates mainly on validation.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param bindingResult object BindingResult with result from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageName object String with page name
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-article-list/{pageName}", method=RequestMethod.POST)
	protected ModelAndView handleSecArticleList(
			@ModelAttribute("command")BlogCommand command,
			BindingResult bindingResult,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String pageName) throws Exception{
		
		boolean isValid = secArticleListValidator.validate(command, bindingResult, pageName);
		if(!isValid){
			return displaySecArticleList(command, request, response, 1);
		}
		
		String[] selectedRowsTab = command.getSelectedRows().split(",");
		String uniqueName = selectedRowsTab[0];
		if("view-article".equals(pageName)){
			return new ModelAndView(new RedirectView("/secured/view-article/" + uniqueName, true, true, true));
		}else if("edit-article".equals(pageName)){
			return new ModelAndView(new RedirectView("/secured/edit-article/" + uniqueName, true, true, true));
		}else if("delete-article".equals(pageName)){
			return new ModelAndView(new RedirectView("/secured/delete-article/" + uniqueName, true, true, true));
		}else{
			throw new SecArticleException(MessageFormat.format("Action with name {0} is not sopported", pageName));
		}
		
	}
	
	/**
	 * Method displays article list with info in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param pageName object String with page name
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/article-list-with-info/{pageName}")
	protected ModelAndView displaySecArticleListWithInfo(
			ModelMap model,
			@ModelAttribute("command")BlogCommand command,
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable String pageName) throws Exception{
		
		Locale loc = localeResolver.resolveLocale(request);
		
		if("create-article".equals(pageName)){
			model.addAttribute("displayOkMessage", true);
			model.addAttribute("contentOkMessage", messageSource.getMessage("ok.create.article", null, loc));
		}else if("edit-article".equals(pageName)){
			model.addAttribute("displayOkMessage", true);
			model.addAttribute("contentOkMessage", messageSource.getMessage("ok.edit.article", null, loc));
		}else if("delete-article".equals(pageName)){
			model.addAttribute("displayOkMessage", true);
			model.addAttribute("contentOkMessage", messageSource.getMessage("ok.delete.article", null, loc));
		}else{
			throw new SecArticleException(MessageFormat.format("Action with name {0} is not sopported", pageName));
		}
		
		return displaySecArticleList(command, request, response, 1);
		
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
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //
	
	
	public void setSecArticleListValidator(
			SecArticleListValidator secArticleListValidator) {
		this.secArticleListValidator = secArticleListValidator;
	}
	
		
}

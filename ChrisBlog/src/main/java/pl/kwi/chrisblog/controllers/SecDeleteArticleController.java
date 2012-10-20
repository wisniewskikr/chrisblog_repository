package pl.kwi.chrisblog.controllers;

import java.util.Calendar;
import java.util.List;
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
import pl.kwi.chrisblog.editors.ArticleTagListEditor;
import pl.kwi.chrisblog.editors.CreationDateEditor;

/**
 * Class of controller for article delete in secured area.
 * 
 * @author Krzysztof Wisniewski
 */
@Controller
@RequestMapping(value="/secured")
public class SecDeleteArticleController extends AbstractController{
	
	
	private static final Logger LOG = Logger.getLogger(SecDeleteArticleController.class);
		
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(List.class, "articleTagList", new ArticleTagListEditor(List.class));
		
		Locale loc = localeResolver.resolveLocale(request);
		binder.registerCustomEditor(Calendar.class, new CreationDateEditor(loc));
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
		articleService.deleteDescriptionFile(uniqueName);
		articleService.deleteContentFile(uniqueName);
		
		return new ModelAndView(new RedirectView("/secured/article-list-with-info/delete-article" , true, true, true));
		
	}	
	
		
}

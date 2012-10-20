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
import pl.kwi.chrisblog.entities.ArticleEntity;

/**
 * Class of controller for article view in secured area.
 * 
 * @author Krzysztof Wisniewski
 */
@Controller
@RequestMapping(value="/secured")
public class SecViewArticleController extends AbstractController{
	
	
	private static final Logger LOG = Logger.getLogger(SecViewArticleController.class);
		
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(List.class, "articleTagList", new ArticleTagListEditor(List.class));
		
		Locale loc = localeResolver.resolveLocale(request);
		binder.registerCustomEditor(Calendar.class, new CreationDateEditor(loc));
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
	 * Method handles action from page view article in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param article object ArticleEntity with article
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-view-article", method=RequestMethod.POST)
	public ModelAndView handleSecViewArticle(
			@ModelAttribute("command")BlogCommand command,
			@ModelAttribute("article")ArticleEntity article,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws Exception{
	
		return new ModelAndView(new RedirectView("/secured/view-article-description/" + article.getUniqueName() , true, true, true));
		
	}
	
	/**
	 * Method displays page with view of article description in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/view-article-description/{uniqueName}")
	public ModelAndView displaySecViewArticleDescription(
			ModelMap model,
			@ModelAttribute("command")BlogCommand command,			
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String uniqueName) throws Exception{
		
		command.setDisplaySecViewArticleDescr(true);		
		handleCommand(command, request);
		
		ArticleEntity article = articleService.getArticleByUniqueName(uniqueName, command.getLocale());
		article.setDescription(articleService.readDescriptionFile(article.getUniqueName()));
		
		model.addAttribute("article", article);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles page with view of article description in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param article object ArticleEntity with article	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-view-article-description", method=RequestMethod.POST)
	public ModelAndView handleSecCreateArticleDescription(
			@ModelAttribute("command")BlogCommand command,
			@ModelAttribute("article")ArticleEntity article,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		return new ModelAndView(new RedirectView("/secured/view-article-content/" + article.getUniqueName() , true, true, true));
		
	}
	
	/**
	 * Method handles page with view of article description after pressing back button.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param article object ArticleEntity with article	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-view-article-description-back-button", method=RequestMethod.POST)
	public ModelAndView handleSecCreateArticleDescriptionBackButton(
			@ModelAttribute("command")BlogCommand command,
			@ModelAttribute("article")ArticleEntity article,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
				
		return new ModelAndView(new RedirectView("/secured/view-article/" + article.getUniqueName() , true, true, true));
		
	}
	
	/**
	 * Method displays page with view of article content in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/view-article-content/{uniqueName}")
	public ModelAndView displaySecViewArticleContent(
			ModelMap model,
			@ModelAttribute("command")BlogCommand command,			
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String uniqueName) throws Exception{
		
		command.setDisplaySecViewArticleContent(true);		
		handleCommand(command, request);
		
		ArticleEntity article = articleService.getArticleByUniqueName(uniqueName, command.getLocale());
		article.setContent(articleService.readContentFile(article.getUniqueName()));
		
		model.addAttribute("article", article);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles page with view of article content after pressing back button.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param article object ArticleEntity with article	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-view-article-content-back-button", method=RequestMethod.POST)
	public ModelAndView handleSecCreateArticleContentBackButton(
			@ModelAttribute("command")BlogCommand command,
			@ModelAttribute("article")ArticleEntity article,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
				
		return new ModelAndView(new RedirectView("/secured/view-article-description/" + article.getUniqueName() , true, true, true));
		
	}
	
		
}

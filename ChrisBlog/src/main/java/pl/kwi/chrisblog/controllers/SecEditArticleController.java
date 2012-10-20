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

/**
 * Class of controller for article edit in secured area.
 * 
 * @author Krzysztof Wisniewski
 */
@Controller
@RequestMapping(value="/secured")
public class SecEditArticleController extends AbstractController{
	
	
	private static final Logger LOG = Logger.getLogger(SecEditArticleController.class);
		
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(List.class, "articleTagList", new ArticleTagListEditor(List.class));
		
		Locale loc = localeResolver.resolveLocale(request);
		binder.registerCustomEditor(Calendar.class, new CreationDateEditor(loc));
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
		
		return new ModelAndView(new RedirectView("/secured/edit-article-description/" + article.getUniqueName() , true, true, true));
		
	}
	
	/**
	 * Method displays editing page with article description in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/edit-article-description/{uniqueName}")
	public ModelAndView displaySecEditArticleDescription(
			ModelMap model,
			@ModelAttribute("command")BlogCommand command,			
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String uniqueName) throws Exception{
		
		command.setDisplaySecEditArticleDescr(true);		
		handleCommand(command, request);
		
		ArticleEntity article = articleService.getArticleByUniqueName(uniqueName, command.getLocale());
		article.setDescription(articleService.readDescriptionFile(article.getUniqueName()));
		
		model.addAttribute("article", article);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles editing page with article description in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param article object ArticleEntity with article	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-edit-article-description", method=RequestMethod.POST)
	public ModelAndView handleSecEditArticleDescription(
			@ModelAttribute("command")BlogCommand command,
			@ModelAttribute("article")ArticleEntity article,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
				
		articleService.writeDescriptionFile(article.getUniqueName(), article.getDescription());
		
		return new ModelAndView(new RedirectView("/secured/edit-article-content/" + article.getUniqueName() , true, true, true));
		
	}
	
	/**
	 * Method handles editing page with article description in secured area after pressing back button.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param article object ArticleEntity with article	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-edit-article-description-back-button", method=RequestMethod.POST)
	public ModelAndView handleSecEditArticleDescriptionBackButton(
			@ModelAttribute("command")BlogCommand command,
			@ModelAttribute("article")ArticleEntity article,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
				
		articleService.writeDescriptionFile(article.getUniqueName(), article.getDescription());
		
		return new ModelAndView(new RedirectView("/secured/edit-article/" + article.getUniqueName() , true, true, true));
		
	}
	
	/**
	 * Method displays editing page with article content in secured area.
	 * 
	 * @param model object ModelMap with model
	 * @param command object BlogCommand with data from page	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/edit-article-content/{uniqueName}")
	public ModelAndView displaySecEditArticleContent(
			ModelMap model,
			@ModelAttribute("command")BlogCommand command,			
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String uniqueName) throws Exception{
		
		command.setDisplaySecEditArticleContent(true);		
		handleCommand(command, request);
		
		ArticleEntity article = articleService.getArticleByUniqueName(uniqueName, command.getLocale());
		article.setContent(articleService.readContentFile(article.getUniqueName()));
		
		model.addAttribute("article", article);
		
		return new ModelAndView("blogJsp");
		
	}
	
	/**
	 * Method handles editing page with article content in secured area.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param article object ArticleEntity with article	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-edit-article-content", method=RequestMethod.POST)
	public ModelAndView handleSecEditArticleContent(
			@ModelAttribute("command")BlogCommand command,
			@ModelAttribute("article")ArticleEntity article,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		articleService.writeContentFile(article.getUniqueName(), article.getContent());
		
		return new ModelAndView(new RedirectView("/secured/article-list-with-info/edit-article" , true, true, true));
		
	}
	
	/**
	 * Method handles editing page with article content in secured area 
	 * after back button pressing.
	 * 
	 * @param command object BlogCommand with data from page
	 * @param article object ArticleEntity with article	 
	 * @param request object HttpServletRequest with request from page 
	 * @param response object HttpServletResponse with response to page
	 * @param uniqueName object String with unique name of article
	 * @return object ModelAndView with model and view of page
	 * @throws Exception
	 */
	@RequestMapping(value="/handle-edit-article-content-back-button", method=RequestMethod.POST)
	public ModelAndView handleSecCreateArticleContentBackButton(
			@ModelAttribute("command")BlogCommand command,
			@ModelAttribute("article")ArticleEntity article,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		articleService.writeContentFile(article.getUniqueName(), article.getContent());
		
		return new ModelAndView(new RedirectView("/secured/edit-article-description/" + article.getUniqueName(), true, true, true));
		
	}
	
		
}

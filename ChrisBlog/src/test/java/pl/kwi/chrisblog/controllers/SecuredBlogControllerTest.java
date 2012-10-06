package pl.kwi.chrisblog.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mcavallo.opencloud.Cloud;
import org.mockito.Mockito;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import pl.kwi.chrisblog.commands.BlogCommand;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.entities.ExplanationEntity;
import pl.kwi.chrisblog.services.ArticleService;
import pl.kwi.chrisblog.services.ArticleTagService;
import pl.kwi.chrisblog.services.ExplanationService;

public class SecuredBlogControllerTest {

	private SecuredBlogController controller;

	@Before
	public void setUp() throws Exception {
		controller = new SecuredBlogController();
		controller.setPathHost("pathHost");
		controller.setPathContext("pathContext");
		controller.setLocaleResolver(mockLocaleResolver());
		controller.setArticleService(mockArticleService());
		controller.setArticleTagService(mockArticleTagService());
	}

	@Test
	public void displaySecArticleList() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		
		ModelAndView modelAndView = controller.displaySecArticleList(command, request, response);
		
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplaySecArticleList());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertEquals("Secured List of Articles", command.getWindowTitle());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloudFooter());
		Assert.assertNotNull(command.getTagsCloudRightSpace());		
				
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	

	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //

	
	private HttpServletRequest mockHttpServletRequest() {

		HttpServletRequest mock = Mockito.mock(HttpServletRequest.class);
		return mock;

	}

	private HttpServletResponse mockHttpServletResponse() {

		HttpServletResponse mock = Mockito.mock(HttpServletResponse.class);
		return mock;

	}

	private LocaleResolver mockLocaleResolver() {

		LocaleResolver mock = Mockito.mock(LocaleResolver.class);
		Mockito.when(mock.resolveLocale(Mockito.any(HttpServletRequest.class)))
				.thenReturn(Locale.ENGLISH);
		return mock;

	}

	private ArticleService mockArticleService() throws Exception {
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();

		ArticleService mock = Mockito.mock(ArticleService.class);

		Mockito.when(mock.getAllArticleList(Mockito.any(Locale.class)))
				.thenReturn(articleList);		

		return mock;

	}

	private ArticleTagService mockArticleTagService() throws Exception {

		ArticleTagEntity articleTag;
		articleTag = new ArticleTagEntity();
		articleTag.setId(1L);
		articleTag.setName("Name");
		articleTag.setUniqueName("unique_name");

		ArticleTagService mock = Mockito.mock(ArticleTagService.class);

		Mockito.when(mock.getTagsCloudFooter(Mockito.anyList())).thenReturn(
				new Cloud());
		Mockito.when(mock.getTagsCloudRightSpace(Mockito.anyList()))
				.thenReturn(new Cloud());
		Mockito.when(mock.getArticleTagByUniqueName(Mockito.anyString()))
				.thenReturn(articleTag);

		return mock;

	}

	private ExplanationService mockExplanationService() throws Exception {

		ExplanationEntity explanation = new ExplanationEntity();
		explanation.setId(1L);
		explanation.setUniqueName("explanation_unique_name");

		ExplanationService mock = Mockito.mock(ExplanationService.class);

		Mockito.when(mock.getExplanationByUniqueName(Mockito.anyString()))
				.thenReturn(explanation);

		return mock;

	}

}

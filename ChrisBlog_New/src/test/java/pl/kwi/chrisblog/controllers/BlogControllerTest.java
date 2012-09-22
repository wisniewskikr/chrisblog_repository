package pl.kwi.chrisblog.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mcavallo.opencloud.Cloud;
import org.mockito.Mockito;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.chrisblog.commands.BlogCommand;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.entities.ExplanationEntity;
import pl.kwi.chrisblog.exceptions.ArticleException;
import pl.kwi.chrisblog.services.impl.ArticleService;
import pl.kwi.chrisblog.services.impl.ArticleTagService;
import pl.kwi.chrisblog.services.impl.ExplanationService;
import pl.kwi.chrisblog.utils.DateUtils;

public class BlogControllerTest {
	
	private BlogController contoller;
	
	@Before
	public void setUp() throws Exception{
		contoller = new BlogController();
		contoller.setPathHost("pathHost");
		contoller.setPathContext("pathContext");
		contoller.setLocaleResolver(mockLocaleResolver());
		contoller.setArticleService(mockArticleService());
		contoller.setArticleTagService(mockArticleTagService());
		contoller.setExplanationService(mockExplanationService());
	}
	
	@Test
	public void init() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		
		ModelAndView modelAndView = contoller.init(command, request, response);
		
		Assert.assertEquals("/page/1", ((RedirectView)modelAndView.getView()).getUrl());
		
	}
	
	@Test
	public void displayArticleList() throws Exception{
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		int pageNumber = 1;
		
		ModelAndView modelAndView = contoller.displayArticleList(command, request, response, pageNumber);
		
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplayArticleList());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloud());
		
		Assert.assertEquals(2, command.getArticleList().size());
		
		Assert.assertEquals(Integer.valueOf(1), command.getPageCurrent());
		Assert.assertEquals(Integer.valueOf(4), command.getPagesCount());
		
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test(expected = ArticleException.class)
	public void displayArticleList_withException() throws Exception{
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		int pageNumber = 8;
		
		contoller.displayArticleList(command, request, response, pageNumber);
		
	}
	
	@Test
	public void displayArticle() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		int pageNumber = 1;
		String uniqueName = "uniqueName";
		
		ModelAndView modelAndView = contoller.displayArticle(command, request, response, pageNumber, uniqueName);
		
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplayArticle());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloud());
		
		Assert.assertEquals("unique_name_1", command.getArticle().getUniqueName());
		
		Assert.assertEquals(Integer.valueOf(1), command.getPageCurrent());
		Assert.assertEquals(Integer.valueOf(3), command.getPagesCount());
		
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test(expected = ArticleException.class)
	public void displayArticle_withException() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		int pageNumber = 8;
		String uniqueName = "uniqueName";
		
		contoller.displayArticle(command, request, response, pageNumber, uniqueName);
		
	}
	
	@Test
	public void displayExplanation() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		String uniqueName = "uniqueName";
		
		ModelAndView modelAndView = contoller.displayExplanation(command, request, response, uniqueName);
		
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplayExplanation());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloud());
		
		Assert.assertNotNull(command.getExplanation());
		Assert.assertEquals("explanation_unique_name", command.getExplanation().getUniqueName());
		
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	public void displayAboutMe() throws Exception{
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		
		ModelAndView modelAndView = contoller.displayAboutMe(command, request, response);
		
		Assert.assertFalse(command.isDisplayExplanation());
		Assert.assertTrue(command.isDisplayAboutMe());
		Assert.assertFalse(command.isDisplayException());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloud());
		
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	public void displayException(){
		
		Exception e = new Exception();
				
		ModelAndView modelAndView = contoller.displayException(e);
		
		BlogCommand command = (BlogCommand)modelAndView.getModel().get("command");
		
		Assert.assertFalse(command.isDisplayExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplayException());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertNull(command.getLocale());
		Assert.assertNull(command.getTagsCloud());
		
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	public void displayError(){
				
		Exception e = new Exception();
		String errorCode = "errorCode";
		
		ModelAndView modelAndView = contoller.displayError(e, errorCode);
		
		BlogCommand command = (BlogCommand)modelAndView.getModel().get("command");
		
		Assert.assertFalse(command.isDisplayExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplayException());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertNull(command.getLocale());
		Assert.assertNull(command.getTagsCloud());
		
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	public void handleCommand() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		
		contoller.handleCommand(command, request);
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloud());
		
	}
	
	@Test
	public void handleCommand_withoutRequest() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = null;
		
		contoller.handleCommand(command, request);
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertNull(command.getLocale());
		Assert.assertNull(command.getTagsCloud());
		
	}
	
//	@Test
//	public void handleArticlePagenation() throws Exception{
//		
//		ArticleEntity selectedArticle = new ArticleEntity();
//		selectedArticle.setPagesCount(3);
//		
//		BlogCommand command = new BlogCommand();
//		command.setSelectedArticle(selectedArticle);
//		command.setSelectedArticlePageCurrent(1);
//		
//		contoller.handleArticlePagenation(command);
//		
//		Assert.assertEquals(Integer.valueOf(3), command.getSelectedArticlePagesCount());
//		
//	}
//	
//	@Test(expected = ArticleException.class)
//	public void handleArticlePagenation_exception() throws Exception{
//		
//		ArticleEntity selectedArticle = new ArticleEntity();
//		selectedArticle.setPagesCount(3);
//		
//		BlogCommand command = new BlogCommand();
//		command.setSelectedArticle(selectedArticle);
//		command.setSelectedArticlePageCurrent(5);
//		
//		contoller.handleArticlePagenation(command);
//		
//	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private List<ArticleEntity> mockCompleteArticleList() throws Exception{
		
		List<ArticleEntity> completeArticleList = new ArrayList<ArticleEntity>();
		
		List<ArticleTagEntity> articleTagList;
		ArticleTagEntity articleTag;
		ArticleEntity article;
		
		//First Article
		articleTagList = new ArrayList<ArticleTagEntity>();
		articleTag = new ArticleTagEntity();
		articleTag.setId(1L);
		articleTag.setName("Name1");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2L);
		articleTag.setName("Name2");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(3L);
		articleTag.setName("Name3");
		articleTagList.add(articleTag);
		
		article = new ArticleEntity();
		article.setId(1L);
		article.setUniqueName("unique_name_1");
		article.setCategoryId(1L);
		article.setTitle("Title 1");
		article.setDescription("Description 1");
		article.setContentPath("Path/path1");
		article.setPagesCount(3);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author1");
		article.setArticleTagList(articleTagList);
		article.setDemoPath("/Demo path1");
		article.setExamplePath("/Example path1");
		article.setSourcePath("/Source path1");
		completeArticleList.add(article);
		
		
		//Second Article
		articleTagList = new ArrayList<ArticleTagEntity>();
		articleTag = new ArticleTagEntity();
		articleTag.setId(4L);
		articleTag.setName("Name4");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(5L);
		articleTag.setName("Name5");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(6L);
		articleTag.setName("Name6");
		articleTagList.add(articleTag);
		
		article = new ArticleEntity();
		article.setId(2L);
		article.setUniqueName("unique_name_2");
		article.setCategoryId(1L);
		article.setTitle("Title 2");
		article.setDescription("Description 2");
		article.setContentPath("Path/path1");
		article.setPagesCount(3);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author2");
		article.setArticleTagList(articleTagList);
		article.setDemoPath("/Demo path2");
		article.setExamplePath("/Example path2");
		article.setSourcePath("/Source path2");
		completeArticleList.add(article);
		
		
		return completeArticleList;		
				
	}
		
	private HttpServletRequest mockHttpServletRequest(){
		
		HttpServletRequest mock = Mockito.mock(HttpServletRequest.class);
		return mock;
		
	}
	
	private HttpServletResponse mockHttpServletResponse(){
		
		HttpServletResponse mock = Mockito.mock(HttpServletResponse.class);
		return mock;
		
	}
	
	private LocaleResolver mockLocaleResolver() {

		LocaleResolver mock = Mockito.mock(LocaleResolver.class);
		Mockito.when(mock.resolveLocale(Mockito.any(HttpServletRequest.class)))
				.thenReturn(Locale.ENGLISH);
		return mock;

	}
	
	private ArticleService mockArticleService() throws Exception{
		
		ArticleService mock = Mockito.mock(ArticleService.class);
		
		Mockito.when(mock.getAllArticleList(Mockito.any(Locale.class))).thenReturn(mockCompleteArticleList());
		Mockito.when(mock.getArticleListByPageTagAndLocal(Mockito.anyInt(), Mockito.any(ArticleTagEntity.class), Mockito.any(Locale.class))).thenReturn(mockCompleteArticleList());
		Mockito.when(mock.getPagesCountOfAllArticles()).thenReturn(4);
		Mockito.when(mock.getArticleByUniqueName(Mockito.anyString(), Mockito.any(Locale.class))).thenReturn(mockCompleteArticleList().get(0));
				
		return mock;
		
	}
	
	private ArticleTagService mockArticleTagService() throws Exception{
		
		ArticleTagService mock = Mockito.mock(ArticleTagService.class);
		
		Mockito.when(mock.getTagsCloud(Mockito.anyList())).thenReturn(new Cloud());
		
		return mock;
		
	}
	
	private ExplanationService mockExplanationService() throws Exception{
		
		ExplanationEntity explanation = new ExplanationEntity();
		explanation.setId(1L);
		explanation.setUniqueName("explanation_unique_name");
		
		ExplanationService mock = Mockito.mock(ExplanationService.class);
		
		Mockito.when(mock.getExplanationByUniqueName(Mockito.anyString())).thenReturn(explanation);
		
		return mock;
		
	}
	
	
}

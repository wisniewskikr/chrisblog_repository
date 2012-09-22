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
	public void displayException(){
		
		Exception e = new Exception();
		contoller.setPathHost("pathHost");
		contoller.setPathContext("pathContext");
		
		ModelAndView modelAndView = contoller.displayException(e);
		
		BlogCommand command = (BlogCommand)modelAndView.getModel().get("command");
		
		Assert.assertFalse(command.isDisplayExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplayException());
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	public void displayError(){
		
		contoller.setPathHost("pathHost");
		contoller.setPathContext("pathContext");
		
		Exception e = new Exception();
		String errorCode = "errorCode";
		
		ModelAndView modelAndView = contoller.displayError(e, errorCode);
		
		BlogCommand command = (BlogCommand)modelAndView.getModel().get("command");
		
		Assert.assertFalse(command.isDisplayExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplayException());
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	@Ignore
	public void displayAboutMe() throws Exception{
		
		contoller.setLocaleResolver(mockLocaleResolver());
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		
		command.setDisplayExplanation(true);
		command.setDisplayAboutMe(true);
		command.setDisplayException(true);		
		
		ModelAndView modelAndView = contoller.displayAboutMe(command, request, response);
		
		Assert.assertFalse(command.isDisplayExplanation());
		Assert.assertTrue(command.isDisplayAboutMe());
		Assert.assertFalse(command.isDisplayException());
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	@Ignore
	public void displayExplanation() throws Exception{
		
		contoller.setLocaleResolver(mockLocaleResolver());
		contoller.setExplanationService(mockExplanationService());
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		String explanationUniqueName = "unique_name";
		
		command.setDisplayExplanation(true);
		command.setDisplayAboutMe(true);
		command.setDisplayException(true);
		
		ModelAndView modelAndView = contoller.displayExplanation(command, request, response, explanationUniqueName);
		
		Assert.assertTrue(command.isDisplayExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertFalse(command.isDisplayException());
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
		Assert.assertNotNull(command.getExplanation());
		
	}
	
	@Test
	@Ignore
	public void handleArticleSelection() throws Exception{
		
//		contoller.setCategoryService(mockCategoryService_CategorySelected());
//		contoller.setLocaleResolver(mockLocaleResolver());
//		contoller.setArticleService(mockArticleService_CategorySelected());
//		
//		BlogCommand command = new BlogCommand();
//		HttpServletRequest request = mockHttpServletRequest();
//		HttpServletResponse response = mockHttpServletResponse();
//		String selectedCategoryPageCurrent = "1";
//		String selectedCategoryUniqueName = "category_unique_name";
//		String selectedArticlePageCurrent = "2";
//		String selectedArticleUniqueName = "article_unique_name";
//		
//		command.setDisplaySelectedCategory(true);
//		command.setDisplaySelectedArticle(true);
//		command.setDisplayExplanation(true);
//		command.setDisplayAboutMe(true);
//		command.setDisplayException(true);
//		
//		ModelAndView modelAndView = contoller.handleArticleSelection(command, request, response, selectedCategoryPageCurrent, selectedCategoryUniqueName, selectedArticlePageCurrent, selectedArticleUniqueName);
//		
//		Assert.assertFalse(command.isDisplaySelectedCategory());
//		Assert.assertTrue(command.isDisplaySelectedArticle());
//		Assert.assertFalse(command.isDisplayExplanation());
//		Assert.assertFalse(command.isDisplayAboutMe());
//		Assert.assertFalse(command.isDisplayException());
//		Assert.assertEquals("blogJsp", modelAndView.getViewName());
//		
//		Assert.assertEquals(Integer.valueOf(1), command.getSelectedCategoryPageCurrent());
//		Assert.assertEquals("category_unique_name", command.getSelectedCategoryUniqueName());
//		Assert.assertEquals(Integer.valueOf(2), command.getSelectedArticlePageCurrent());
//		Assert.assertEquals("article_unique_name", command.getSelectedArticleUniqueName());
//		Assert.assertEquals(Integer.valueOf(5), command.getSelectedArticlePagesCount());
		
	}
	
	@Test
	public void handleCategorySelection() throws Exception{
		
//		contoller.setCategoryService(mockCategoryService_CategorySelected());
//		contoller.setLocaleResolver(mockLocaleResolver());
//		contoller.setArticleService(mockArticleService_CategorySelected());
//		
//		BlogCommand command = new BlogCommand();
//		HttpServletRequest request = mockHttpServletRequest();
//		HttpServletResponse response = mockHttpServletResponse();
//		String selectedCategoryPageCurrent = "1";
//		String selectedCategoryUniqueName = "category_unique_name";
//		
//		command.setDisplaySelectedCategory(true);
//		command.setDisplaySelectedArticle(true);
//		command.setDisplayExplanation(true);
//		command.setDisplayAboutMe(true);
//		command.setDisplayException(true);
//		
//		ModelAndView modelAndView = contoller.handleCategorySelection(command, request, response, selectedCategoryPageCurrent, selectedCategoryUniqueName);
//		
//		Assert.assertTrue(command.isDisplaySelectedCategory());
//		Assert.assertFalse(command.isDisplaySelectedArticle());
//		Assert.assertFalse(command.isDisplayExplanation());
//		Assert.assertFalse(command.isDisplayAboutMe());
//		Assert.assertFalse(command.isDisplayException());
//		Assert.assertEquals("blogJsp", modelAndView.getViewName());
//		
//		Assert.assertEquals(Integer.valueOf(1), command.getSelectedCategoryPageCurrent());
//		Assert.assertEquals("category_unique_name", command.getSelectedCategoryUniqueName());
		
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
	
	
	private List<ArticleEntity> mockCompleteArticleList1() throws Exception{
		
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
	
	private List<ArticleEntity> mockCompleteArticleList2() throws Exception{
		
		List<ArticleEntity> completeArticleList = new ArrayList<ArticleEntity>();
		
		List<ArticleTagEntity> articleTagList;
		ArticleTagEntity articleTag;
		ArticleEntity article;
		
		//Third Article
		articleTagList = new ArrayList<ArticleTagEntity>();
		articleTag = new ArticleTagEntity();
		articleTag.setId(7L);
		articleTag.setName("Name7");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(8L);
		articleTag.setName("Name8");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(9L);
		articleTag.setName("Name9");
		articleTagList.add(articleTag);
		
		article = new ArticleEntity();
		article.setId(3L);
		article.setUniqueName("unique_name_3");
		article.setCategoryId(2L);
		article.setTitle("Title 3");
		article.setDescription("Description 3");
		article.setContentPath("Path/path3");
		article.setPagesCount(3);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author3");
		article.setArticleTagList(articleTagList);
		article.setDemoPath("/Demo path3");
		article.setExamplePath("/Example path3");
		article.setSourcePath("/Source path3");
		completeArticleList.add(article);
		
		
		//Fourth Article
		articleTagList = new ArrayList<ArticleTagEntity>();
		articleTag = new ArticleTagEntity();
		articleTag.setId(10L);
		articleTag.setName("Name10");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(11L);
		articleTag.setName("Name11");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(12L);
		articleTag.setName("Name12");
		articleTagList.add(articleTag);
		
		article = new ArticleEntity();
		article.setId(4L);
		article.setUniqueName("unique_name_4");
		article.setCategoryId(2L);
		article.setTitle("Title 4");
		article.setDescription("Description 4");
		article.setContentPath("Path/path4");
		article.setPagesCount(3);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author4");
		article.setArticleTagList(articleTagList);
		article.setDemoPath("/Demo path4");
		article.setExamplePath("/Example path4");
		article.setSourcePath("/Source path4");
		completeArticleList.add(article);
		
		
		return completeArticleList;		
				
	}
	
	private List<ArticleEntity> mockCompleteArticleList3() throws Exception{
		
		List<ArticleEntity> completeArticleList = new ArrayList<ArticleEntity>();
		
		List<ArticleTagEntity> articleTagList;
		ArticleTagEntity articleTag;
		ArticleEntity article;
		
		//Fifth Article
		articleTagList = new ArrayList<ArticleTagEntity>();
		articleTag = new ArticleTagEntity();
		articleTag.setId(13L);
		articleTag.setName("Name13");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(14L);
		articleTag.setName("Name14");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(15L);
		articleTag.setName("Name15");
		articleTagList.add(articleTag);
		
		article = new ArticleEntity();
		article.setId(5L);
		article.setUniqueName("unique_name_5");
		article.setCategoryId(3L);
		article.setTitle("Title 5");
		article.setDescription("Description 5");
		article.setContentPath("Path/path5");
		article.setPagesCount(3);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author5");
		article.setArticleTagList(articleTagList);
		article.setDemoPath("/Demo path5");
		article.setExamplePath("/Example path5");
		article.setSourcePath("/Source path5");
		completeArticleList.add(article);
		
		
		//Sixth Article
		articleTagList = new ArrayList<ArticleTagEntity>();
		articleTag = new ArticleTagEntity();
		articleTag.setId(13L);
		articleTag.setName("Name13");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(14L);
		articleTag.setName("Name14");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(15L);
		articleTag.setName("Name15");
		articleTagList.add(articleTag);
		
		article = new ArticleEntity();
		article.setId(6L);
		article.setUniqueName("unique_name_6");
		article.setCategoryId(3L);
		article.setTitle("Title 4");
		article.setDescription("Description 6");
		article.setContentPath("Path/path6");
		article.setPagesCount(3);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author6");
		article.setArticleTagList(articleTagList);
		article.setDemoPath("/Demo path6");
		article.setExamplePath("/Example path6");
		article.setSourcePath("/Source path6");
		completeArticleList.add(article);
		
		
		return completeArticleList;		
		
	}
	
	private ArticleService mockArticleService_CategorySelected() throws Exception{
		
		ArticleService mock = Mockito.mock(ArticleService.class);
		
		ArticleEntity selectedArticle = new ArticleEntity();
		selectedArticle.setPagesCount(5);
		
		Mockito.when(mock.getArticleFromListByUniqueName(Mockito.anyList(), Mockito.anyString())).thenReturn(selectedArticle);
		
		return mock;
		
	}
	
	private HttpServletRequest mockHttpServletRequest(){
		
		HttpServletRequest mock = Mockito.mock(HttpServletRequest.class);
		return mock;
		
	}
	
	private HttpServletResponse mockHttpServletResponse(){
		
		HttpServletResponse mock = Mockito.mock(HttpServletResponse.class);
		return mock;
		
	}
	
	private ExplanationService mockExplanationService() throws Exception{
		
		ExplanationEntity explanation = new ExplanationEntity();
		explanation.setId(1L);
		explanation.setUniqueName("unique_name_1");
		
		ExplanationService mock = Mockito.mock(ExplanationService.class);
		
		Mockito.when(mock.getExplanationByUniqueName(Mockito.anyString())).thenReturn(explanation);
		
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
		
		Mockito.when(mock.getAllArticleList(Mockito.any(Locale.class))).thenReturn(mockCompleteArticleList1());
		Mockito.when(mock.getArticleListByPageTagAndLocal(Mockito.anyInt(), Mockito.any(ArticleTagEntity.class), Mockito.any(Locale.class))).thenReturn(mockCompleteArticleList1());
		Mockito.when(mock.getPagesCountOfAllArticles()).thenReturn(4);
		Mockito.when(mock.getArticleByUniqueName(Mockito.anyString(), Mockito.any(Locale.class))).thenReturn(mockCompleteArticleList1().get(0));
				
		return mock;
		
	}
	
	private ArticleTagService mockArticleTagService() throws Exception{
		
		ArticleTagService mock = Mockito.mock(ArticleTagService.class);
		
		Mockito.when(mock.getTagsCloud(Mockito.anyList())).thenReturn(new Cloud());
		
		return mock;
		
	}
	
	
}

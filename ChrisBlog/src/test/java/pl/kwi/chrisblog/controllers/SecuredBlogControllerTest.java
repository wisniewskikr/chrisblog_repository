package pl.kwi.chrisblog.controllers;

import static junit.framework.Assert.assertNotNull;

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
import pl.kwi.chrisblog.exceptions.PagenationException;
import pl.kwi.chrisblog.services.ArticleService;
import pl.kwi.chrisblog.services.ArticleTagService;
import pl.kwi.chrisblog.utils.DateUtils;

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
	public void displaySecArticleListPageOne() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		
		ModelAndView modelAndView = controller.displaySecArticleListPageOne(command, request, response);
		
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
	
	@Test
	public void displayArticleListPageNotOne() throws Exception{
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		int pageNumber = 2;
		
		ModelAndView modelAndView = controller.displaySecArticleListPageNotOne(command, request, response, pageNumber);
		
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplaySecArticleList());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertEquals("Secured List of Articles", command.getWindowTitle());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloudFooter());
		Assert.assertNotNull(command.getTagsCloudRightSpace());
		
		Assert.assertEquals(2, command.getArticleList().size());
		
		Assert.assertEquals(Integer.valueOf(2), command.getPageCurrent());
		Assert.assertEquals(Integer.valueOf(4), command.getPagesCount());
		
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test(expected = PagenationException.class)
	public void displayArticleListPageNotOne_withException() throws Exception{
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		int pageNumber = 8;
		
		controller.displaySecArticleListPageNotOne(command, request, response, pageNumber);
		
	}
	
	@Test
	public void displayArticleList() throws Exception{
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		int pageNumber = 1;
		
		ModelAndView modelAndView = controller.displaySecArticleList(command, request, response, pageNumber);
		
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplaySecArticleList());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertEquals("Secured List of Articles", command.getWindowTitle());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloudFooter());
		Assert.assertNotNull(command.getTagsCloudRightSpace());
		
		Assert.assertEquals(2, command.getArticleList().size());
		
		Assert.assertEquals(Integer.valueOf(1), command.getPageCurrent());
		Assert.assertEquals(Integer.valueOf(4), command.getPagesCount());
		
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test(expected = PagenationException.class)
	public void displayArticleList_withException() throws Exception{
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		int pageNumber = 8;
		
		controller.displaySecArticleList(command, request, response, pageNumber);
		
	}
	
	@Test
	public void displaySecViewArticle() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		String uniqueName = "uniqueName";
		
		ModelAndView modelAndView = controller.displaySecViewArticle(command, request, response, uniqueName);
		
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplaySecViewArticle());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertEquals("Secured View of Article", command.getWindowTitle());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloudFooter());
		Assert.assertNotNull(command.getTagsCloudRightSpace());
		
		assertNotNull(command.getArticle());
				
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	public void displaySecEditArticle() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		String uniqueName = "uniqueName";
		
		ModelAndView modelAndView = controller.displaySecEditArticle(command, request, response, uniqueName);
		
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplaySecEditArticle());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertEquals("Secured Edit of Article", command.getWindowTitle());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloudFooter());
		Assert.assertNotNull(command.getTagsCloudRightSpace());
		
		assertNotNull(command.getArticle());
				
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	public void displaySecCreateArticle() throws Exception {
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		
		ModelAndView modelAndView = controller.displaySecCreateArticle(command, request, response);
		
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplaySecCreateArticle());
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertEquals("Secured Create of Article", command.getWindowTitle());
		Assert.assertNotNull(command.getLocale());
		Assert.assertNotNull(command.getTagsCloudFooter());
		Assert.assertNotNull(command.getTagsCloudRightSpace());
		
		assertNotNull(command.getArticle());
		
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

	private ArticleService mockArticleService() throws Exception{
		
		ArticleService mock = Mockito.mock(ArticleService.class);
		
		Mockito.when(mock.getAllArticleList(Mockito.any(Locale.class))).thenReturn(mockCompleteArticleList());
		Mockito.when(mock.findAllSortedByDateDesc(Mockito.anyInt(), Mockito.any(Locale.class))).thenReturn(mockCompleteArticleList());
		Mockito.when(mock.findAllWithTagsSortedByDateDesc(Mockito.anyInt(), Mockito.any(ArticleTagEntity.class), Mockito.any(Locale.class))).thenReturn(mockCompleteArticleList());
		Mockito.when(mock.getPagesCountOfAllArticles()).thenReturn(4);
		Mockito.when(mock.getPagesCountArticlesWithTag(Mockito.any(ArticleTagEntity.class))).thenReturn(4);
		Mockito.when(mock.getArticleByUniqueName(Mockito.anyString(), Mockito.any(Locale.class))).thenReturn(mockCompleteArticleList().get(0));
				
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
		article.setTitle("Title 1");
		article.setDescription("Description 1");
		article.setContent("Path/path1");
		article.setPagesCount(3);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author1");
		article.setArticleTagList(articleTagList);
		article.setDemoName("Demo path1");
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
		article.setTitle("Title 2");
		article.setDescription("Description 2");
		article.setContent("Path/path1");
		article.setPagesCount(3);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author2");
		article.setArticleTagList(articleTagList);
		article.setDemoName("Demo path2");
		article.setExamplePath("/Example path2");
		article.setSourcePath("/Source path2");
		completeArticleList.add(article);
		
		
		return completeArticleList;		
				
	}

}

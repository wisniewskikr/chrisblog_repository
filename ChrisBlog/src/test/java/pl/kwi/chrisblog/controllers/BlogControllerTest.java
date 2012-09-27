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
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.chrisblog.commands.BlogCommand;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.entities.CategoryEntity;
import pl.kwi.chrisblog.entities.ExplanationEntity;
import pl.kwi.chrisblog.exceptions.ArticleException;
import pl.kwi.chrisblog.exceptions.CategoryException;
import pl.kwi.chrisblog.services.impl.ArticleService;
import pl.kwi.chrisblog.services.impl.CategoryService;
import pl.kwi.chrisblog.services.impl.ExplanationService;
import pl.kwi.chrisblog.utils.DateUtils;

public class BlogControllerTest {
	
	private BlogController contoller;
	
	@Before
	public void setUp(){
		contoller = new BlogController();		
	}
	
	@Test
	public void clearDisplays(){
		
		BlogCommand command = new BlogCommand();
		command.setDisplaySelectedCategory(true);
		command.setDisplaySelectedArticle(true);
		command.setDisplayAboutMe(true);
		command.setDisplaySelectedExplanation(true);
		command.setDisplayException(true);
		
		contoller.clearDisplays(command);
		
		Assert.assertFalse(command.isDisplaySelectedCategory());
		Assert.assertFalse(command.isDisplaySelectedArticle());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertFalse(command.isDisplaySelectedExplanation());
		Assert.assertFalse(command.isDisplayException());
		
	}
	
	@Test
	public void getTagsCloud() throws Exception{
		
		List<CategoryEntity> categoryList = mockCompleteCategoryList();
		
		Cloud tagsCloud = contoller.getTagsCloud(categoryList);
		
		Assert.assertEquals(15, tagsCloud.size());		
		
	}
	
	@Test(expected = CategoryException.class)
	public void getTagsCloud_CategoryListNull() throws Exception{
		
		List<CategoryEntity> categoryList = null;
		
		contoller.getTagsCloud(categoryList);
		
	}
	
	@Test
	public void fillCommand_NoSelectedCategory() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_NoCategorySelected());
		contoller.setPathHost("pathHost");
		contoller.setPathContext("pathContext");
		
		BlogCommand command = new BlogCommand();
		Locale loc = Locale.ENGLISH;
		
		contoller.fillCommand(command, loc);
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		
		List<CategoryEntity> categoryList = command.getCategoryList();
		
		Assert.assertEquals(3, categoryList.size());
		
		Assert.assertEquals(Long.valueOf(1), categoryList.get(0).getId());
		Assert.assertEquals("unique_name_1", categoryList.get(0).getUniqueName());
		Assert.assertEquals(2,  categoryList.get(0).getArticleList().size());
		
		Assert.assertEquals(Long.valueOf(2), categoryList.get(1).getId());
		Assert.assertEquals("unique_name_2", categoryList.get(1).getUniqueName());
		Assert.assertEquals(2,  categoryList.get(1).getArticleList().size());
		
		Assert.assertEquals(Long.valueOf(3), categoryList.get(2).getId());
		Assert.assertEquals("unique_name_3", categoryList.get(2).getUniqueName());
		Assert.assertEquals(2,  categoryList.get(2).getArticleList().size());
		
		Assert.assertNull(command.getSelectedCategory());
		
		Assert.assertNull(command.getSelectedArticle());
		
	}
	
	@Test
	public void fillCommand_SelectedCategory() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_CategorySelected());
		contoller.setArticleService(mockArticleService_CategorySelected());
		contoller.setPathHost("pathHost");
		contoller.setPathContext("pathContext");
		
		BlogCommand command = new BlogCommand();
		Locale loc = Locale.ENGLISH;
		
		contoller.fillCommand(command, loc);
		
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		
		List<CategoryEntity> categoryList = command.getCategoryList();
		
		Assert.assertEquals(3, categoryList.size());
		
		Assert.assertEquals(Long.valueOf(1), categoryList.get(0).getId());
		Assert.assertEquals("unique_name_1", categoryList.get(0).getUniqueName());
		Assert.assertEquals(2,  categoryList.get(0).getArticleList().size());
		
		Assert.assertEquals(Long.valueOf(2), categoryList.get(1).getId());
		Assert.assertEquals("unique_name_2", categoryList.get(1).getUniqueName());
		Assert.assertEquals(2,  categoryList.get(1).getArticleList().size());
		
		Assert.assertEquals(Long.valueOf(3), categoryList.get(2).getId());
		Assert.assertEquals("unique_name_3", categoryList.get(2).getUniqueName());
		Assert.assertEquals(2,  categoryList.get(2).getArticleList().size());
		
		Assert.assertNull(command.getSelectedCategoryPagesCount());
		
		Assert.assertNotNull(command.getSelectedCategory());
		
		Assert.assertNotNull(command.getSelectedArticle());
		
	}
	
	@Test
	public void initSelected() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_NoCategorySelected());
		
		BlogCommand command = new BlogCommand(); 
		Locale loc = Locale.ENGLISH;
		
		contoller.initSelected(command, loc);
		
		Assert.assertEquals(Long.valueOf(1), command.getSelectedCategoryId());
		Assert.assertEquals(Integer.valueOf(1), command.getSelectedCategoryPageCurrent());
		Assert.assertEquals("unique_name_1", command.getSelectedCategoryUniqueName());
		Assert.assertNotNull(command.getSelectedCategory());
		
	}
	
	@Test(expected = CategoryException.class)
	public void initSelected_CategoryListNull() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_AllCategoriesListNull());
		
		BlogCommand command = new BlogCommand(); 
		Locale loc = Locale.ENGLISH;
		
		contoller.initSelected(command, loc);
				
	}
	
	@Test
	public void initSelected_CategoryListEmpty() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_AllCategoriesListEmpty());
		
		BlogCommand command = new BlogCommand(); 
		Locale loc = Locale.ENGLISH;
		
		contoller.initSelected(command, loc);
		
		Assert.assertNull(command.getSelectedCategoryId());
		Assert.assertNull(command.getSelectedCategoryPageCurrent());
		Assert.assertNull(command.getSelectedCategoryUniqueName());
		Assert.assertNull(command.getSelectedCategory());
				
	}
	
	@Test
	public void handleException(){
		
		Exception e = new Exception();
		contoller.setPathHost("pathHost");
		contoller.setPathContext("pathContext");
		
		ModelAndView modelAndView = contoller.handleException(e);
		
		BlogCommand command = (BlogCommand)modelAndView.getModel().get("command");
		
		Assert.assertFalse(command.isDisplaySelectedCategory());
		Assert.assertFalse(command.isDisplaySelectedArticle());
		Assert.assertFalse(command.isDisplaySelectedExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplayException());
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	public void handleError(){
		
		contoller.setPathHost("pathHost");
		contoller.setPathContext("pathContext");
		
		Exception e = new Exception();
		String errorCode = "errorCode";
		
		ModelAndView modelAndView = contoller.handleError(e, errorCode);
		
		BlogCommand command = (BlogCommand)modelAndView.getModel().get("command");
		
		Assert.assertFalse(command.isDisplaySelectedCategory());
		Assert.assertFalse(command.isDisplaySelectedArticle());
		Assert.assertFalse(command.isDisplaySelectedExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertTrue(command.isDisplayException());
		Assert.assertEquals("pathHost", command.getPathHost());
		Assert.assertEquals("pathContext", command.getPathContext());
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	public void handleAboutMe() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_NoCategorySelected());
		contoller.setLocaleResolver(mockLocaleResolver());
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		
		command.setDisplaySelectedCategory(true);
		command.setDisplaySelectedArticle(true);
		command.setDisplaySelectedExplanation(true);
		command.setDisplayAboutMe(true);
		command.setDisplayException(true);		
		
		ModelAndView modelAndView = contoller.handleAboutMe(command, request, response);
		
		Assert.assertFalse(command.isDisplaySelectedCategory());
		Assert.assertFalse(command.isDisplaySelectedArticle());
		Assert.assertFalse(command.isDisplaySelectedExplanation());
		Assert.assertTrue(command.isDisplayAboutMe());
		Assert.assertFalse(command.isDisplayException());
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
	}
	
	@Test
	public void handleExplanationSelection() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_NoCategorySelected());
		contoller.setLocaleResolver(mockLocaleResolver());
		contoller.setExplanationService(mockExplanationService());
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		String selectedExplanationUniqueName = "unique_name";
		
		command.setDisplaySelectedCategory(true);
		command.setDisplaySelectedArticle(true);
		command.setDisplaySelectedExplanation(true);
		command.setDisplayAboutMe(true);
		command.setDisplayException(true);
		
		ModelAndView modelAndView = contoller.handleExplanationSelection(command, request, response, selectedExplanationUniqueName);
		
		Assert.assertFalse(command.isDisplaySelectedCategory());
		Assert.assertFalse(command.isDisplaySelectedArticle());
		Assert.assertTrue(command.isDisplaySelectedExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertFalse(command.isDisplayException());
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
		Assert.assertEquals(Long.valueOf(1), command.getSelectedExplanationId());
		Assert.assertEquals("unique_name_1", command.getSelectedExplanationUniqueName());
		Assert.assertNotNull(command.getSelectedExplanation());
		
	}
	
	@Test
	public void handleArticleSelection() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_CategorySelected());
		contoller.setLocaleResolver(mockLocaleResolver());
		contoller.setArticleService(mockArticleService_CategorySelected());
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		String selectedCategoryPageCurrent = "1";
		String selectedCategoryUniqueName = "category_unique_name";
		String selectedArticlePageCurrent = "2";
		String selectedArticleUniqueName = "article_unique_name";
		
		command.setDisplaySelectedCategory(true);
		command.setDisplaySelectedArticle(true);
		command.setDisplaySelectedExplanation(true);
		command.setDisplayAboutMe(true);
		command.setDisplayException(true);
		
		ModelAndView modelAndView = contoller.handleArticleSelection(command, request, response, selectedCategoryPageCurrent, selectedCategoryUniqueName, selectedArticlePageCurrent, selectedArticleUniqueName);
		
		Assert.assertFalse(command.isDisplaySelectedCategory());
		Assert.assertTrue(command.isDisplaySelectedArticle());
		Assert.assertFalse(command.isDisplaySelectedExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertFalse(command.isDisplayException());
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
		Assert.assertEquals(Integer.valueOf(1), command.getSelectedCategoryPageCurrent());
		Assert.assertEquals("category_unique_name", command.getSelectedCategoryUniqueName());
		Assert.assertEquals(Integer.valueOf(2), command.getSelectedArticlePageCurrent());
		Assert.assertEquals("article_unique_name", command.getSelectedArticleUniqueName());
		Assert.assertEquals(Integer.valueOf(5), command.getSelectedArticlePagesCount());
		
	}
	
	@Test
	public void handleCategorySelection() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_CategorySelected());
		contoller.setLocaleResolver(mockLocaleResolver());
		contoller.setArticleService(mockArticleService_CategorySelected());
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		String selectedCategoryPageCurrent = "1";
		String selectedCategoryUniqueName = "category_unique_name";
		
		command.setDisplaySelectedCategory(true);
		command.setDisplaySelectedArticle(true);
		command.setDisplaySelectedExplanation(true);
		command.setDisplayAboutMe(true);
		command.setDisplayException(true);
		
		ModelAndView modelAndView = contoller.handleCategorySelection(command, request, response, selectedCategoryPageCurrent, selectedCategoryUniqueName);
		
		Assert.assertTrue(command.isDisplaySelectedCategory());
		Assert.assertFalse(command.isDisplaySelectedArticle());
		Assert.assertFalse(command.isDisplaySelectedExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertFalse(command.isDisplayException());
		Assert.assertEquals("blogJsp", modelAndView.getViewName());
		
		Assert.assertEquals(Integer.valueOf(1), command.getSelectedCategoryPageCurrent());
		Assert.assertEquals("category_unique_name", command.getSelectedCategoryUniqueName());
		
	}
	
	@Test
	public void init() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_CategorySelected());
		contoller.setLocaleResolver(mockLocaleResolver());
		contoller.setArticleService(mockArticleService_CategorySelected());
		
		BlogCommand command = new BlogCommand();
		HttpServletRequest request = mockHttpServletRequest();
		HttpServletResponse response = mockHttpServletResponse();
		
		command.setDisplaySelectedCategory(true);
		command.setDisplaySelectedArticle(true);
		command.setDisplaySelectedExplanation(true);
		command.setDisplayAboutMe(true);
		command.setDisplayException(true);
		
		ModelAndView modelAndView = contoller.init(command, request, response);
		
		Assert.assertTrue(command.isDisplaySelectedCategory());
		Assert.assertFalse(command.isDisplaySelectedArticle());
		Assert.assertFalse(command.isDisplaySelectedExplanation());
		Assert.assertFalse(command.isDisplayAboutMe());
		Assert.assertFalse(command.isDisplayException());
		Assert.assertEquals("/categories/1/unique_name_1", ((RedirectView)modelAndView.getView()).getUrl());
		
		Assert.assertEquals(Long.valueOf(1), command.getSelectedCategoryId());
		Assert.assertEquals(Integer.valueOf(1), command.getSelectedCategoryPageCurrent());
		Assert.assertEquals("unique_name_1", command.getSelectedCategoryUniqueName());
		Assert.assertNotNull(command.getSelectedCategory());
		
	}
	
	@Test
	public void handleCategoryPagenation() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_HandleCategoryPagenation());
		contoller.setArticleService(mockArticleService_CategorySelected());
		
		BlogCommand command = new BlogCommand();
		command.setSelectedCategoryPageCurrent(1);
		command.setSelectedCategory(new CategoryEntity());
		
		contoller.handleCategoryPagenation(command);
		
		Assert.assertEquals(Integer.valueOf(3), command.getSelectedCategoryPagesCount());
		
	}
	
	@Test(expected = CategoryException.class)
	public void handleCategoryPagenation_exception() throws Exception{
		
		contoller.setCategoryService(mockCategoryService_HandleCategoryPagenation());
		contoller.setArticleService(mockArticleService_CategorySelected());
		
		BlogCommand command = new BlogCommand();
		command.setSelectedCategoryPageCurrent(5);
		command.setSelectedCategory(new CategoryEntity());
		
		contoller.handleCategoryPagenation(command);
		
	}
	
	@Test
	public void handleArticlePagenation() throws Exception{
		
		ArticleEntity selectedArticle = new ArticleEntity();
		selectedArticle.setPagesCount(3);
		
		BlogCommand command = new BlogCommand();
		command.setSelectedArticle(selectedArticle);
		command.setSelectedArticlePageCurrent(1);
		
		contoller.handleArticlePagenation(command);
		
		Assert.assertEquals(Integer.valueOf(3), command.getSelectedArticlePagesCount());
		
	}
	
	@Test(expected = ArticleException.class)
	public void handleArticlePagenation_exception() throws Exception{
		
		ArticleEntity selectedArticle = new ArticleEntity();
		selectedArticle.setPagesCount(3);
		
		BlogCommand command = new BlogCommand();
		command.setSelectedArticle(selectedArticle);
		command.setSelectedArticlePageCurrent(5);
		
		contoller.handleArticlePagenation(command);
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private List<CategoryEntity> mockCompleteCategoryList() throws Exception{
		
		List<CategoryEntity> categoryList = new ArrayList<CategoryEntity>();		
		CategoryEntity category;
		
		category = new CategoryEntity();		
		category.setId(1L);
		category.setUniqueName("unique_name_1");
		category.setTitle("Title 1");
		category.setCountArticlesOnPage(5);
		category.setArticleList(mockCompleteArticleList1());
		categoryList.add(category);
		
		category = new CategoryEntity();		
		category.setId(2L);
		category.setUniqueName("unique_name_2");
		category.setTitle("Title 2");
		category.setCountArticlesOnPage(5);
		category.setArticleList(mockCompleteArticleList2());
		categoryList.add(category);
		
		category = new CategoryEntity();		
		category.setId(3L);
		category.setUniqueName("unique_name_3");
		category.setTitle("Title 3");
		category.setCountArticlesOnPage(5);
		category.setArticleList(mockCompleteArticleList3());
		categoryList.add(category);
				
		return categoryList;
		
	}
	
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
	
	private CategoryService mockCategoryService_NoCategorySelected() throws Exception{
		
		CategoryService mock = Mockito.mock(CategoryService.class);
		
		Mockito.when(mock.getAllCategoriesList(Mockito.any(Locale.class))).thenReturn(mockCompleteCategoryList());
		Mockito.when(mock.getCategoryFromListByUniqueName(Mockito.anyList(), Mockito.anyString())).thenReturn(null);
		
		return mock;
		
	}
	
	private CategoryService mockCategoryService_CategorySelected() throws Exception{
		
		CategoryService mock = Mockito.mock(CategoryService.class);
		
		CategoryEntity selectedCategory = new CategoryEntity();
		
		Mockito.when(mock.getAllCategoriesList(Mockito.any(Locale.class))).thenReturn(mockCompleteCategoryList());
		Mockito.when(mock.getCategoryFromListByUniqueName(Mockito.anyList(), Mockito.anyString())).thenReturn(selectedCategory);
		Mockito.when(mock.getCategoryPagesCount(Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(5);
		
		return mock;
		
	}
	
	private ArticleService mockArticleService_CategorySelected() throws Exception{
		
		ArticleService mock = Mockito.mock(ArticleService.class);
		
		ArticleEntity selectedArticle = new ArticleEntity();
		selectedArticle.setPagesCount(5);
		
		Mockito.when(mock.getArticleFromListByUniqueName(Mockito.anyList(), Mockito.anyString())).thenReturn(selectedArticle);
		
		return mock;
		
	}
	
	private CategoryService mockCategoryService_AllCategoriesListNull() throws Exception{
		
		CategoryService mock = Mockito.mock(CategoryService.class);
		
		Mockito.when(mock.getAllCategoriesList(Mockito.any(Locale.class))).thenReturn(null);
		
		return mock;
		
	}
	
	private CategoryService mockCategoryService_AllCategoriesListEmpty() throws Exception{
		
		CategoryService mock = Mockito.mock(CategoryService.class);
		
		Mockito.when(mock.getAllCategoriesList(Mockito.any(Locale.class))).thenReturn(new ArrayList<CategoryEntity>());
		
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
	
	private LocaleResolver mockLocaleResolver() {

		LocaleResolver mock = Mockito.mock(LocaleResolver.class);
		Mockito.when(mock.resolveLocale(Mockito.any(HttpServletRequest.class)))
				.thenReturn(Locale.ENGLISH);
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
	
	private CategoryService mockCategoryService_HandleCategoryPagenation() throws Exception{
		
		CategoryService mock = Mockito.mock(CategoryService.class);
		
		Mockito.when(mock.getCategoryPagesCount(Mockito.anyInt(), Mockito.anyInt())).thenReturn(3);
		
		return mock;
		
	}
	
	
}

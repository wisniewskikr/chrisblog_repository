package pl.kwi.chrisblog.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.exceptions.ArticleException;
import pl.kwi.chrisblog.utils.DateUtils;

public class ArticleServiceTest {
	
	private ArticleService service;
	
	@Before
	public void setUp() throws Exception{
		service = new ArticleService();
		service.setFolderExamples("folderExamples");
		service.setFolderSources("folderSources");
	}
	
	@Test
	public void getArticleListByCategoryId() throws Exception{
		
		Long categoryId = 1L;
		Locale loc = Locale.ENGLISH;
		List<ArticleEntity> completeArticleList = mockCompleteArticleList();
		
		List<ArticleEntity> articleList = service.getArticleListByCategoryId(categoryId, loc, completeArticleList);
		
		Assert.assertEquals(1, articleList.size());
		Assert.assertEquals(Long.valueOf(1L), articleList.get(0).getId());
		Assert.assertEquals("Unique name", articleList.get(0).getUniqueName());
		Assert.assertEquals(Long.valueOf(1L), articleList.get(0).getCategoryId());
		Assert.assertEquals("Title", articleList.get(0).getTitle());
		Assert.assertEquals("Description", articleList.get(0).getDescription());
		Assert.assertEquals("Path/path", articleList.get(0).getContentPath());
		Assert.assertEquals(Integer.valueOf(3), articleList.get(0).getPagesCount());
		Assert.assertEquals("Author", articleList.get(0).getAuthor());
		Assert.assertEquals("December 25, 1999", articleList.get(0).getCreationDateAsString());
		Assert.assertEquals("/Demo path", articleList.get(0).getDemoPath());
		Assert.assertEquals("/folderExamples/Example path", articleList.get(0).getExamplePath());
		Assert.assertEquals("/folderSources/Source path", articleList.get(0).getSourcePath());
		
		Assert.assertEquals(Integer.valueOf(1999), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.YEAR)));
		Assert.assertEquals(Integer.valueOf(11), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.MONTH)));
		Assert.assertEquals(Integer.valueOf(25), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.DAY_OF_MONTH)));
		Assert.assertEquals(Integer.valueOf(17), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.HOUR_OF_DAY)));
		Assert.assertEquals(Integer.valueOf(45), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.MINUTE)));
		Assert.assertEquals(Integer.valueOf(53), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.SECOND)));
		
		List<ArticleTagEntity> articleTagList = articleList.get(0).getArticleTagList();
		Assert.assertEquals(Integer.valueOf(3), Integer.valueOf(articleTagList.size()));
		Assert.assertEquals(Long.valueOf(1), articleTagList.get(0).getId());
		Assert.assertEquals("Java", articleTagList.get(0).getName());
		Assert.assertEquals(Long.valueOf(2), articleTagList.get(1).getId());
		Assert.assertEquals("Maven", articleTagList.get(1).getName());
		Assert.assertEquals(Long.valueOf(3), articleTagList.get(2).getId());
		Assert.assertEquals("Servlet", articleTagList.get(2).getName());
		
	}
	
	@Test(expected = ArticleException.class)
	public void getArticleListByCategoryId_CategoryIdNull() throws Exception{
		
		Long categoryId = null;
		Locale loc = Locale.ENGLISH;
		List<ArticleEntity> completeArticleList = mockCompleteArticleList();
		
		service.getArticleListByCategoryId(categoryId, loc, completeArticleList);
		
	}
	
	@Test(expected = ArticleException.class)
	public void getArticleListByCategoryId_LocaleNull() throws Exception{
		
		Long categoryId = 1L;
		Locale loc = null;
		List<ArticleEntity> completeArticleList = mockCompleteArticleList();
		
		service.getArticleListByCategoryId(categoryId, loc, completeArticleList);
		
	}
	
	@Test
	public void getArticleListByCategoryId_NoArticleInCategory() throws Exception{
		
		Long categoryId = 2L;
		Locale loc = Locale.ENGLISH;
		List<ArticleEntity> completeArticleList = mockCompleteArticleList();
		
		List<ArticleEntity> articleList = service.getArticleListByCategoryId(categoryId, loc, completeArticleList);
		
		Assert.assertTrue(articleList.isEmpty());
		
	}
	
	@Test
	public void getArticleFromListById() throws Exception{
		
		List<ArticleEntity> articleList = mockCompleteArticleList();
		Long articleId = 1L;
		
		ArticleEntity article = service.getArticleFromListById(articleList, articleId);
		
		Assert.assertEquals(Long.valueOf(1L), article.getId());
		Assert.assertEquals("Unique name", article.getUniqueName());
		Assert.assertEquals(Long.valueOf(1L), article.getCategoryId());
		Assert.assertEquals("Title", article.getTitle());
		Assert.assertEquals("Description", article.getDescription());
		Assert.assertEquals("Path/path", article.getContentPath());
		Assert.assertEquals(Integer.valueOf(3), article.getPagesCount());
		Assert.assertEquals("Author", article.getAuthor());
		Assert.assertEquals("December 25, 1999", article.getCreationDateAsString());
		Assert.assertEquals("/Demo path", article.getDemoPath());
		Assert.assertEquals("/Example path", article.getExamplePath());
		Assert.assertEquals("/Source path", article.getSourcePath());
		
		Assert.assertEquals(Integer.valueOf(1999), Integer.valueOf(article.getCreationDate().get(Calendar.YEAR)));
		Assert.assertEquals(Integer.valueOf(11), Integer.valueOf(article.getCreationDate().get(Calendar.MONTH)));
		Assert.assertEquals(Integer.valueOf(25), Integer.valueOf(article.getCreationDate().get(Calendar.DAY_OF_MONTH)));
		Assert.assertEquals(Integer.valueOf(17), Integer.valueOf(article.getCreationDate().get(Calendar.HOUR_OF_DAY)));
		Assert.assertEquals(Integer.valueOf(45), Integer.valueOf(article.getCreationDate().get(Calendar.MINUTE)));
		Assert.assertEquals(Integer.valueOf(53), Integer.valueOf(article.getCreationDate().get(Calendar.SECOND)));
		
		List<ArticleTagEntity> articleTagList = article.getArticleTagList();
		Assert.assertEquals(Integer.valueOf(3), Integer.valueOf(articleTagList.size()));
		Assert.assertEquals(Long.valueOf(1), articleTagList.get(0).getId());
		Assert.assertEquals("Java", articleTagList.get(0).getName());
		Assert.assertEquals(Long.valueOf(2), articleTagList.get(1).getId());
		Assert.assertEquals("Maven", articleTagList.get(1).getName());
		Assert.assertEquals(Long.valueOf(3), articleTagList.get(2).getId());
		Assert.assertEquals("Servlet", articleTagList.get(2).getName());
		
	}
	
	@Test(expected = ArticleException.class)
	public void getArticleFromListById_ArticleListNull() throws Exception{
		
		List<ArticleEntity> articleList = null;
		Long articleId = 1L;
		
		service.getArticleFromListById(articleList, articleId);
		
	}
	
	@Test(expected = ArticleException.class)
	public void getArticleFromListById_ArticleIdNull() throws Exception{
		
		List<ArticleEntity> articleList = mockCompleteArticleList();
		Long articleId = null;
		
		service.getArticleFromListById(articleList, articleId);
		
	}
	
	@Test
	public void getArticleFromListById_NoArticleInList() throws Exception{
		
		List<ArticleEntity> articleList = mockCompleteArticleList();
		Long articleId = 2L;
		
		ArticleEntity article = service.getArticleFromListById(articleList, articleId);
		
		Assert.assertNull(article);
		
	}
	
	@Test
	public void getArticleFromListByUniqueName() throws Exception{
		
		List<ArticleEntity> articleList = mockCompleteArticleList();
		String articleUniqueName = "Unique name";
		
		ArticleEntity article = service.getArticleFromListByUniqueName(articleList, articleUniqueName);
		
		Assert.assertEquals(Long.valueOf(1L), article.getId());
		Assert.assertEquals("Unique name", article.getUniqueName());
		Assert.assertEquals(Long.valueOf(1L), article.getCategoryId());
		Assert.assertEquals("Title", article.getTitle());
		Assert.assertEquals("Description", article.getDescription());
		Assert.assertEquals("Path/path", article.getContentPath());
		Assert.assertEquals(Integer.valueOf(3), article.getPagesCount());
		Assert.assertEquals("Author", article.getAuthor());
		Assert.assertEquals("December 25, 1999", article.getCreationDateAsString());
		Assert.assertEquals("/Demo path", article.getDemoPath());
		Assert.assertEquals("/Example path", article.getExamplePath());
		Assert.assertEquals("/Source path", article.getSourcePath());
		
		Assert.assertEquals(Integer.valueOf(1999), Integer.valueOf(article.getCreationDate().get(Calendar.YEAR)));
		Assert.assertEquals(Integer.valueOf(11), Integer.valueOf(article.getCreationDate().get(Calendar.MONTH)));
		Assert.assertEquals(Integer.valueOf(25), Integer.valueOf(article.getCreationDate().get(Calendar.DAY_OF_MONTH)));
		Assert.assertEquals(Integer.valueOf(17), Integer.valueOf(article.getCreationDate().get(Calendar.HOUR_OF_DAY)));
		Assert.assertEquals(Integer.valueOf(45), Integer.valueOf(article.getCreationDate().get(Calendar.MINUTE)));
		Assert.assertEquals(Integer.valueOf(53), Integer.valueOf(article.getCreationDate().get(Calendar.SECOND)));
		
		List<ArticleTagEntity> articleTagList = article.getArticleTagList();
		Assert.assertEquals(Integer.valueOf(3), Integer.valueOf(articleTagList.size()));
		Assert.assertEquals(Long.valueOf(1), articleTagList.get(0).getId());
		Assert.assertEquals("Java", articleTagList.get(0).getName());
		Assert.assertEquals(Long.valueOf(2), articleTagList.get(1).getId());
		Assert.assertEquals("Maven", articleTagList.get(1).getName());
		Assert.assertEquals(Long.valueOf(3), articleTagList.get(2).getId());
		Assert.assertEquals("Servlet", articleTagList.get(2).getName());
		
	}
	
	@Test(expected = ArticleException.class)
	public void getArticleFromListByUniqueName_ArticleListNull() throws Exception{
		
		List<ArticleEntity> articleList = null;
		String articleUniqueName = "Unique name";
		
		service.getArticleFromListByUniqueName(articleList, articleUniqueName);
		
	}
	
	@Test
	public void getArticleFromListByUniqueName_UniqueNameNull() throws Exception{
		
		List<ArticleEntity> articleList = mockCompleteArticleList();
		String articleUniqueName = null;
		
		ArticleEntity article = service.getArticleFromListByUniqueName(articleList, articleUniqueName);
		
		Assert.assertNull(article);
		
	}
	
	@Test
	public void getArticleFromListByUniqueName_NoArticleInList() throws Exception{
		
		List<ArticleEntity> articleList = mockCompleteArticleList();
		String articleUniqueName = "Unique name another";
		
		ArticleEntity article = service.getArticleFromListByUniqueName(articleList, articleUniqueName);
		
		Assert.assertNull(article);
		
	}
	
	@Test(expected = ArticleException.class)
	public void countPageNumberForEveryArticleFromList_ArticlesOnPageNull() throws Exception{
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		ArticleEntity article;
		
		article = new ArticleEntity();
		articleList.add(article);
				
		Integer articlesOnPage = null;
		
		service.countPageNumberForEveryArticleFromList(articleList, articlesOnPage);
		
	}
	
	@Test
	public void countPageNumberForEveryArticleFromList_ArticleListNull() throws Exception{
		
		List<ArticleEntity> articleList = null;
		Integer articlesOnPage = 3;
		
		service.countPageNumberForEveryArticleFromList(articleList, articlesOnPage);
		
		Assert.assertNull(articleList);
		
	}
	
	@Test
	public void countPageNumberForEveryArticleFromList_OneArticle() throws Exception{
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		ArticleEntity article;
		
		article = new ArticleEntity();
		articleList.add(article);
				
		Integer articlesOnPage = 3;
		
		service.countPageNumberForEveryArticleFromList(articleList, articlesOnPage);
		
		Assert.assertEquals(Integer.valueOf(1), articleList.get(0).getNumberCategoryPage());
		
	}
	
	@Test
	public void countPageNumberForEveryArticleFromList_ThreeArticles() throws Exception{
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		ArticleEntity article;
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		Integer articlesOnPage = 3;
		
		service.countPageNumberForEveryArticleFromList(articleList, articlesOnPage);
		
		Assert.assertEquals(Integer.valueOf(1), articleList.get(0).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(1), articleList.get(1).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(1), articleList.get(2).getNumberCategoryPage());
		
	}
	
	@Test
	public void countPageNumberForEveryArticleFromList_SixArticles() throws Exception{
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		ArticleEntity article;
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		Integer articlesOnPage = 3;
		
		service.countPageNumberForEveryArticleFromList(articleList, articlesOnPage);
		
		Assert.assertEquals(Integer.valueOf(1), articleList.get(0).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(1), articleList.get(1).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(1), articleList.get(2).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(2), articleList.get(3).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(2), articleList.get(4).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(2), articleList.get(5).getNumberCategoryPage());
		
	}
	
	@Test
	public void countPageNumberForEveryArticleFromList_NineArticles() throws Exception{
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		ArticleEntity article;
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		Integer articlesOnPage = 3;
		
		service.countPageNumberForEveryArticleFromList(articleList, articlesOnPage);
		
		Assert.assertEquals(Integer.valueOf(1), articleList.get(0).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(1), articleList.get(1).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(1), articleList.get(2).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(2), articleList.get(3).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(2), articleList.get(4).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(2), articleList.get(5).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(3), articleList.get(6).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(3), articleList.get(7).getNumberCategoryPage());
		Assert.assertEquals(Integer.valueOf(3), articleList.get(8).getNumberCategoryPage());
		
	}
	
	@Test
	public void convertArticlesToDisplayableForm() throws Exception{
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		ArticleEntity article;
		
		article = new ArticleEntity();
		article.setId(1L);
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("20111226120000"));
		article.setDemoPath("/demoPath");
		article.setExamplePath("/examplePath");
		article.setSourcePath("/sourcePath");
		articleList.add(article);
		
		Locale loc = Locale.ENGLISH;
		service.setFolderExamples("folderExamples");
		service.setFolderSources("folderSources");
		
		articleList = service.convertArticlesToDisplayableForm(articleList, loc);
		
		Assert.assertEquals(1, articleList.size());
		Assert.assertEquals(Long.valueOf(1L), articleList.get(0).getId());
		Assert.assertEquals(Integer.valueOf(2011), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.YEAR)));
		Assert.assertEquals(Integer.valueOf(11), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.MONTH)));
		Assert.assertEquals(Integer.valueOf(26), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.DAY_OF_MONTH)));
		Assert.assertEquals(Integer.valueOf(12), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.HOUR_OF_DAY)));
		Assert.assertEquals(Integer.valueOf(00), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.MINUTE)));
		Assert.assertEquals(Integer.valueOf(00), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.SECOND)));
		Assert.assertEquals("December 26, 2011", articleList.get(0).getCreationDateAsString());
		Assert.assertEquals("/demoPath", articleList.get(0).getDemoPath());
		Assert.assertEquals("/folderExamples/examplePath", articleList.get(0).getExamplePath());
		Assert.assertEquals("/folderSources/sourcePath", articleList.get(0).getSourcePath());
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private List<ArticleEntity> mockCompleteArticleList() throws Exception{
		
		List<ArticleEntity> completeArticleList = new ArrayList<ArticleEntity>();
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1L);
		articleTag.setName("Java");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2L);
		articleTag.setName("Maven");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(3L);
		articleTag.setName("Servlet");
		articleTagList.add(articleTag);
		
		ArticleEntity article;
		
		article = new ArticleEntity();
		article.setId(1L);
		article.setUniqueName("Unique name");
		article.setCategoryId(1L);
		article.setTitle("Title");
		article.setDescription("Description");
		article.setContentPath("Path/path");
		article.setPagesCount(3);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author");
		article.setArticleTagList(articleTagList);
		article.setDemoPath("/Demo path");
		article.setExamplePath("/Example path");
		article.setSourcePath("/Source path");
		completeArticleList.add(article);
		
		return completeArticleList;		
				
	}

	
}

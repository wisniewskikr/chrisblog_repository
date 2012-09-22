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
		service.setCompleteArticleList(mockCompleteArticleList());
	}
	
	@Test
	public void getAllArticleList() throws Exception{
		
		Locale loc = Locale.ENGLISH;
		
		List<ArticleEntity> articleList = service.getAllArticleList(loc);
		
		Assert.assertEquals(Integer.valueOf(1999), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.YEAR)));
		Assert.assertEquals(Integer.valueOf(11), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.MONTH)));
		Assert.assertEquals(Integer.valueOf(25), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.DAY_OF_MONTH)));
		Assert.assertEquals(Integer.valueOf(17), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.HOUR_OF_DAY)));
		Assert.assertEquals(Integer.valueOf(45), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.MINUTE)));
		Assert.assertEquals(Integer.valueOf(53), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.SECOND)));
		Assert.assertEquals(Long.valueOf(1L), articleList.get(0).getId());
		Assert.assertEquals("Unique name", articleList.get(0).getUniqueName());
		Assert.assertEquals(Long.valueOf(1L), articleList.get(0).getCategoryId());
		Assert.assertEquals("Title", articleList.get(0).getTitle());
		Assert.assertEquals("Description", articleList.get(0).getDescription());
		Assert.assertEquals("Path/path", articleList.get(0).getContentPath());
		Assert.assertEquals(Integer.valueOf(4), articleList.get(0).getPagesCount());
		Assert.assertEquals("Author", articleList.get(0).getAuthor());
		Assert.assertEquals("December 25, 1999", articleList.get(0).getCreationDateAsString());
		Assert.assertEquals("/demoPath", articleList.get(0).getDemoPath());
		Assert.assertEquals("/folderExamples/examplePath", articleList.get(0).getExamplePath());
		Assert.assertEquals("/folderSources/sourcePath", articleList.get(0).getSourcePath());
		
	}
	
	@Test(expected = ArticleException.class)
	public void getAllArticleList_articleListNull() throws Exception{
		
		service.setCompleteArticleList(null);
		Locale loc = Locale.ENGLISH;
		
		service.getAllArticleList(loc);
			
	}
	
	@Test(expected = ArticleException.class)
	public void getAllArticleList_localeNull() throws Exception{
		
		Locale loc = null;
		
		service.getAllArticleList(loc);
			
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
		Assert.assertEquals("/demoPath", article.getDemoPath());
		Assert.assertEquals("/examplePath", article.getExamplePath());
		Assert.assertEquals("/sourcePath", article.getSourcePath());
		
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
		article.setDemoPath("/demoPath");
		article.setExamplePath("/examplePath");
		article.setSourcePath("/sourcePath");
		completeArticleList.add(article);
		
		return completeArticleList;		
				
	}

	
}

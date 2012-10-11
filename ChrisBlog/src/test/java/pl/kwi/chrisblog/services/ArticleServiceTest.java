package pl.kwi.chrisblog.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import pl.kwi.chrisblog.daos.ArticleDao;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.exceptions.ArticleException;
import pl.kwi.chrisblog.services.ArticleService;
import pl.kwi.chrisblog.utils.DateUtils;

public class ArticleServiceTest {
	
	private ArticleService service;
	
	@Before
	public void setUp() throws Exception{
		service = new ArticleService();
		service.setFolderExamples("folderExamples");
		service.setFolderSources("folderSources");
		service.setCountArticlesPerPage(10);
		service.setArticleTagService(mockArticleTagService());
		service.setArticleDao(mockArticleDao(mockCompleteArticleList()));
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
		Assert.assertEquals("Title", articleList.get(0).getTitle());
		Assert.assertEquals("Unique name", articleList.get(0).getDescription());
		Assert.assertEquals("Unique name", articleList.get(0).getContent());
		Assert.assertEquals(Integer.valueOf(4), articleList.get(0).getPagesCount());
		Assert.assertEquals("Author", articleList.get(0).getAuthor());
		Assert.assertEquals("December 25, 1999", articleList.get(0).getCreationDateAsString());
		Assert.assertEquals("/demoPath", articleList.get(0).getDemoPath());
		Assert.assertEquals("/folderExamples/exampleFile", articleList.get(0).getExamplePath());
		Assert.assertEquals("/folderSources/sourceFile", articleList.get(0).getSourcePath());
		
	}
	
	@Test(expected = ArticleException.class)
	public void getAllArticleList_articleListNull() throws Exception{
		
		service.setArticleDao(mockArticleDao(null));
		Locale loc = Locale.ENGLISH;
		
		service.getAllArticleList(loc);
			
	}
	
	@Test(expected = ArticleException.class)
	public void getAllArticleList_localeNull() throws Exception{
		
		Locale loc = null;
		
		service.getAllArticleList(loc);
			
	}
	
	@Test
	public void findAllSortedByDateDesc() throws Exception{
		
		int pageNumber = 1;
		Locale loc = Locale.ENGLISH;
		
		List<ArticleEntity> articleList = service.findAllSortedByDateDesc(pageNumber, loc);
		
		Assert.assertEquals(Integer.valueOf(1999), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.YEAR)));
		Assert.assertEquals(Integer.valueOf(11), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.MONTH)));
		Assert.assertEquals(Integer.valueOf(25), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.DAY_OF_MONTH)));
		Assert.assertEquals(Integer.valueOf(17), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.HOUR_OF_DAY)));
		Assert.assertEquals(Integer.valueOf(45), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.MINUTE)));
		Assert.assertEquals(Integer.valueOf(53), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.SECOND)));
		Assert.assertEquals(Long.valueOf(1L), articleList.get(0).getId());
		Assert.assertEquals("Unique name", articleList.get(0).getUniqueName());
		Assert.assertEquals("Title", articleList.get(0).getTitle());
		Assert.assertEquals("Unique name", articleList.get(0).getDescription());
		Assert.assertEquals("Unique name", articleList.get(0).getContent());
		Assert.assertEquals(Integer.valueOf(4), articleList.get(0).getPagesCount());
		Assert.assertEquals("Author", articleList.get(0).getAuthor());
		Assert.assertEquals("December 25, 1999", articleList.get(0).getCreationDateAsString());
		Assert.assertEquals("/demoPath", articleList.get(0).getDemoPath());
		Assert.assertEquals("/folderExamples/exampleFile", articleList.get(0).getExamplePath());
		Assert.assertEquals("/folderSources/sourceFile", articleList.get(0).getSourcePath());
		
	}
	
	@Test(expected = ArticleException.class)
	public void findAllSortedByDateDesc_articleListNull() throws Exception{
		
		service.setArticleDao(mockArticleDao(null));
		int pageNumber = 1;
		Locale loc = Locale.ENGLISH;
		
		service.findAllSortedByDateDesc(pageNumber, loc);
		
	}
	
	@Test(expected = ArticleException.class)
	public void findAllSortedByDateDesc_localeNull() throws Exception{
		
		int pageNumber = 1;
		Locale loc = null;
		
		service.findAllSortedByDateDesc(pageNumber, loc);
		
	}
	
	@Test
	public void findAllWithTagsSortedByDateDesc() throws Exception{
		
		int pageNumber = 1;		
		Locale loc = Locale.ENGLISH;
		
		ArticleTagEntity articleTag = new ArticleTagEntity();
		articleTag.setId(5L);
		articleTag.setName("EJB3");
		articleTag.setUniqueName("ejb3");
		
		List<ArticleEntity> articleList = service.findAllWithTagsSortedByDateDesc(pageNumber, articleTag, loc);
		
		Assert.assertEquals(Integer.valueOf(1999), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.YEAR)));
		Assert.assertEquals(Integer.valueOf(11), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.MONTH)));
		Assert.assertEquals(Integer.valueOf(25), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.DAY_OF_MONTH)));
		Assert.assertEquals(Integer.valueOf(17), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.HOUR_OF_DAY)));
		Assert.assertEquals(Integer.valueOf(45), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.MINUTE)));
		Assert.assertEquals(Integer.valueOf(53), Integer.valueOf(articleList.get(0).getCreationDate().get(Calendar.SECOND)));
		Assert.assertEquals(Long.valueOf(1L), articleList.get(0).getId());
		Assert.assertEquals("Unique name", articleList.get(0).getUniqueName());
		Assert.assertEquals("Title", articleList.get(0).getTitle());
		Assert.assertEquals("Unique name", articleList.get(0).getDescription());
		Assert.assertEquals("Unique name", articleList.get(0).getContent());
		Assert.assertEquals(Integer.valueOf(4), articleList.get(0).getPagesCount());
		Assert.assertEquals("Author", articleList.get(0).getAuthor());
		Assert.assertEquals("December 25, 1999", articleList.get(0).getCreationDateAsString());
		Assert.assertEquals("/demoPath", articleList.get(0).getDemoPath());
		Assert.assertEquals("/folderExamples/exampleFile", articleList.get(0).getExamplePath());
		Assert.assertEquals("/folderSources/sourceFile", articleList.get(0).getSourcePath());
		
	}
	
	@Test(expected = ArticleException.class)
	public void findAllWithTagsSortedByDateDesc_articleTagNull() throws Exception{
		
		int pageNumber = 1;		
		Locale loc = Locale.ENGLISH;
		
		ArticleTagEntity articleTag = null;
		
		service.findAllWithTagsSortedByDateDesc(pageNumber, articleTag, loc);
				
	}
	
	@Test
	public void getArticleByUniqueName() throws Exception{
		
		String articleUniqueName = "Unique name";
		Locale loc = Locale.ENGLISH;
		
		ArticleEntity article = service.getArticleByUniqueName(articleUniqueName, loc);
		
		Assert.assertEquals(Long.valueOf(1L), article.getId());
		Assert.assertEquals("Unique name", article.getUniqueName());
		Assert.assertEquals("Title", article.getTitle());
		Assert.assertEquals("Unique name", article.getDescription());
		Assert.assertEquals("Unique name", article.getContent());
		Assert.assertEquals(Integer.valueOf(4), article.getPagesCount());
		Assert.assertEquals("Author", article.getAuthor());
		Assert.assertEquals("December 25, 1999", article.getCreationDateAsString());
		Assert.assertEquals("/demoPath", article.getDemoPath());
		Assert.assertEquals("/folderExamples/exampleFile", article.getExamplePath());
		Assert.assertEquals("/folderSources/sourceFile", article.getSourcePath());
		
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
	public void getArticleByUniqueName_articleListNull() throws Exception{
		
		service.setArticleDao(mockArticleDao(null));
		String articleUniqueName = "Unique name";
		Locale loc = Locale.ENGLISH;
		
		service.getArticleByUniqueName(articleUniqueName, loc);
		
	}
	
	@Test(expected = ArticleException.class)
	public void getArticleByUniqueName_localeNull() throws Exception{
		
		String articleUniqueName = "Unique name";
		Locale loc = null;
		
		service.getArticleByUniqueName(articleUniqueName, loc);
		
	}
	
	@Test(expected = ArticleException.class)
	public void getArticleByUniqueName_uniqueNameNull() throws Exception{
		
		String articleUniqueName = null;
		Locale loc = Locale.ENGLISH;
		
		service.getArticleByUniqueName(articleUniqueName, loc);
		
	}
	
	@Test
	public void getPagesCountOfAllArticles_articlesPerPage_10() throws Exception{
		
		int result = service.getPagesCountOfAllArticles();
		
		Assert.assertEquals(1, result);
		
	}
	
	@Test
	public void getPagesCountOfAllArticles_articlesPerPage_3() throws Exception{
		
		service.setCountArticlesPerPage(3);
		
		int result = service.getPagesCountOfAllArticles();
		
		Assert.assertEquals(1, result);
		
	}
	
	@Test
	public void getPagesCountOfAllArticles_articlesPerPage_2() throws Exception{
		
		service.setCountArticlesPerPage(2);
		
		int result = service.getPagesCountOfAllArticles();
		
		Assert.assertEquals(2, result);
		
	}
	
	@Test
	public void getPagesCountArticlesWithTag_articlesPerPage_10() throws Exception{
		
		ArticleTagEntity articleTag = new ArticleTagEntity();
		
		int result = service.getPagesCountArticlesWithTag(articleTag);
		
		Assert.assertEquals(1, result);
		
	}
	
	@Test
	public void getPagesCountArticlesWithTag_articlesPerPage_3() throws Exception{
		
		service.setCountArticlesPerPage(3);
		ArticleTagEntity articleTag = new ArticleTagEntity();
		
		int result = service.getPagesCountArticlesWithTag(articleTag);
		
		Assert.assertEquals(1, result);
		
	}
	
	@Test
	public void getPagesCountArticlesWithTag_articlesPerPage_2() throws Exception{
		
		service.setCountArticlesPerPage(2);
		ArticleTagEntity articleTag = new ArticleTagEntity();
		
		int result = service.getPagesCountArticlesWithTag(articleTag);
		
		Assert.assertEquals(1, result);
		
	}
	
	@Test
	public void getPagesCountArticlesWithTag_articlesPerPage_1() throws Exception{
		
		service.setCountArticlesPerPage(1);
		ArticleTagEntity articleTag = new ArticleTagEntity();
		
		int result = service.getPagesCountArticlesWithTag(articleTag);
		
		Assert.assertEquals(2, result);
		
	}
	
	@Test(expected = ArticleException.class)
	public void getPagesCountArticlesWithTag_articleTagNull() throws Exception{
		
		ArticleTagEntity articleTag = null;
		
		service.getPagesCountArticlesWithTag(articleTag);
		
	}
	
	@Test
	public void convertArticlesToDisplayableForm() throws Exception{
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		ArticleEntity article;
		
		article = new ArticleEntity();
		article.setId(1L);
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("20111226120000"));
		article.setDemoName("demoPath");
		article.setExamplePath("/examplePath");
		article.setExampleFileName("exampleFile");
		article.setSourcePath("/sourcePath");
		article.setSourceFileName("sourceFile");
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
		Assert.assertEquals("/folderExamples/exampleFile", articleList.get(0).getExamplePath());
		Assert.assertEquals("/folderSources/sourceFile", articleList.get(0).getSourcePath());
		Assert.assertNotNull(articleList.get(0).getDiffToCurrentDateAsString());
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private ArticleDao mockArticleDao(List<ArticleEntity> articleList) throws Exception{
				
		ArticleDao mock = Mockito.mock(ArticleDao.class);
		Mockito.when(mock.findAllActive()).thenReturn(articleList);
		Mockito.when(mock.findAllSortedByDateDesc(Mockito.anyInt(), Mockito.anyInt())).thenReturn(articleList);
		Mockito.when(mock.findAllWithTagsSortedByDateDesc(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyList())).thenReturn(articleList);
		if(articleList != null){
			Mockito.when(mock.getArticleByUniqueName(Mockito.anyString())).thenReturn(articleList.get(0));
		}else {
			Mockito.when(mock.getArticleByUniqueName(Mockito.anyString())).thenReturn(null);
		}
		Mockito.when(mock.getCountOfAllArticles()).thenReturn(3);
		Mockito.when(mock.getCountArticlesWithTags(Mockito.anyList())).thenReturn(2);
		return mock;
		
	}
	
	private List<ArticleEntity> mockCompleteArticleList() throws Exception{
		
		List<ArticleEntity> completeArticleList = new ArrayList<ArticleEntity>();
		List<ArticleTagEntity> articleTagList = null;
		ArticleTagEntity articleTag;
		ArticleEntity article;

		// ----- First article ----- //
		
		articleTagList = new ArrayList<ArticleTagEntity>();
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1L);
		articleTag.setName("Java");
		articleTag.setUniqueName("java");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2L);
		articleTag.setName("Maven");
		articleTag.setUniqueName("maven");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(3L);
		articleTag.setName("Servlet");
		articleTag.setUniqueName("servlet");
		articleTagList.add(articleTag);
		
		
		article = new ArticleEntity();
		article.setId(1L);
		article.setUniqueName("Unique name");
		article.setTitle("Title");
		article.setDescription("Description");
		article.setContent("Path/path");
		article.setPagesCount(4);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author");
		article.setArticleTagList(articleTagList);
		article.setDemoName("demoPath");
		article.setExampleFileName("exampleFile");
		article.setSourceFileName("sourceFile");
		completeArticleList.add(article);
		
		// ----- Second article ----- //
		
		articleTagList = new ArrayList<ArticleTagEntity>();
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(4L);
		articleTag.setName("Swing");
		articleTag.setUniqueName("swing");
		articleTagList.add(articleTag);
		
		
		article = new ArticleEntity();
		article.setId(2L);
		article.setUniqueName("Unique name 2");
		article.setTitle("Title 2");
		article.setDescription("Description");
		article.setContent("Path/path");
		article.setPagesCount(4);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author");
		article.setArticleTagList(articleTagList);
		article.setDemoName("demoPath");
		article.setExampleFileName("exampleFile");
		article.setSourceFileName("sourceFile");
		completeArticleList.add(article);
		
		// ----- Third article ----- //
		
		articleTagList = new ArrayList<ArticleTagEntity>();
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(5L);
		articleTag.setName("EJB3");
		articleTag.setUniqueName("ejb3");
		articleTagList.add(articleTag);
		
		
		article = new ArticleEntity();
		article.setId(3L);
		article.setUniqueName("Unique name 3");
		article.setTitle("Title 3");
		article.setDescription("Description");
		article.setContent("Path/path");
		article.setPagesCount(4);		
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author");
		article.setArticleTagList(articleTagList);
		article.setDemoName("demoPath");
		article.setExampleFileName("exampleFile");
		article.setSourceFileName("sourceFile");
		completeArticleList.add(article);
		
		
		return completeArticleList;		
				
	}
	
	private ArticleTagService mockArticleTagService(){
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(5L);
		articleTag.setName("EJB3");
		articleTag.setUniqueName("ejb3");
		articleTagList.add(articleTag);
		
		ArticleTagService articleTagService = Mockito.mock(ArticleTagService.class);
		
		Mockito.when(articleTagService.getArticleTagListByUniqueNameList(Mockito.anyList())).thenReturn(articleTagList);
		
		return articleTagService;
		
	}

	
}

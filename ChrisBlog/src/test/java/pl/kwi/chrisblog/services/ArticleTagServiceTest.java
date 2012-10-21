package pl.kwi.chrisblog.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mcavallo.opencloud.Cloud;
import org.mockito.Mockito;

import pl.kwi.chrisblog.daos.ArticleTagDao;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.exceptions.ArticleTagException;
import pl.kwi.chrisblog.services.ArticleTagService;
import pl.kwi.chrisblog.utils.DateUtils;

/**
 * Class with test for class ArticleTagService.
 * 
 * @author Krzysztof Wisniewski
 */
public class ArticleTagServiceTest {
	
	
	private ArticleTagService service;
	
	
	@Before
	public void setUp(){
		service = new ArticleTagService();
		service.setDao(mockArticleTagDao());
	}	
	
	@Test
	public void getArticleTagByUniqueName(){
		
		String uniqueName = "java";
		
		ArticleTagEntity result = service.getArticleTagByUniqueName(uniqueName);
		
		Assert.assertEquals(Long.valueOf(1), result.getId());
		Assert.assertEquals("Java", result.getName());
		
	}
	
	@Test
	public void getArticleTagByUniqueName_UniqueNameNull(){
		
		String uniqueName = null;
		
		ArticleTagEntity result = service.getArticleTagByUniqueName(uniqueName);
		
		Assert.assertNull(result);
	
	}
	
	@Test
	public void getArticleTagListByUniqueNameList(){
		
		List<String> uniqueNameList = new ArrayList<String>();
		uniqueNameList.add("java");
		uniqueNameList.add("servlet");
		uniqueNameList.add("html");
		
		List<ArticleTagEntity> resultList = service.getArticleTagListByUniqueNameList(uniqueNameList);
		
		Assert.assertEquals(Integer.valueOf(8), Integer.valueOf(resultList.size()));
		Assert.assertEquals(Long.valueOf(1), resultList.get(0).getId());
		Assert.assertEquals("Java", resultList.get(0).getName());
		Assert.assertEquals(Long.valueOf(2), resultList.get(1).getId());
		Assert.assertEquals("Servlet", resultList.get(1).getName());
		Assert.assertEquals(Long.valueOf(3), resultList.get(2).getId());
		Assert.assertEquals("Html", resultList.get(2).getName());
		
	}
	
	@Test
	public void getArticleTagListByUniqueNameList_UniqueNameListEmpty(){
		
		List<String> uniqueNameList = new ArrayList<String>();
		
		List<ArticleTagEntity> resultList = service.getArticleTagListByUniqueNameList(uniqueNameList);
		
		Assert.assertTrue(resultList.isEmpty());
		
	}
	
	@Test
	public void getArticleTagListByUniqueNameList_UniqueNameListNull(){
		
		List<String> uniqueNameList = null;
		
		List<ArticleTagEntity> resultList = service.getArticleTagListByUniqueNameList(uniqueNameList);
		
		Assert.assertTrue(resultList.isEmpty());
		
	}
	
	@Test
	public void getTagsCloudFooter() throws Exception{
		
		List<ArticleEntity> articleList = mockArticleList();
		
		Cloud tagsCloud = service.getTagsCloudFooter(articleList);
		
		Assert.assertEquals(3, tagsCloud.size());
		Assert.assertEquals("java", tagsCloud.tags().get(0).getName());
		Assert.assertEquals("maven", tagsCloud.tags().get(1).getName());
		Assert.assertEquals("servlet", tagsCloud.tags().get(2).getName());
		
	}
	
	@Test(expected = ArticleTagException.class)
	public void getTagsCloudFooter_CategoryListNull() throws Exception{
		
		List<ArticleEntity> articleList = null;
		
		service.getTagsCloudFooter(articleList);
		
	}
	
	@Test
	public void getTagsCloudRightSpace() throws Exception{
		
		List<ArticleEntity> articleList = mockArticleList();
		
		Cloud tagsCloud = service.getTagsCloudRightSpace(articleList);
		
		Assert.assertEquals(3, tagsCloud.size());
		Assert.assertEquals("java (1)", tagsCloud.tags().get(0).getName());
		Assert.assertEquals("maven (1)", tagsCloud.tags().get(1).getName());
		Assert.assertEquals("servlet (1)", tagsCloud.tags().get(2).getName());
		
		Assert.assertEquals("tag/java", tagsCloud.tags().get(0).getLink());
		Assert.assertEquals("tag/maven", tagsCloud.tags().get(1).getLink());
		Assert.assertEquals("tag/servlet", tagsCloud.tags().get(2).getLink());
		
	}
	
	@Test(expected = ArticleTagException.class)
	public void getTagsCloudRightSpace_CategoryListNull() throws Exception{
		
		List<ArticleEntity> articleList = null;
		
		service.getTagsCloudRightSpace(articleList);
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	public List<ArticleTagEntity> mockArticleTagList(){
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		articleList.add(new ArticleEntity());
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1L);
		articleTag.setName("Java");
		articleTag.setUniqueName("java");
		articleTag.setArticleList(articleList);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2L);
		articleTag.setName("Servlet");
		articleTag.setUniqueName("servlet");
		articleTag.setArticleList(articleList);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(3L);
		articleTag.setName("Html");
		articleTag.setUniqueName("html");
		articleTag.setArticleList(articleList);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(4L);
		articleTag.setName("Jsp");
		articleTag.setUniqueName("jsp");
		articleTag.setArticleList(articleList);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(5L);
		articleTag.setName("Css");
		articleTag.setUniqueName("css");
		articleTag.setArticleList(articleList);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(6L);
		articleTag.setName("Java Script");
		articleTag.setUniqueName("java_script");
		articleTag.setOccurencesCount(6);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(7L);
		articleTag.setName("Maven");
		articleTag.setUniqueName("maven");
		articleTag.setArticleList(articleList);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(8L);
		articleTag.setName("Tomcat");
		articleTag.setUniqueName("tomcat");
		articleTag.setArticleList(articleList);
		articleTagList.add(articleTag);
		
		return articleTagList;
		
	}
	
	private ArticleTagDao mockArticleTagDao(){
		
		ArticleTagDao mock = Mockito.mock(ArticleTagDao.class);
		
		Mockito.when(mock.findAll()).thenReturn(mockArticleTagList());
		Mockito.when(mock.findByUniqueNameList(Mockito.anyList())).thenReturn(mockArticleTagList());
		
		return mock;
		
	}
	
	private List<ArticleEntity> mockArticleList() throws Exception{
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		articleList.add(new ArticleEntity());
		
		List<ArticleEntity> completeArticleList = new ArrayList<ArticleEntity>();
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1L);
		articleTag.setName("Java");
		articleTag.setUniqueName("java");
		articleTag.setArticleList(articleList);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2L);
		articleTag.setName("Maven");
		articleTag.setUniqueName("maven");
		articleTag.setArticleList(articleList);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(3L);
		articleTag.setName("Servlet");
		articleTag.setUniqueName("servlet");
		articleTag.setArticleList(articleList);
		articleTagList.add(articleTag);
		
		ArticleEntity article;
		
		article = new ArticleEntity();
		article.setId(1L);
		article.setUniqueName("Unique name");
		article.setTitle("Title");
		article.setDescriptionPath("Description");
		article.setContentPath("Path/path");
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("19991225174553"));
		article.setCreationDateAsString("December 25, 1999");
		article.setAuthor("Author");
		article.setArticleTagList(articleTagList);
		article.setDemoPath("/demoPath");
		article.setExamplePath("/examplePath");
		article.setSourcePath("/sourcePath");
		completeArticleList.add(article);
		
		article = new ArticleEntity();
		article.setId(2L);
		article.setUniqueName("Unique name 2");
		article.setTitle("Title 2");
		article.setDescriptionPath("Description 2");
		article.setContentPath("Path/path");
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

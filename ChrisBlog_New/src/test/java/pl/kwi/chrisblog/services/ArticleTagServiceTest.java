package pl.kwi.chrisblog.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mcavallo.opencloud.Cloud;

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
	}
	
	@Test
	public void getArticleTagListByIdList(){
		
		List<Long> idList = new ArrayList<Long>();
		idList.add(1L);
		idList.add(3L);
		idList.add(5L);
		
		service.setCompleteArticleTagList(mockArticleTagList());
		List<ArticleTagEntity> resultList = service.getArticleTagListByIdList(idList);
		
		Assert.assertEquals(Integer.valueOf(3), Integer.valueOf(resultList.size()));
		Assert.assertEquals(Long.valueOf(1), resultList.get(0).getId());
		Assert.assertEquals("Java", resultList.get(0).getName());
		Assert.assertEquals(Long.valueOf(3), resultList.get(1).getId());
		Assert.assertEquals("Html", resultList.get(1).getName());
		Assert.assertEquals(Long.valueOf(5), resultList.get(2).getId());
		Assert.assertEquals("Css", resultList.get(2).getName());
		
	}
	
	@Test
	public void getArticleTagListByIdList_idListNull(){
		
		List<Long> idList = null;
		
		List<ArticleTagEntity> resultList = service.getArticleTagListByIdList(idList);
		
		Assert.assertTrue(resultList.isEmpty());
		
	}
	
	@Test
	public void getArticleTagListByIdList_idListEmpty(){
		
		List<Long> idList = new ArrayList<Long>();
		
		List<ArticleTagEntity> resultList = service.getArticleTagListByIdList(idList);
		
		Assert.assertTrue(resultList.isEmpty());
		
	}
	
	@Test
	public void getTagsCloud() throws Exception{
		
		List<ArticleEntity> articleList = mockArticleList();
		
		Cloud tagsCloud = service.getTagsCloud(articleList);
		
		Assert.assertEquals(3, tagsCloud.size());		
		
	}
	
	@Test(expected = ArticleTagException.class)
	public void getTagsCloud_CategoryListNull() throws Exception{
		
		List<ArticleEntity> articleList = null;
		
		service.getTagsCloud(articleList);
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	public List<ArticleTagEntity> mockArticleTagList(){
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1L);
		articleTag.setName("Java");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2L);
		articleTag.setName("Servlet");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(3L);
		articleTag.setName("Html");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(4L);
		articleTag.setName("Jsp");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(5L);
		articleTag.setName("Css");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(6L);
		articleTag.setName("Java Script");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(7L);
		articleTag.setName("Maven");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(8L);
		articleTag.setName("Tomcat");
		articleTagList.add(articleTag);
		
		return articleTagList;
		
	}
	
	private List<ArticleEntity> mockArticleList() throws Exception{
		
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
		
		article = new ArticleEntity();
		article.setId(2L);
		article.setUniqueName("Unique name 2");
		article.setTitle("Title 2");
		article.setDescription("Description 2");
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

package pl.kwi.chrisblog.services.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import pl.kwi.chrisblog.entities.ArticleTagEntity;

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

}

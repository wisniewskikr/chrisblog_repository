package pl.kwi.chrisblog.daos;

import static junit.framework.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.enums.ArticleStatusEnum;
import pl.kwi.chrisblog.utils.DateUtils;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/conf/spring-dispatcher-conf.xml", 
		"/conf/spring-db-conf-unit-tests.xml"
		})
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    						DbUnitTestExecutionListener.class })
public class ArticleDaoTest {
	
	@Autowired
	private ArticleDao dao;

	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void findAll() {
		
		List<ArticleEntity> list = dao.findAll();
		assertEquals(4, list.size());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void findAllActive() {
		
		List<ArticleEntity> list = dao.findAllActive();
		assertEquals(3, list.size());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void findAllSortedByDateDesc_firstResult_0(){
		
		int firstResult = 0; 
		int maxResults = 2;
		
		List<ArticleEntity> list = dao.findAllSortedByDateDesc(firstResult, maxResults);
		
		assertEquals(2, list.size());
		assertEquals(Long.valueOf(4), list.get(0).getId());
		assertEquals(Long.valueOf(3), list.get(1).getId());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void findAllSortedByDateDesc_firstResult_2(){
		
		int firstResult = 2; 
		int maxResults = 2;
		
		List<ArticleEntity> list = dao.findAllSortedByDateDesc(firstResult, maxResults);
		
		assertEquals(2, list.size());
		assertEquals(Long.valueOf(2), list.get(0).getId());
		assertEquals(Long.valueOf(1), list.get(1).getId());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void findAllActiveSortedByDateDesc_firstResult_0(){
		
		int firstResult = 0; 
		int maxResults = 2;
		
		List<ArticleEntity> list = dao.findAllActiveSortedByDateDesc(firstResult, maxResults);
		
		assertEquals(2, list.size());
		assertEquals(Long.valueOf(3), list.get(0).getId());
		assertEquals(Long.valueOf(2), list.get(1).getId());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void findAllActiveSortedByDateDesc_firstResult_2(){
		
		int firstResult = 2; 
		int maxResults = 2;
		
		List<ArticleEntity> list = dao.findAllActiveSortedByDateDesc(firstResult, maxResults);
		
		assertEquals(1, list.size());
		assertEquals(Long.valueOf(1), list.get(0).getId());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void findAllWithTagsSortedByDateDesc_firstResult_0(){
		
		int firstResult = 0; 
		int maxResults = 2;
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag = null;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1l);
		articleTag.setUniqueName("uniqueName1");
		articleTag.setName("name1");
		articleTagList.add(articleTag);
		
		List<ArticleEntity> list = dao.findAllWithTagsSortedByDateDesc(firstResult, maxResults, articleTagList);
		
		assertEquals(2, list.size());
		assertEquals(Long.valueOf(3), list.get(0).getId());
		assertEquals(Long.valueOf(2), list.get(1).getId());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void findAllWithTagsSortedByDateDesc_firstResult_2(){
		
		int firstResult = 2; 
		int maxResults = 2;
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag = null;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1l);
		articleTag.setUniqueName("uniqueName1");
		articleTag.setName("name1");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2l);
		articleTag.setUniqueName("uniqueName2");
		articleTag.setName("name2");
		articleTagList.add(articleTag);
		
		List<ArticleEntity> list = dao.findAllWithTagsSortedByDateDesc(firstResult, maxResults, articleTagList);
		
		assertEquals(1, list.size());
		assertEquals(Long.valueOf(1), list.get(0).getId());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void getArticleByUniqueName(){
		
		String uniqueName = "uniqueName1";
		
		ArticleEntity result = dao.getArticleByUniqueName(uniqueName);
		assertEquals(Long.valueOf(1), result.getId());
		
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void getArticleByUniqueName_noArticle(){
		
		String uniqueName = "test";
		
		ArticleEntity result = dao.getArticleByUniqueName(uniqueName);
		assertNull(result);
		
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void getCountOfAllArticles(){
		
		int result = dao.getCountOfAllArticles();
		assertEquals(3, result);
		
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void getCountArticlesWithTags_tag_1(){
		
		ArticleTagEntity articleTag = new ArticleTagEntity();
		articleTag.setId(1l);
		articleTag.setUniqueName("uniqueName1");
		articleTag.setName("name1");		
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		articleTagList.add(articleTag);
		
		int result = dao.getCountArticlesWithTags(articleTagList);
		assertEquals(3, result);
				
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void getCountArticlesWithTags_tag_2(){
		
		ArticleTagEntity articleTag = new ArticleTagEntity();
		articleTag.setId(2l);
		articleTag.setUniqueName("uniqueName2");
		articleTag.setName("name2");
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		articleTagList.add(articleTag);
		
		int result = dao.getCountArticlesWithTags(articleTagList);
		assertEquals(1, result);		
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void getCountArticlesWithTags_tag_1_and_2(){
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag = null;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1l);
		articleTag.setUniqueName("uniqueName1");
		articleTag.setName("name1");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2l);
		articleTag.setUniqueName("uniqueName2");
		articleTag.setName("name2");
		articleTagList.add(articleTag);
		
		
		int result = dao.getCountArticlesWithTags(articleTagList);
		assertEquals(3, result);		
		
	}
	
	@Test
	public void create(){
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1L);
		articleTagList.add(articleTag);
		
		ArticleEntity article = new ArticleEntity();
		article.setAuthor("author");
		article.setContent("content");
		article.setCreationDate(Calendar.getInstance());
		article.setDemoName("demo");
		article.setDescription("description");
		article.setExampleFileName("example");
		article.setPagesCount(1);
		article.setSourceFileName("source");
		article.setStatus(ArticleStatusEnum.ACTIVE);
		article.setTitle("title");
		article.setUniqueName("uniqueName");
		article.setCreationDateAsString("creationDateAsString");
		article.setArticleTagList(articleTagList);
		
		dao.create(article);
		
		assertNotNull(article.getId());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void create_withTags(){
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1L);
		articleTagList.add(articleTag);
		
		
		ArticleEntity article = new ArticleEntity();
		article.setAuthor("author");
		article.setContent("content");
		article.setCreationDate(Calendar.getInstance());
		article.setDemoName("demo");
		article.setDescription("description");
		article.setExampleFileName("example");
		article.setPagesCount(1);
		article.setSourceFileName("source");
		article.setStatus(ArticleStatusEnum.ACTIVE);
		article.setTitle("title");
		article.setUniqueName("uniqueName");
		article.setArticleTagList(articleTagList);
		article.setCreationDateAsString("creationDateAsString");
		
		dao.create(article);
		
		assertNotNull(article.getId());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	@Ignore
	public void update(){
		
		ArticleEntity article = dao.findOne(1L);
		
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2L);
		articleTagList.add(articleTag);
		
		
		article.setUniqueName("uniqueName-changed");
		article.setTitle("title-changed");
		article.setPagesCount(9);
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("20010101120000"));
		article.setAuthor("author-changed");
		article.setDemoName("demoName-changed");
		article.setExampleFileName("exampleFileName-changed");
		article.setSourceFileName("sourceFileName-changed");
		article.setStatus(ArticleStatusEnum.NOT_ACTIVE);
		article.setArticleTagList(articleTagList);
		article.setCreationDateAsString("creationDateAsString");
		
		dao.update(article);
		
		assertEquals("uniqueName-changed", article.getUniqueName());
		assertEquals("title-changed", article.getTitle());
		assertEquals(Integer.valueOf(9), article.getPagesCount());
		assertEquals(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("20010101120000"), article.getCreationDate());
		assertEquals("author-changed", article.getAuthor());
		assertEquals("demoName-changed", article.getDemoName());
		assertEquals("exampleFileName-changed", article.getExampleFileName());
		assertEquals("sourceFileName-changed", article.getSourceFileName());
		assertEquals(ArticleStatusEnum.NOT_ACTIVE, article.getStatus());
		assertEquals(1, article.getArticleTagList().size());
		assertEquals(Long.valueOf(2), article.getArticleTagList().get(0).getId());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void delete(){
		
		dao.deleteById(1L);
		
		List<ArticleEntity> list = dao.findAll();
		assertEquals(3, list.size());
		
	}

}

package pl.kwi.chrisblog.daos;

import static junit.framework.Assert.assertEquals;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import pl.kwi.chrisblog.entities.ArticleEntity;
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
		assertEquals(3, list.size());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void findAllSortedByDateDesc(){
		
		List<ArticleEntity> list = dao.findAllSortedByDateDesc();
		assertEquals(3, list.size());
		assertEquals(Long.valueOf(3), list.get(0).getId());
		assertEquals(Long.valueOf(2), list.get(1).getId());
		assertEquals(Long.valueOf(1), list.get(2).getId());
		
		assertEquals(2, list.get(0).getArticleTagList().size());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleDaoTest.xml")
	public void getCountOfAllArticles(){
		
		int result = dao.getCountOfAllArticles();
		assertEquals(3, result);
		
		
	}

}

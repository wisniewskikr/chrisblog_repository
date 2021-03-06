package pl.kwi.chrisblog.daos;

import static junit.framework.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import pl.kwi.chrisblog.entities.ArticleTagEntity;

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
public class ArticleTagDaoTest {
	
	@Autowired
	private ArticleTagDao dao;

	@Test
	@DatabaseSetup("/dbunit/ArticleTagDaoTest.xml")
	public void findAll() {
		
		List<ArticleTagEntity> list = dao.findAll();
		assertEquals(2, list.size());
		assertEquals(Integer.valueOf(2), list.get(0).getOccurencesCount());
		assertEquals(Integer.valueOf(0), list.get(1).getOccurencesCount());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleTagDaoTest.xml")
	public void findByUniqueNameList(){
		
		List<String> uniqueNameList = new ArrayList<String>();
		uniqueNameList.add("uniqueName1");
		uniqueNameList.add("uniqueName2");
		
		List<ArticleTagEntity> resultList = dao.findByUniqueNameList(uniqueNameList);
		
		assertEquals(2, resultList.size());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/ArticleTagDaoTest.xml")
	public void findByUniqueNameList_noResults(){
		
		List<String> uniqueNameList = new ArrayList<String>();
		uniqueNameList.add("test");
		
		List<ArticleTagEntity> resultList = dao.findByUniqueNameList(uniqueNameList);
		
		assertTrue(resultList.isEmpty());
		
	}


}

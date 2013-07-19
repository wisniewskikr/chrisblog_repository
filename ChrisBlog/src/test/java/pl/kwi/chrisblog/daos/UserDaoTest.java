package pl.kwi.chrisblog.daos;

import static junit.framework.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import pl.kwi.chrisblog.entities.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/conf/spring-dispatcher-conf.xml", 
		"/conf/spring-db-conf-unit-tests.xml"
		})
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    						DbUnitTestExecutionListener.class })
public class UserDaoTest{
	
	@Autowired
	private UserDao dao;

	@Test
	@DatabaseSetup("/dbunit/UserDaoTest.xml")
	public void findAll() {
		
		List<UserEntity> userList = dao.findAll();
		assertEquals(3, userList.size());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/UserDaoTest.xml")
	public void findOne_enabledTrue(){
		
		UserEntity user = dao.findOne(1L);
		
		assertEquals("admin1", user.getName());
		assertEquals("password1", user.getPassword());
		assertTrue(user.getEnabled());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/UserDaoTest.xml")
	public void findOne_enabledFalse(){
		
		UserEntity user = dao.findOne(2L);
		
		assertEquals("admin2", user.getName());
		assertEquals("password2", user.getPassword());
		assertFalse(user.getEnabled());
		
	}
	
	@Test
	@DatabaseSetup("/dbunit/UserDaoTest.xml")
	public void create() {
		
		UserEntity user = new UserEntity();
		user.setName("admin4");
		user.setPassword("password4");
		user.setEnabled(true);
		dao.create(user);
		
		assertEquals(4, dao.findAll().size());
		
	}

}

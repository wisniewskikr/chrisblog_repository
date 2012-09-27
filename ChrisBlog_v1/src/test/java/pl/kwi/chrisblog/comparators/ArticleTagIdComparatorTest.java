package pl.kwi.chrisblog.comparators;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.kwi.chrisblog.entities.ArticleTagEntity;

public class ArticleTagIdComparatorTest {
	
	
	private ArticleTagIdComparator comparator;
	
	
	@Before
	public void setUp(){
		comparator = new ArticleTagIdComparator();
	}

	@Test
	public void compare_Equal() {
		
		ArticleTagEntity articleTag1 = new ArticleTagEntity();
		articleTag1.setId(1L);
		ArticleTagEntity articleTag2 = new ArticleTagEntity();
		articleTag2.setId(1L);
		
		int result = comparator.compare(articleTag1, articleTag2);
		
		assertEquals(0, result);
		
	}
	
	@Test
	public void compare_FirstLessThenSecond() {
		
		ArticleTagEntity articleTag1 = new ArticleTagEntity();
		articleTag1.setId(1L);
		ArticleTagEntity articleTag2 = new ArticleTagEntity();
		articleTag2.setId(2L);
		
		int result = comparator.compare(articleTag1, articleTag2);
		
		assertEquals(-1, result);
		
	}
	
	@Test
	public void compare_FirstMoreThenSecond() {
		
		ArticleTagEntity articleTag1 = new ArticleTagEntity();
		articleTag1.setId(2L);
		ArticleTagEntity articleTag2 = new ArticleTagEntity();
		articleTag2.setId(1L);
		
		int result = comparator.compare(articleTag1, articleTag2);
		
		assertEquals(1, result);
		
	}
	

}

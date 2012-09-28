package pl.kwi.chrisblog.comparators;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.kwi.chrisblog.entities.ArticleTagEntity;

public class ArticleTagIdComparatorTest {
	
	
	private ArticleTagUniqueNameComparator comparator;
	
	
	@Before
	public void setUp(){
		comparator = new ArticleTagUniqueNameComparator();
	}

	@Test
	public void compare_Equal() {
		
		ArticleTagEntity articleTag1 = new ArticleTagEntity();
		articleTag1.setId(1L);
		articleTag1.setUniqueName("uniqueName");
		ArticleTagEntity articleTag2 = new ArticleTagEntity();
		articleTag2.setId(1L);
		articleTag2.setUniqueName("uniqueName");
		
		int result = comparator.compare(articleTag1, articleTag2);
		
		assertEquals(0, result);
		
	}
	
	@Test
	public void compare_FirstLessThenSecond() {
		
		ArticleTagEntity articleTag1 = new ArticleTagEntity();
		articleTag1.setId(1L);
		articleTag1.setUniqueName("uniqueName1");
		ArticleTagEntity articleTag2 = new ArticleTagEntity();
		articleTag2.setId(2L);
		articleTag2.setUniqueName("uniqueName2");
		
		int result = comparator.compare(articleTag1, articleTag2);
		
		assertEquals(-1, result);
		
	}
	
	@Test
	public void compare_FirstMoreThenSecond() {
		
		ArticleTagEntity articleTag1 = new ArticleTagEntity();
		articleTag1.setId(2L);
		articleTag1.setUniqueName("uniqueName2");
		ArticleTagEntity articleTag2 = new ArticleTagEntity();
		articleTag2.setId(1L);
		articleTag2.setUniqueName("uniqueName1");
		
		int result = comparator.compare(articleTag1, articleTag2);
		
		assertEquals(1, result);
		
	}
	
	@Test
	public void compare_NotFits() {
		
		ArticleTagEntity articleTag1 = new ArticleTagEntity();
		articleTag1.setId(2L);
		articleTag1.setUniqueName("aaa");
		ArticleTagEntity articleTag2 = new ArticleTagEntity();
		articleTag2.setId(1L);
		articleTag2.setUniqueName("bbb");
		
		int result = comparator.compare(articleTag1, articleTag2);
		
		assertEquals(-1, result);
		
	}
	

}

package pl.kwi.chrisblog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.CategoryEntity;
import pl.kwi.chrisblog.exceptions.CategoryException;

public class CategoryServiceTest {
	
	private CategoryService service;
	
	@Before
	public void setUp() throws Exception{
		service = new CategoryService();
		service.setCompleteCategoryList(mockCompleteCategoryList());
		service.setArticleService(mockArticleService());
	}
	
	@Test
	public void getAllCategoriesList() throws Exception{
		
		Locale loc = Locale.ENGLISH;
		
		List<CategoryEntity> categoriesList = service.getAllCategoriesList(loc);
		
		Assert.assertEquals(3, categoriesList.size());
		
		Assert.assertEquals(Long.valueOf(1L), categoriesList.get(0).getId());
		Assert.assertEquals("unique_name_1", categoriesList.get(0).getUniqueName());
		Assert.assertEquals("Title 1", categoriesList.get(0).getTitle());
		Assert.assertEquals(Integer.valueOf(5), categoriesList.get(0).getCountArticlesOnPage());		
		Assert.assertEquals(3, categoriesList.get(0).getArticleList().size());
		
		Assert.assertEquals(Long.valueOf(2L), categoriesList.get(1).getId());
		Assert.assertEquals("unique_name_2", categoriesList.get(1).getUniqueName());
		Assert.assertEquals("Title 2", categoriesList.get(1).getTitle());
		Assert.assertEquals(Integer.valueOf(5), categoriesList.get(1).getCountArticlesOnPage());		
		Assert.assertEquals(3, categoriesList.get(1).getArticleList().size());
		
		Assert.assertEquals(Long.valueOf(3L), categoriesList.get(2).getId());
		Assert.assertEquals("unique_name_3", categoriesList.get(2).getUniqueName());
		Assert.assertEquals("Title 3", categoriesList.get(2).getTitle());
		Assert.assertEquals(Integer.valueOf(5), categoriesList.get(2).getCountArticlesOnPage());		
		Assert.assertEquals(3, categoriesList.get(2).getArticleList().size());
		
	}
	
	@Test(expected = CategoryException.class)
	public void getAllCategoriesList_LocaleNull() throws Exception{
		
		Locale loc = null;
		
		service.getAllCategoriesList(loc);
				
	}
	
	@Test
	public void getCategoryFromListById() throws Exception{
		
		List<CategoryEntity> categoryList = mockCompleteCategoryList(); 
		Long categoryId = 1L;
		
		CategoryEntity category = service.getCategoryFromListById(categoryList, categoryId);
		
		Assert.assertEquals(Long.valueOf(1L), category.getId());
		Assert.assertEquals("unique_name_1", category.getUniqueName());
		Assert.assertEquals("Title 1", category.getTitle());
		Assert.assertEquals(Integer.valueOf(5), category.getCountArticlesOnPage());		
		Assert.assertEquals(0, category.getArticleList().size());
		
	}
	
	@Test(expected = CategoryException.class)
	public void getCategoryFromListById_CatetoryListNull() throws Exception{
		
		List<CategoryEntity> categoryList = null; 
		Long categoryId = 1L;
		
		service.getCategoryFromListById(categoryList, categoryId);
		
	}
	
	@Test
	public void getCategoryFromListById_CatetoryListEmpty() throws Exception{
		
		List<CategoryEntity> categoryList = new ArrayList<CategoryEntity>(); 
		Long categoryId = 1L;
		
		CategoryEntity category = service.getCategoryFromListById(categoryList, categoryId);
		
		Assert.assertNull(category);
		
	}
	
	@Test
	public void getCategoryFromListById_CatetoryIdNull() throws Exception{
		
		List<CategoryEntity> categoryList = mockCompleteCategoryList(); 
		Long categoryId = null;
		
		CategoryEntity category = service.getCategoryFromListById(categoryList, categoryId);
		
		Assert.assertNull(category);
		
	}	
	
	@Test
	public void getCategoryFromListByUniqueName() throws Exception{
		
		List<CategoryEntity> categoryList = mockCompleteCategoryList(); 
		String categoryUniqueName = "unique_name_1";
		
		CategoryEntity category = service.getCategoryFromListByUniqueName(categoryList, categoryUniqueName);
		
		Assert.assertEquals(Long.valueOf(1L), category.getId());
		Assert.assertEquals("unique_name_1", category.getUniqueName());
		Assert.assertEquals("Title 1", category.getTitle());
		Assert.assertEquals(Integer.valueOf(5), category.getCountArticlesOnPage());		
		Assert.assertEquals(0, category.getArticleList().size());
		
	}
	
	@Test(expected = CategoryException.class)
	public void getCategoryFromListByUniqueName_CatetoryListNull() throws Exception{
		
		List<CategoryEntity> categoryList = null;
		String categoryUniqueName = "unique_name_1";
		
		service.getCategoryFromListByUniqueName(categoryList, categoryUniqueName);
		
	}
	
	@Test
	public void getCategoryFromListByUniqueName_CatetoryListEmpty() throws Exception{
		
		List<CategoryEntity> categoryList = new ArrayList<CategoryEntity>(); 
		String categoryUniqueName = "unique_name_1";
		
		CategoryEntity category = service.getCategoryFromListByUniqueName(categoryList, categoryUniqueName);
		
		Assert.assertNull(category);
		
	}
	
	@Test
	public void getCategoryFromListByUniqueName_UniqueNameNull() throws Exception{
		
		List<CategoryEntity> categoryList = mockCompleteCategoryList(); 
		String categoryUniqueName = null;
		
		CategoryEntity category = service.getCategoryFromListByUniqueName(categoryList, categoryUniqueName);
		
		Assert.assertNull(category);
		
	}
	
	@Test(expected = CategoryException.class)
	public void getCategoryPagesCount_CountArticlesOnPageNull() throws Exception{
		
		Integer countArticlesOnPage = null; 
		Integer countArticles = 3;
		
		service.getCategoryPagesCount(countArticlesOnPage, countArticles);
		
	}
	
	@Test(expected = CategoryException.class)
	public void getCategoryPagesCount_CountArticlesOnPageZero() throws Exception{
		
		Integer countArticlesOnPage = 0; 
		Integer countArticles = 3;
		
		service.getCategoryPagesCount(countArticlesOnPage, countArticles);
		
	}
	
	@Test
	public void getCategoryPagesCount_CountArticlesNull() throws Exception{
		
		Integer countArticlesOnPage = 3; 
		Integer countArticles = null;
		
		Integer result = service.getCategoryPagesCount(countArticlesOnPage, countArticles);
		
		Assert.assertEquals(Integer.valueOf(1), result);
		
	}
	
	@Test
	public void getCategoryPagesCount_CountArticlesZero() throws Exception{
		
		Integer countArticlesOnPage = 3; 
		Integer countArticles = 0;
		
		Integer result = service.getCategoryPagesCount(countArticlesOnPage, countArticles);
		
		Assert.assertEquals(Integer.valueOf(1), result);
		
	}
	
	@Test
	public void getCategoryPagesCount_ArticlesCountOne() throws Exception{
		
		Integer countArticlesOnPage = 3; 
		Integer countArticles = 1;
		
		Integer result = service.getCategoryPagesCount(countArticlesOnPage, countArticles);
		
		Assert.assertEquals(Integer.valueOf(1), result);
		
	}
	
	@Test
	public void getCategoryPagesCount_ArticlesCountThree() throws Exception{
		
		Integer countArticlesOnPage = 3; 
		Integer countArticles = 3;
		
		Integer result = service.getCategoryPagesCount(countArticlesOnPage, countArticles);
		
		Assert.assertEquals(Integer.valueOf(1), result);
		
	}
	
	@Test
	public void getCategoryPagesCount_ArticlesCountFour() throws Exception{
		
		Integer countArticlesOnPage = 3; 
		Integer countArticles = 4;
		
		Integer result = service.getCategoryPagesCount(countArticlesOnPage, countArticles);
		
		Assert.assertEquals(Integer.valueOf(2), result);
		
	}
	
	@Test
	public void getCategoryPagesCount_ArticlesCountSeven() throws Exception{
		
		Integer countArticlesOnPage = 3; 
		Integer countArticles = 7;
		
		Integer result = service.getCategoryPagesCount(countArticlesOnPage, countArticles);
		
		Assert.assertEquals(Integer.valueOf(3), result);
		
	}
		
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private List<CategoryEntity> mockCompleteCategoryList(){
		
		List<CategoryEntity> categoryList = new ArrayList<CategoryEntity>();		
		CategoryEntity category;
		
		category = new CategoryEntity();		
		category.setId(1L);
		category.setUniqueName("unique_name_1");
		category.setTitle("Title 1");
		category.setCountArticlesOnPage(5);
		categoryList.add(category);
		
		category = new CategoryEntity();		
		category.setId(2L);
		category.setUniqueName("unique_name_2");
		category.setTitle("Title 2");
		category.setCountArticlesOnPage(5);
		categoryList.add(category);
		
		category = new CategoryEntity();		
		category.setId(3L);
		category.setUniqueName("unique_name_3");
		category.setTitle("Title 3");
		category.setCountArticlesOnPage(5);
		categoryList.add(category);
				
		return categoryList;
		
	}
	
	private ArticleService mockArticleService() throws Exception{
		
		ArticleService mock = Mockito.mock(ArticleService.class);
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		ArticleEntity article;
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		article = new ArticleEntity();
		articleList.add(article);
		
		Mockito.when(mock.getArticleListByCategoryId(Mockito.anyLong(), Mockito.any(Locale.class))).thenReturn(articleList);
		
		return mock;
		
	}
	

}

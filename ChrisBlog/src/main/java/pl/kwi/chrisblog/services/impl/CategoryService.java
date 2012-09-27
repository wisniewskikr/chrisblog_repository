package pl.kwi.chrisblog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kwi.chrisblog.entities.CategoryEntity;
import pl.kwi.chrisblog.exceptions.CategoryException;
import pl.kwi.chrisblog.services.intf.IArticleService;
import pl.kwi.chrisblog.services.intf.ICategoryService;

/**
 * Class with implementation of interface ICategoryService.
 * 
 * @author Krzysztof Wisniewski
 */
@Service
public class CategoryService implements ICategoryService {
	
	
	private List<CategoryEntity> completeCategoryList;
	
	@Autowired
	private IArticleService articleService;
	
	
	@PostConstruct
	public void init(){
		completeCategoryList = initCompleteCategoryList();
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblogjava.services.intf.ICategoryService#getAllCategoriesList()
	 */
	public List<CategoryEntity> getAllCategoriesList(Locale loc) throws Exception{
		
		if(loc == null){
			throw new CategoryException("Error category handling. Object with locale is null");
		}
		
		List<CategoryEntity> categoryList = new ArrayList<CategoryEntity>();		
		
		for (CategoryEntity category : completeCategoryList) {			
			category.setArticleList(articleService.getArticleListByCategoryId(category.getId(), loc));
			categoryList.add(category);			
		}
		
		return categoryList;
		
	}

	/* (non-Javadoc)
	 * @see pl.kwi.chrisblogjava.services.intf.ICategoryService#getCategoryFromListById(java.util.List, java.lang.Long)
	 */
	public CategoryEntity getCategoryFromListById(List<CategoryEntity> categoryList, Long categoryId) throws Exception {
		
		if(categoryList == null){
			throw new CategoryException("Error category handling. List of categories is null.");
		}
		
		if(categoryId == null){
			return null;
		}
				
		for (CategoryEntity category : categoryList) {
			
			if(category.getId().equals(categoryId)){
				return category;
			}
			
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblogjava.services.intf.ICategoryService#getCategoryFromListById(java.util.List, java.lang.Long)
	 */
	public CategoryEntity getCategoryFromListByUniqueName(List<CategoryEntity> categoryList, String categoryUniqueName) throws Exception {
		
		if(categoryList == null){
			throw new CategoryException("Error category handling. List of categories is null.");
		}
		
		if(categoryUniqueName == null){
			return null;
		}
		
		for (CategoryEntity category : categoryList) {
			
			if(category.getUniqueName().equals(categoryUniqueName)){
				return category;
			}
			
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.services.intf.ICategoryService#getCategoryPagesCount(int, int)
	 */
	public Integer getCategoryPagesCount(Integer countArticlesOnPage, Integer countArticles) throws Exception{
		
		int result = 1;
		
		if(countArticlesOnPage == null){
			throw new CategoryException("Error category handling. Attribute with count of articles on page is null.");
		}
		
		if(countArticlesOnPage.equals(0)){
			throw new CategoryException("Error category handling. Attribute with count of articles on page is zero.");
		}
		
		if(countArticles == null || countArticles.equals(0)){
			return result;
		}
		
		result = countArticles/countArticlesOnPage;
		int rest = countArticles%countArticlesOnPage;
		if(rest != 0){
			result += 1;
		}
		
		return result;
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private List<CategoryEntity> initCompleteCategoryList(){
		
		List<CategoryEntity> categoryList = new ArrayList<CategoryEntity>();		
		CategoryEntity category;
		
		category = new CategoryEntity();		
		category.setId(1L);
		category.setUniqueName("servlets");
		category.setTitle("Servlets");
		category.setCountArticlesOnPage(5);
		categoryList.add(category);
		
		category = new CategoryEntity();		
		category.setId(2L);
		category.setUniqueName("spring_3");
		category.setTitle("Spring 3");
		category.setCountArticlesOnPage(5);
		categoryList.add(category);
		
		category = new CategoryEntity();		
		category.setId(3L);
		category.setUniqueName("ejb_3");
		category.setTitle("EJB 3");
		category.setCountArticlesOnPage(5);
		categoryList.add(category);
		
		return categoryList;
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //
	
	
	public List<CategoryEntity> getCompleteCategoryList() {
		return completeCategoryList;
	}
	public void setCompleteCategoryList(List<CategoryEntity> completeCategoryList) {
		this.completeCategoryList = completeCategoryList;
	}

	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}
	

}

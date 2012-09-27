package pl.kwi.chrisblog.services.intf;

import java.util.List;
import java.util.Locale;

import pl.kwi.chrisblog.entities.CategoryEntity;

/**
 * Interface with methods for handling object CategoryEntity.
 * 
 * @author Krzysztof Wisniewski
 */
public interface ICategoryService {
	
	/**
	 * Method gets list of all categories.
	 * 
	 * @param loc object Locale with international localization
	 * @return list of all categories
	 * @throws Exception 
	 */
	public List<CategoryEntity> getAllCategoriesList(Locale loc) throws Exception;
	
	/**
	 * Method gets category by specified id.
	 * 
	 * @param categoryList list of categories
	 * @param categoryId object Long with id of looked for category
	 * @return object CategoryEntity with specified id
	 * @throws Exception 
	 */
	public CategoryEntity getCategoryFromListById(List<CategoryEntity> categoryList, Long categoryId) throws Exception;
	
	/**
	 * Method gets category by specified unique name.
	 * 
	 * @param categoryList list of categories
	 * @param categoryUniqueName object String with category unique name
	 * @return object CategoryEntity with specified unique name
	 * @throws Exception 
	 */
	public CategoryEntity getCategoryFromListByUniqueName(List<CategoryEntity> categoryList, String categoryUniqueName) throws Exception;
	
	/**
	 * Method gets count of pages for category. This count depends on count of all
	 * articles and specified count of articles on page. 
	 * 
	 * @param countArticlesOnPage object Integer with count of articles on page
	 * @param countArticles object Integer with count of all articles connected with category
	 * @return object Integer with count of pages of category
	 * @throws Exception 
	 */
	public Integer getCategoryPagesCount(Integer countArticlesOnPage, Integer countArticles) throws Exception;

}

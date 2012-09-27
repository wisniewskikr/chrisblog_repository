package pl.kwi.chrisblog.services.intf;

import java.util.List;
import java.util.Locale;

import pl.kwi.chrisblog.entities.ArticleEntity;

/**
 * Interface with methods handling object ArticleEntity.
 * 
 * @author Krzysztof Wisniewski
 */
public interface IArticleService {
	
	/**
	 * Method gets list of articles connected with specified category.
	 * 
	 * @param categoryId object Long with id of specified category
	 * @param loc object Locale with international localization
	 * @return list of articles connected with specified category
	 * @throws Exception 
	 */
	public List<ArticleEntity> getArticleListByCategoryId(Long categoryId, Locale loc) throws Exception;
	
	/**
	 * Method gets article from list by specified id.
	 * 
	 * @param articleList list of article where specified article is looked for
	 * @param articleId object Long with id of article looked for
	 * @return object ArticleEntity with specified id
	 * @throws Exception 
	 */
	public ArticleEntity getArticleFromListById(List<ArticleEntity> articleList, Long articleId) throws Exception;
	
	/**
	 * Method gets article from list by article unique name.
	 * 
	 * @param articleList list of article where specified article is looked for
	 * @param articleUniqueName object String with unique name of article looked for
	 * @return object ArticleEntity with specified unique name
	 * @throws Exception 
	 */
	public ArticleEntity getArticleFromListByUniqueName(List<ArticleEntity> articleList, String articleUniqueName) throws Exception;
	
	/**
	 * Method counts number of page for every article from list.
	 * 
	 * @param articleList list of articles
	 * @param articlesOnPage object Integer with defined count of articles on one page
	 */
	public void countPageNumberForEveryArticleFromList(List<ArticleEntity> articleList, Integer articlesOnPage) throws Exception;

}

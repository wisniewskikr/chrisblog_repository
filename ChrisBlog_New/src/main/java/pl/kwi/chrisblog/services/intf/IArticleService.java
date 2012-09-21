package pl.kwi.chrisblog.services.intf;

import java.util.List;
import java.util.Locale;

import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;

/**
 * Interface with methods handling object ArticleEntity.
 * 
 * @author Krzysztof Wisniewski
 */
public interface IArticleService {
	
	/**
	 * Method gets list of articles connected with specified page number, tag and locale.
	 * 
	 * @param pageNumber int with number of page
	 * @param tag object ArticleTagEntity with tag of article
	 * @param loc object Locale with international localization
	 * @return list of articles connected with specified page number, tag and locale
	 * @throws Exception
	 */
	public List<ArticleEntity> getArticleListByPageTagAndLocal(int pageNumber, ArticleTagEntity tag, Locale loc) throws Exception;
	
	/**
	 * Method gets article by article unique name.
	 * 
	 * @param articleUniqueName object String with unique name of article looked for
	 * @param loc object Locale with international localization
	 * @return object ArticleEntity with specified unique name
	 * @throws Exception
	 */
	public ArticleEntity getArticleByUniqueName(String articleUniqueName, Locale loc) throws Exception;
	
	/**
	 * Method gets count of pages of all articles.
	 * 
	 * @return int with count of pages of all articles
	 * @throws Exception
	 */
	public int getPagesCountOfAllArticles() throws Exception;
	
	/**
	 * Method gets count of article`s pages.
	 * 
	 * @param article object ArticleEntity for whitch pages are counted
	 * @return int with count of article`s pages
	 * @throws Exception
	 */
	public int getPagesCountOfArticle(ArticleEntity article) throws Exception;
	
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

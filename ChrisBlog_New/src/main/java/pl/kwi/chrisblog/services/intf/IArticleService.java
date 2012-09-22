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
	 * Method gets list of all articles.
	 * 
	 * @param loc object Locale with international localization
	 * @return list of all articles
	 * @throws Exception
	 */
	public List<ArticleEntity> getAllArticleList(Locale loc) throws Exception;
	
	/**
	 * Method gets list of articles connected with specified page number, tag and locale.
	 * These articles are sorted by date descending.
	 * 
	 * @param pageNumber int with number of page
	 * @param loc object Locale with international localization
	 * @return list of articles connected with specified page number, tag and locale
	 * @throws Exception
	 */
	public List<ArticleEntity> getArticleListSortedByDateDesc(int pageNumber, Locale loc) throws Exception;
	
	/**
	 * Method gets list of articles connected with specified page number, tag and locale.
	 * These articles are sorted by date descending.
	 * 
	 * @param pageNumber int with number of page
	 * @param tag object ArticleTagEntity with tag of article
	 * @param loc object Locale with international localization
	 * @return list of articles connected with specified page number, tag and locale
	 * @throws Exception
	 */
	public List<ArticleEntity> getArticleListSortedByDateDesc(int pageNumber, ArticleTagEntity tag, Locale loc) throws Exception;
	
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
		
}

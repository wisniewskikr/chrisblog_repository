package pl.kwi.chrisblog.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.kwi.chrisblog.comparators.ArticleTagUniqueNameComparator;
import pl.kwi.chrisblog.daos.ArticleDao;
import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.exceptions.ArticleException;
import pl.kwi.chrisblog.utils.DateUtils;

/**
 * Class implementing interface IArticleService.
 * 
 * @author Krzysztof Wisniewski
 */
@Service
public class ArticleService {
		
	
	@Value("${folder.examples}")
	private String folderExamples;
	@Value("${folder.sources}")
	private String folderSources;
	@Value("${count.articles.per.page}")
	private int countArticlesPerPage;
	
	@Autowired
	private ArticleTagService articleTagService;
	
	@Autowired
	private ArticleDao articleDao;

	
	/**
	 * Method gets list of all articles.
	 * 
	 * @param loc object Locale with international localization
	 * @return list of all articles
	 * @throws Exception
	 */
	public List<ArticleEntity> getAllArticleList(Locale loc) throws Exception {
		
		List<ArticleEntity> articleList = articleDao.findAll();
		
		return convertArticlesToDisplayableForm(articleList, loc);
		
	}
	
	/**
	 * Method gets list of articles connected with specified page number, tag and locale.
	 * These articles are sorted by date descending.
	 * 
	 * @param pageNumber int with number of page
	 * @param loc object Locale with international localization
	 * @return list of articles connected with specified page number, tag and locale
	 * @throws Exception
	 */
	public List<ArticleEntity> getArticleListSortedByDateDesc(int pageNumber, Locale loc) throws Exception {
		
		return getArticleListWithTagSortedByDateDesc(pageNumber, null, loc);
		
	}
	
	/**
	 * Method gets list of articles connected with specified page number, tag and locale.
	 * These articles are sorted by date descending.
	 * 
	 * @param pageNumber int with number of page
	 * @param tagUniqueName object String with unique name of tag specified for article from list
	 * @param loc object Locale with international localization
	 * @return list of articles connected with specified page number, tag and locale
	 * @throws Exception
	 */
	public List<ArticleEntity> getArticleListWithTagSortedByDateDesc(int pageNumber, ArticleTagEntity articleTag, Locale loc) throws Exception {
		
		int firstResult = (pageNumber - 1) * countArticlesPerPage;
		int maxResults = countArticlesPerPage;
		
		List<ArticleEntity> articleList = articleDao.findAllWithPaginationSortedByDateDesc(firstResult, maxResults);
		articleList = convertArticlesToDisplayableForm(articleList, loc);
				 
		 List<ArticleTagEntity> searchedArticleTagList = new ArrayList<ArticleTagEntity>();
		 searchedArticleTagList.add(articleTag);
		 		 
		 if(articleTag == null){
			 return articleList;
		 } else {
			 return extractArticleListMarkedByTags(articleList, searchedArticleTagList);			 
		 }
		
	}
	
	/**
	 * Method gets list of articles connected with specified page number, tag and locale.
	 * These articles are sorted by date descending. When article tag is null then
	 * exception is thrown.
	 * 
	 * @param pageNumber int with number of page
	 * @param tagUniqueName object String with unique name of tag specified for article from list
	 * @param loc object Locale with international localization
	 * @return list of articles connected with specified page number, tag and locale
	 * @throws Exception
	 */
	public List<ArticleEntity> getArticleListWithTagSortedByDateDescWithExp(int pageNumber, ArticleTagEntity articleTag, Locale loc) throws Exception {
		
		if(articleTag == null){
			throw new ArticleException("Error article handling. Can not get article list for article tag which is null");
		}
		
		return getArticleListWithTagSortedByDateDesc(pageNumber, articleTag, loc);
		
	}
	
	/**
	 * Method gets article by article unique name.
	 * 
	 * @param articleUniqueName object String with unique name of article looked for
	 * @param loc object Locale with international localization
	 * @return object ArticleEntity with specified unique name
	 * @throws Exception
	 */
	public ArticleEntity getArticleByUniqueName(String articleUniqueName, Locale loc) throws Exception {
		
		List<ArticleEntity> articleList = articleDao.findAll();
		articleList = convertArticlesToDisplayableForm(articleList, loc);
		
		ArticleEntity article = getArticleFromListByUniqueName(articleList, articleUniqueName);
		
		if(article == null){
			throw new ArticleException(MessageFormat.format("Can not find article with unique name: {0}", articleUniqueName));
		}
		
		return article;
		
	}
	
	/**
	 * Method gets count of pages of all articles.
	 * 
	 * @return int with count of pages of all articles
	 * @throws Exception
	 */
	public Integer getPagesCountOfAllArticles() throws Exception {
		
		int articlesCount = articleDao.getCountOfAllArticles();
		
		if(articlesCount == 0){
			throw new ArticleException("Count of all articles strored in db is 0.");
		}
		
		int result = articlesCount / countArticlesPerPage;
		int rest = articlesCount % countArticlesPerPage;
		
		if(rest != 0){
			result++;
		}
		
		return result;
		
	}
	
	/**
	 * Method gets count of pages of articles containing specified tag.
	 * 
	 * @param articleTag object ArticleTagEntity with specified tag of articles which are counted
	 * @return int with count of pages of all articles
	 * @throws Exception
	 */
	public Integer getPagesCountArticlesWithTag(ArticleTagEntity articleTag) throws Exception {
		
		if(articleTag == null){
			throw new ArticleException("Can not count pages for article tag which is null.");
		}
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		articleTagList.add(articleTag);
		
		int articlesCount = articleDao.getCountArticlesWithTags(articleTagList);
		
		if(articlesCount == 0){
			throw new ArticleException(MessageFormat.format("Count of articles with tag: {0} stored in db is 0.", articleTag.getName()));
		}
		
		int result = articlesCount / countArticlesPerPage;
		int rest = articlesCount % countArticlesPerPage;
		
		if(rest != 0){
			result++;
		}
		
		return result;
		
	}
		
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	/**
	 * Method gets article from list by article unique name.
	 * 
	 * @param articleList list of article where specified article is looked for
	 * @param articleUniqueName object String with unique name of article looked for
	 * @return object ArticleEntity with specified unique name
	 * @throws Exception 
	 */
	protected ArticleEntity getArticleFromListByUniqueName(List<ArticleEntity> articleList, String articleUniqueName) throws Exception {
		
		if(articleList == null){
			throw new ArticleException("Error article handling. List of articles is null.");
		}
		
		if(articleUniqueName == null){
			return null;
		}
		
		for (ArticleEntity article : articleList) {
			
			if(article.getUniqueName().equals(articleUniqueName)){
				return article;
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * Method converts list of articles to displayable in jsp form - for instance converts data from object
	 * Calendar to specified, localized String.
	 * 
	 * @param articleList list of articles
	 * @param loc object Locale with international localization
	 * 
	 * @return list of articles converted to displayable form
	 * @throws Exception 
	 */
	protected List<ArticleEntity> convertArticlesToDisplayableForm(List<ArticleEntity> articleList, Locale loc) throws Exception{
					
		if(articleList == null){
			throw new ArticleException("Error article handling. List of articles is null or empty.");
		}
		
		if(loc == null){
			throw new ArticleException("Error article handling. Object with locale is null");
		}
		
		for (ArticleEntity article : articleList) {
			
			String creationDateAsString = DateUtils.convertCalendarToStringWithMonthAsText(article.getCreationDate(), loc);
			article.setCreationDateAsString(creationDateAsString);
			article.setDemoPath("/" + article.getDemoName());
			article.setExamplePath("/" + folderExamples + "/" + article.getExampleFileName());
			article.setSourcePath("/" + folderSources + "/" + article.getSourceFileName());
			
		}
		
		return articleList;
		
	}
	
	/**
	 * Method extracts from article list only these articles which are marked by one of the tags from list.
	 * 
	 * @param articleList list of articles
	 * @param tagsList list of tags
	 * @return list of articles marked by one of the tagss
	 */
	protected List<ArticleEntity> extractArticleListMarkedByTags(List<ArticleEntity> articleList, List<ArticleTagEntity> tagsList){
				
		List<ArticleEntity> resultList = new ArrayList<ArticleEntity>();
		 
		for (ArticleEntity article : articleList) {
			List<ArticleTagEntity> articleTagList = article.getArticleTagList();
						
			for (ArticleTagEntity articleTag : tagsList) {
				
				ArticleTagUniqueNameComparator comparator = new ArticleTagUniqueNameComparator();
				
				Collections.sort(articleTagList, comparator);				
				int index = Collections.binarySearch(articleTagList, articleTag, comparator);
				if(index >= 0){
					resultList.add(article);
					break;
				}

			}
		}
		 
		 return resultList;
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //
	

	public void setFolderExamples(String folderExamples) {
		this.folderExamples = folderExamples;
	}

	public void setFolderSources(String folderSources) {
		this.folderSources = folderSources;
	}
		
	public void setCountArticlesPerPage(int countArticlesPerPage) {
		this.countArticlesPerPage = countArticlesPerPage;
	}

	public void setArticleTagService(ArticleTagService articleTagService) {
		this.articleTagService = articleTagService;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	
}

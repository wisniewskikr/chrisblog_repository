package pl.kwi.chrisblog.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
		List<ArticleEntity> articleList = articleDao.findAllActive();
		
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
	public List<ArticleEntity> findAllSortedByDateDesc(int pageNumber, Locale loc) throws Exception {
		
		int firstResult = (pageNumber - 1) * countArticlesPerPage;
		int maxResults = countArticlesPerPage;
		
		List<ArticleEntity> articleList = articleDao.findAllSortedByDateDesc(firstResult, maxResults);
		return convertArticlesToDisplayableForm(articleList, loc);
		
	}
	
	/**
	 * Method gets list of active articles connected with specified page number, tag and locale.
	 * These articles are sorted by date descending.
	 * 
	 * @param pageNumber int with number of page
	 * @param loc object Locale with international localization
	 * @return list of articles connected with specified page number, tag and locale
	 * @throws Exception
	 */
	public List<ArticleEntity> findAllActiveSortedByDateDesc(int pageNumber, Locale loc) throws Exception {
		
		int firstResult = (pageNumber - 1) * countArticlesPerPage;
		int maxResults = countArticlesPerPage;
		
		List<ArticleEntity> articleList = articleDao.findAllActiveSortedByDateDesc(firstResult, maxResults);
		return convertArticlesToDisplayableForm(articleList, loc);
		
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
	public List<ArticleEntity> findAllWithTagsSortedByDateDesc(int pageNumber, ArticleTagEntity articleTag, Locale loc) throws Exception {
		
		if(articleTag == null){
			throw new ArticleException("Error article handling. Can not get article list for article tag which is null");
		}
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		articleTagList.add(articleTag);
		
		int firstResult = (pageNumber - 1) * countArticlesPerPage;
		int maxResults = countArticlesPerPage;
		
		List<ArticleEntity> articleList = articleDao.findAllWithTagsSortedByDateDesc(firstResult, maxResults, articleTagList);
		return convertArticlesToDisplayableForm(articleList, loc);
		
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
		
		if(articleUniqueName == null){
			throw new ArticleException("Can not find article because unique name is null.");
		}
			
		ArticleEntity article = articleDao.getArticleByUniqueName(articleUniqueName);
		
		if(article == null){
			throw new ArticleException(MessageFormat.format("Can not find article with unique name: {0}", articleUniqueName));
		}
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		articleList.add(article);
		articleList = convertArticlesToDisplayableForm(articleList, loc);
				
		return articleList.get(0);
		
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
	
	/**
	 * Method creates object ArticleEntity
	 * 
	 * @param article object ArticleEntity which is created
	 * @return object Long with id created article
	 */
	@Transactional
	public Long create(ArticleEntity article){
						
		articleDao.create(article);
		return article.getId();
		
	}
	
	/**
	 * Method updates object ArticleEntity
	 * 
	 * @param article object ArticleEntity which is updated
	 */
	@Transactional
	public void update(ArticleEntity article){
						
		articleDao.update(article);
		
	}
		
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //

	
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
			article.setContent(article.getUniqueName());
			article.setDescription(article.getUniqueName());
			article.setDiffToCurrentDateAsString(DateUtils.getDifferenceFromCurrentDateAsText(article.getCreationDate(), loc));
			
		}
		
		return articleList;
		
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

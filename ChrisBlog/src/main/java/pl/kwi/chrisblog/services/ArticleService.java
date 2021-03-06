package pl.kwi.chrisblog.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
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
	
	@Autowired
	private ServletContext servletContext;

	
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
				
		int result = articlesCount / countArticlesPerPage;
		int rest = articlesCount % countArticlesPerPage;
		
		if(rest != 0){
			result++;
		}
		
		if(result == 0) {
			result = 1;
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
	
	/**
	 * Method deletes object ArticleEntity
	 * 
	 * @param id object Long with article`s id which is deleted
	 */
	@Transactional
	public void delete(Long id){
		
		articleDao.deleteById(id);
		
	}
	
	/**
	 * Method deletes object ArticleEntity with specified unique name
	 * 
	 * @param uniqueName object String with article`s unique name which is deleted
	 */
	@Transactional
	public void deleteByUniqueName(String uniqueName){
		
		articleDao.deleteByUniqueName(uniqueName);
		
	}
	
	/**
	 * Method creates description file basing on article unique name.
	 * 
	 * @param uniqueName object String with article unique name
	 * @throws Exception
	 */
	public void createDescriptionFile(String uniqueName) throws Exception{
		
		String path = getPathDescription(uniqueName);
		
		String firstLine = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
		
		FileOutputStream fos = new FileOutputStream(path);
		byte[] bytes = firstLine.getBytes();
		fos.write(bytes);
		fos.close();
		
	}
	
	/**
	 * Method creates content file basing on article unique name.
	 * 
	 * @param uniqueName object String with article unique name
	 * @throws Exception
	 */
	public void createContentFile(String uniqueName) throws Exception{
		
		String path = getPathContent(uniqueName);
		
		String firstLine = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
		
		FileOutputStream fos = new FileOutputStream(path);
		byte[] bytes = firstLine.getBytes();
		fos.write(bytes);
		fos.close();
		
	}
	
	/**
	 * Method reads description of article from file specified by article unique name.
	 * 
	 * @param uniqueName object String with article unique name
	 * @return object String with content of article description
	 * @throws Exception
	 */
	public String readDescriptionFile(String uniqueName) throws Exception{
		
		String path = getPathDescription(uniqueName);		
		return readFile(path);
		
	}
	
	/**
	 * Method writes description of article to file specified by article unique name.
	 * 
	 * @param uniqueName object String with article unique name
	 * @param description object String with description
	 * @throws Exception
	 */
	public void writeDescriptionFile(String uniqueName, String description) throws Exception{
		
		String path = getPathDescription(uniqueName);		
		writeFile(path, description);
		
	}
	
	/**
	 * Method reads content of article from file specified by article unique name.
	 * 
	 * @param uniqueName object String with article unique name
	 * @return object String with content of article 
	 * @throws Exception
	 */
	public String readContentFile(String uniqueName) throws Exception{
		
		String path = getPathContent(uniqueName);		
		return readFile(path);
		
	}
	
	/**
	 * Method writes content of article to file specified by article unique name.
	 * 
	 * @param uniqueName object String with article unique name
	 * @param content object String with content
	 * @throws Exception
	 */
	public void writeContentFile(String uniqueName, String content) throws Exception{
		
		String path = getPathContent(uniqueName);		
		writeFile(path, content);
		
	}
	
	/**
	 * Method deletes file with article description
	 * 
	 * @param uniqueName object String with article unique name
	 */
	public void deleteDescriptionFile(String uniqueName){
		
		String path = getPathDescription(uniqueName);
		deleteFile(path);
		
	}
	
	/**
	 * Method deletes file with article content
	 * 
	 * @param uniqueName object String with article unique name
	 */
	public void deleteContentFile(String uniqueName){
		
		String path = getPathContent(uniqueName);
		deleteFile(path);
		
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
			article.setContentPath(article.getUniqueName());
			article.setDescriptionPath(article.getUniqueName());
			article.setDiffToCurrentDateAsString(DateUtils.getDifferenceFromCurrentDateAsText(article.getCreationDate(), loc));
			
			if(StringUtils.isNotBlank(article.getDemoName())){
				article.setDemoPath("/" + article.getDemoName());
			}
			if(StringUtils.isNotBlank(article.getExampleFileName())){
				article.setExamplePath("/" + folderExamples + "/" + article.getExampleFileName());
			}
			if(StringUtils.isNotBlank(article.getSourceFileName())){
				article.setSourcePath("/" + folderSources + "/" + article.getSourceFileName());
			}			
			
		}
		
		return articleList;
		
	}
	
	/**
	 * Method gets path file of article`s description specified by unique name.
	 * 
	 * @param uniqueName object String with article unique name
	 * @return object Stirng with path of description
	 */
	protected String getPathDescription(String uniqueName){
		
		return getPath(uniqueName, "articles_description");
		
	}
	
	/**
	 * Method gets path file of article`s content specified by unique name.
	 * 
	 * @param uniqueName object String with article unique name
	 * @return object Stirng with path of content
	 */
	protected String getPathContent(String uniqueName){
		
		return getPath(uniqueName, "articles_content");
		
	}
	
	/**
	 * Method gets path specified by article unique name and folder name.
	 * 
	 * @param uniqueName object String with article unique name
	 * @param folderName object String with folder name
	 * @return object String with path
	 */
	protected String getPath(String uniqueName, String folderName){
		
		String separator = System.getProperties().getProperty("file.separator");
		String realPath = servletContext.getRealPath("/");
		String filename = uniqueName + ".jsp";
		return realPath + separator + "jsp" + separator + folderName + separator + filename;
		
	}
	
	/**
	 * Method reads file specified by path.
	 * 
	 * @param path object String with file path
	 * @return object String with content of file
	 * @throws Exception
	 */
	protected String readFile(String path) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		
		String sCurrentLine;
 
		BufferedReader br = new BufferedReader(new FileReader(path)); 
		while ((sCurrentLine = br.readLine()) != null) {
			sb.append(sCurrentLine);
			sb.append("\n");
		}
			
		if (br != null){
			br.close();
		}
		
		return sb.toString();
		
	}
	
	/**
	 * Method writes file specified by path.
	 * 
	 * @param path object String with file path
	 * @param text object String with file text
	 * @throws Exception
	 */
	protected void writeFile(String path, String text) throws Exception{
		
		BufferedWriter out = new BufferedWriter(new FileWriter(path));
	    out.write(text);
	    out.close();
		
	}
	
	/**
	 * Method deletes file specified by path.
	 * 
	 * @param path object String with file path
	 */
	protected void deleteFile(String path){
		
		File file = new File(path);
		file.delete();
		
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

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
		
}

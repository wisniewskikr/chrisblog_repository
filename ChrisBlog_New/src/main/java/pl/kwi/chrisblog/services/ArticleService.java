package pl.kwi.chrisblog.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.kwi.chrisblog.comparators.ArticleTagUniqueNameComparator;
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
	
	@Autowired
	private ArticleTagService articleTagService;
	
	private List<ArticleEntity> completeArticleList;
	

	@PostConstruct
	public void init(){
		completeArticleList = initCompleteArticleList();			
	}
	
	/**
	 * Method gets list of all articles.
	 * 
	 * @param loc object Locale with international localization
	 * @return list of all articles
	 * @throws Exception
	 */
	public List<ArticleEntity> getAllArticleList(Locale loc) throws Exception {
		
		return convertArticlesToDisplayableForm(completeArticleList, loc);
		
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
		
		//TODO KWi: handle page number 
		List<ArticleEntity> articleList = convertArticlesToDisplayableForm(completeArticleList, loc);
				 
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
		
		completeArticleList = convertArticlesToDisplayableForm(completeArticleList, loc);
		
		ArticleEntity article = getArticleFromListByUniqueName(completeArticleList, articleUniqueName);
		
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
		
		// TODO KWi: implement real method
		return 1;
		
	}
	
	/**
	 * Method gets count of article`s pages.
	 * 
	 * @param article object ArticleEntity for which pages are counted
	 * @return int with count of article`s pages
	 * @throws Exception
	 */
	public Integer getPagesCountOfArticle(ArticleEntity article) throws Exception {
		
		// TODO KWi: implement real method
		if(article == null){
			throw new ArticleException("Can not count pages for article which is null.");
		}
		return 4;
		
	}
	
	/**
	 * Method gets count of pages of articles containing specified tag.
	 * 
	 * @param articleTag object ArticleTagEntity with specified tag of articles which are counted
	 * @return int with count of pages of all articles
	 * @throws Exception
	 */
	public Integer getPagesCountArticlesWithTag(ArticleTagEntity articleTag) throws Exception {
		
		// TODO KWi: implement real method
		if(articleTag == null){
			throw new ArticleException("Can not count pages for article tag which is null.");
		}
		return 1;
		
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
			article.setExamplePath("/" + folderExamples + article.getExamplePath());
			article.setSourcePath("/" + folderSources + article.getSourcePath());
			article.setPagesCount(getPagesCountOfArticle(article));
			
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
	
	/**
	 * Method inits complete article list.
	 * 
	 * @return list with all articles
	 */
	private List<ArticleEntity> initCompleteArticleList(){
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		
		articleList.add(getArticleHelloWorldServlets());
		
		return articleList;
		
	}
	
	/**
	 * Method gets content of article "Hello World Servlets".
	 * 
	 * @return object ArticleEntity with content of article "Hello World Servlets"
	 */
	private ArticleEntity getArticleHelloWorldServlets(){
		
		ArticleEntity article;
		
		StringBuffer descriptionSb = new StringBuffer();
		descriptionSb.append("In this tutorial you can learn - step by step - how to create a simple ");
		descriptionSb.append("<a href='explanation/web_application' target='_blank' class='dialogLink'>web application</a> ");
		descriptionSb.append("using ");
		descriptionSb.append("<a href='explanation/java_servlets_technology' target='_blank' class='dialogLink'>Java Servlets Technology</a> ");
		descriptionSb.append("and ");
		descriptionSb.append("<a href='explanation/jsp' target='_blank' class='dialogLink'>JSP</a>. ");
		descriptionSb.append("An example application will retrieve user`s name from one ");
		descriptionSb.append("<a href='explanation/web_page' target='_blank' class='dialogLink'>web page</a> ");
		descriptionSb.append("and display on the second page after the sentence: 'Hello World' (view demo). ");
		descriptionSb.append("This used Java Servlet Technology is the part of ");
		descriptionSb.append("<a href='explanation/j2ee' target='_blank' class='dialogLink'>J2EE</a> ");
		descriptionSb.append("and it`s a base for creating web applications in ");
		descriptionSb.append("<a href='explanation/java_language' target='_blank' class='dialogLink'>Java language</a>. ");
					
		article = new ArticleEntity();
		article.setId(1L);
		article.setUniqueName("hello_world_servlets");
		article.setTitle("Hello World Servlets");
		article.setDescription(descriptionSb.toString());
		article.setContentPath("categories/servlets/helloWorldServlets/helloWorldServletsJsp");
		article.setCreationDate(DateUtils.convertStringToCalendarYYYYMMDDHHMMSS("20120104200700"));
		article.setAuthor("Chris");
		article.setDemoPath("/HelloWorldServlets");
		article.setExamplePath("/servlets/HelloWorldServlets.war");
		article.setSourcePath("/servlets/HelloWorldServlets.zip");
		
		
		List<String> articleTagUniqueNameList = getArticleTagUniqueNameListForHelloWorldServlets();
		List<ArticleTagEntity> articleTagList = articleTagService.getArticleTagListByUniqueNameList(articleTagUniqueNameList);
		article.setArticleTagList(articleTagList);
		
		List<String> articleTagFrontEndIdList = getArticleTagFrontEndIdListForHelloWorldServlets();
		List<ArticleTagEntity> articleTagFrontEndList = articleTagService.getArticleTagListByUniqueNameList(articleTagFrontEndIdList);
		article.setArticleTagFrontEndList(articleTagFrontEndList);
		
		List<String> articleTagBackEndIdList = getArticleTagBackEndIdListForHelloWorldServlets();
		List<ArticleTagEntity> articleTagBackEndList = articleTagService.getArticleTagListByUniqueNameList(articleTagBackEndIdList);
		article.setArticleTagBackEndList(articleTagBackEndList);
		
		return article;
		
	}
	
	/**
	 * Method gets list with unique names of tags for article 'Hello World Servlets'.
	 * 
	 * @return list of objects String with unique names of tags for article 'Hello World Servlets'
	 */
	private List<String> getArticleTagUniqueNameListForHelloWorldServlets(){
		
		List<String> articleTagUniqueNamesList = new ArrayList<String>();
		
		articleTagUniqueNamesList.add("java");
		articleTagUniqueNamesList.add("servlets");
		articleTagUniqueNamesList.add("jsp");
		articleTagUniqueNamesList.add("css");
		articleTagUniqueNamesList.add("maven");
		articleTagUniqueNamesList.add("tomcat");
		
		return articleTagUniqueNamesList;
		
	}
	
	/**
	 * Method gets list with unique names of front-end tags for article 'Hello World Servlets'.
	 * 
	 * @return list of objects String with unique names of tags for article 'Hello World Servlets'
	 */
	private List<String> getArticleTagFrontEndIdListForHelloWorldServlets(){
		
		List<String> articleTagUniqueNamesList = new ArrayList<String>();
		
		articleTagUniqueNamesList.add("servlets");
		articleTagUniqueNamesList.add("jsp");
		
		return articleTagUniqueNamesList;
		
	}
	
	/**
	 * Method gets list with unique names of back-end tags for article 'Hello World Servlets'.
	 * 
	 * @return list of objects String with unique names of tags for article 'Hello World Servlets'
	 */
	private List<String> getArticleTagBackEndIdListForHelloWorldServlets(){
		
		List<String> articleTagUniqueNamesList = new ArrayList<String>();
				
		return articleTagUniqueNamesList;
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //
		
	
	public void setArticleTagService(ArticleTagService articleTagService) {
		this.articleTagService = articleTagService;
	}

	public void setFolderExamples(String folderExamples) {
		this.folderExamples = folderExamples;
	}

	public void setFolderSources(String folderSources) {
		this.folderSources = folderSources;
	}

	public List<ArticleEntity> getCompleteArticleList() {
		return completeArticleList;
	}
	public void setCompleteArticleList(List<ArticleEntity> completeArticleList) {
		this.completeArticleList = completeArticleList;
	}	

	
}

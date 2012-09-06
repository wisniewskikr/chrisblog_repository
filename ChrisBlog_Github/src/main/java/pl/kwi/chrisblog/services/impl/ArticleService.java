package pl.kwi.chrisblog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.exceptions.ArticleException;
import pl.kwi.chrisblog.services.intf.IArticleService;
import pl.kwi.chrisblog.utils.DateUtils;

/**
 * Class implementing interface IArticleService.
 * 
 * @author Krzysztof Wisniewski
 */
@Service
public class ArticleService implements IArticleService {
		
	
	@Value("${folder.examples}")
	private String folderExamples;
	@Value("${folder.sources}")
	private String folderSources;
	
	@Autowired
	private ArticleTagService articleTagService;
	

	/* (non-Javadoc)
	 * @see pl.kwi.chrisblogjava.services.intf.ITopicService#getTopicListByCategoryId(java.lang.Long)
	 */
	public List<ArticleEntity> getArticleListByCategoryId(Long categoryId, Locale loc) throws Exception {
		
		List<ArticleEntity> completeArticleList = initCompleteArticleList();
		
		return getArticleListByCategoryId(categoryId, loc, completeArticleList);
		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblogjava.services.intf.ITopicService#getTopicFromListById(java.util.List, java.lang.Long)
	 */
	public ArticleEntity getArticleFromListById(List<ArticleEntity> articleList, Long articleId) throws Exception {
		
		if(articleList == null){
			throw new ArticleException("Error article handling. List of articles is null.");
		}
		
		if(articleId == null){
			throw new ArticleException("Error article handling. Id of article is null.");
		}

		for (ArticleEntity article : articleList) {
			
			if(article.getId().equals(articleId)){
				return article;
			}
			
		}
		
		return null;
		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblogjava.services.intf.ITopicService#getTopicFromListById(java.util.List, java.lang.Long)
	 */
	public ArticleEntity getArticleFromListByUniqueName(List<ArticleEntity> articleList, String articleUniqueName) throws Exception {
		
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
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.services.intf.IArticleService#countPageNumberForEveryArticleFromList(java.util.List, int)
	 */
	public void countPageNumberForEveryArticleFromList(List<ArticleEntity> articleList, Integer articlesOnPage) throws Exception{
		
		if(articlesOnPage == null){
			throw new ArticleException("Error article handling. Atribute with count of articles on one category page is null.");
		}
		
		if(articleList == null){
			return;
		}
		
		int index = 0;
		int pageNumber = 0;
		int rest = 0;
		
		for (int i = 0; i < articleList.size(); i++) {
			
			index = i + 1;
			pageNumber = index/articlesOnPage;
			rest = index%articlesOnPage;
			if(rest != 0){
				pageNumber += 1;
			}
			
			articleList.get(i).setNumberCategoryPage(pageNumber);
			
		}
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	/**
	 * Method gets list of articles connected with specified category.
	 * 
	 * @param categoryId object Long with id of specified category
	 * @param loc object Locale with international localization
	 * @param completeArticleList list of all articles
	 * @return list of articles connected with specified category
	 * @throws Exception
	 */
	protected List<ArticleEntity> getArticleListByCategoryId(Long categoryId, Locale loc, List<ArticleEntity> completeArticleList) throws Exception {
		
		if(categoryId == null){
			throw new ArticleException("Error article handling. Id of category is null");
		}
		
		if(loc == null){
			throw new ArticleException("Error article handling. Object with locale is null");
		}		
		
		List<ArticleEntity> articleList = new ArrayList<ArticleEntity>();
		
		for (ArticleEntity article : completeArticleList) {
			
			if(categoryId.equals(article.getCategoryId())){
				articleList.add(article);
			}
			
		}
		
		articleList = convertArticlesToDisplayableForm(articleList, loc);
		
		return articleList;
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
			
		}
		
		return articleList;
		
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
		descriptionSb.append("<a href='explanations/web_application' class='dialogLink'>web application</a> ");
		descriptionSb.append("using ");
		descriptionSb.append("<a href='explanations/java_servlets_technology' class='dialogLink'>Java Servlets Technology</a> ");
		descriptionSb.append("and ");
		descriptionSb.append("<a href='explanations/jsp' class='dialogLink'>JSP</a>. ");
		descriptionSb.append("An example application will retrieve user`s name from one ");
		descriptionSb.append("<a href='explanations/web_page' class='dialogLink'>web page</a> ");
		descriptionSb.append("and display on the second page after the sentence: 'Hello World' (view demo). ");
		descriptionSb.append("This used Java Servlet Technology is the part of ");
		descriptionSb.append("<a href='explanations/j2ee' class='dialogLink'>J2EE</a> ");
		descriptionSb.append("and it`s a base for creating web applications in ");
		descriptionSb.append("<a href='explanations/java_language' class='dialogLink'>Java language</a>. ");
					
		article = new ArticleEntity();
		article.setId(1L);
		article.setUniqueName("hello_world_servlets");
		article.setCategoryId(1L);
		article.setTitle("Hello World Servlets");
		article.setDescription(descriptionSb.toString());
		article.setContentPath("categories/servlets/helloWorldServlets/helloWorldServletsJsp");
		article.setPagesCount(4);
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

	
}

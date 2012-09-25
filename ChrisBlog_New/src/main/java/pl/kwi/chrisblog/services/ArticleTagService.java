package pl.kwi.chrisblog.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;
import org.springframework.stereotype.Service;

import pl.kwi.chrisblog.entities.ArticleEntity;
import pl.kwi.chrisblog.entities.ArticleTagEntity;
import pl.kwi.chrisblog.exceptions.ArticleTagException;

/**
 * Class implementing interface IArticleTagService.
 * 
 * @author Krzysztof Wisniewski
 */
@Service
public class ArticleTagService {
	
	
	private List<ArticleTagEntity> completeArticleTagList;
	

	@PostConstruct
	public void init(){		
		completeArticleTagList = initCompleteArticleTagList();			
	}	
	
	/**
	 * Method gets article tag with unique name.
	 * 
	 * @param uniqueName object String with unique name of selected article
	 * @return object ArticleTagEntity with article tag with specified unique name
	 */
	public ArticleTagEntity getArticleTagByUniqueName(String uniqueName) {
		
		ArticleTagEntity result = null;
		
		if(uniqueName == null){
			return result;
		}
		
		List<String> uniqueNameList = new ArrayList<String>();
		uniqueNameList.add(uniqueName);		
		
		List<ArticleTagEntity> articleTagList = getArticleTagListByUniqueNameList(uniqueNameList);
		
		if(!articleTagList.isEmpty()){
			result = articleTagList.get(0);
		}
		
		return result;
	}
	
	/**
	 * Method gest list of article tag by list of unique names these articles.
	 * 
	 * @param uniqueNameList list of object String with unique name selected articles
	 * @return list of object ArticleTagEntity with article tags with specified unique names
	 */
	public List<ArticleTagEntity> getArticleTagListByUniqueNameList(List<String> uniqueNameList) {
		
		List<ArticleTagEntity> resultList = new ArrayList<ArticleTagEntity>();
		
		if(uniqueNameList == null || uniqueNameList.isEmpty()){
			return resultList;
		}
		
		for (ArticleTagEntity articleTag : completeArticleTagList) {
			for (String uniqueName : uniqueNameList) {
				if(uniqueName.equals(articleTag.getUniqueName())){
					resultList.add(articleTag);
				}
			}
		}
		
		return resultList;
	}
	
	/**
	 * Method gets cloud of tags. Tags are topics connected
	 * with specified article. For instance article "Hello World Servlet"
	 * can have tags like: Java, Servlet, Jsp etc.
	 * 
	 * @param articleList list of articles with articles tags
	 * @return object Cloud with tags cloud
	 * @throws Exception 
	 */
	public Cloud getTagsCloud(List<ArticleEntity> articleList) throws Exception {
		
		if(articleList == null){
			throw new ArticleTagException("Error article tags handling. List of articles is null");
		}
		
		Cloud cloud = new Cloud();
		cloud.setMinWeight(10.0);
		cloud.setMaxWeight(20.0);
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
				
		for (ArticleEntity article : articleList) {
			articleTagList.addAll(article.getArticleTagList());
		}
		
		for (ArticleTagEntity articleTag : articleTagList) {
			cloud.addTag(articleTag.getName());
		}
		
		return cloud;
		
	}
	
	/**
	 * Method gets cloud of tags for right space. Tags are topics connected
	 * with specified article. For instance article "Hello World Servlet"
	 * can have tags like: Java, Servlet, Jsp etc.
	 * 
	 * @param articleList list of articles with articles tags
	 * @return object Cloud with tags cloud
	 * @throws Exception 
	 */
	public Cloud getTagsCloudRightSpace(List<ArticleEntity> articleList) throws Exception {
		
		if(articleList == null){
			throw new ArticleTagException("Error article tags handling. List of articles is null");
		}
		
		Cloud cloud = new Cloud();
		cloud.setMinWeight(10.0);
		cloud.setMaxWeight(20.0);
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
				
		for (ArticleEntity article : articleList) {
			articleTagList.addAll(article.getArticleTagList());
		}
		
		for (ArticleTagEntity articleTag : articleTagList) {
			String name = articleTag.getName() + " (" + articleTag.getOccurencesCount() + ")";
			String link = "tag/" + articleTag.getUniqueName();
			cloud.addTag(name, link);
		}
			
		return cloud;
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	/**
	 * Method gets all available article tags.
	 * 
	 * @return list of objects ArticleTagEntity with all available article tags
	 */
	protected List<ArticleTagEntity> initCompleteArticleTagList(){
		
		List<ArticleTagEntity> articleTagList = new ArrayList<ArticleTagEntity>();
		ArticleTagEntity articleTag;
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(1L);
		articleTag.setUniqueName("java");
		articleTag.setName("Java");
		articleTag.setOccurencesCount(1);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2L);
		articleTag.setUniqueName("servlets");
		articleTag.setName("Servlets");
		articleTag.setOccurencesCount(1);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(3L);
		articleTag.setUniqueName("html");
		articleTag.setName("Html");
		articleTag.setOccurencesCount(1);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(4L);
		articleTag.setUniqueName("jsp");
		articleTag.setName("Jsp");
		articleTag.setOccurencesCount(1);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(5L);
		articleTag.setUniqueName("css");
		articleTag.setName("Css");
		articleTag.setOccurencesCount(1);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(6L);
		articleTag.setUniqueName("java_script");
		articleTag.setName("Java Script");
		articleTag.setOccurencesCount(1);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(7L);
		articleTag.setUniqueName("maven");
		articleTag.setName("Maven");
		articleTag.setOccurencesCount(1);
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(8L);
		articleTag.setUniqueName("tomcat");
		articleTag.setName("Tomcat");
		articleTag.setOccurencesCount(1);
		articleTagList.add(articleTag);
		
		return articleTagList;
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //


	public List<ArticleTagEntity> getCompleteArticleTagList() {
		return completeArticleTagList;
	}
	public void setCompleteArticleTagList(
			List<ArticleTagEntity> completeArticleTagList) {
		this.completeArticleTagList = completeArticleTagList;
	}	
	
}

package pl.kwi.chrisblog.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.mcavallo.opencloud.Cloud;
import org.springframework.stereotype.Service;

import pl.kwi.chrisblog.comparators.ArticleTagIdComparator;
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
	 * Method gest list of article tag by list of id these articles.
	 * 
	 * @param idList list of object Long with id selected articles
	 * @return list of object ArticleTagEntity with article tags with specified ids
	 */
	public List<ArticleTagEntity> getArticleTagListByIdList(List<Long> idList) {
		
		List<ArticleTagEntity> resultList = new ArrayList<ArticleTagEntity>();
		
		if(idList == null || idList.isEmpty()){
			return resultList;
		}
		
		ArticleTagIdComparator comparator = new ArticleTagIdComparator();
		
		for (Long id : idList) {
			int index = Collections.binarySearch(completeArticleTagList, new ArticleTagEntity(id), comparator);
			if(index != -1){
				resultList.add(completeArticleTagList.get(index));
			}
		}		
		
		return resultList;
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
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(2L);
		articleTag.setUniqueName("servlets");
		articleTag.setName("Servlets");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(3L);
		articleTag.setUniqueName("html");
		articleTag.setName("Html");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(4L);
		articleTag.setUniqueName("jsp");
		articleTag.setName("Jsp");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(5L);
		articleTag.setUniqueName("css");
		articleTag.setName("Css");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(6L);
		articleTag.setUniqueName("java_script");
		articleTag.setName("Java Script");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(7L);
		articleTag.setUniqueName("maven");
		articleTag.setName("Maven");
		articleTagList.add(articleTag);
		
		articleTag = new ArticleTagEntity();
		articleTag.setId(8L);
		articleTag.setUniqueName("tomcat");
		articleTag.setName("Tomcat");
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